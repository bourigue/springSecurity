package com.example.springsecurity.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springsecurity.Entites.User;
import com.example.springsecurity.Entites.userRole;
import com.example.springsecurity.Repositories.UserRepository;
import com.example.springsecurity.Services.AccountServicesImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @GetMapping(path = "/refreshToken")
 public void refresheToken(HttpServletRequest request, HttpServletResponse response) throws Exception{

     String auhToken=request.getHeader("Authorization");
     if(auhToken!=null && auhToken.startsWith("Bearer ")){
         try{
             String jwt=auhToken.substring(7);
             Algorithm algorithm=Algorithm.HMAC256("mySecret1234");
             JWTVerifier jwtVerifier = JWT.require(algorithm).build();
             DecodedJWT decodedJWT=jwtVerifier.verify(jwt);
             String username=decodedJWT.getSubject();
             User user=accountServices.loadUserByUserneme(username);

             Algorithm algol=Algorithm.HMAC256("mySecret1234");
             String jwtAccessToken= JWT.create()
                     .withSubject(user.getName())
                     .withExpiresAt(new Date(System.currentTimeMillis()+1*60*1000))
                     .withIssuer(request.getRequestURL().toString())
                     .withClaim("roles",user.getUseroles().stream().map(r->r.getRolename()).collect(Collectors.toList()))
                     .sign(algol);


             Map<String,String> idToken=new HashMap<>();
             idToken.put("access-token",jwtAccessToken);
             idToken.put("refresh-token",jwt);
             response.setContentType("application/json");
             new ObjectMapper().writeValue(response.getOutputStream(),idToken);

     }catch (Exception e){
             //response.setHeader("error-message",e.getMessage());
             //response.sendError(HttpServletResponse.SC_FORBIDDEN);
             throw e;
         }
     }else{

         throw new RuntimeException("Rfersh token required !!!");
     }

 }
@GetMapping("/profile")
 public  User profile(Principal principal){
return  accountServices.loadUserByUserneme(principal.getName());

 }





}





@Data
class RoleToUser{
    String username;
    String rolename;
}