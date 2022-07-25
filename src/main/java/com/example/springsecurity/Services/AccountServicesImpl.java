package com.example.springsecurity.Services;

import com.example.springsecurity.Entites.User;
import com.example.springsecurity.Entites.userRole;
import com.example.springsecurity.Repositories.UserRepository;
import com.example.springsecurity.Repositories.userRoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class AccountServicesImpl implements AccountServices {
    private UserRepository userrep;
    private userRoleRepository userrolerepo;
    private PasswordEncoder passwordEncoder;
    AccountServicesImpl(UserRepository userrep, userRoleRepository userrolerepo, PasswordEncoder passwordEncoder){
        this.userrep=userrep;
        this.userrolerepo=userrolerepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User addNewUser(User user) {
        String pass=user.getPassword();
        user.setPassword(passwordEncoder.encode(pass));

        return userrep.save(user);
    }

    @Override
    public userRole addNewRole(userRole userrole) {
        return userrolerepo.save(userrole);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        User user=userrep.findByName(username);
        userRole userrole=userrolerepo.findByRolename(rolename);
        user.getUseroles().add(userrole);
    }

    @Override
    public User loadUserByUserneme(String username) {
        return userrep.findByName(username);
    }

    @Override
    public List<User> listuser() {
        return userrep.findAll();
    }
}

