package com.example.springsecurity.Repositories;


import com.example.springsecurity.Entites.userRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface userRoleRepository extends JpaRepository<userRole, Long> {

    userRole findByRolename(String name);


}