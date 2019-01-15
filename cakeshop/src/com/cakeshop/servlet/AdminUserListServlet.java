package com.cakeshop.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cakeshop.model.Page;
import com.cakeshop.service.OrderService;
import com.cakeshop.service.UserService;

/**
 * Servlet implementation class AdminUserListServlet
 */
@WebServlet("/admin/user_list")
public class AdminUserListServlet extends HttpServlet {
	
	private UserService uService = new UserService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNumber = 1;
		if(request.getParameter("pageNumber") != null) {
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		}
		
		Page page = uService.getUserPage(pageNumber);
		
		request.setAttribute("page", page);
		request.getRequestDispatcher("/admin/user_list.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
