package com.infy.api;

//http//localhost:6969/infyPlatform/course
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.infy.dto.CourseDTO;
import com.infy.dto.RequestLessonDTO;
import com.infy.dto.ResponseLessonDTO;
import com.infy.entity.Lesson;
import com.infy.exception.CourseException;
import com.infy.repository.LessonRepository;
import com.infy.service.CourseLessonService;
import com.infy.service.CourseLessonServiceImpl;


import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

@RestController
@RequestMapping("/infyPlatform")
public class CourseServiceController {
	
	@Autowired
	private CourseLessonService courseLessonService;
	
	@Autowired
	private LessonRepository lessonRepository;
	
	@PostMapping("/course")
	public ResponseEntity<String> addCourse(@RequestBody CourseDTO course) throws CourseException{ //YAML -> Java by requestMapping
		
		
		Integer cId = courseLessonService.addCourse(course);
		
		String str =  "Successfully added course with Id" + ": " + cId ;
		return new ResponseEntity<>(str, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/course/{courseId}")
	public ResponseEntity<CourseDTO> getCourseById(@PathVariable Integer courseId) throws CourseException { //why path vari..
		
		CourseDTO courseDto = courseLessonService.getCourseById(courseId);
		
		
		return new ResponseEntity<>(courseDto, HttpStatus.OK);
		
	}
	
	@GetMapping("/course/{courseName}")
	public ResponseEntity<List<CourseDTO>> getCourseByName(@PathVariable String courseName) throws CourseException{
		
		List<CourseDTO> cDtoList =  courseLessonService.getCourseByName(courseName);
		return new ResponseEntity<>(cDtoList, HttpStatus.OK);
		
	}
	
	@GetMapping("/allCourses")
	public ResponseEntity<List<CourseDTO>> getAllCourses() throws CourseException{
		List<CourseDTO> cDtoList = courseLessonService.getAllCourses();
		
		return new ResponseEntity<>(cDtoList, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/course/{courseId}")
	public ResponseEntity<String> deleteCourseById(@PathVariable Integer courseId) throws CourseException{
		
		String courseString = courseLessonService.deleteCourseById(courseId); 
		return new ResponseEntity<>(courseString, HttpStatus.OK);
		
	}
	@PostMapping("/lesson")
    public ResponseEntity<String> addLesson(
            @RequestBody RequestLessonDTO lesson) throws CourseException {

        Integer lessonId = courseLessonService.addLesson(lesson);

        String str = "Successfully added lesson with Id: " + lessonId;

        return new ResponseEntity<>(str, HttpStatus.CREATED);
    }

    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<ResponseLessonDTO> getLessonById(
            @PathVariable Integer lessonId) throws CourseException {

    	ResponseLessonDTO lessonDTO =
                courseLessonService.getLessonById(lessonId);

        return new ResponseEntity<>(lessonDTO, HttpStatus.OK);
    }
    @GetMapping("/lesson/{lessonId}/pdf")
    public ResponseEntity<Resource> getLessonPdf(
            @PathVariable Integer lessonId) throws IOException, CourseException {

        // 1. Find lesson from database
        Lesson lesson = lessonRepository.findById(lessonId)
        		
                .orElseThrow(() -> new CourseException("Lesson not found"));

        // 2. Get file path stored in database
        String filePath = lesson.getUrl();

        // 3. Convert the stored path into the actual file location
        String basePath = "C:/Users/DEll/Downloads/learningPlatform/learningPlatform/";

        Path path = Paths.get(basePath + filePath);

        // 4. Check whether file exists
        if (!Files.exists(path)) {
            throw new CourseException("PDF file not found");
        }

        // 5. Read the PDF as a Resource
//        Resource resource = new UrlResource(((java.nio.file.Path) path).toUri());
        Resource resource = new UrlResource(path.toUri());

        // 6. Return the PDF
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
    
    
    
    
//    @GetMapping("/lesson/course/{courseId}")
//    public ResponseEntity<List<LessonDTO>> getLessonsByCourseId(
//            @PathVariable Integer courseId) throws CourseException {
//
//        List<LessonDTO> lessonDTOList =
//                courseLessonService.getLessonById(courseId);
//
//        return new ResponseEntity<>(lessonDTOList, HttpStatus.OK);
//    }
    
    @PostMapping("/lesson/upload/pdf")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file) throws  IOException {
    	
    	String filePath = "C:/Users/DEll/Downloads/learningPlatform/learningPlatform/Uploads/PDF's/" + file.getOriginalFilename();

    	file.transferTo(new File(filePath));

        return new ResponseEntity<>("File received", HttpStatus.OK);
    }
    

    
    @DeleteMapping("/lesson/{lessonId}")
    public ResponseEntity<String> deleteLessonById(
            @PathVariable Integer lessonId) throws CourseException {

        String lessonString =
                courseLessonService.deleteLessonById(lessonId);

        return new ResponseEntity<>(lessonString, HttpStatus.OK);
    }
	
	
}

