package com.cakeshop.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cakeshop.model.Goods;
import com.cakeshop.service.GoodsService;

/**
 * Servlet implementation class GoodsDetailServlet
 */
@WebServlet("/goods_detail")
public class GoodsDetailServlet extends HttpServlet {

	private GoodsService gService = new GoodsService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		Goods goods = gService.getGoodsById(id);
		request.setAttribute("goods", goods);
		
		request.getRequestDispatcher("/detail.jsp").forward(request, response);
		
	}

}
