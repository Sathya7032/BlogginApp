package com.bloggingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloggingApp.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
