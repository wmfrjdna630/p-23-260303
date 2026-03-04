package com.back;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;

@Configuration
public class AppConfig {

//    @Bean
//    public PersonService personServiceV2() {
//        System.out.println("personService v2 빈이 생성되었습니다.");
//        return new PersonService(2);
//    }
//
//    @Bean
//    public PersonService personServiceV3() {
//        System.out.println("personService v3 빈이 생성되었습니다.");
//        return new PersonService(3);
//    }

    @Bean
    public int personVersion() {
        return 100;
    }

    @Bean
    @Order(1)
    public ApplicationRunner myApplicationRunner1() {
        return args -> {
            System.out.println("MyApplicationRunner1 is running");
        };
    }

    @Bean
    @Order(2)
    public ApplicationRunner myApplicationRunner2() {
        return args -> {
            System.out.println("MyApplicationRunner2 is running");
        };
    }

    @Autowired
    @Lazy
    private AppConfig self;

    @Bean
    public ApplicationRunner myApplicationRunner3() {
        return args -> {
            self.work1();
            self.work2();
        };
    }

    @Transactional
    public void work1(){
        System.out.println("work1");
    }

    @Transactional
    public void work2(){
        System.out.println("work2");
    }

}