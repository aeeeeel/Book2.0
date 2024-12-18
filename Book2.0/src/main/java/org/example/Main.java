package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.entity.Book;
import org.example.mapper.BookMapper;
import org.example.entity.Student;
import org.example.sql.SqlUtil;

import javax.print.attribute.HashDocAttributeSet;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.LogManager;

@WebServlet("/test")
@Log
public class Main {
    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(System.in)) {
            LogManager manager = LogManager.getLogManager();
            manager.readConfiguration(Resources.getResourceAsStream("logging.properties"));
            while (true) {
                System.out.println("1.录入学生信息");
                System.out.println("2.录入书籍信息");
                System.out.println("3.录入借阅信息");
                System.out.println("4.查询借阅信息");
                System.out.println("5.查询学生信息列表");
                System.out.println("6.查询书籍信息列表");
                System.out.println("输入你想执行的操作（输入其他任意数字退出）");
                int input;
                try {
                    input = scanner.nextInt();
                } catch (Exception e) {
                    return;
                }
                scanner.nextLine();
                switch (input) {
                    case 1:
                        addStudent(scanner);
                        break;
                    case 2:
                        addBook(scanner);
                        break;
                    case 3:
                        addBorrow(scanner);
                        break;
                    case 4:
                        showBorrowList();
                        break;
                    case 5:
                        showStudentList();
                        break;
                    case 6:
                        showBookList();
                        break;
                    case 8:
                        getStudentBySid(scanner);
                        break;
                    case 9:
                        getBookByBid(scanner);
                        break;
                    case 0:
                        break;
                }

            }

        }

    }

    public class Login extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.getParameterMap().forEach((key, value) -> {
                System.out.println(key + ":" + Arrays.toString(value));
            });
        }
    }

    private static void addStudent(Scanner scanner) {
        System.out.println("姓名：");
        String name = scanner.nextLine();
        System.out.println("性别：");
        String sex = scanner.nextLine();
        System.out.println("成绩：");
        String grade = scanner.nextLine();
        int g = Integer.parseInt(grade);
        Student student = new Student(name, sex, g);
        SqlUtil.doSqlWork(mapper -> {
            int res = mapper.addStudent(student);
            if (res > 0) {
                System.out.println("success");
                log.info("插入学生信息成功");
            } else System.out.println("fail");
        });
    }

    private static void addBook(Scanner scanner) {
        System.out.println("书名:");
        String title = scanner.nextLine();
        System.out.println("简介:");
        String desc = scanner.nextLine();
        System.out.println("价格:");
        String price = scanner.nextLine();
        double p = Double.parseDouble(price);
        Book book = new Book(title, desc, p);
        SqlUtil.doSqlWork(mapper -> {
            int res = mapper.addBook(book);
            if (res > 0) {
                System.out.println("success");
                log.info("插入书籍信息成功");
            } else System.out.println("fail");
        });
    }

    private static void addBorrow(Scanner scanner) {
        System.out.println("sid:");
        String sid = scanner.nextLine();
        int s = Integer.parseInt(sid);
        System.out.println("bid:");
        String bid = scanner.nextLine();
        int b = Integer.parseInt(bid);
        SqlUtil.doSqlWork(mapper -> {
            int res = mapper.addBorrow(s, b);
            if (res > 0) {
                System.out.println("success");
                log.info("插入书籍信息成功");
            } else System.out.println("fail");
        });
    }

    private static void getStudentBySid(Scanner scanner) {
        System.out.println("sid:");
        String sid = scanner.nextLine();
        int id = Integer.parseInt(sid);
        SqlUtil.doSqlWork(mapper -> {
            System.out.println(mapper.getStudentBySid(id));
        });
    }

    private static void showBorrowList() {
        System.out.println();
        SqlUtil.doSqlWork(mapper -> {
            mapper.showBorrowList().forEach(borrow -> {
                System.out.println(borrow.getStudent().getName() + "-->" + borrow.getBook().getTitle());
            });
            System.out.println(mapper.showBorrowList());
        });
    }

    private static void showStudentList() {
        System.out.println();
        SqlUtil.doSqlWork(mapper -> {
            mapper.showStudentList().forEach(student -> {
                System.out.println(student.getSid() + " " + student.getName() + " " + student.getSex() + " " + student.getGrade());
            });
        });
    }

    private static void showBookList() {
        System.out.println();
        SqlUtil.doSqlWork(mapper -> {
            mapper.showBookList().forEach(book -> {
                System.out.println(book.getBid() + " " + book.getTitle() + " " + book.getDesc() + " " + book.getPrice());
            });
        });
    }

    private static void getBookByBid(Scanner scanner) {
        System.out.println("bid:");
        String bid = scanner.nextLine();
        int id = Integer.parseInt(bid);
        SqlUtil.doSqlWork(mapper -> {
            System.out.println(mapper.getBookByBid(id));
        });
    }

}