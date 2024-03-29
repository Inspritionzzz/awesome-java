package com.bupt.microboot.mapper;

import com.bupt.microboot.entity.DepartmentGenerator;
import com.bupt.microboot.entity.DepartmentGeneratorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DepartmentGeneratorMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_t_sys_department
     *
     * @mbg.generated
     */
    long countByExample(DepartmentGeneratorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_t_sys_department
     *
     * @mbg.generated
     */
    int deleteByExample(DepartmentGeneratorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_t_sys_department
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer did);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_t_sys_department
     *
     * @mbg.generated
     */
    int insert(DepartmentGenerator record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_t_sys_department
     *
     * @mbg.generated
     */
    int insertSelective(DepartmentGenerator record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_t_sys_department
     *
     * @mbg.generated
     */
    List<DepartmentGenerator> selectByExample(DepartmentGeneratorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_t_sys_department
     *
     * @mbg.generated
     */
    DepartmentGenerator selectByPrimaryKey(Integer did);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_t_sys_department
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") DepartmentGenerator record, @Param("example") DepartmentGeneratorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_t_sys_department
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") DepartmentGenerator record, @Param("example") DepartmentGeneratorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_t_sys_department
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DepartmentGenerator record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_t_sys_department
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DepartmentGenerator record);
}