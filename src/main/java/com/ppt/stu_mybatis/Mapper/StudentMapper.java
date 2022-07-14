package com.ppt.stu_mybatis.Mapper;

import java.util.List;
import com.ppt.stu_mybatis.Model.StudentBean;
import org.apache.ibatis.annotations.*;

@Mapper
public interface StudentMapper {

@Insert("")
void addstudent(StudentBean sbean);

@Select("")
List<StudentBean> selectAll();

@Select("")
List<StudentBean> selectBySidOrSnameOrCname(String student_id, String student_name, String class_name);

@Delete("delete from student where student_id = #{studnet_id}")
void deleteStudent(String student_id);

@Select("")
StudentBean findStudnetById(String student_id);

@Select("")
List<StudentBean> findByStudentid(String student_id);
//findStudnetById
//findByStudentid(list)
}
