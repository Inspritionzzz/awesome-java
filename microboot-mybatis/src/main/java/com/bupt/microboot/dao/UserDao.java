package com.bupt.microboot.dao;

import com.bupt.microboot.dto.UserInfoQueryParam;
import com.bupt.microboot.model.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

/**
 * Mybatis查询demo
 */
@Component
public interface UserDao {
    /**
     * 用户列表查询
     * @param userInfoQueryParam
     * @return
     */
    List<User> getList(UserInfoQueryParam userInfoQueryParam);

    /**
     * 用户信息查询
     * @param userId
     * @return
     */
    User getUser(String userId);

    /**
     * 添加用户
     * @param user
     */
    void addUser(User user);

    /**
     * 添加多个用户
     * @param userList
     */
    void addUsers(List<User> userList);

    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    User checkLogin(String userName, String password);

    /**
     * 用户登录
     * @param map
     * @return
     */
    User checkLoginByMap(Map<String, String> map);

    /**
     * 用户登录（@param）
     * @param username
     * @param password
     * @return
     */
    User checkLoginByParam(@Param(value = "username") String username, @Param("password") String password);

    /**
     * 查询所有用户信息
     * @return
     */
    List<User> getAllUsers();

    /**
     * 统计用户数量
     * @return
     */
    int getUserCount();

    /**
     * 查询用户并转换成map
     * @return
     */
    Map<String, Object> getUserByIdToMap(@Param("id") String id);

    /**
     * 查询所有用户并转换成map
     * @return
     */
    List<Map<String, Object>> getAllUsersToMap();

    /**
     * 查询所有用户并转换成map
     * @return
     */
    @MapKey("user_name")
    Map<String, Object> getAllUsersToMap2();

    /**
     * 根据用户名模糊查询
     * @return
     */
    List<User> getUserByLike(@Param("username") String username);

    /**
     * 批量删除
     * @param usernames
     * @return
     */
    int deleteUsers(@Param("usernames") String usernames);

    /**
     * 指定表名查询数据
     * @return
     */
    List<User> getUserByTableName(@Param("tableName") String tableName);

    /**
     * 添加用户(主设置键自增)
     * @param user
     */
    void addUserAutoIncre2(User user);

    /**
     * 添加用户(不设置键自增)
     * @param user
     */
    void addUserAutoIncre(User user);

    /**
     * 使用resultmap设置自定义映射获取结果集
     * @return
     */
    List<User> getUsers();

    /**
     * 多条件查询
     * @param user
     * @return
     */
    List<User> getUserByCondition(User user);

    /**
     * 多条件查询2
     * @param user
     * @return
     */
    List<User> getUserByCondition2(User user);

    /**
     * 多条件查询3
     * @param user
     * @return
     */
    List<User> getUserByCondition3(User user);

    /**
     * 测试choose when otherwise
     * @param user
     * @return
     */
    List<User> getUserByChoose(User user);

    /**
     * 通过数组批量删除
     * @param ids
     * @return
     */
    int deleteMoreByArray(@Param("ids") Integer[] ids);


    /**
     * 通过数组批量删除2
     * @param ids
     * @return
     */
    int deleteMoreByArray2(@Param("ids") Integer[] ids);

    /**
     * 通过数组批量删除
     * @param userList
     * @return
     */
    int insertMoreByList(@Param("users") List<User> userList);



}
