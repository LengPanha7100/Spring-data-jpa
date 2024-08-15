package com.example.demospring.demomanyto_many.controller;

import com.example.demospring.demomanyto_many.model.APIResponse;
import com.example.demospring.demomanyto_many.model.StudentCourse;
import com.example.demospring.demomanyto_many.model.request.StudentCourseRequest;
import com.example.demospring.demomanyto_many.service.StudentCourseService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/studentCourse")
public class StudentCourseController {
    private StudentCourseService studentCourseService;

    @PostMapping
    public ResponseEntity<APIResponse<StudentCourse>> createStuCourse(@RequestBody StudentCourseRequest studentCourseRequest){
        APIResponse<StudentCourse> apiResponse = APIResponse.<StudentCourse>builder()
                .message("Successfully!")
                .payload(studentCourseService.createStuCourse(studentCourseRequest))
                .status(HttpStatus.CREATED)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<StudentCourse>>> getAllStudentCourse (@RequestParam int page ,
                                                                                 @RequestParam int size, @RequestParam String sortBy,
                                                                                 @RequestParam Sort.Direction orderBy){
        APIResponse<List<StudentCourse>> apiResponse = APIResponse.<List<StudentCourse>>builder()
                .message("Successfully!")
                .payload(studentCourseService.getAllStudentCourse(page, size, sortBy, orderBy))
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<StudentCourse>> getByIdStudentCourse (@PathVariable Integer id){
        APIResponse<StudentCourse> apiResponse = APIResponse.<StudentCourse>builder()
                .message("Successfully!")
                .payload(studentCourseService.getByIdStudentCourse(id))
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<StudentCourse>> updateStudentCourse (@RequestBody StudentCourseRequest studentCourseRequest,@PathVariable Integer id){
        APIResponse<StudentCourse> apiResponse = APIResponse.<StudentCourse>builder()
                .message("Successfully!")
                .payload(studentCourseService.updateStudentCourse(studentCourseRequest,id))
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<StudentCourse>> deleteStudentCourse (@PathVariable Integer id){
        APIResponse<StudentCourse> apiResponse = APIResponse.<StudentCourse>builder()
                .message("Successfully!")
                .payload(studentCourseService.deleteStudentCourse(id))
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/StudentCourse/student/name")
    public ResponseEntity<?> findStudentByI(@RequestParam String name){
        List<StudentCourse> studentCourses = studentCourseService.findStudentByName(name);
        return ResponseEntity.ok(studentCourses);
    }

}
