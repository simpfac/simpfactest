package com.chiaxiao.tushumannger.Controller;

import com.chiaxiao.tushumannger.POJO.Book;
import com.chiaxiao.tushumannger.POJO.userBook;
import com.chiaxiao.tushumannger.Service.impl.UserBookservice;
import com.chiaxiao.tushumannger.util.Result;
import com.chiaxiao.tushumannger.util.getcookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userbooks")
public class userbookcontroller {

    @Autowired
    private UserBookservice userBookservice;
    //借书
    @PostMapping("/borrow")
    public Result borrow(@RequestBody Book bookmessage, @CookieValue(value = "username", required = false) String username){
        if(userBookservice.borrow(bookmessage,username)){
            return new Result(true,101,"成功借书");
        }else{
            return new Result(false,100,"借书失败");
        }
    }
    //还书
    @PostMapping("/return")
    @CrossOrigin(origins = "${frontend.origin}", allowCredentials = "true")
    public Result returnbook(@RequestBody userBook bookmessage, @CookieValue(value = "username", required = false) String username){
        if(userBookservice.returnbook(bookmessage,username)){
            return new Result(true,131,"成功还书");
        }else{
            return new Result(false,130,"还书失败");
        }
    }

    @PostMapping("/getbyall")
    public Result gebytall(@RequestBody Book bookmessage, @CookieValue(value = "username", required = false) String username){
        List<Book> all = userBookservice.getbyall(bookmessage, username);
        return new Result(all,101,"搜索成功");

    }

    @GetMapping("/getall")
    public Result getall(@CookieValue(value = "username", required = false) String username){
        List<userBook> all = userBookservice.getall(username);
        return new Result(all,101,"查询成功");
    }
}
