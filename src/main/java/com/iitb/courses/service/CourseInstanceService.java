package com.iitb.courses.service;

import com.iitb.courses.entity.CourseInstance;
import com.iitb.courses.entity.Course;
import com.iitb.courses.repository.CourseInstanceRepository;
import com.iitb.courses.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CourseInstanceService {
    @Autowired
    private CourseInstanceRepository courseInstanceRepository;
    @Autowired
    private CourseRepository courseRepository;

    public List<CourseInstance> getInstancesByYearAndSemester(int year, int semester) {
        return courseInstanceRepository.findByYearAndSemester(year, semester);
    }

    public Optional<CourseInstance> getInstance(int year, int semester, String courseId) {
        return courseInstanceRepository.findByYearAndSemesterAndCourse_CourseId(year, semester, courseId);
    }

    @Transactional
    public CourseInstance createInstance(int year, int semester, String courseId) {
        Course course = courseRepository.findByCourseId(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found: " + courseId));
        CourseInstance instance = new CourseInstance();
        instance.setYear(year);
        instance.setSemester(semester);
        instance.setCourse(course);
        return courseInstanceRepository.save(instance);
    }

    @Transactional
    public void deleteInstance(int year, int semester, String courseId) {
        courseInstanceRepository.deleteByYearAndSemesterAndCourse_CourseId(year, semester, courseId);
    }
}
