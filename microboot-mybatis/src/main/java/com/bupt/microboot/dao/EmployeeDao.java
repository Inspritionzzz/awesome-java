package com.bupt.microboot.dao;

import com.bupt.microboot.model.Employee;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmployeeDao {
    /**
     * 查询所有员工信息
     * @return
     */
    List<Employee> getAllEmployee();

    /**
     * 多对一：查询所有员工包括部门信息
     * @return
     */
    List<Employee> getAllEmployeeAndDepartment();

    /**
     * 多对一：查询所有员工包括部门信息2
     * @return
     */
    List<Employee> getAllEmployeeAndDepartment2();

    /**
     * 多对一：查询所有员工包括部门信息-step1
     * @return
     */
    List<Employee> getAllEmployeeAndDepartmentByStepOne(@Param("eid") Integer eid);

    /**
     * 一对多（分步查询）：获取部门以及部门中多有员工的信息-step2
     */
    List<Employee> getDepartmentAndEmployeeByStepTwo(@Param("did") Integer did);

}
