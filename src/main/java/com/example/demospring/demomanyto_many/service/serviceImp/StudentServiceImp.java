package com.example.demospring.demomanyto_many.service.serviceImp;

import com.example.demospring.demomanyto_many.exception.CustomNotfoundException;
import com.example.demospring.demomanyto_many.model.Card;
import com.example.demospring.demomanyto_many.model.Student;
import com.example.demospring.demomanyto_many.model.request.StudentRequest;
import com.example.demospring.demomanyto_many.repository.StudentRepository;
import com.example.demospring.demomanyto_many.service.CardService;
import com.example.demospring.demomanyto_many.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImp implements StudentService {
    private final StudentRepository studentRepository;
    private final CardService cardService;

    public StudentServiceImp(StudentRepository studentRepository, CardService cardService) {
        this.studentRepository = studentRepository;
        this.cardService = cardService;
    }


    @Override
    public Student createStudent(StudentRequest studentRequest) {
        Optional<Card> card = cardService.getByIdCard(studentRequest.getCardId());
        if(!card.isPresent()){
            throw new CustomNotfoundException("Card id is not found!");
        }
        Student student = new Student();
        student.setStuName(studentRequest.getStuName());
        student.setGender(studentRequest.getGender());
        student.setAge(studentRequest.getAge());
        student.setCard(card.get());
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(StudentRequest studentRequest, Integer id) {
        Optional<Card> card = cardService.getByIdCard(studentRequest.getCardId());
        if(!card.isPresent()){
            throw new CustomNotfoundException("Card id is not found!");
        }
        Student student = studentRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Student id is not found!")
        );
        student.setStuName(studentRequest.getStuName());
        student.setGender(studentRequest.getGender());
        student.setAge(studentRequest.getAge());
        student.setCard(card.get());
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudent(int page, int size , String sortBy , Sort.Direction orderBy) {
        Pageable pageable = PageRequest.of(page,size,Sort.by(orderBy,sortBy));
        Page<Student> students = studentRepository.findAll(pageable);
        return students.getContent();
    }

    @Override
    public Student deleteStudent(Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new CustomNotfoundException("Card id is not found")
        );
        studentRepository.deleteById(id);
        return student;
    }

    @Override
    public Student getByIdStudent(Integer id) {
        return studentRepository.findById(id).orElseThrow(
                () -> new CustomNotfoundException("Student id is not found")
        );
    }

    @Override
    public List<Student> filterStudentAge(Double start, Double end) {
        return studentRepository.findByAgeBetween(start,end);
    }

    @Override
    public List<Student> filterStudentName(String name) {
        return studentRepository.findByStuNameContains(name);
    }


}
