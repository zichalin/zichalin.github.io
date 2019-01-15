package com.cakeshop.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cakeshop.model.Order;
import com.cakeshop.model.User;
import com.cakeshop.service.OrderService;

/**
 * Servlet implementation class OrderLIstServlet
 */
@WebServlet("/order_list")
public class OrderListServlet extends HttpServlet {
	
	private OrderService oService = new OrderService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("user");
		System.out.println(user);
		List<Order> oList = oService.selectOrder(user.getId());
		request.setAttribute("orderList", oList);
		request.getRequestDispatcher("/order_list.jsp").forward(request, response);
	}

}
