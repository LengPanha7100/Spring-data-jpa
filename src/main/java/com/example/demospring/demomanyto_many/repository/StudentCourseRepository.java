package com.example.demospring.demomanyto_many.repository;

import com.example.demospring.demomanyto_many.model.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse,Integer> {

//    List<StudentCourse>
}
