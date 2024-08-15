package com.example.demospring.demomanyto_many.controller;

import com.example.demospring.demomanyto_many.model.APIResponse;
import com.example.demospring.demomanyto_many.model.Student;
import com.example.demospring.demomanyto_many.model.request.StudentRequest;
import com.example.demospring.demomanyto_many.service.StudentService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody StudentRequest studentRequest){
        Student student = studentService.createStudent(studentRequest);
        APIResponse<?> apiResponse = new APIResponse<>(
                "Successfully",
                student,
                HttpStatus.CREATED,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@RequestBody StudentRequest studentRequest,@PathVariable Integer id){
        Student student = studentService.updateStudent(studentRequest,id);
        APIResponse<?> apiResponse = new APIResponse<>(
                "Successfully",
                student,
                HttpStatus.OK,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Student>>> getAllStudent (@RequestParam int page , @RequestParam int size , @RequestParam String sortBy , @RequestParam Sort.Direction orderBy){
        List<Student> students = studentService.getAllStudent(page,size,sortBy,orderBy);
        APIResponse<List<Student>> apiResponse = new APIResponse<>(
                "Successfully",
                students,
                HttpStatus.OK,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Integer id){
        Student student = studentService.deleteStudent(id);
        APIResponse<?> apiResponse = new APIResponse<>(
                "Successfully",
                student,
                HttpStatus.OK,
                LocalDateTime.now()
        );
        return ResponseEntity.ok("Delete student is successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Student>> getByIdStudent(@PathVariable Integer id){
//        Student student = studentService.getByIdStudent(id);
        APIResponse<Student> apiResponse = APIResponse.<Student>builder()
                .message("Successfully!")
                .payload(studentService.getByIdStudent(id))
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/filter")
    public List<Student> filterStudentAge (@RequestParam Double start , @RequestParam Double end){
        return studentService.filterStudentAge(start,end);
    }

    @GetMapping("/filter/Student/name")
    public List<Student > filterStudentName (@RequestParam String name){
        return studentService.filterStudentName(name);
    }
}
