package com.ykk.data.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ykk.data.persistence.dao.UserMapper;
import com.ykk.data.persistence.model.User;
import com.ykk.data.util.Md5Util;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	/**
	 * 验证输入的user实体是否符合
	 * 
	 * @param user
	 * @return
	 */
	public Map<String, Object> checkUser(User user) {
		Map<String, Object> map = new HashMap<String, Object>();

		// 传入的password，并转为Md5格式
		String password = user.getPassword();
		String passwordMd5 = Md5Util.EncoderByMd5(password);

		// 数据库中的用户实体
		User dbUser = userMapper.selectByName(user.getName());

		if (dbUser != null) {
			if (passwordMd5.equals(dbUser.getPassword())) {
				map.put("status", 1);
				map.put("message", "核实正确");
			} else {
				map.put("status", 0);
				map.put("errorMessage", "用户名或密码错误，请核实");
			}
		} else {
			map.put("status", 0);
			map.put("errorMessage", "用户名或密码错误，请核实");
		}

		return map;
	}

	public User selectByPrimaryKey(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}
}
