package com.iitb.courses.service;

import com.iitb.courses.entity.Course;
import com.iitb.courses.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseByCourseId(String courseId) {
        return courseRepository.findByCourseId(courseId);
    }

    @Transactional
    public Course createCourse(Course course) throws IllegalArgumentException {
        // Validate and fetch prerequisites as managed entities
        if (course.getPrerequisites() != null) {
            List<Course> validPrereqs = new java.util.ArrayList<>();
            for (Course prereq : course.getPrerequisites()) {
                Course found = courseRepository.findByCourseId(prereq.getCourseId())
                    .orElseThrow(() -> new IllegalArgumentException("Prerequisite course does not exist: " + prereq.getCourseId()));
                validPrereqs.add(found);
            }
            course.setPrerequisites(validPrereqs);
        }
        return courseRepository.save(course);
    }

    public boolean isCoursePrerequisiteForOthers(Course course) {
        List<Course> allCourses = courseRepository.findAll();
        for (Course c : allCourses) {
            if (c.getPrerequisites() != null && c.getPrerequisites().contains(course)) {
                return true;
            }
        }
        return false;
    }

    @Transactional
    public void deleteCourse(Course course) {
        courseRepository.delete(course);
    }
}
