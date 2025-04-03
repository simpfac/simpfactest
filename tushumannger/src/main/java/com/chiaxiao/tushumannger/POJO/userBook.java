package com.chiaxiao.tushumannger.POJO;

public class userBook {
    /*
            | bookid      | int          | NO   | PRI | NULL    | auto_increment |
            | bookname    | varchar(255) | NO   |     | NULL    |                |
            | bookauthor  | varchar(255) | NO   |     | NULL    |                |
            | number      | int          | NO   |     | NULL    |                |
            | description | varchar(255) | YES  |     | NULL    |                |
            | isexist     | tinyint(1)   | NO   |     | NULL    |                |
            | type        | varchar(255) | NO   |     | NULL    |                |
            | user_id     | int          | NO   | MUL | NULL    |                |

    */
    private String bookname;
    private String bookauthor;
    private Integer number;
    private String description;
    private Integer isexist;
    private String type;
    private Integer user_id;
    private Integer returnnumber;

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    private Integer bookid;

    public Integer getReturnnumber() {
        return returnnumber;
    }

    public void setReturnnumber(Integer returnnumber) {
        this.returnnumber = returnnumber;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBookauthor() {
        return bookauthor;
    }

    public void setBookauthor(String bookauthor) {
        this.bookauthor = bookauthor;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsexist() {
        return isexist;
    }

    public void setIsexist(Integer isexist) {
        this.isexist = isexist;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "userBook{" +
                "bookname='" + bookname + '\'' +
                ", bookauthor='" + bookauthor + '\'' +
                ", number=" + number +
                ", description='" + description + '\'' +
                ", isexist=" + isexist +
                ", type='" + type + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
