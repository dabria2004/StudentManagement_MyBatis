package com.ppt.stu_mybatis.Model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CourseBean {

    private String class_id;
    @NotEmpty(message = "Class name cannot be blank!")
    private String class_name;
}
