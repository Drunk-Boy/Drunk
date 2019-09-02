package com.xu.controller.elder;

import com.alibaba.druid.support.json.JSONUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xu.entities.Elder;
import com.xu.entities.File;
import com.xu.entities.User;
import com.xu.service.elder.ElderService;
import com.xu.service.elder.FileService;
import com.xu.tool.FtpUtil;
import com.xu.tool.fileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
public class ElderController {
    @Autowired
    User user;

    @Autowired
    ElderService elderService;

    @Autowired
    FileService fileService;

    //转List页面
    @GetMapping("/emp/getAll")
    public ModelAndView getAllElder(@ModelAttribute(value = "elder") Elder elder,Model model,@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        //在查询之前调用PageHelper.startPage传入页码和每页显示的记录数
        PageHelper.startPage(pn,5);
        //在startPage后面紧跟的这个查询就为分页查询
//        List<Elder> list = elderService.getAllElder();
        List<Elder> list = elderService.getElderByFile(elder);
        System.out.println("------------------------------");
        System.out.println(user.getUserName());
        System.out.println("------------------------------");
        //查询结果封装入PageInfo中，其中加入传入的页数
        PageInfo page = new PageInfo(list,5);
        model.addAttribute("page",page);
        return new ModelAndView("elder/elderView");
    }
    //BindingResult post请求需要加
    //转增加页面（返回查询结果到页面）
    @PostMapping("/emp/add")
    public ModelAndView goAdd1(@ModelAttribute(value = "elder") Elder elder,Model model,BindingResult bindingResult){
        //判断是否从添加进入还是从编辑进入（编辑id>0，添加id<0）
        if(Integer.valueOf(elder.getId())>0){
            List<Elder> list = elderService.getElderByFile(elder);
            model.addAttribute("elder",list.get(0));
        }
        return new ModelAndView("elder/elderAdd");
    }

    //更新
    @PutMapping("elder/update")
    public ModelAndView elderUpdate(@ModelAttribute(value = "elder") Elder elder,Model model,BindingResult bindingResult){
        elderService.elderUpdate(elder);
        //redirect重定向
        return new ModelAndView("redirect:/emp/getAll");
    }

    //转查看老人信息的页面
    @PostMapping("/elder/details")
        public ModelAndView goDetails(@ModelAttribute(value = "elder") Elder elder,Model model,BindingResult bindingResult){
        if(Integer.valueOf(elder.getId())>0){
            List<Elder> list = elderService.getElderByFile(elder);
            elder.setFileId(list.get(0).getFileId());
            elder.setFile(list.get(0).getFile());
            model.addAttribute("elder",elder);
            model.addAttribute("details","edit");
        }else {
            //根据elder中的fileId从File表中查出file，赋值给elder中的file
            //不知为何在前端value中没有逗号，传到后台却有，所以这里切割
            if(!elder.getFileId().equals(null) && !elder.getFileId().equals("")){
                elder.setFile(fileService.selectFile(Integer.valueOf(elder.getFileId().split(",")[1])));
                elder.setFileId(elder.getFileId().split(",")[1]);
            }
//      将elder传到页面
            model.addAttribute("elder",elder);
        }
        return new ModelAndView("elder/elderDetails");
    }

    @GetMapping("emp/elder/editDetails")
    public ModelAndView editDetails(@RequestParam(name = "id") Integer id,HttpServletRequest request, HttpServletResponse response, ModelMap model){
        Elder melder = new Elder();
        melder.setId(id);
        List<Elder> list = elderService.getElderByFile(melder);
        model.addAttribute("elder",list.get(0));
        model.addAttribute("isDetails","details");
        return new ModelAndView("elder/elderDetails");
    }

    //保存后转List
    @PostMapping("/elder/save")
    public ModelAndView save(@ModelAttribute(value = "elder") Elder elder,BindingResult bindingResult){
        elderService.saveElder(elder);
        //redirect重定向
        return new ModelAndView("redirect:/emp/getAll");
    }
    @PostMapping("/emp/select")
    public ModelAndView selectName(@ModelAttribute(value = "elder") Elder elder,BindingResult bindingResult, Model model,@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        //在查询之前调用PageHelper.startPage传入页码和每页显示的记录数
        PageHelper.startPage(pn,5);
        //在startPage后面紧跟的这个查询就为分页查询
        List<Elder> list = elderService.getElder(elder);
        //查询结果封装入PageInfo中，其中加入传入的页数
        PageInfo page = new PageInfo(list,5);
        model.addAttribute("page",page);
        return new ModelAndView("elder/elderView");
    }

    @DeleteMapping("/elder/delete")
    public List<Elder> elderDelete(HttpServletRequest request,Model model){
        Integer id = Integer.valueOf(request.getParameter("id"));
        elderService.elderDeleteById(Integer.valueOf(id));
        List<Elder> list = elderService.getAllElder();
        return list;
    }

    @PostMapping("/elder/edit")
    public ModelAndView elderEdit(@ModelAttribute(value = "elder") Elder elder,BindingResult bindingResult, Model model){
        elderService.elderUpdate(elder);
        return new ModelAndView("redirect:/emp/getAll");
    }

    /**
     * 文件上传
     * @param request
     * @param response
     * @param model
     * @return
     */
    @PostMapping("/elder/input")
    public Elder fileInput(HttpServletRequest request, HttpServletResponse response, ModelMap model){
//        String reportGroupId = request.getParameter("data");
        String filepath = request.getParameter("filepath");
        if (ServletFileUpload.isMultipartContent(request)) {
            //获取文件
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Locale l = multipartRequest.getLocale();
            List<MultipartFile> fileList = multipartRequest.getFiles("reportFile");
            //获取文件名字，是否上传成功，url，当前上传文件类型
            Map<String,String> map =  fileUpload.maxFileUpload(fileList);
            //PrintWriter形式返回前端的map
            final Map<String, Object> result = new HashMap<>();
            ServletOutputStream out = null;
            //上传成功处理
            if(map.get("is").equals("true")){
                File file = new File();
                file.setFileName(map.get("fileName"));
                file.setFileUrl(map.get("reportAddr"));
                file.setFtpUrl(map.get("reportAddr"));
                file.setFtpName(map.get("saveFileName"));
                fileService.insert(file);
                result.put("fileid",file.getFileId());
                try {
                    out=response.getOutputStream();
                    response.setContentType("application/json");
                    out.write(JSONUtils.toJSONString(result).getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }finally{
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }else {
                model.addAttribute("msg","文件上传失败");
            }

        }
        return new Elder();
    }

    /**
     * 文件删除
     * @param request
     * @param response
     * @param model
     * @return
     */
    @PostMapping("/elder/inputDelete")
    public Elder inputDelete(HttpServletRequest request, HttpServletResponse response, ModelMap model){
        //获取id
        String fileId = request.getParameter("id");
        File file = fileService.selectFile(Integer.valueOf(fileId));
        //ftp上删除文件
        FtpUtil.deleteFile(file.getFtpUrl(),file.getFtpName());
        //file数据库删除
        fileService.delete(Integer.valueOf(fileId));
        return new Elder();
    }
    @PostMapping("/elder/download")
    public String download(HttpServletRequest request, HttpServletResponse response, ModelMap model){
        File file = fileService.selectFile(Integer.valueOf(request.getParameter("id")));
        //访问ftp不加根目录,所以切割
        String ftpUrl = file.getFtpUrl().substring(4);
        String ftpName = file.getFtpName();
        boolean isDownload = fileUpload.download(ftpUrl,ftpName);
        String url = ftpUrl + "/" + ftpName;
        if(isDownload){
            return url;
        }else {
            return "下载失败";
        }
    }
}
