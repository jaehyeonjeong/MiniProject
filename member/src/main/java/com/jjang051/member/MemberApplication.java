package com.jjang051.member;

import com.jjang051.member.controller.HomeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MemberApplication {

    public static void main(String[] args) {
        //HomeController controller = new HomeController();
        //스프링 컨테이너에 등록해 둔다. 이때 singleton pattern
        SpringApplication.run(MemberApplication.class, args);
    }


}
