package com.cakeshop.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.cakeshop.model.User;
import com.cakeshop.service.UserService;

/**
 * Servlet implementation class UserChangeAddressServlet
 */
@WebServlet("/user_change")
public class UserChangeAddressServlet extends HttpServlet {
	
	private UserService uService = new UserService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User loginUser = (User) request.getSession().getAttribute("user");
		try {
			BeanUtils.copyProperties(loginUser , request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		uService.updateUserAddress(loginUser);
		request.setAttribute("msg", "信息更改成功");
		request.getRequestDispatcher("/mycenter.jsp").forward(request, response);
		
	}
	

}
