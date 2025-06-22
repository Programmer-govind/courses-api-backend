package com.iitb.courses.controller;

import com.iitb.courses.entity.CourseInstance;
import com.iitb.courses.service.CourseInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/instances")
public class CourseInstanceController {
    @Autowired
    private CourseInstanceService courseInstanceService;

    @PostMapping
    public ResponseEntity<?> createInstance(@RequestParam int year, @RequestParam int semester, @RequestParam String courseId) {
        try {
            CourseInstance instance = courseInstanceService.createInstance(year, semester, courseId);
            return new ResponseEntity<>(instance, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{year}/{semester}")
    public List<CourseInstance> getInstances(@PathVariable int year, @PathVariable int semester) {
        return courseInstanceService.getInstancesByYearAndSemester(year, semester);
    }

    @GetMapping("/{year}/{semester}/{courseId}")
    public ResponseEntity<?> getInstance(@PathVariable int year, @PathVariable int semester, @PathVariable String courseId) {
        Optional<CourseInstance> instance = courseInstanceService.getInstance(year, semester, courseId);
        if (instance.isPresent()) {
            return ResponseEntity.ok(instance.get());
        } else {
            return new ResponseEntity<>("Instance not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{year}/{semester}/{courseId}")
    public ResponseEntity<?> deleteInstance(@PathVariable int year, @PathVariable int semester, @PathVariable String courseId) {
        Optional<CourseInstance> instance = courseInstanceService.getInstance(year, semester, courseId);
        if (instance.isEmpty()) {
            return new ResponseEntity<>("Instance not found", HttpStatus.NOT_FOUND);
        }
        courseInstanceService.deleteInstance(year, semester, courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
