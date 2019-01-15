package com.cakeshop.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.cakeshop.model.Type;
import com.cakeshop.service.TypeService;

/**
 * Servlet implementation class AdminTypeEditServlet
 */
@WebServlet("/admin/type_edit")
public class AdminTypeEditServlet extends HttpServlet {

	private TypeService tService = new TypeService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Type t = new Type();
		int id = Integer.parseInt(request.getParameter("id"));
		String name = new String(request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
		t.setId(id);
		t.setName(name);
		tService.updateType(t);
		request.setAttribute("msf", "类型名称更改成功！");
		request.getRequestDispatcher("/admin/type_list").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
