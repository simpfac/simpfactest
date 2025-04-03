package com.chiaxiao.tushumannger.Mapper;

import com.chiaxiao.tushumannger.POJO.Book;
import com.chiaxiao.tushumannger.POJO.userBook;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface userbookmapper {
    //添加书
    void save(Map<String,Object> userbookessage);
    //三个选项合并搜索
    List<Book> getbyall(Map<String, Object> choose);
    //修改书的数量
    @Update("update userbook set number=#{number} where bookid=#{id}")
    void updatenumber(Integer number,Integer id);
    //查询书的id
    @Select("select bookid from userbook where bookname=#{bookname} and bookauthor=#{bookauthor} and type=#{type}")
    Integer selectid(userBook bookmessage);
    //查询书的数量
    @Select("select number from userbook where bookname=#{bookname} and bookauthor=#{bookauthor} and type=#{type}")
    Integer selectnumber(userBook bookmessage);
    //修改书是否存在
    @Update("update userbook set isexist=#{isexist} where bookid=#{id}")
    void updateisexist(Integer id,Integer isexist);
    //查询属于当前用户的书（where user_id= username）并在这些书里面通过筛选选出要还的书
    userBook selectreturnbook(userBook bookmessage);
    //删除书
    @Delete("delete from userbook where bookname=#{bookname} and bookauthor=#{bookauthor} and type=#{type}")
    void deletebook(userBook bookmessage);
    //查询所有书
    @Select("select bookid,bookname,bookauthor,number,description,type from userbook where user_id = #{user_id}")
    List<userBook> getall(Integer user_id);
}
