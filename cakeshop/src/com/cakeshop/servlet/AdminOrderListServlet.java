package com.cakeshop.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cakeshop.model.Page;
import com.cakeshop.service.OrderService;

/**
 * Servlet implementation class AdminOrderListServlet
 */
@WebServlet("/admin/order_list")
public class AdminOrderListServlet extends HttpServlet {
	
	private OrderService oService = new OrderService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int status = 0;
		if(request.getParameter("status") != null) {
			status = Integer.parseInt(request.getParameter("status"));
		}
		
		int pageNumber = 1;
		if(request.getParameter("pageNumber") != null) {
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		}
		Page page = oService.getOrderPage(status, pageNumber);
		
		request.setAttribute("status", status);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/admin/order_list.jsp").forward(request, response);
		
	}

}
