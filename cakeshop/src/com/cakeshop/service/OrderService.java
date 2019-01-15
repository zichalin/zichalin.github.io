package com.cakeshop.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.cakeshop.dao.OrderDao;
import com.cakeshop.dao.UserDao;
import com.cakeshop.model.Order;
import com.cakeshop.model.OrderItem;
import com.cakeshop.model.Page;
import com.cakeshop.utils.DBUtil;

public class OrderService {
	
	private OrderDao oDao = new OrderDao();
	
	public void addOrder(Order order) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			oDao.insertOrder(conn, order);
			int id = oDao.getLastInsertId(conn);
			order.setId(id);
			for (OrderItem item : order.getItemMap().values()) {
				oDao.insertOrderItem(conn, item);
			}
			
			conn.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	
	public List<Order> selectOrder(int userid){
		List<Order> oList = null;
		try {
			oList = oDao.selectOrder(userid);
			for (Order order : oList) {
				List<OrderItem> i = oDao.selectOrderItems(order.getId());
				order.setItemList(i);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return oList;
	}

	public Page getOrderPage(int status, int pageNumber) {
		Page page = new Page();
		page.setPageNumber(pageNumber);
		int pageSize = 10;
		int totalCount = 0;
		try {
			totalCount =oDao.getOrderCount(status);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		page.setPageSizeAndTotalCount(pageSize,totalCount);
		List list = null;
		try {
			list = oDao.selectOrderList(status, pageNumber, pageSize);
			for (Order order : (List<Order>)list) {
				List<OrderItem> i = oDao.selectOrderItems(order.getId());
				order.setItemList(i);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		page.setList(list);
		return page;
	}
	
	public void updateStatus(int id, int status) {
		try {
			oDao.updateStatus(id, status);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete(int id) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			oDao.deleteOrderItem(conn, id);
			oDao.deleteOrder(conn, id);
			
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

}
