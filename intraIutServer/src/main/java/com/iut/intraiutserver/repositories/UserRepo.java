package com.iut.intraiutserver.repositories;

import com.iut.intraiutserver.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Integer> {

}