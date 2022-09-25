package com.example.springwebserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.ApplicationContext;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.Arrays;


@SpringBootApplication
public class SpringWebServerApplication {

    private static final Logger log = LoggerFactory.getLogger(SpringWebServerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringWebServerApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }


}
// https://spring.io/guides/gs/spring-boot/
//https://stackoverflow.com/questions/22877350/how-to-extract-ip-address-in-spring-mvc-controller-get-call
//https://www.baeldung.com/spring-request-param
//https://www.baeldung.com/spring-rest-http-headers
//https://mkyong.com/spring/spring-jdbctemplate-querying-examples/
//https://dzone.com/articles/spring-boot-and-spring-jdbc-with-h2
//https://www.baeldung.com/spring-boot-h2-database
//https://www.youtube.com/watch?v=9ME94z46fsU