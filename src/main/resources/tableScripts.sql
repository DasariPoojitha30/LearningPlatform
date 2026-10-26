DROP SCHEMA IF EXISTS learning_platform;

CREATE SCHEMA learning_platform;

USE learning_platform;

CREATE TABLE course (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    course_name VARCHAR(255),
    description VARCHAR(255),
    educator_id INT
);

CREATE TABLE lessons (
    lesson_id INT AUTO_INCREMENT PRIMARY KEY,
    course_id INT,
    lesson_name VARCHAR(255),
    type VARCHAR(255),
    url VARCHAR(500),

    CONSTRAINT fk_lesson_course
        FOREIGN KEY (course_id)
        REFERENCES course(course_id)
);