package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.entity.Book;
import org.example.entity.Borrow;
import org.example.entity.Student;

import java.util.List;

public interface BookMapper {

    @Insert("insert into student (name,sex,grade) values (#{name},#{sex},#{grade})")
    int addStudent(Student student);

    @Insert("insert into book (title,`desc`,price) values (#{title},#{desc},#{price})")
    int addBook(Book book);

    @Insert("insert into book_student (sid,bid) values (#{sid},#{bid})")
    int addBorrow(@Param("sid") int sid, @Param("bid") int bid);

    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "sid", property = "student", one = @One(select = "getStudentBySid")),
            @Result(column = "bid", property = "book", one = @One(select = "getBookByBid"))
    })
    @Select("select * from book_student")
    List<Borrow> showBorrowList();

    @Select("select * from book where bid=#{bid}")
    Book getBookByBid(int bid);

    @Select("select * from student where sid=#{sid}")
    Student getStudentBySid(int sid);

    @Select("select * from book")
    List<Book> showBookList();

    @Select("select * from student")
    List<Student> showStudentList();
}
