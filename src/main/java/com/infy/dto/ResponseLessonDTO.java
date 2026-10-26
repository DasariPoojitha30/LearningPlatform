package com.infy.dto;

import com.infy.entity.Course;
import com.infy.entity.LessonType;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseLessonDTO {
	private Integer lessonId;
//	@NotBlank(message = "provide a input variable")
//	private CourseDTO courseDto;
	@NotBlank(message = "provide a input variable")
	private String lessonName;
	private LessonType type;
	private String url;

}
