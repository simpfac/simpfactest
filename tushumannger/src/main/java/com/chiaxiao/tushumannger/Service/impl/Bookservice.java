package com.chiaxiao.tushumannger.Service.impl;

import com.chiaxiao.tushumannger.POJO.Book;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface Bookservice {
    //查询所有的书
    List<Book> getall();
    //按照名字搜索书
    List<Book> getbyname(String name);
    //按照作者搜索书
    List<Book> getbyauthor(String author);
    //按照类别搜索书
    List<Book> getbytype(String type);
    //三个选项合并搜索
    List<Book> getbyall(Book bookessage);
    //添加书
    boolean save(Book bookessage);
    //删除书
    boolean delete(Book bookmessage);
    //查询书的数量
    Integer selectnumber(Book bookmessage);
    //查询书的id
    Integer selectid(Book bookmessage);
    //修改书的数量
    boolean updatenumber(Integer number,Integer id);
    //修改书是否存在
    boolean updateisexist(Integer id,Integer isexist);
    //修改书的所有信息
    boolean updateall(Book bookmessage);
}
