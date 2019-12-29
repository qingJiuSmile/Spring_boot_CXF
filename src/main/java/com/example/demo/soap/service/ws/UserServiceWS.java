package com.example.demo.soap.service.ws;


import com.example.demo.soap.vo.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "UserServiceWS", targetNamespace = "http://webservice.ws.mabo.com/")
public interface UserServiceWS {
	@WebMethod
	User findUserById(@WebParam(name = "id") String id);

	@WebMethod
	User findUserByName(@WebParam(name = "name") String name);
}
