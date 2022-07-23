package com.example.springsecurity;

import com.example.springsecurity.Entites.User;
import com.example.springsecurity.Entites.userRole;
import com.example.springsecurity.Services.AccountServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SpringSecurityApplication implements CommandLineRunner {



     private AccountServicesImpl accountServices;
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }




    @Override
    public void run(String... args) throws Exception {

        accountServices.addNewUser(new User(null,"rachidrachid","rachid@gmail.com","rachid123",new ArrayList<>()));
        accountServices.addNewUser(new User(null,"abdoadbo","abdo@gmail.com","abdo1234",new ArrayList<>()));
        accountServices.addNewUser(new User(null,"hassanhassan","hassan@gmail.com","hassan123",new ArrayList<>()));
        accountServices.addNewRole(new userRole(null,"admin"));
        accountServices.addNewRole(new userRole(null,"user"));
        accountServices.addRoleToUser("hassanhassan","user");
        accountServices.addRoleToUser("rachidrachid","admin");
        accountServices.addRoleToUser("abdoadbo","user");
        accountServices.listuser().forEach(post -> {
          System.out.print(post.getUseroles()+"///////////////////////////////");

        });


    }
}
