package com.xu.mapper;

import com.xu.entities.Employee;

public interface EmployeeMapper {
    public Employee getEmpById(Integer id);

    public void insertEmp(Employee employee);
}
