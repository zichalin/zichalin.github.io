package com.cakeshop.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cakeshop.service.TypeService;

/**
 * Servlet implementation class AdminTypeDeleteServlet
 */
@WebServlet("/admin/type_delete")
public class AdminTypeDeleteServlet extends HttpServlet {
	
	TypeService tService = new TypeService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		tService.deleteType(id);
		request.setAttribute("msg", "删除类目成功！");
		request.getRequestDispatcher("/admin/type_list").forward(request, response);
	}

}
