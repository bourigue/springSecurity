package com.example.springsecurity.Services;

import com.example.springsecurity.Entites.User;
import com.example.springsecurity.Entites.userRole;

import java.util.List;

public interface AccountServices {
    User addNewUser(User user);
    userRole addNewRole(userRole userrole);
    void addRoleToUser(String username, String rolename);
    User loadUserByUserneme(String username);
    List<User> listuser();
}
