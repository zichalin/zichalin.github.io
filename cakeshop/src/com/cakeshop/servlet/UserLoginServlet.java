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
 * Servlet implementation class UserLginServlet
 */
@WebServlet("/user_login")
public class UserLoginServlet extends HttpServlet {
	
	private UserService uService = new UserService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ue = request.getParameter("ue");
		String password = request.getParameter("password");
		
		User user = uService.login(ue, password);
		if (user == null) {
			request.setAttribute("fmsg", "登陆失败，请重新登陆！");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}else {
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("/mycenter.jsp").forward(request, response);
		}
		
	}

}
