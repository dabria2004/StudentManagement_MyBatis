package com.ppt.stu_mybatis.Model;

import lombok.Data;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentBean {
    private String student_id;
    private String student_name;
    private String dob;
    private String gender;
    private String phone;
    private String education;
    private List<String> attendCourses;
}
