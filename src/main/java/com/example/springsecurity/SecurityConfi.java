package com.example.springsecurity;


import com.example.springsecurity.Filter.JwtAuthenticationFilter;
import com.example.springsecurity.Filter.JwtAuthorizationFilter;
import com.example.springsecurity.Entites.User;
import com.example.springsecurity.Services.AccountServicesImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.activation.DataSource;
import java.util.ArrayList;
import java.util.Collection;


@Configuration
@EnableWebSecurity
public class SecurityConfi extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;
    private AccountServicesImpl accountServices;

    public SecurityConfi(AccountServicesImpl accountServices) {
        this.accountServices = accountServices;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User usere=accountServices.loadUserByUserneme(username);
                Collection<GrantedAuthority> authorities=new ArrayList<>();
                usere.getUseroles().forEach(r->{
                    authorities.add(new SimpleGrantedAuthority(r.getRolename()));
                });

                return new org.springframework.security.core.userdetails.User(usere.getName(),usere.getPassword(),authorities);
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.headers().frameOptions().disable();
       http.authorizeRequests().antMatchers( "*/refreshToken/**").permitAll();
      //  http.formLogin();
     //   http.authorizeRequests().antMatchers(HttpMethod.POST,"/users/**").hasAuthority("admin");
     //   http.authorizeRequests().antMatchers(HttpMethod.GET,"/users/**").hasAuthority("user");
     //   http.authorizeRequests().antMatchers(HttpMethod.GET,"/users/**").hasAuthority("admin");

        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new JwtAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
