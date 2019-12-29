package com.example.demo.soap.service.ws;

import com.example.demo.soap.vo.User;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.util.Calendar;

@WebService(serviceName = "CustomerServiceWS",
		targetNamespace = "http://webservice.ws.mabo.com/",
		endpointInterface = "com.example.demo.soap.service.ws.UserServiceWS"
)
@Component
public class UserServiceImplWS implements UserServiceWS {

	@Override
	public User findUserById(String id) {
		User user = new User();
		user.setId(id);
		user.setName(id);
		return user;
	}
	@Override
	public User findUserByName(String name) {
		User user = new User();
		user.setId(name);
		user.setName(name);
		return user;
	}
}