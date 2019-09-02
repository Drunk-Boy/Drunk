package com.xu.service.elder;

import com.xu.entities.File;
import com.xu.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    @Autowired
    FileMapper fileMapper;
    //插入
    public void insert(File file){
       fileMapper.insertFile(file);
    }
    //删除
    public void delete(Integer fileId) {
        fileMapper.deleteFile(fileId);
    }
    //查询
    public File selectFile(Integer id){
        return fileMapper.selectFile(id);
    }
}

