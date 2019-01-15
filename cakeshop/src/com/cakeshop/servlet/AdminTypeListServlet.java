package com.cakeshop.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cakeshop.model.Type;
import com.cakeshop.service.TypeService;

/**
 * Servlet implementation class AdminTypeListServlet
 */
@WebServlet("/admin/type_list")
public class AdminTypeListServlet extends HttpServlet {
	
	private TypeService tService = new TypeService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Type> list = tService.selectAll();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/admin/type_list.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
