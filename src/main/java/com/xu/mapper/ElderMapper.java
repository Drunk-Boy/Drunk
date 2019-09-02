package com.xu.mapper;

import com.xu.entities.Elder;

import java.util.List;

public interface ElderMapper {
    //根据id查找
    public List<Elder> getElderById(Integer id);

    //查询
    public List<Elder> getElder(Elder elder);

    //全部查询
    public List<Elder> getAllElder();

    //增加
    public void insert(Elder elder);

    //根据id删除
    public void elderDeleteById(Integer id);

    //更新
    public void elderUpdate(Elder elder);

    //联合文件查询
    public List<Elder> getElderByFile(Elder elder);
}
