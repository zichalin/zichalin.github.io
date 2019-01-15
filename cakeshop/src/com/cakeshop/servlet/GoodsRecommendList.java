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
 * Servlet implementation class GoodsRecommendList
 */
@WebServlet("/goodsrecommend_list")
public class GoodsRecommendList extends HttpServlet {
	
	private GoodsService gService = new GoodsService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int type = Integer.parseInt(request.getParameter("type"));
		int pageNumber = 1;
		if (request.getParameter("pageNumber") != null) {
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		}
		
		Page page = gService.getGoodsRecommendPage(type, pageNumber);
		request.setAttribute("page", page);
		request.setAttribute("type", type);
		request.getRequestDispatcher("/goodsrecommendlist.jsp").forward(request, response);
	}

}
