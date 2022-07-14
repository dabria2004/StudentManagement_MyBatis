package com.ppt.stu_mybatis.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ppt.stu_mybatis.Mapper.UserMapper;
import com.ppt.stu_mybatis.Model.UserBean;

@Controller
public class LoginController {

    @Autowired
    UserMapper userMapper;

    @GetMapping(value="/menu")
	public String menu() {
	return "MNU001";
	}
	
	@GetMapping(value="/")
	public String login() {
	return "LGN001";
	}
	
	@PostMapping(value = "/welcomepage")
	public String finalexampage(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session,ModelMap model) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String currentDate = formatter.format(date);
		if(userMapper.existsByEmailAndPassword(email, password)) {
			UserBean res = userMapper.selectUserByEmail(email);
			session.setAttribute("userInfo", res);
			session.setAttribute("date", currentDate);
			return "MNU001";
		}else {
			model.addAttribute("error","Invalid user email or password!");
			return "LGN001";
		}
	}

    @GetMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("userInfo");
		return "redirect:/";
	}
}
