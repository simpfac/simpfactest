package com.chiaxiao.tushumannger.Mapper;

import com.chiaxiao.tushumannger.POJO.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface usermapper {

    //查询所有用户
    @Select("select * from user")
    List<User> getall();
    //查询单个用户通过名字
    @Select("select * from user where username=#{username}")
    User getbyname(String username);
    //查询单个用户通过id
    @Select("select * from user where userid=#{userid}")
    User getbyid(int userid);
    //添加用户
    @Insert("insert into user(username,userpassword,sex,hobby) values(#{username},#{userpassword},#{sex},#{hobby})")
    void save(User usermessage);
    //更新用户爱好
    @Update("update user set hobby = #{hobby} where username=#{username}")
    void update(String hobby, String username);
    //删除用户
    @Delete("delete from user where username = #{username}")
    void delete(String username);
    //检查用户名是否存在
    Integer checkuser(@Param("username") String username);
    //用户登录检查
    String userlogincheck(@Param("username") String username);
    //查询当前用户id
    @Select("select userid from user where username=#{username}")
    Integer selectuserid(@Param("username") String username);
    //管理员登录检查
    String adminlogincheck(@Param("username") String username);

}
