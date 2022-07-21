package com.ppt.stu_mybatis;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ppt.stu_mybatis.Mapper.UserMapper;
import com.ppt.stu_mybatis.Model.CourseBean;
import com.ppt.stu_mybatis.Model.StudentBean;
import com.ppt.stu_mybatis.Model.UserBean;
import com.ppt.stu_mybatis.Service.ReportService;

import net.sf.jasperreports.engine.JRException;

@MappedTypes({UserBean.class, StudentBean.class, CourseBean.class})
@MapperScan("com.ppt.stu_mybatis.Mapper")
@SpringBootApplication
@RestController
public class StuMybatisApplication {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private ReportService reportService;

	@GetMapping("/getUser")
	public List<UserBean> getUser(){
		return userMapper.selectAll();
	}

	@GetMapping("/report/{format}")
	public void generateReport(@PathVariable String format,HttpServletResponse response ) throws JRException, IOException{
		// return
		 reportService.exportReport(format, response );
	}

	public static void main(String[] args) {
		SpringApplication.run(StuMybatisApplication.class, args);
	}

}
