package com.cakeshop.servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cakeshop.model.Page;
import com.cakeshop.service.GoodsService;

/**
 * Servlet implementation class GoodsSearchServlet
 */
@WebServlet("/goods_search")
public class GoodsSearchServlet extends HttpServlet {

	private GoodsService gService = new GoodsService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("text/html;UTF-8");
		String keyword = new String(request.getParameter("keyword").getBytes("ISO-8859-1"), "UTF-8");
		//String keyword = request.getParameter("keyword");
		System.out.println(keyword);
		int pageNumber = 1;
		if(request.getParameter("pageNumber") != null) {
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		}
		
		Page page = gService.getSearchGoodsPage(keyword, pageNumber);
		request.setAttribute("page", page);
		request.setAttribute("keyword", keyword);
		request.getRequestDispatcher("/goods_search.jsp").forward(request, response);
		}
//	
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		response.setCharacterEncoding("UTF-8");
//		String keyword = request.getParameter("keyword");
//		System.out.println(keyword);
//		int pageNumber = 1;
//		if(request.getParameter("pageNumber") != null) {
//			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
//		}
//		
//		Page page = gService.getSearchGoodsPage(keyword, pageNumber);
//		request.setAttribute("page", page);
//		request.setAttribute("keyword", URLEncoder.encode(keyword, "UTF-8"));
//		request.getRequestDispatcher("/goods_search.jsp").forward(request, response);
//	}

}
