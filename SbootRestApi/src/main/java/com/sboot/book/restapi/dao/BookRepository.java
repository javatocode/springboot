package com.sboot.book.restapi.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sboot.book.restapi.model.Books;

public interface BookRepository extends CrudRepository<Books,Integer>{
public Books findById(int id);
}
