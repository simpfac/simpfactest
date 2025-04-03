package com.chiaxiao.tushumannger.Service.impl.impl;

import com.chiaxiao.tushumannger.Mapper.bookmapper;
import com.chiaxiao.tushumannger.POJO.Book;
import com.chiaxiao.tushumannger.Service.impl.Bookservice;
import com.chiaxiao.tushumannger.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class Bookserviceimpl implements Bookservice {
    @Autowired
    private bookmapper bookmapper;

    public List<Book> getall() {
        List<Book> getall = bookmapper.getall();
        return getall;
    }

    public List<Book> getbyname(String name) {
        return null;
    }

    public List<Book> getbyauthor(String author) {
        return null;
    }

    public List<Book> getbytype(String type) {
        return null;
    }

    public List<Book> getbyall(Book bookmessage) {
        Map<String, String> choose = new HashMap<>();
        choose.put("bookname",bookmessage.getBookname());
        choose.put("bookauthor",bookmessage.getBookauthor());
        choose.put("type",bookmessage.getType());
        List<Book> getbyall = bookmapper.getbyall(choose);
        return getbyall;
    }

    public boolean save(Book bookessage) {
        //存入前检查数据是否存在，若数据完全相同则改为让number加要存入的number,需要一个检查方法，一个添加方法，一个只修改number方法
        if(getbyall(bookessage) ==null || getbyall(bookessage).isEmpty()){
            bookessage.setIsexist(1);
            bookmapper.save(bookessage);
        }else{
            Integer number = bookmapper.selectnumber(bookessage);
            Integer id = bookmapper.selectid(bookessage);
            number=number+bookessage.getNumber();
            bookmapper.updatenumber(number,id);
            bookmapper.updateisexist(id,1);
        }
        return true;
    }

    public boolean delete(Book bookmessage) {
        bookmapper.delete(bookmessage);
        return true;
    }

    public Integer selectnumber(Book bookmessage) {
        return bookmapper.selectnumber(bookmessage);
    }

    public Integer selectid(Book bookmessage) {
        return bookmapper.selectid(bookmessage);
    }

    public boolean updatenumber(Integer number, Integer id) {
        bookmapper.updatenumber(number,id);
        return true;
    }

    public boolean updateisexist(Integer id,Integer isexist) {
        bookmapper.updateisexist(id,isexist);
        return true;
    }

    public boolean updateall(Book bookmessage){
        bookmapper.updateall(bookmessage);
        return true;
    }


}
