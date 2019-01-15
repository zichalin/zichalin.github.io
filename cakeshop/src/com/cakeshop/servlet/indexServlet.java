package com.cakeshop.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cakeshop.service.GoodsService;

/**
 * Servlet implementation class indexServlet
 */
@WebServlet("/index")
public class indexServlet extends HttpServlet {
	
	private GoodsService gService = new GoodsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//取得条幅商品
		Map<String, Object> scrollMap = gService.getScrollGoodsMap();
		request.setAttribute("scroll", scrollMap);
		
		//取得热搜商品
		List<Map<String,Object>> hotList = gService.getHotGoodsList();
		request.setAttribute("hotList", hotList);
		
		//取得新上架商品
		List<Map<String,Object>> newList = gService.getNewGoodsList();
		request.setAttribute("newList", newList);
		
		//跳转到index.jsp
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		
	}

}
