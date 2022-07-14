package com.ppt.stu_mybatis.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ppt.stu_mybatis.Mapper.CourseMapper;
import com.ppt.stu_mybatis.Model.CourseBean;

@Controller
public class CourseController {

    @Autowired
    CourseMapper courseMapper;

    @GetMapping(value = "/setupaddclass")
	public ModelAndView setupaddclass(ModelMap model, CourseBean cbean) {
		
        List<CourseBean> courseList = courseMapper.selectAll();
		if (courseList.size() == 0) {
			cbean.setClass_id("COU001");
		}else {
		int tempId = Integer.parseInt(courseList.get(courseList.size() - 1).getClass_id().substring(3)) + 1;
		String classid = String.format("COU%03d", tempId);
		cbean.setClass_id(classid);
		}
		return new ModelAndView("BUD003", "cbean", cbean);
	}
	
	@GetMapping(value = "/setupaddclassagain")
	public ModelAndView setupaddclassagain(ModelMap model) {
		
		CourseBean cbean = new CourseBean();
		List<CourseBean> courseList = courseMapper.selectAll();
		if (courseList.size() == 0) {
			cbean.setClass_id("COU001");
		}else {
		int tempId = Integer.parseInt(courseList.get(courseList.size() - 1).getClass_id().substring(3)) + 1;
		String classid = String.format("COU%03d", tempId);
		cbean.setClass_id(classid);
		}
		model.addAttribute("success", "Successfully Registered!!");
		return new ModelAndView("BUD003", "cbean", cbean);
	}
	
	@PostMapping(value = "/addclass")
	public String addclass(@ModelAttribute("cbean") @Validated CourseBean cbean, BindingResult bs, ModelMap model) {
		if(bs.hasErrors()) {
			model.addAttribute("error", "You must fullfill the fields!!");
			return "BUD003";
		}
		if(courseMapper.existsByClassName(cbean.getClass_name())){
            model.addAttribute("classname", "Class name already exists.");
            return "BUD003";
        }
         else {
			courseMapper.addCourse(cbean);
			return "redirect:/setupaddclassagain";
		}
	}
}
