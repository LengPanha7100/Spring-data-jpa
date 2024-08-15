package com.example.demospring.demomanyto_many.service.serviceImp;

import com.example.demospring.demomanyto_many.exception.CustomNotfoundException;
import com.example.demospring.demomanyto_many.model.Course;
import com.example.demospring.demomanyto_many.model.request.CourseRequest;
import com.example.demospring.demomanyto_many.repository.CourseRepository;
import com.example.demospring.demomanyto_many.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImp implements CourseService {
    private final CourseRepository courseRepository;

    public CourseServiceImp(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course createCourse(CourseRequest courseRequest) {
        Course course = new Course();
        course.setName(courseRequest.getName());
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(CourseRequest courseRequest, Integer id) {
        Course courseById  = courseRepository.findById(id).orElseThrow(
                () -> new CustomNotfoundException("Not Found")
        );
        Course course = new Course();
        course.setName(courseRequest.getName());
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCource() {
        return courseRepository.findAll();
    }

    @Override
    public Course getByIdCourse(Integer id) {
        return courseRepository.findById(id).orElseThrow(
                () -> new CustomNotfoundException("Not Found")
        );
    }

    @Override
    public Course deleteCourse(Integer id) {
        return courseRepository.findById(id).orElseThrow(
                () -> new CustomNotfoundException("Not Found")
        );
    }
}
