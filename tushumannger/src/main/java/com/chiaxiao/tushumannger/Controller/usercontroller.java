package com.chiaxiao.tushumannger.Controller;

import com.chiaxiao.tushumannger.POJO.User;
import com.chiaxiao.tushumannger.Service.impl.Userservice;
import com.chiaxiao.tushumannger.util.Result;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class usercontroller {

    @Autowired
    private Userservice userservice;

    @PostMapping("/register")
    public Result userregister(@RequestBody User usermessage){
           if(userservice.save(usermessage)){
               return new Result(true,101,"注册成功");
           }else{
               return new Result(false,100,"注册失败，已经存在相同用户");
           }

    }

    @PostMapping("/login")
    public Result userlogin(@RequestBody User usermessage, HttpServletResponse response){
        if(userservice.userlogincheck(usermessage.getUsername(),usermessage.getUserpassword())){
            ResponseCookie usernameCookie = ResponseCookie.from("username", usermessage.getUsername())
                    .httpOnly(true)    // 防止XSS攻击
                    .secure(false)       // 关键修改：开发环境关闭Secure
                    .path("/")         // 全站可用
                    .maxAge(7 * 24 * 60 * 60)
                    .build();
            response.addHeader("Set-Cookie", usernameCookie.toString());
            return new Result(true,101,"登录成功");
        }else {
            return new Result(false,100,"用户名或密码错误");
        }
    }

    @PostMapping("/sethobby")
    public Result setuserhobby(@RequestBody User usermessage){
        if(userservice.update(usermessage.getHobby(),usermessage.getUsername())){
            return new Result(true,131,"修改用户爱好成功");
        }else {
            return new Result(false,130,"修改用户爱好失败");
        }
    }

    @PostMapping("/loginout")
    public Result loginout(HttpServletResponse response){
        ResponseCookie usernameCookie = ResponseCookie.from("username", null)
                .httpOnly(true)    // 防止XSS攻击
                .secure(false)       // 关键修改：开发环境关闭Secure
                .path("/")         // 全站可用
                .maxAge(0)
                .build();
        response.addHeader("Set-Cookie", usernameCookie.toString());
        return new Result(true,131,"登出成功");
    }

    @PostMapping("/adminlogin")
    public Result adminlogin(@RequestBody User usermessage){
        if (userservice.adminlogincheck(usermessage.getUsername(),usermessage.getUserpassword())){
            return new Result(true,101,"登录成功");
        }
        return new Result(false,100,"登录失败，检查用户名和密码");
    }

    @GetMapping("/mymessage")
    public Result mymessage(@CookieValue(value = "username", required = false) String username){
        User getbyname = userservice.getbyname(username);
        return new Result(getbyname,101,"查询成功");
    }
}
