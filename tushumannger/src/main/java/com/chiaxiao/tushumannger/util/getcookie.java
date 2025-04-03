package com.chiaxiao.tushumannger.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class getcookie {
    String username = null;
    public getcookie(){

    }

    public getcookie(HttpServletRequest request){
        // 1. 从 Cookie 获取 username
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    System.out.println(cookie.getName());
                    username = cookie.getValue();
                    break;
                }
            }
        }else {
            System.out.println("cookie为空");
        }
    }

    public String returncookie(){
        return username;
    }
}
