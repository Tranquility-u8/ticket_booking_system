package cn.itfxq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("cn.itfxq")
public class MovieServerApp {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(MovieServerApp.class);

        springApplication.run(MovieServerApp.class,args);
    }
}
