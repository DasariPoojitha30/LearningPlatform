package com.infy.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
	
	
	 @NotNull(message = "provide a courseId")
	 private Integer courseId;
	 @NotNull(message = "provide a courseName")
	 @NotEmpty(message = "not blank")
	 @Pattern(regexp = "[A-Za-z][ A-Za-z]+", message = "provide a valid name")
     private String courseName;
     private String description;
     private Integer educatorId;
     
     private  List<ResponseLessonDTO> LessonList; 
     
}


  
