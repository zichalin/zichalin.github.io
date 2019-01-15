package com.cakeshop.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cakeshop.service.GoodsService;

/**
 * Servlet implementation class AdminGoodsDeleteServlet
 */
@WebServlet("/admin/goods_delete")
public class AdminGoodsDeleteServlet extends HttpServlet {
	
	GoodsService gService = new GoodsService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		int type = Integer.parseInt(request.getParameter("type"));
		gService.deleteGoods(id);
		request.getRequestDispatcher("/admin/goods_list?pageNumber="+pageNumber+"&type="+type).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
