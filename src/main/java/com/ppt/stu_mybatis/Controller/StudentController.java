package com.ppt.stu_mybatis.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ppt.stu_mybatis.Mapper.CourseMapper;
import com.ppt.stu_mybatis.Mapper.StudentMapper;
import com.ppt.stu_mybatis.Model.CourseBean;
import com.ppt.stu_mybatis.Model.StudentBean;

@Controller
public class StudentController {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    CourseMapper courseMapper;

    @GetMapping(value = "/setupaddstudent")
	public ModelAndView setupaddstudent(ModelMap model) {
		List<CourseBean> courseList = courseMapper.selectAll();
		model.addAttribute("courseList", courseList);
		return new ModelAndView ("STU001", "sbean", new StudentBean());
	}
	
	@GetMapping(value = "/setupaddstudentagain")
	public ModelAndView setupaddstudentagain(ModelMap model) {
		
		List<CourseBean> courseList = courseMapper.selectAll();
		model.addAttribute("courseList", courseList);
		model.addAttribute("success", "Successfully Registered!!");
		return new ModelAndView ("STU001", "sbean", new StudentBean());
	}
	
	@PostMapping(value = "/addstudent")
	public String addstudent(@ModelAttribute("sbean") @Validated StudentBean sbean,BindingResult bs, ModelMap model) {
		
		List<StudentBean> studentList = studentMapper.selectAll();
		List<CourseBean> courseList = courseMapper.selectAll();
		model.addAttribute("courseList", courseList);
		System.out.println(studentList);
		// if (studentList == null) {
		// 	studentList = new ArrayList<>();
		// }
		if (studentList.size() == 0) {
			sbean.setStudent_id("STU001");
		} else {
			int tempId = Integer.parseInt(studentList.get(studentList.size() - 1).getStudent_id().substring(3)) + 1;
			String userId = String.format("STU%03d", tempId);
			sbean.setStudent_id(userId);
		}
        if(bs.hasErrors()) {
			System.out.println("Taw 123123123123thar");
			model.addAttribute("error", "You must fullfill the fields!!");
            model.addAttribute("data", sbean);
			return "STU001";
		}
        studentMapper.addstudent(sbean);
		String[] attendCourses = new String[sbean.getAttendCourses().size()];
		attendCourses = sbean.getAttendCourses().toArray(attendCourses);
		for (int i = 0; i < attendCourses.length; i++) {
			studentMapper.insertStudentCourse(sbean.getStudent_id(), attendCourses[i]);
		}
		return "redirect:/setupaddstudentagain";	
	}

    @GetMapping("/setupstudentsearch")
	public String studentManagement(ModelMap model) {
		List<StudentBean> studentList = studentMapper.selectAll();
		for(StudentBean student : studentList){
			List<String> clist = courseMapper.selectCidBySid(student.getStudent_id());
			student.setAttendCourses(clist);
		}
		model.addAttribute("studentList", studentList);
		return "STU003";
	}
	
	@GetMapping("/studentdetail")
	public ModelAndView seeMore(@RequestParam("id") String id, ModelMap model) {
        StudentBean student = studentMapper.findStudnetById(id);
        List<CourseBean> courseList = courseMapper.selectAll();
		List<CourseBean> couList = courseMapper.selectCoursesByStudentId(id);
		//List<StudentBean> student = studentMapper.findByStudentid(id);
		List<String> stuCourseList=new ArrayList<String>();
	 	for(CourseBean course: couList) {
	 		stuCourseList.add(course.getClass_id());
	 	}
		student.setAttendCourses(stuCourseList);
		model.addAttribute("courseList", courseList);
		model.addAttribute("data", student);
		return new ModelAndView ("STU002", "sbean", student);
	}

    @PostMapping("/updatestudent")
	public String updateStudent(@ModelAttribute("sbean") @Validated StudentBean sbean, BindingResult bs, ModelMap model) {
		
		List<CourseBean> courseList = courseMapper.selectAll();
		model.addAttribute("courseList", courseList);
		
		if(bs.hasErrors()) {
			model.addAttribute("data", sbean);
			model.addAttribute("error", "Fill the blank !!");
			return "STU002";
		}
        studentMapper.updateStudent(sbean);
		studentMapper.deleteAttendCoursesByStudentId(sbean.getStudent_id());
		String[] attendCourses = new String[sbean.getAttendCourses().size()];
		attendCourses = sbean.getAttendCourses().toArray(attendCourses);
		for (int i = 0; i < attendCourses.length; i++) {
			studentMapper.insertStudentCourse(sbean.getStudent_id(), attendCourses[i]);
		}
		return "redirect:/setupstudentsearch";
	}
	
	@GetMapping("/deleteStudent")
	public String deleteStudent(@RequestParam("id") String id) {
		studentMapper.deleteAttendCoursesByStudentId(id);
		studentMapper.deleteStudent(id);
		return "redirect:/setupstudentsearch";
	}

	@PostMapping("/searchstudent")
	public String searchStudent(@RequestParam("id") String id, @RequestParam("name") String name,
			@RequestParam("course") String course, ModelMap model) {
				
		String sid = id.isBlank() ? "%$&*" : "%" + id + "%";
		String sname = name.isBlank() ? "%$&*" : "%" + name + "%";
		String scourse = course.isBlank() ? "%$&*" : "%" + course + "%";
		List<StudentBean> studentList = studentMapper.selectBySidOrSnameOrCname(sid, sname, scourse);
		for (StudentBean student : studentList) {
			List<String> clist = courseMapper.selectCidBySid(student.getStudent_id());
			student.setAttendCourses(clist);
		}
		if (studentList.size() == 0) {
			studentList = studentMapper.selectAll();
		for (StudentBean student : studentList) {
			List<String> clist = courseMapper.selectCidBySid(student.getStudent_id());
			student.setAttendCourses(clist);
		}
		model.addAttribute("studentList", studentList);
 			return "STU003";
	}
		model.addAttribute("studentList", studentList);
			return "STU003";	
	}
}

// String sid = id.isBlank() ? ")#<>(}" : id;
// 		String sname = name.isBlank() ? ")#<>(}" : name;
// 		String scourse = course.isBlank() ? ")#<>(}" : course;
// 		System.out.println( "sid => " + sid + " " + "sname => " + sname + " " + "scourse => " + scourse);
// 		List<StudentResponseDTO> studentList = studentDao.selectStudentListByIdOrNameOrCourse(sid, sname, scourse);
// 		System.out.println("first studentList => " + studentList);
// 		for (StudentResponseDTO student : studentList) {
// 			List<String> clist = classDao.selectCidByStuid(student.getStudentid());
// 			student.setAttendCourses(clist);
// 		}
// 		if (studentList.size() == 0) {
// 			studentList = studentDao.selectAll();
// 			System.out.println("second studentList => " + studentList);
// 			for (StudentResponseDTO student : studentList) {
// 				System.out.println(student);
// 				List<String> clist = classDao.selectCidByStuid(student.getStudentid());
// 				student.setAttendCourses(clist);
// 				System.out.println("---------------------------");
// 			}
// 			model.addAttribute("studentList", studentList);
// 			return "STU003";		
// 		}
// 		model.addAttribute("studentList", studentList);
// 		return "STU003";	
