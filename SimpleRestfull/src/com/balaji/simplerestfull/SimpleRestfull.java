package com.balaji.simplerestfull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.balaji.dao.AadharUserDAO;
import com.balaji.pojos.AadharUser;

@Path("/simplerest")
public class SimpleRestfull {

	AadharUserDAO userDao = new AadharUserDAO();
	private static final String SUCCESS_RESULT = "<result>success</result>";
	private static final String FAILURE_RESULT = "<result>failure</result>";

	@GET
	@Produces("application/json")
	public Response getMessage() {
		String msg = "Hello World";
		return Response.status(200).entity(msg).build();
	}

	@GET
	@Path("/users")
	@Produces({ MediaType.APPLICATION_ATOM_XML })
	public List<AadharUser> getAllUsers() {

		return userDao.getAllUsers();

	}

	@GET
	@Path("/users/{userid}")
	@Produces(MediaType.APPLICATION_XML)
	public AadharUser getUser(@PathParam("userid") String userid) {
		return userDao.getUser(userid);
	}

	@PUT
	@Path("/users")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createUser(@FormParam("udid") String udid, @FormParam("mobile") String mobile,
			@Context HttpServletResponse servletResponse) throws IOException {
		System.out.println("Hit came in put");
		AadharUser user = new AadharUser(udid, mobile);
		int result = userDao.addUser(user);
		if (result == 1) {
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}

	@POST
	@Path("/users")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateUser(@FormParam("udid") String udid, @FormParam("mobile") String mobile,
			@Context HttpServletResponse servletResponse) throws IOException {
		AadharUser user = new AadharUser(udid, mobile);
		int result = userDao.updateUser(user);
		if (result == 1) {
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}

	@DELETE
	@Path("/users/{udid}")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String deleteUser(@PathParam("udid") String udid) {
		int result = userDao.deleteUser(udid);
		if (result == 1) {
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}

}
