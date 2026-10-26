package com.infy.service;

import java.util.List;

import com.infy.dto.CourseDTO;
import com.infy.dto.RequestLessonDTO;
import com.infy.dto.ResponseLessonDTO;
import com.infy.exception.CourseException;

public interface CourseLessonService {
	
	public Integer addCourse(CourseDTO course) throws CourseException;

	public CourseDTO getCourseById(Integer courseId) throws CourseException;

	public List<CourseDTO> getCourseByName(String courseName) throws CourseException;

	public List<CourseDTO> getAllCourses() throws CourseException;

	public String deleteCourseById(Integer courseId) throws CourseException;

	public Integer addLesson(RequestLessonDTO lesson) throws CourseException;

	public ResponseLessonDTO getLessonById(Integer lessonId) throws CourseException;

//	public List<LessonDTO> getLessonByName(String lessonName) throws CourseException;

	public List<ResponseLessonDTO> getAllLessons() throws CourseException;

	public String deleteLessonById(Integer lessonId) throws CourseException;

}
