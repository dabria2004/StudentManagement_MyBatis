package com.ppt.stu_mybatis.Mapper;

import java.util.List;
import com.ppt.stu_mybatis.Model.CourseBean;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CourseMapper {
    // @Insert("insert into user (user_id, user_name, user_email, user_password, user_conpassword, user_role) values (#{user_id}, #{user_name}, #{user_email}, #{user_password}, #{user_conpassword}, #{user_role})")
@Insert("insert into class(class_id,class_name) values(#{class_id}, #{class_name})")
void addCourse(CourseBean cBean);

@Select("select * from class")
List<CourseBean> selectAll();

@Select("select * form class where class_name = #{class_name}")
Boolean existsByClassName(String className);
}

// public List<ClassResponseDTO> selectCoursesByStudentId(String studentid) {
//     String sql = "select class.class_name, class.class_id from selected_courses join class "
//             + "on selected_courses.class_id = class.class_id where selected_courses.student_id = ? ";

// List<String> selectCidByStuid(String studentid) {
//     String sql = "select class.class_name, class.class_id from selected_courses join class "
//             + "on selected_courses.class_id = class.class_id where selected_courses.student_id = ? ";
