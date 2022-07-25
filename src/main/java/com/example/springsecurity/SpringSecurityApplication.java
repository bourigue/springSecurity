package com.example.springsecurity;

import com.example.springsecurity.Entites.User;
import com.example.springsecurity.Entites.userRole;
import com.example.springsecurity.Services.AccountServicesImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled=true,securedEnabled=true)
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner start(AccountServicesImpl accountServices){
    return args -> {
//      accountServices.addNewUser(new User(null,"rachidrachid","rachidmail","rachid123",new ArrayList<>()));
//      accountServices.addNewUser(new User(null,"hassanhassan","hassanmail","rachid123",new ArrayList<>()));
//      accountServices.addNewUser(new User(null,"brahimbrahim","brahimmail","brahim123",new ArrayList<>()));
//      accountServices.addNewRole(new userRole(null,"admin"));
//      accountServices.addNewRole(new userRole(null,"user"));
//      accountServices.addNewRole(new userRole(null,"manager"));
//      accountServices.addRoleToUser("rachidrachid","admin");
//      accountServices.addRoleToUser("hassanhassan","user");
//      accountServices.addRoleToUser("brahimbrahim","manager");







    };

    }
}
