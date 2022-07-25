package com.example.springsecurity.Controller;

import com.example.springsecurity.Entites.User;
import com.example.springsecurity.Entites.userRole;
import com.example.springsecurity.Repositories.UserRepository;
import com.example.springsecurity.Services.AccountServicesImpl;
import lombok.Data;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class AccountCountroller {

    private UserRepository userRepository;
    private AccountServicesImpl accountServices;

    public AccountCountroller(UserRepository userRepository, AccountServicesImpl accountServices) {
        this.userRepository = userRepository;
        this.accountServices = accountServices;
    }

@GetMapping(path="/users")
@PostAuthorize("hasAuthority('user')")
List<User> listuser(){
return  accountServices.listuser();
}

@PostMapping(path = "/users")
User addUser(@RequestBody User user){
 return   accountServices.addNewUser(user);
}

@PostMapping(path = "/roles")
    userRole addRole(@RequestBody userRole role){
        return   accountServices.addNewRole(role);
    }

@PostMapping(path = "/RoleToUer")
void addRoleToUser(@RequestBody RoleToUser roleToUser){
         accountServices.addRoleToUser(roleToUser.username,roleToUser.rolename);
    }

    @GetMapping(path = "/hhhh")
    User byuseranme(){
        return accountServices.loadUserByUserneme("rachidrachid");
    }
}
@Data
class RoleToUser{
    String username;
    String rolename;
}