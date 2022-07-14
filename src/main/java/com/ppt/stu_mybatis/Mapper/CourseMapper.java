package com.ppt.stu_mybatis.Mapper;

import java.util.List;
import com.ppt.stu_mybatis.Model.CourseBean;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CourseMapper {

@Insert("insert into class(class_id,class_name) values(#{class_id}, #{class_name})")
void addCourse(CourseBean cBean);

@Select("select * from class")
List<CourseBean> selectAll();

@Select("SELECT EXISTS(SELECT 1 FROM class WHERE class_name=#{class_name})")
Boolean existsByClassName(String className);

@Select("select class.class_name, class.class_id from selected_courses join class on selected_courses.class_id = class.class_id where selected_courses.student_id = #{student_id}")
List<CourseBean> selectCoursesByStudentId(String student_id);

@Select("select class.class_name, class.class_id from selected_courses join class  on selected_courses.class_id = class.class_id where selected_courses.student_id = #{student_id}")
List<String> selectCidBySid(String student_id);
}
