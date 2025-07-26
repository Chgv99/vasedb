package com.santobucle.VaseDB.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santobucle.VaseDB.entity.User;

public interface UserRepository extends JpaRepository<User,UUID>{
    
}
