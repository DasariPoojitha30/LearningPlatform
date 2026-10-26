package com.infy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.entity.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
	
}
