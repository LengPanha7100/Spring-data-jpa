package com.example.demospring.demomanyto_many.service;

import com.example.demospring.demomanyto_many.model.Course;
import com.example.demospring.demomanyto_many.model.request.CourseRequest;

import java.util.List;

public interface CourseService {
    Course createCourse(CourseRequest courseRequest);

    Course updateCourse(CourseRequest courseRequest, Integer id);

    List<Course> getAllCource();

    Course getByIdCourse(Integer id);

    Course deleteCourse(Integer id);
}
