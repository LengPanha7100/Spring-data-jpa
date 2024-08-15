package com.example.demospring.demomanyto_many.repository;

import com.example.demospring.demomanyto_many.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>{

    List<Student> findByAgeBetween (Double start , Double end);

    List<Student> findByStuNameContains (String name);

}
