package com.xu.service.elder;

import com.xu.entities.Elder;
import com.xu.mapper.ElderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElderService {
    @Autowired
    ElderMapper elderMapper;

    //联合文件查询
    public List<Elder> getElderByFile(Elder elder){
        return elderMapper.getElderByFile(elder);
    }

    //查出所有老人
    public List<Elder> getAllElder(){
        return elderMapper.getAllElder();
    }
    //添加老人
    public void saveElder(Elder elder){
        elderMapper.insert(elder);
    }
    //姓名查询
    public List<Elder> getElder(Elder elder){
        return elderMapper.getElder(elder);
    }
    //id查询
    public List<Elder> getElderById(Integer id){
        return elderMapper.getElderById(id);
    }
    //按id删除
    public void elderDeleteById(Integer id){
        elderMapper.elderDeleteById(id);
    }
    //更新
    public void elderUpdate(Elder elder){
        elderMapper.elderUpdate(elder);
    }

//    public PageVo<Elder> list(Elder elder, Integer pageSize, Integer page) {
//        PageVo<Elder> pageVo=new PageVo();
//        pageVo.setPageSize(pageSize);
//        pageVo.setPage(page);
//        PageHelper.startPage(page,pageSize);
//        List<Elder> data = elderMapper.getElder(elder);
//        List<Elder> resultAddCoverPre = new ArrayList<Elder>();
//        for(Elder elder1:data) {
//            elder1.setCover(img_base_url + elder1.getCover());
//            resultAddCoverPre.add(elder1);
//        }
//        pageVo.setData(resultAddCoverPre);
//        Integer count=elderMapper.getCount();
//        pageVo.setCount(count);
//        return pageVo;
//    }
}
