package com.sboot.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sboot.jpa.model.Student;

public interface StudentRepository extends CrudRepository<Student,Integer>{
	// custome query methods
	public List<Student> findBySname(String name);
	public List<Student> findBySnameAndCity(String name, String city);
	
	
	// JPQL
	
	@Query("select s from Student s")
	public List<Student> getAllStudent();
	
	@Query("select s from Student s where s.sname =:sn")
	public List<Student> getStudentNaame(@Param("sn") String sname);

	
	// Native Query
	@Query(value = "select * from Student",nativeQuery=true)
	public List<Student> getStudents();
}
