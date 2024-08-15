package com.example.demospring.demomanyto_many.service;

import com.example.demospring.demomanyto_many.model.StudentCourse;
import com.example.demospring.demomanyto_many.model.request.StudentCourseRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface StudentCourseService {
    StudentCourse createStuCourse(StudentCourseRequest studentCourseRequest);

    List<StudentCourse> getAllStudentCourse(int page, int size, String sortBy, Sort.Direction orderBy);

    StudentCourse getByIdStudentCourse(Integer id);

    StudentCourse updateStudentCourse(StudentCourseRequest studentCourseRequest, Integer id);

    StudentCourse deleteStudentCourse(Integer id);

    List<StudentCourse> findStudentByName(String name);
}
