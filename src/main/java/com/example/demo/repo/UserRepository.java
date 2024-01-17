package com.example.demo.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserData;

@Repository
public interface UserRepository extends CrudRepository<UserData, Integer>{

}
