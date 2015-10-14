package com.ykk.data.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ykk.data.persistence.model.User;
import com.ykk.data.service.ProductNetValueService;
import com.ykk.data.service.UserService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductNetValueService productNetValueService;
	@Autowired
	private UserService userService;

	//http://localhost:8080/NetValue/product/insert.do?productId=1&type=1&value=1.2&dateTime=2015-2-2&name=name1&password=123456
	@RequestMapping(value = "insert", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> insert(
			@RequestParam("productId") Integer productId,
			@RequestParam("type") Integer type,
			@RequestParam("value") BigDecimal value,
			@DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam("dateTime") Date dateTime,
			@RequestParam("name") String name,
			@RequestParam("password") String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		Map<String, Object> map2 = userService.checkUser(user);
		if (map2.get("status").toString().equals("1")) {

			try {
				map = productNetValueService.insert(productId, type, value,
						dateTime, name);
			} catch (Exception ex) {
				ex.printStackTrace();
				map.put("status", 0);
				map.put("errorMessage", "插入失败，请联系管理员");
			}

		} else {
			map = map2;
		}
		return map;
	}
}
