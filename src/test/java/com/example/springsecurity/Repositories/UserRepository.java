package com.example.springsecurity.Repositories;

import com.example.springsecurity.Entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}
