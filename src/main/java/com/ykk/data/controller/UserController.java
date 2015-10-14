package com.ykk.data.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ykk.data.persistence.model.User;
import com.ykk.data.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	//http://localhost:8080/NetValue/user/check.do?name=name1&password=123456
	@RequestMapping(value = "check", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> check(User user) {
		return userService.checkUser(user);
	}

}
