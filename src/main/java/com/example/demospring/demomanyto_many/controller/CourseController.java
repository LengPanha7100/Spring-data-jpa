package com.example.demospring.demomanyto_many.controller;

import com.example.demospring.demomanyto_many.model.APIResponse;
import com.example.demospring.demomanyto_many.model.Course;
import com.example.demospring.demomanyto_many.model.request.CourseRequest;
import com.example.demospring.demomanyto_many.repository.CourseRepository;
import com.example.demospring.demomanyto_many.service.CourseService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {
    private final CourseService courseService;
    private final CourseRepository courseRepository;

    public CourseController(CourseService courseService,
                            CourseRepository courseRepository) {
        this.courseService = courseService;
        this.courseRepository = courseRepository;
    }

    @PostMapping
    public ResponseEntity<APIResponse<Course>> createCourse(@RequestBody CourseRequest courseRequest){
        APIResponse<Course> apiResponse = APIResponse.<Course>builder()
                .message("Successfully")
                .payload(courseService.createCourse(courseRequest))
                .status(HttpStatus.CREATED)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Course>> updateCourse(@RequestBody CourseRequest courseRequest,@PathVariable Integer id){
        APIResponse<Course> apiResponse = APIResponse.<Course>builder()
                .message("Successfully")
                .payload(courseService.updateCourse(courseRequest,id))
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Course>>> getAllCourse(int page, int size , String sortBy , Sort.Direction orderBy){
        APIResponse<List<Course>> apiResponse = APIResponse.<List<Course>>builder()
                .message("Successfully")
                .payload(courseService.getAllCource())
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Course>> getByIdCourse (@PathVariable Integer id){
        APIResponse<Course> apiResponse = APIResponse.<Course>builder()
                .message("Successfully")
                .payload(courseService.getByIdCourse(id))
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping
    public ResponseEntity<APIResponse<Course>> deleteCourse (@PathVariable Integer id){
        APIResponse<Course> apiResponse = APIResponse.<Course>builder()
                .message("Successfully")
                .payload(courseService.deleteCourse(id))
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
