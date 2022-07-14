package com.ppt.stu_mybatis.Model;

import lombok.Data;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentBean {

    private String student_id;
    @NotEmpty(message = "Name cannot be blank!")
    private String student_name;
    @NotEmpty(message = "Date of birth cannot be blank!")
    private String dob;
    @NotEmpty(message = "Select your gender!")
    private String gender;
    @NotEmpty(message = "Enter your phone number!")
    private String phone;
    @NotEmpty(message = "Select your education!")
    private String education;
    @NotEmpty(message = "Select the course(s) you want to attend!")
    private List<String> attendCourses;
}
