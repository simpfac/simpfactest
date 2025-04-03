package com.chiaxiao.tushumannger.Service.impl;

import com.chiaxiao.tushumannger.POJO.Book;
import com.chiaxiao.tushumannger.POJO.userBook;

import java.util.List;

public interface UserBookservice {
    //借书
    boolean borrow(Book bookmessage, String username);
    //还书
    boolean returnbook(userBook bookmessage,String username);
    //三个选项合并搜索
    List<Book> getbyall(Book bookessage,String username);
    //修改书的数量
    boolean updatenumber(Integer number,Integer id);
    //查询书的id
    Integer selectid(userBook bookmessage);
    //查询书的数量
    Integer selectnumber(userBook bookmessage);
    //修改书是否存在
    boolean updateisexist(Integer id,Integer isexist);
    //查询所有书
    List<userBook> getall(String username);

}
