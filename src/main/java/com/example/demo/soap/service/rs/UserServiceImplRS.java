package com.example.demo.soap.service.rs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.soap.vo.User;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Service;
@Api("/UserServiceImplRS")
@Service
public class UserServiceImplRS implements UserServiceRS {

	@Override
	public User findUserById(String id) {
		User user = new User();
		user.setId(id);
		user.setName(id);
		return user;
	}
	@Override
	public Object findUserByName(String name) {
		User user = new User();
		user.setId(name);
		user.setName(name);
		return JSONObject.toJSONString(user);
	}
}