package com.chiaxiao.tushumannger.Service.impl;


import com.chiaxiao.tushumannger.POJO.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Userservice {

    //查询所有用户
    List<User> getall();
    //查询单个用户
    User getbyname(String username);
    //查询单个用户
    User getbyid(int userid);
    //添加用户
    boolean save(User usermessage);
    //更新用户爱好
    boolean update(String hobby, String username);
    //删除用户
    boolean delete(String username);
    //检查用户名是否存在
    Integer checkuser(String username);
    //用户登录检查
    boolean userlogincheck(String username,String password);
    //查询用户id
    Integer selectuserid(String username);
    //管理员登录检查
    boolean adminlogincheck(String username,String password);
}
