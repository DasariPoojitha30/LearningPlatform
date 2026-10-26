package com.infy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.dto.CourseDTO;
import com.infy.dto.RequestLessonDTO;
import com.infy.dto.ResponseLessonDTO;
import com.infy.entity.Course;
import com.infy.entity.Lesson;
import com.infy.exception.CourseException;
import com.infy.repository.CourseRepository;
import com.infy.repository.LessonRepository;

@Service
public class CourseLessonServiceImpl  implements CourseLessonService {

	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	LessonRepository lessonRepository;
	
	
	ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public Integer addCourse(CourseDTO course) throws CourseException {
		// TODO Auto-generated method stub
		
		
		Course courseObj = modelMapper.map(course, Course.class);
		if (course.getLessonList() != null) {
		    for (ResponseLessonDTO lesson : course.getLessonList()) {
		    	Lesson lessonObj = modelMapper.map(lesson, Lesson.class);
		        courseObj.addLessons(lessonObj);
		    }
		}
		
		return courseRepository.save(courseObj).getCourseId();
	}

	@Override
	public CourseDTO getCourseById(Integer courseId) throws CourseException {
		// TODO Auto-generated method stub
		Optional<Course> optCourse =  courseRepository.findById(courseId);
		
		Course courseObj2 = optCourse.orElseThrow(() -> new CourseException("course not found"));
		
		CourseDTO courseDtoObj = modelMapper.map(courseObj2, CourseDTO.class);
		
		return courseDtoObj;
	}

	@Override
	public List<CourseDTO> getCourseByName(String courseName) throws CourseException {
		// TODO Auto-generated method stub
		
		List<Course> courseList= courseRepository.getCourseByName(courseName);
		if(courseList.isEmpty()) {
			throw new CourseException("No courses by name");
		}
		
		List<CourseDTO> courseDtoList = new ArrayList<>();
		
		for(Course c : courseList) {
			CourseDTO courseDTO = modelMapper.map(c, CourseDTO.class);
			courseDtoList.add(courseDTO);
		}
		
		
		return courseDtoList;
	}

	@Override
	public List<CourseDTO> getAllCourses() throws CourseException {
		List<Course> courseList = courseRepository.findAll();
		
		if(courseList.isEmpty()) {
			throw new CourseException("No courses by name");
		}
		
		List<CourseDTO> courseDtoList = new ArrayList<>();
		for(Course c : courseList) {
			CourseDTO courseDTO = modelMapper.map(c, CourseDTO.class);
			courseDtoList.add(courseDTO);
		}
		
		// TODO Auto-generated method stub
		return courseDtoList;
	}

	@Override
	public String deleteCourseById(Integer courseId) throws CourseException {

		if(!courseRepository.existsById(courseId)) {
			throw new CourseException("No course found by this");
		}
		courseRepository.deleteById(courseId);
		return "Course deleted successfully with id : " +  courseId;
	}
	
//	{java, fullstack, python,oops}
//Java == {steams, strings, vari}
//	{oops->java,1}
////	coursedto dt = javaDto1;
//	java = oops
//	
	
	
	@Override
	public Integer addLesson(RequestLessonDTO lesson) throws CourseException {
		
		CourseDTO courseDto = lesson.getCourseDto();
//		Course course = modelMapper.map(courseDto, Course.class); here i have created new course twicw
		
		Course course = courseRepository.findById(courseDto.getCourseId()).orElseThrow(() -> new CourseException("cannot find course"));
		 
		Lesson lessonObj = modelMapper.map(lesson, Lesson.class);
//		lessonObj.setCourse(course);
		
		course.addLessons(lessonObj);
		
		
		courseRepository.save(course);

		return lessonRepository.save(lessonObj).getLessonId();
	}

	@Override
	public ResponseLessonDTO getLessonById(Integer lessonId) throws CourseException {
		// TODO Auto-generated method stub
		Optional<Lesson> optLesson =  lessonRepository.findById(lessonId);
		
		
		Lesson lessonObj2 = optLesson.orElseThrow(() -> new CourseException("lesson not found"));
//		CourseDTO courseDto  = modelMapper.map(lessonObj2.getCourse(), CourseDTO.class);
		
		
		ResponseLessonDTO lessonDtoObj = modelMapper.map(lessonObj2, ResponseLessonDTO.class);
//		lessonDtoObj.setCourseDto(courseDto);
		
		return lessonDtoObj;
		//request kaani pampocha?
	}


	@Override
	public List<ResponseLessonDTO> getAllLessons() throws CourseException {
		List<Lesson> lessonList = lessonRepository.findAll();
		
		if(lessonList.isEmpty()) {
			throw new CourseException("No courses by name");
		}
		
		List<ResponseLessonDTO> lessonDtoList = new ArrayList<>();
		for(Lesson l : lessonList) {
			ResponseLessonDTO lessonDTO = modelMapper.map(l, ResponseLessonDTO.class);
			lessonDtoList.add(lessonDTO);
		}
		
		// TODO Auto-generated method stub
		return lessonDtoList;
	
	}

	@Override
	public String deleteLessonById(Integer lessonId) throws CourseException {
		if(!lessonRepository.existsById(lessonId)) {
			throw new CourseException("No course found by this");
		}
		lessonRepository.deleteById(lessonId);
		return "Lesson deleted successfully";
	}

}
