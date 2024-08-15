package com.example.demospring.demomanyto_many.service.serviceImp;

import com.example.demospring.demomanyto_many.exception.CustomNotfoundException;
import com.example.demospring.demomanyto_many.model.Course;
import com.example.demospring.demomanyto_many.model.Student;
import com.example.demospring.demomanyto_many.model.StudentCourse;
import com.example.demospring.demomanyto_many.model.request.StudentCourseRequest;
import com.example.demospring.demomanyto_many.repository.StudentCourseRepository;
import com.example.demospring.demomanyto_many.service.CourseService;
import com.example.demospring.demomanyto_many.service.StudentCourseService;
import com.example.demospring.demomanyto_many.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentCourseServiceImp implements StudentCourseService {

    private final StudentCourseRepository studentCourseRepository;
    private final StudentService studentService;
    private final CourseService courseService;

    public StudentCourseServiceImp(StudentCourseRepository studentCourseRepository, StudentService studentService, CourseService courseService) {
        this.studentCourseRepository = studentCourseRepository;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @Override
    public StudentCourse createStuCourse(StudentCourseRequest studentCourseRequest) {
        Optional<Student> student = Optional.ofNullable(studentService.getByIdStudent(studentCourseRequest.getStudentId()));
        if(!student.isPresent()){
            throw new CustomNotfoundException("Student id is not found");
        }
        Optional<Course> course = Optional.ofNullable(courseService.getByIdCourse(studentCourseRequest.getCourseId()));
        if(!course.isPresent()){
            throw new CustomNotfoundException("Course id is not found");
        }
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setEnrollmentDate(studentCourseRequest.getEnrollmentDate());
        studentCourse.setStudent(student.get());
        studentCourse.setCourse(course.get());
        studentCourseRepository.save(studentCourse);
        return studentCourse;
    }

    @Override
    public List<StudentCourse> getAllStudentCourse(int page, int size, String sortBy, Sort.Direction orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy, sortBy)) ;
        Page<StudentCourse> studentCourses = studentCourseRepository.findAll(pageable);
        return studentCourses.getContent();
    }

    @Override
    public StudentCourse getByIdStudentCourse(Integer id) {
        return studentCourseRepository.findById(id).orElseThrow(
                () -> new CustomNotfoundException("Not Found")
        );
    }

    @Override
    public StudentCourse updateStudentCourse(StudentCourseRequest studentCourseRequest, Integer id) {
        Optional<Student> student = Optional.ofNullable(studentService.getByIdStudent(studentCourseRequest.getStudentId()));
        if(!student.isPresent()){
            throw new CustomNotfoundException("Not Found");
        }
        Optional<Course> course = Optional.ofNullable(courseService.getByIdCourse(studentCourseRequest.getCourseId()));
        if(!course.isPresent()){
            throw new CustomNotfoundException("Not Found");
        }
        StudentCourse studentCourse = studentCourseRepository.findById(id).orElseThrow(
                () -> new CustomNotfoundException("Not Found")
        );
        StudentCourse studentCourse1 = StudentCourse.builder()
                .enrollmentDate(studentCourseRequest.getEnrollmentDate())
                .student(student.get())
                .course(course.get())
                .build();
//        studentCourse.setEnrollmentDate(studentCourseRequest.getEnrollmentDate());
//        studentCourse.setStudent(student.get());
//        studentCourse.getCourse(course.get());
        return studentCourseRepository.save(studentCourse1);
    }

    @Override
    public StudentCourse deleteStudentCourse(Integer id) {
        StudentCourse studentCourse = studentCourseRepository.findById(id).orElseThrow(
                () -> new CustomNotfoundException("Not Found")
        );
        studentCourseRepository.deleteById(id);
        return studentCourse;
    }

    @Override
    public List<StudentCourse> findStudentByName(String name) {
        return null;
    }

}
