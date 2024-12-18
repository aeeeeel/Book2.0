package org.example.sql;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.mapper.BookMapper;

import java.io.IOException;
import java.util.function.Consumer;

public class SqlUtil {
    private static SqlSessionFactory factory;
    private SqlUtil() {};

    static  {
        try {
            factory=new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void doSqlWork(Consumer<BookMapper> consumer){
    try (SqlSession session=factory.openSession(true)) {
        BookMapper bookMapper=session.getMapper(BookMapper.class);
        consumer.accept(bookMapper);
    }
    }

}
