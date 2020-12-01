package com.fxf.blogbs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.fxf.blogbs.dao")
public class BlogBsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogBsApplication.class, args);
    }

}
