package com.chiaxiao.tushumannger.Controller;

import com.chiaxiao.tushumannger.POJO.Book;
import com.chiaxiao.tushumannger.Service.impl.Bookservice;
import com.chiaxiao.tushumannger.Service.impl.UserBookservice;
import com.chiaxiao.tushumannger.Service.impl.Userservice;
import com.chiaxiao.tushumannger.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class bookcontroller {

    @Autowired
    private Bookservice bookservice;
    @Autowired
    private UserBookservice userBookservice;

    @GetMapping("/getall")
    public Result getall(){
        return new Result(bookservice.getall(),101,"查询成功");
    }

    @PostMapping("/getbyall")
    public Result getbyall(@RequestBody Book bookmessage){
        List<Book> getbyall = bookservice.getbyall(bookmessage);
        return new Result(getbyall,101,"查询成功");
    }

    @PostMapping("/save")
    public Result save(@RequestBody Book bookmessage){
        bookservice.save(bookmessage);
        return new Result(true,101,"存入成功");
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Book bookmessage){
        if(bookservice.delete(bookmessage)) {
            return new Result(true, 121, "删除成功");
        }else{
            return new Result(false, 120, "删除失败");
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody Book bookmessage){
        if(bookservice.updateall(bookmessage)){
            return new Result(true, 131, "修改成功");
        }else{
            return new Result(false, 130, "修改失败");
        }
    }

}
