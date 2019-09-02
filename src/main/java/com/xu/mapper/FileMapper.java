package com.xu.mapper;

import com.xu.entities.File;

public interface FileMapper {
    //根据id查询
    public File selectFile(Integer id);
    //插入
    public void insertFile(File file);
    //根据id删除
    public void deleteFile(Integer id);
    //更新
    public void updateFile(File file);
}

