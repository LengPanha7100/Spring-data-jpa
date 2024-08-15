package com.example.demospring.demomanyto_many.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourseRequest {
    private Date enrollmentDate;
    private Integer studentId;
    private Integer courseId;
}
