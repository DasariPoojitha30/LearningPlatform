package com.infy.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer courseId;
     private String courseName;
     private String description;
     private Integer educatorId;

    @OneToMany(mappedBy = "course")
    private List<Lesson> lessonList = new ArrayList<>();
      
    public Integer getCourseId() {
 		return courseId;
 	}
 	public void setCourseId(Integer courseId) {
 		this.courseId = courseId;
 	}
 	public String getCourseName() {
 		return courseName;
 	}
 	public void setCourseName(String courseName) {
 		this.courseName = courseName;
 	}
 	
//	 {123, "spring Boot", "java", "pdf"}
 	public void addLessons(Lesson lesson) {
 		lessonList.add(lesson);
 		lesson.setCourse(this);
 		
 	}
 	
 	public List<Lesson> getLesson(){
 		return this.lessonList;
 	}

 	public String getDescription() {
 		return description;
 	}
 	public void setDescription(String description) {
 		this.description = description;
 	}
 	public Integer getEducatorId() {
 		return educatorId;
 	}
 	public void setEducatorId(Integer educatorId) {
 		this.educatorId = educatorId;
 	}
}
