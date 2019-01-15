package com.cakeshop.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cakeshop.model.Page;
import com.cakeshop.service.GoodsService;

/**
 * Servlet implementation class AdminGoodsListServlet
 */
@WebServlet("/admin/goods_list")
public class AdminGoodsListServlet extends HttpServlet {

	private GoodsService gService = new GoodsService();

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int type = 0;       //推荐类型
		if(request.getParameter("type") != null) {
			type = Integer.parseInt(request.getParameter("type"));
		}
		
		int pageNumber = 1;
		if(request.getParameter("pageNumber") != null) {
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		}
		
		Page page = gService.getGoodsRecommendPage(type, pageNumber);
		request.setAttribute("page", page);
		request.setAttribute("type", type);
		request.getRequestDispatcher("/admin/goods_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
