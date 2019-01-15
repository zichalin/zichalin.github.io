package com.cakeshop.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cakeshop.model.Goods;
import com.cakeshop.model.Page;
import com.cakeshop.model.Type;
import com.cakeshop.service.GoodsService;
import com.cakeshop.service.TypeService;

/**
 * Servlet implementation class GoodsListServlet
 */
@WebServlet("/goods_list")
public class GoodsListServlet extends HttpServlet {

	private GoodsService gService = new GoodsService();
	private TypeService tService = new TypeService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = 0;
		if(request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
		}
		
		int pageNumber = 1;
		if(request.getParameter("pageNumber") != null) {
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		}
		
//		List<Goods> list = gService.selectGoods(id, 1, 8);
//		request.setAttribute("list", list);
		
		Page page = gService.getGoodsPage(id, pageNumber);
		request.setAttribute("page", page);
		request.setAttribute("id", id);
		
		Type type = null;
		if (id != 0) {
			type = tService.selecType(id);
		}
		request.setAttribute("t", type);
		
		request.getRequestDispatcher("/goodslist.jsp").forward(request, response);
		
	}

}
