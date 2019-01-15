package com.cakeshop.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cakeshop.service.UserService;

/**
 * Servlet implementation class AdminUserDeleteServlet
 */
@WebServlet("/admin/user_delete")
public class AdminUserDeleteServlet extends HttpServlet {

	private UserService uService = new UserService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		boolean isSuccess = uService.isDeleteUser(id);
		if (isSuccess) {
			request.setAttribute("msg", "删除客户成功！");
		} else {
			request.setAttribute("fmsg", "未成功删除客户，请先处理该客户下订单！");
		}
		request.getRequestDispatcher("/admin/user_list").forward(request, response);
	}

}
