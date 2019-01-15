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
 * Servlet implementation class AdminUserEditShowServlet
 */
@WebServlet("/admin/user_editshow")
public class AdminUserEditShowServlet extends HttpServlet {
	
	UserService uService = new UserService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		User u = uService.selectById(id);
		request.setAttribute("u", u);
		request.getRequestDispatcher("/admin/user_edit.jsp").forward(request, response);
	}

}
