package com.chiaxiao.tushumannger.Service.impl.impl;

import com.chiaxiao.tushumannger.Mapper.bookmapper;
import com.chiaxiao.tushumannger.Mapper.userbookmapper;
import com.chiaxiao.tushumannger.Mapper.usermapper;
import com.chiaxiao.tushumannger.POJO.Book;
import com.chiaxiao.tushumannger.POJO.userBook;
import com.chiaxiao.tushumannger.Service.impl.Bookservice;
import com.chiaxiao.tushumannger.Service.impl.UserBookservice;
import com.chiaxiao.tushumannger.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserBookserviceimpl implements UserBookservice {
    @Autowired
    private userbookmapper userbookmapper;
    @Autowired
    private bookmapper bookmapper;
    @Autowired
    private usermapper usermapper;
    @Autowired
    private Bookservice bookservice;
    //借书
    public boolean borrow(Book bookmessage, String username) {

        Integer number = bookmapper.selectnumber(bookmessage);
        //通过cookie来获取用户的username
        //查询当前操作用户id
        Integer user_id=usermapper.selectuserid(username);
        Integer id = bookmapper.selectid(bookmessage);
        if((number-bookmessage.getBorrownumber()) >=0){
            number=number-bookmessage.getBorrownumber();
            bookmapper.updatenumber(number,id);
            if(bookmapper.selectnumber(bookmessage) ==0){
                bookmapper.updateisexist(id,0);
            }
            //存入前检查数据是否存在，若数据完全相同则改为让number加要存入的number,需要一个检查方法，一个添加方法，一个只修改number方法
            if(getbyall(bookmessage,username) ==null || getbyall(bookmessage,username).isEmpty()){
                //将书添加进userbook
                String description = bookmapper.selectdescription(bookmessage.getBookname());
                Map<String,Object> userbookmessage = new HashMap<>();
                userbookmessage.put("bookname", bookmessage.getBookname());
                userbookmessage.put("bookauthor", bookmessage.getBookauthor());
                userbookmessage.put("number",bookmessage.getBorrownumber());
                userbookmessage.put("description", description);
                userbookmessage.put("isexist",1);
                userbookmessage.put("type", bookmessage.getType());
                userbookmessage.put("user_id", user_id);
                userbookmapper.save(userbookmessage);
            }else{
                userBook userBook = new userBook();
                userBook.setBookname(bookmessage.getBookname());
                userBook.setBookauthor(bookmessage.getBookauthor());
                userBook.setType(bookmessage.getType());
                Integer userbooknumber = userbookmapper.selectnumber(userBook);
                Integer userbookid = userbookmapper.selectid(userBook);
                userbooknumber=userbooknumber+bookmessage.getBorrownumber();
                userbookmapper.updatenumber(userbooknumber,userbookid);
                userbookmapper.updateisexist(userbookid,1);
            }
            return true;
        }else{
            return false;
        }

    }

    //还书
    public boolean returnbook(userBook bookmessage,String username){
        //查询属于当前用户的书（where user_id= username）并在这些书里面通过筛选选出要还的书
        Integer id = usermapper.selectuserid(username);
        bookmessage.setUser_id(id);
        userBook selectreturnbook = userbookmapper.selectreturnbook(bookmessage);
        //确定要还书的数量
        Integer returnnumber = bookmessage.getReturnnumber();
        //判断谁的书多
        if(selectreturnbook.getNumber() >= returnnumber){
            //修改userbook书的数量
            Integer number=selectreturnbook.getNumber()-returnnumber;
            userbookmapper.updatenumber(number,selectreturnbook.getBookid());
            //当数量为0时直接从userbook中删除书
            if(userbookmapper.selectnumber(selectreturnbook) ==0){
                userbookmapper.deletebook(selectreturnbook);
            }
            //把书添加回book
            Book book = new Book();
            book.setNumber(returnnumber);
            book.setBookname(selectreturnbook.getBookname());
            book.setBookauthor(selectreturnbook.getBookauthor());
            book.setType(selectreturnbook.getType());
            book.setDescription(selectreturnbook.getDescription());
            bookservice.save(book);
            return true;
        }else{
            return false;
        }
    }


    public List<Book> getbyall(Book bookessage,String username) {
        Integer user_id = usermapper.selectuserid(username);
        Map<String, Object> choose = new HashMap<>();
        choose.put("bookname",bookessage.getBookname());
        choose.put("bookauthor",bookessage.getBookauthor());
        choose.put("type",bookessage.getType());
        choose.put("user_id",user_id);
        List<Book> getbyall = userbookmapper.getbyall(choose);
        return getbyall;
    }

    public boolean updatenumber(Integer number, Integer id) {
        userbookmapper.updatenumber(number,id);
        return true;
    }

    public Integer selectid(userBook bookmessage) {
        return userbookmapper.selectid(bookmessage);
    }

    public Integer selectnumber(userBook bookmessage) {
        return userbookmapper.selectnumber(bookmessage);
    }

    public boolean updateisexist(Integer id,Integer isexist) {
        bookmapper.updateisexist(id,isexist);
        return true;
    }

    public List<userBook> getall(String username) {
        Integer user_id = usermapper.selectuserid(username);
        List<userBook> getall = userbookmapper.getall(user_id);
        return getall;
    }
}
