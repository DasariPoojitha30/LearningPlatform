package com.infy.dto;

import com.infy.entity.LessonType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestLessonDTO {



	private Integer lessonId;
	@NotNull(message = "provide a course")
	private CourseDTO courseDto;
	@NotBlank(message = "provide a input variable")
	private String lessonName;
	private LessonType type;
	private String url;


}
