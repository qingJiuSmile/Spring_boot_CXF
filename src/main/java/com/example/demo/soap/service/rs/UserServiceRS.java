package com.example.demo.soap.service.rs;

/**
 * Created by mabo-pc on 2017/7/8.
 */

import com.example.demo.soap.vo.User;

import javax.ws.rs.*;

@Path(value = "/")
public interface UserServiceRS {
	@GET
	@Path(value = "/{id}/info")
	@Produces({"application/json","application/xml"})
	User findUserById(@PathParam("id") String id);

	@GET
	@Path(value = "/search")
	@Produces({"application/json","application/xml"})
    Object findUserByName(@QueryParam("name") String name);
}
