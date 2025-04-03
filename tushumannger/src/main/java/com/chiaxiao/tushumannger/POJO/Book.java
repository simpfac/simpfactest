package com.chiaxiao.tushumannger.POJO;

public class Book {

    private String bookname;
    private String bookauthor;
    private Integer number;
    private String description;
    private Integer isexist;
    private String type;
    private Integer bookid;
    private Integer borrownumber;
    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }


    public Integer getBorrownumber() {
        return borrownumber;
    }

    public void setBorrownumber(Integer borrownumber) {
        this.borrownumber = borrownumber;
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

    @Override
    public String toString() {
        return "Book{" +
                "bookname='" + bookname + '\'' +
                ", bookauthor='" + bookauthor + '\'' +
                ", number=" + number +
                ", description='" + description + '\'' +
                ", isexist=" + isexist +
                ", type='" + type + '\'' +
                ", bookid=" + bookid +
                ", borrownumber=" + borrownumber +
                '}';
    }
}
