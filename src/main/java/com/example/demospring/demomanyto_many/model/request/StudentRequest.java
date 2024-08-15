package com.example.demospring.demomanyto_many.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {
    private String stuName;
    private String gender;
    private Integer cardId;
    private Integer age;
}
