package com.cakeshop.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cakeshop.model.Goods;
import com.cakeshop.model.Order;
import com.cakeshop.service.GoodsService;

/**
 * Servlet implementation class GoodsByServlet
 */
@WebServlet("/goods_buy")
public class GoodsBuyServlet extends HttpServlet {

	private GoodsService gService = new GoodsService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		int goodsid = Integer.parseInt(request.getParameter("goodsid"));
//		System.out.println(goodsid);
		Order o = null;
		if(request.getSession().getAttribute("order") != null) {
			o = (Order) request.getSession().getAttribute("order");
		}else {
			o = new Order();
			request.getSession().setAttribute("order", o);
		}
		int goodsid = Integer.parseInt(request.getParameter("goodsid"));
		Goods goods = gService.getGoodsById(goodsid);
		if(goods.getStock()>0) {
			o.addGoods(goods);
			response.getWriter().print("ok");
		}else {
			response.getWriter().print("fail");
		}
	}

}
