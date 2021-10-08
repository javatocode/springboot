package com.stocktrade.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stocktrade.models.User;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

}
