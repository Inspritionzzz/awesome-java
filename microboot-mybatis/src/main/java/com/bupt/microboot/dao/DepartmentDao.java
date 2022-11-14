package com.bupt.microboot.dao;

import com.bupt.microboot.model.Department;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


@Component
public interface DepartmentDao {
    /**
     * 多对一：查询所有员工包括部门信息-step2
     * @return
     */
    Department getAllEmployeeAndDepartmentByStepTwo(@Param("did") Integer did);

    /**
     * 一对多：获取部门以及部门中多有员工的信息
     */
    Department getDepartmentAndEmployee(@Param("did") Integer did);

    /**
     * 一对多（分步查询）：获取部门以及部门中多有员工的信息-step1
     * @param did
     * @return
     */
    Department getDepartmentAndEmployeeByStepOne(@Param("did") Integer did);
}
