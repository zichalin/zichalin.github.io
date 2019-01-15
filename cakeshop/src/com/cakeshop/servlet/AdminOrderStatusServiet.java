package com.cakeshop.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cakeshop.service.OrderService;

/**
 * Servlet implementation class AdminOrderStatusServiet
 */
@WebServlet("/admin/order_status")
public class AdminOrderStatusServiet extends HttpServlet {
	
	private OrderService oService = new OrderService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int status = Integer.parseInt(request.getParameter("status"));
		oService.updateStatus(id, status);
		response.sendRedirect(request.getContextPath()+"/admin/order_list?status="+status);
	}

}
