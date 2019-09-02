package com.xu.tool;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class fileUpload {

    public static Map<String,String> maxFileUpload(List<MultipartFile> fileList){
        Map<String,String> map = new HashMap<>();
        String fileName = "";        //当前上传文件全名称
        String fileType = "";        //当前上传文件类型
        String saveFileName = "";    //保存到服务器目录的文件名称
        String reportAddr = "";      //保存到服务器目录的文件全路径
        boolean is = false;           //是否上传成功
        for (MultipartFile item : fileList) {
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(currentTime);
            fileName =item.getOriginalFilename();
            fileType =item.getContentType();
            saveFileName = dateString+"-"+item.getOriginalFilename();
            reportAddr = "/www/images/yx";
            //将item转换成InputStream
            FileInputStream inputStream = null;
            try {
                inputStream = (FileInputStream)item.getInputStream();
                is =  FtpUtil.uploadFile("94.191.102.105", 21, "ftpuser", "lcc1813547", "/www/images","/yx", saveFileName, inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        map.put("is",String.valueOf(is));
        map.put("saveFileName",saveFileName);
        map.put("fileName",fileName);
        map.put("fileType",fileType);
        map.put("reportAddr",reportAddr);
        return map;
    }

    /**
     *
     * @param remotePath FTP服务器上的相对路径
     * @param fileName 要下载的文件名
     * @return
     */
    public static Boolean download(String remotePath,String fileName){
        //FTP服务器hostname
        String host = "94.191.102.105";
        //FTP服务器端口
        int port = 21;
        //FTP登录账号
        String username = "ftpuser";
        //FTP登录密码
        String password = "lcc1813547";
        //下载后保存到本地的路径
        String localPath = "D:\\download";
        boolean isDownload = FtpUtil.downloadFile(host,port,username,password,remotePath,fileName,localPath);
        return isDownload;
    }
}
