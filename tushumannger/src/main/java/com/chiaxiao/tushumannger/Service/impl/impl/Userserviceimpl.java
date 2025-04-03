package com.chiaxiao.tushumannger.Service.impl.impl;

import com.chiaxiao.tushumannger.Mapper.usermapper;
import com.chiaxiao.tushumannger.POJO.User;
import com.chiaxiao.tushumannger.Service.impl.Userservice;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class Userserviceimpl implements Userservice {

    @Autowired
    private usermapper usermapper;


    public List<User> getall() {
        return usermapper.getall();
    }

    public User getbyname(String username) {
        return usermapper.getbyname(username);
    }

    public User getbyid(int userid) {
        return usermapper.getbyid(userid);
    }

    public boolean save(User usermessage) {
        if(checkuser(usermessage.getUsername()) ==null){
            usermapper.save(usermessage);
            return true;
        }else{
            return false;
        }
    }

    public boolean update(String hobby, String username) {
        usermapper.update(hobby,username);
        return true;
    }

    public boolean delete(String username) {
        usermapper.delete(username);
        return true;
    }

    public Integer checkuser(String username){
        Integer checkuser = usermapper.checkuser(username);
        return checkuser;
    }

    public boolean userlogincheck(String username,String password){
        String userlogincheck = usermapper.userlogincheck(username);
        if(password.equals(userlogincheck)){
            return true;
        }else {
            return false;
        }
    }

    public Integer selectuserid(String username) {
        return usermapper.selectuserid(username);
    }

    public boolean adminlogincheck(String username,String password){
        String adminlogincheck = usermapper.adminlogincheck(username);
        if(password.equals(adminlogincheck)){
            return true;
        }else {
            return false;
        }
    }


}
