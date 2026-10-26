package com.infy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "lessons")
@Data
public class Lesson {
	 @Id
	 @GeneratedValue(strategy= GenerationType.IDENTITY)
	 private Integer lessonId;
	 @ManyToOne
	 @JoinColumn(name = "course_id")
	 private Course course;
	 private String lessonName;
	 @Enumerated(EnumType.STRING)
	 private LessonType type;
	 
	 private String url;

  
     
     
}
