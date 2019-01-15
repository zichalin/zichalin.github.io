package com.cakeshop.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cakeshop.model.User;
import com.cakeshop.service.UserService;

/**
 * Servlet implementation class UserChangePWD
 */
@WebServlet("/user_changePWD")
public class UserChangePWDServlet extends HttpServlet {

	UserService uService = new UserService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String password = request.getParameter("password");
		String newPassword = request.getParameter("newPassword");

		User user = (User) request.getSession().getAttribute("user");
		if (password.equals(user.getPassword())) {
			user.setPassword(newPassword);
			uService.changePassword(user);
			request.setAttribute("msg", "密码更改成功，请重新登陆！");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}else {
			request.setAttribute("msg", "密码更改失败，请确认输入旧密码正确！");
			request.getRequestDispatcher("/mycenter.jsp").forward(request, response);
		}
		
	}

}
