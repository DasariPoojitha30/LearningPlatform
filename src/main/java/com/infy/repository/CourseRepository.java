package com.infy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.infy.dto.CourseDTO;
import com.infy.entity.Course;

import jakarta.persistence.NamedQuery;

public interface CourseRepository extends JpaRepository<Course, Integer>{
	
	@Query("SELECT p from Course p where p.courseName = ?1")
	public List<Course> getCourseByName(String courseName);
	
	
//	@Query("SELECT c FROM Course c WHERE c.courseName = :courseName")
//	List<Course> findByCourseName(@Param("courseName") String courseName);
}
