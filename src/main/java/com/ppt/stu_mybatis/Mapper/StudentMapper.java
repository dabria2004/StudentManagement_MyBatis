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

// public int insertStudentCourse(String course_id, String student_id) {
//     int result = 0;
//     String sql = "insert into selected_courses (student_id, class_id) values(?, ?)";

// public int updateData(StudentRequestDTO dto) {
//     int result = 0;
//     String sql = "update student set student_name=?, dob=?, gender=?, phone=?, education=? where student_id=?";

// public List<StudentResponseDTO> selectStudentListByIdOrNameOrCourse(String id, String name, String course) {	
//     String sql = "select distinct student.student_id, student.student_name "
//             + "from selected_courses join student "
//             + "on selected_courses.student_id = student.student_id join class "
//             + "on selected_courses.class_id = class.class_id "
//             + "where student.student_id like ? or student.student_name like ? or class.class_name like ?";

// public int deleteAttendCoursesByStudentId(String student_id) {
//     int result = 0;
//     String sql = "delete from selected_courses where student_id=?";

// public List<String> selectCidByStuid(String studentid) {
//     String sql = "select class.class_name, class.class_id from selected_courses join class "
//             + "on selected_courses.class_id = class.class_id where selected_courses.student_id = ? ";

// public List<ClassResponseDTO> selectCoursesByStudentId(String studentid) {
//     String sql = "select class.class_name, class.class_id from selected_courses join class "
//             + "on selected_courses.class_id = class.class_id where selected_courses.student_id = ? ";