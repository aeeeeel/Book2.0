import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.LogManager;
import org.example.sql.SqlUtil;

public class Test {
    //SqlSessionFactory sqlSessionFactory;
    //sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
//        try(ServerSocket serverSocket = new ServerSocket(8888)){
//        Socket socket = serverSocket.accept();
//        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        bufferedReader.readLine();
//        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
//        outputStreamWriter.write("abc");
//        outputStreamWriter.flush();
//    }
//        try(Socket socket = new Socket("127.0.0.1",8888);Scanner scanner = new Scanner(System.in);){
//        OutputStream outputStream = socket.getOutputStream();
//        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
//        FileOutputStream fileOutputStream = new FileOutputStream("books.txt");
//        String text = scanner.nextLine();
//        outputStreamWriter.write(text);
//        outputStreamWriter.flush();
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        FileInputStream fileInputStream = new FileInputStream("books.txt");
//        bufferedReader.readLine();
//    }
}

