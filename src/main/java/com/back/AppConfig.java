package com.back;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

//    @Bean
//    public ApplicationRunner myApplicationRunner() {
//        return new MyApplicationRunner();
//    }

    @Bean
    public ApplicationRunner myApplicationRunner() {
        return args -> {
            System.out.println("MyApplicationRunner is running");
        };
    }


//    @Bean
//    public ArrayList<Integer> numbers() {
//        System.out.println("numbers 빈이 생성되었습니다.");
//        return new ArrayList(List.of(1,2,3));
//    }



}