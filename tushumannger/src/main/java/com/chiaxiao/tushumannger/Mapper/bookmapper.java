package com.chiaxiao.tushumannger.Mapper;

import com.chiaxiao.tushumannger.POJO.Book;
import com.chiaxiao.tushumannger.POJO.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface bookmapper {

    //查询所有的书
    @Select("select bookid,bookname,bookauthor,number,description,isexist,type from book")
    List<Book> getall();
    //三个选项合并搜索
    List<Book> getbyall(Map<String,String> choose);
    //添加书
    void save(Book bookmessage);
    //删除书
    void delete(Book bookmessage);
    //查询书的数量
    @Select("select number from book where bookname=#{bookname} and bookauthor=#{bookauthor} and type=#{type}")
    Integer selectnumber(Book bookmessage);
    //查询书的id
    @Select("select bookid from book where bookname=#{bookname} and bookauthor=#{bookauthor} and type=#{type}")
    Integer selectid(Book bookmessage);
    //修改书的数量
    @Update("update book set number=#{number} where bookid=#{id}")
    void updatenumber(Integer number,Integer id);
    //修改书是否存在
    @Update("update book set isexist=#{isexist} where bookid=#{id}")
    void updateisexist(Integer id,Integer isexist);
    //获取书的描述
    @Select("select description from book where bookname= #{bookname}")
    String selectdescription(String bookname);
    //修改书的信息
    @Update("update book set bookname=#{bookname},bookauthor=#{bookauthor},number=#{number},description=#{description},type=#{type} where bookid=#{bookid}")
    void updateall(Book bookmessage);
}
