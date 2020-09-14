package com.lead;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author mac
 */
@SpringBootApplication
@EnableSwagger2
@MapperScan(value = "com.lead.dao")
public class SpringbootJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJwtApplication.class, args);
    }
}
