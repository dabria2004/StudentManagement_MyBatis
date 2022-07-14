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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ppt.stu_mybatis.Mapper.UserMapper;
import com.ppt.stu_mybatis.Model.UserBean;

@Controller
public class UserController {

    @Autowired
    UserMapper userMapper;

    @GetMapping(value="/setupadduser")
	public ModelAndView setupadduser() {
		return new ModelAndView("USR001","user",new UserBean());
	}

    @GetMapping(value="/setupadduseragain")
	public ModelAndView setupadduseragain(ModelMap model) {
		model.addAttribute("success", "Succesfully Registered!!");
		return new ModelAndView("USR001","user",new UserBean());
	}

    @PostMapping(value="/adduser")
	public String adduser(@ModelAttribute("user") @Validated UserBean user,BindingResult bs, ModelMap model) {
		if(bs.hasErrors()) {
			model.addAttribute("error", "You must fullfill the fields.");
			return "USR001";
		}
		if(userMapper.existsByEmail(user.getUser_email())){
			model.addAttribute("email", "Email already exists!!");
			return "USR001";
			}
		if(!user.getUser_password().equals(user.getUser_conpassword())) {
			model.addAttribute("password", "Passwords do not match!!");
			return "USR001";
		}else {
			List<UserBean> userlist = userMapper.selectAll();
			if(userlist.size() == 0 ) {
				user.setUser_id("USR001");
			}else {
				int tempId = Integer.parseInt(userlist.get(userlist.size() - 1).getUser_id().substring(3)) + 1;
				String userId = String.format("USR%03d", tempId);
				user.setUser_id(userId);
			}
			userMapper.insertUser(user);
			return "redirect:/setupadduseragain";
		}			
	}

    @GetMapping(value="/setupusersearch")
	public String usersearchpage(ModelMap model) { 
		List<UserBean> userlist = userMapper.selectAll();
		model.addAttribute("searchInfo", userlist);
		System.out.println("ShowAllUsers =>" + userlist.toString()); 
		return "USR003";
	}

	@PostMapping(value="/usersearch")
	public String usersearch(@RequestParam("userid") String userid,@RequestParam("username") String username,ModelMap model) {
		String id = userid.isBlank() ? "#&^@)" : "%" + userid + "%";
		String name = username.isBlank() ? "#&^@)" : "%" + username + "%";
		List<UserBean> searchList = null;
		searchList = userMapper.selectUserListByIdOrName(id, name);
		if(searchList.size() == 0 ){
			searchList = userMapper.selectAll();
		}
		model.addAttribute("searchInfo", searchList);
		return "USR003";
	}

    @GetMapping(value="/setupUpdateUser")
	public ModelAndView setupUpdateUser(@RequestParam("id") String userid) {
		return new ModelAndView("USR002","user", userMapper.selectUserById(userid));
	}
	
	@PostMapping(value="/updateuser")
	public String updateuser(@ModelAttribute("user") @Validated UserBean user,BindingResult bs, ModelMap model) {
		
		UserBean userId = userMapper.selectUserById(user.getUser_id());
		// String userEmail = userId.getEmail();
		// String uemail = user.getEmail();
		if(bs.hasErrors()) {
			model.addAttribute("error", "You must fullfill the fields.");
			return "USR002";
		}
		if(!user.getUser_password().equals(user.getUser_conpassword())) {
			model.addAttribute("password", "Passwords do not match!!");
			return "USR002";
		}else if(userMapper.existsByEmail(user.getUser_email()) && !userId.getUser_email().equals(user.getUser_email())){
			model.addAttribute("error", "Email already exists!!");
			return "USR002";
		}
		else {
		    user = new UserBean(user.getUser_id(), user.getUser_name(), user.getUser_email(), user.getUser_password(), user.getUser_conpassword(), user.getUser_role());
			userMapper.insertUser(user);
            return "redirect:/setupusersearch";
		}
	}
	
	@GetMapping(value="/deleteuser")
	public String deleteuser(@RequestParam("id") String userid) {
		userMapper.deleteUser(userid);
		return "redirect:/setupusersearch";
	}
}
