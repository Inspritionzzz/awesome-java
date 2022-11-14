package com.bupt.microboot.service;


import com.bupt.microboot.dao.DepartmentDao;
import com.bupt.microboot.dao.EmployeeDao;
import com.bupt.microboot.dao.UserDao;
import com.bupt.microboot.dto.UserInfoQueryParam;
import com.bupt.microboot.model.Department;
import com.bupt.microboot.model.Employee;
import com.bupt.microboot.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


@SpringBootTest
public class SelectDemoServiceTest {

    @Autowired(required = false)
    UserDao userDao;

    @Autowired(required = false)
    EmployeeDao employeeDao;

    @Autowired(required = false)
    DepartmentDao departmentDao;

    @Resource
    SelectDemoService selectDemoService;

    @Resource
    MybatisTemplateService mybatisTemplateService;

    @Test
    void userInfoQuery() {
        UserInfoQueryParam userInfoQueryParam = new UserInfoQueryParam();
        userInfoQueryParam.setUserId("csc04014");
        userInfoQueryParam.setUserName("jason");
        List<User> userList = userDao.getList(userInfoQueryParam);
        // lambda表达式遍历
//        userList.forEach(item->{
//            System.out.println(item);
//        });

        // lambda表达式遍历
//        userList.forEach(System.out::println);

        // lambda表达式过滤
        userList.stream().filter(s -> s.getUserId().equals("csc04014")).forEach(System.out::println);
    }

    @Test
    void getUserTest() {
        User user = selectDemoService.getUser("csc04014");
        System.out.println(user.toString());
    }

    /**
     * 加载配置文件，创建sqlSession的方式访问数据库
     * @throws IOException
     */
    @Test
    void getUserTest2() throws IOException {
        UserInfoQueryParam userInfoQueryParam = new UserInfoQueryParam();
        userInfoQueryParam.setUserId("csc04014");
        userInfoQueryParam.setUserName("jason");

        InputStream is = Resources.getResourceAsStream("...xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);  // 设置自动提交事务
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        userDao.getList(userInfoQueryParam);
        // sqlSession.commit();
    }

    @Test
    void addUser() {
        User user = new User();
        user.setUserId("csc26378");
        user.setUserName("jason");
        mybatisTemplateService.addUser(user);
    }

    @Test
    void addUsers() {
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        User user2 = new User();
        user1.setUserId("csc26380");
        user1.setUserName("jason3");
        user2.setUserId("csc26381");
        user2.setUserName("jason4");
        userList.add(user2);
        userList.add(user1);
        mybatisTemplateService.addUsers(userList);
    }

    @Test
    void checkLoginTest() {
        User user1 = new User();
        user1.setUserId("csc26380");
        user1.setUserName("jason");
        user1.setPassword("100037");
        System.out.println(userDao.checkLogin(user1.getUserName(), user1.getPassword()));
    }

    @Test
    void checkLoginByMapTest() {
        User user1 = new User();
        user1.setUserId("csc26380");
        user1.setUserName("jason");
        user1.setPassword("100037");
        Map<String, String> map = new HashMap<>();
        // map.put(user1.getUserName(), user1.getPassword());
        map.put("userName", user1.getUserName());  // 手动把键放入map
        map.put("password", user1.getPassword());
        System.out.println(userDao.checkLoginByMap(map));
    }

    @Test
    void checkLoginByParamTest() {
        User user1 = new User();
        user1.setUserId("csc26380");
        user1.setUserName("jason");
        user1.setPassword("100037");
        System.out.println(userDao.checkLoginByParam(user1.getUserName(), user1.getPassword()));
    }

    @Test
    void getAllUsersTest() {
        List<User> userList = userDao.getAllUsers();
        System.out.println(userList);
        // userList.forEach(item ->System.out.println());
    }

    @Test
    void getUserCountTest() {
        int count = userDao.getUserCount();
        System.out.println("用户数量：" + count);
        // userList.forEach(item ->System.out.println());
    }

    @Test
    void getUserByIdToMapTest() {
        Map<String, Object> map =  userDao.getUserByIdToMap("csc04014");
        System.out.println(map);
    }

    @Test
    void getAllUsersToMapTest() {
        List<Map<String, Object>> mapList = userDao.getAllUsersToMap();
        System.out.println(mapList);
    }

    @Test
    void getAllUsersToMap2Test() {
        Map<String, Object> mapList = userDao.getAllUsersToMap2();
        System.out.println(mapList);
    }

    @Test
    void getUserByLikeTest() {
        List<User> userList = userDao.getUserByLike("jason");
        System.out.println(userList);
    }

    @Test
    void deleteUsersTest() {
        int result = userDao.deleteUsers("'jason3','jason4'");
        System.out.println("删除结果：" + result);
    }

    @Test
    void getUserByTableNameTest() {
        List<User> result = userDao.getUserByTableName("mb_t_sys_user");
        System.out.println("查询结果：" + result);
    }

    @Test
    void addUserAutoIncre2Test() {
        User user1 = new User();
        user1.setUserId("csc26380");
        user1.setUserName("jason");
        user1.setPassword("100037");
        userDao.addUserAutoIncre2(user1);
    }

    @Test
    void addUserAutoIncreTest() {
        User user1 = new User();
        user1.setUserId("csc26380");
        user1.setUserName("jason");
        user1.setPassword("100037");
        userDao.addUserAutoIncre(user1);
    }

    @Test
    void getUsersTest() {
        List<User> userList = userDao.getUsers();
        System.out.println(userList);
    }

    @Test
    void getAllEmployeeTest() {
        List<Employee> employees = employeeDao.getAllEmployee();
        System.out.println(employees);
    }

    @Test
    void getAllEmployeeAndDepartmentTest() {
        List<Employee> employees = employeeDao.getAllEmployeeAndDepartment();
        System.out.println(employees);
    }

    @Test
    void getAllEmployeeAndDepartmentTest2() {
        List<Employee> employees = employeeDao.getAllEmployeeAndDepartment2();
        System.out.println(employees);
    }

    @Test
    void getAllEmployeeAndDepartmentTest3() {
        List<Employee> employees = employeeDao.getAllEmployeeAndDepartmentByStepOne(1);
        System.out.println(employees);
    }

    @Test
    void getDepartmentAndEmployeeTest() {
        Department department = departmentDao.getDepartmentAndEmployee(1);
        System.out.println(department);
    }

    @Test
    void getDepartmentAndEmployeeByStepTest() {
        Department department = departmentDao.getDepartmentAndEmployeeByStepOne(1);
        System.out.println(department);
    }

    @Test
    void getUserByConditionTest() {
        User user1 = new User();
        user1.setUserId(null);
        user1.setUserName("jason");
        user1.setPassword("100037");
        List<User> userList = userDao.getUserByCondition(user1);
        System.out.println(userList);
    }

    @Test
    void getUserByCondition2Test() {
        User user1 = new User();
        user1.setUserId(null);
        user1.setUserName("jason");
        user1.setPassword("100037");
        List<User> userList = userDao.getUserByCondition2(user1);
        System.out.println(userList);
    }

    @Test
    void getUserByCondition3Test() {
        User user1 = new User();
        user1.setUserId(null);
        user1.setUserName("jason");
        user1.setPassword("100037");
        List<User> userList = userDao.getUserByCondition3(user1);
        System.out.println(userList);
    }

    @Test
    void getUserByChooseTest() {
        User user1 = new User();
        user1.setUserId(null);
        user1.setUserName(null);
        user1.setPassword(null);
        List<User> userList = userDao.getUserByChoose(user1);
        System.out.println(userList);
    }

    @Test
    void deleteMoreByArrayTest() {
        int result = userDao.deleteMoreByArray(new Integer[]{21, 22});
        System.out.println(result);
    }

    @Test
    void deleteMoreByArray2Test() {
        int result = userDao.deleteMoreByArray2(new Integer[]{8, 11});
        System.out.println(result);
    }

    @Test
    void getUserByCacheTest() {
        List<User> userList = userDao.getUsers();
        List<User> userList2 = userDao.getUsers();
        System.out.println(userList);
        System.out.println(userList2);
    }

}
