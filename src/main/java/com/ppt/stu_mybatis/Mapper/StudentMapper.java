package com.ppt.stu_mybatis.Mapper;

import java.util.List;
import com.ppt.stu_mybatis.Model.StudentBean;
import org.apache.ibatis.annotations.*;

@Mapper
public interface StudentMapper {

    @Insert("insert into student (student_id, student_name, dob, gender, phone, education) values (#{student_id}, #{student_name}, #{dob}, #{gender}, #{phone}, #{education})")
    void addstudent(StudentBean sbean);

    @Insert("insert into selected_courses (student_id, class_id) values (#{student_id}, #{class_id})")
    void insertStudentCourse(String student_id, String class_id);

    @Select("select * from student")
    List<StudentBean> selectAll();

    @Select("select distinct student.student_id, student.student_name from selected_courses join student on selected_courses.student_id = student.student_id join class on selected_courses.class_id = class.class_id where student.student_id like #{student_id} or student.student_name like #{student_name} or class.class_name like #{class_name}")
    List<StudentBean> selectBySidOrSnameOrCname(String student_id, String student_name, String class_name);

    @Select("select * from student where student_id = #{student_id}")
    List<StudentBean> findByStudentid(String student_id);

    @Select("select * from student where student_id = #{student_id}")
    StudentBean findStudnetById(String student_id);

    @Update("update student set student_name = #{student_name}, dob = #{dob}, gender = #{gender}, phone = #{phone}, education = #{education} where student_id = #{student_id}")
    void updateStudent(StudentBean student);

    @Delete("delete from student where student_id = #{studnet_id}")
    void deleteStudent(String student_id);

    @Delete("delete from selected_courses where student_id = #{student_id}")
    void deleteAttendCoursesByStudentId(String student_id);
}
