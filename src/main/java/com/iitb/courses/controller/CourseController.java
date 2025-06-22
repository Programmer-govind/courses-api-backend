package com.iitb.courses.controller;

import com.iitb.courses.entity.Course;
import com.iitb.courses.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody Course course) {
        try {
            Course created = courseService.createCourse(course);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<?> getCourse(@PathVariable String courseId) {
        Optional<Course> course = courseService.getCourseByCourseId(courseId);
        if (course.isPresent()) {
            return ResponseEntity.ok(course.get());
        } else {
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable String courseId) {
        Optional<Course> courseOpt = courseService.getCourseByCourseId(courseId);
        if (courseOpt.isEmpty()) {
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }
        Course course = courseOpt.get();
        if (courseService.isCoursePrerequisiteForOthers(course)) {
            return new ResponseEntity<>("Course cannot be deleted due to existing dependencies", HttpStatus.CONFLICT);
        }
        courseService.deleteCourse(course);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
