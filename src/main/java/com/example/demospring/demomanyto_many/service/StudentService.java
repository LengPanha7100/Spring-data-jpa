package com.example.demospring.demomanyto_many.service;

import com.example.demospring.demomanyto_many.model.Student;
import com.example.demospring.demomanyto_many.model.request.StudentRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface StudentService {
    Student createStudent(StudentRequest studentRequest);

    Student updateStudent(StudentRequest studentRequest, Integer id);

    List<Student> getAllStudent(int page, int size, String sortBy, Sort.Direction orderBy);

    Student deleteStudent(Integer id);

    Student getByIdStudent(Integer id);


    List<Student> filterStudentAge(Double start, Double end);

    List<Student> filterStudentName(String name);
}
