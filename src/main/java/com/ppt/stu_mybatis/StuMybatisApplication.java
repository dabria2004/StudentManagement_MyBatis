package com.ppt.stu_mybatis;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ppt.stu_mybatis.Model.CourseBean;
import com.ppt.stu_mybatis.Model.StudentBean;
import com.ppt.stu_mybatis.Model.UserBean;

@MappedTypes({UserBean.class, StudentBean.class, CourseBean.class})
@MapperScan("com.ppt.stu_mybatis.Mapper")
@SpringBootApplication
public class StuMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(StuMybatisApplication.class, args);
	}

}
