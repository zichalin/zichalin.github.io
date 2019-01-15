package com.cakeshop.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.cakeshop.model.User;
import com.cakeshop.service.UserService;

/**
 * Servlet implementation class UserRegisterServlet
 */
@WebServlet("/user_register")
public class UserRegisterServlet extends HttpServlet {

	private UserService uService = new UserService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = new User();
		try {
			//使用工具将得到的请求放入user对象
			BeanUtils.copyProperties(user, request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(uService.register(user)){
			request.setAttribute("msg", "注册成功，请登陆！");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}else {
			request.setAttribute("msg", "注册失败，用户名或邮箱重复！");
			request.getRequestDispatcher("/register.jsp").forward(request, response);

		}
		
 	}

}
