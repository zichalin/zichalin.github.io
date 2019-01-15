package com.cakeshop.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.cakeshop.model.Goods;
import com.cakeshop.model.Order;
import com.cakeshop.model.OrderItem;
import com.cakeshop.utils.DBUtil;

public class OrderDao {
	
	public void insertOrder(Connection conn, Order order) throws SQLException {
		QueryRunner runner = new QueryRunner();
		String sql = "insert into `order`(total, amount, status, paytype, name, phone, address, datetime, user_id) values(?,?,?,?,?,?,?,?,?)";
		runner.update(conn, sql, order.getTotal(), order.getAmount(), order.getStatus(), order.getPaytype(), order.getName(), order.getPhone(), order.getAddress(), order.getDatetime(), order.getUser().getId());
	}
	
	public int getLastInsertId(Connection conn) throws SQLException {
		QueryRunner runner = new QueryRunner();
		String sql = "select last_insert_id()";
		BigInteger bigInteger =  runner.query(conn, sql, new ScalarHandler<BigInteger>());
		return Integer.parseInt(bigInteger.toString());
	}
	
	public  void insertOrderItem(Connection conn, OrderItem item) throws SQLException {
		QueryRunner runner = new QueryRunner();
		String sql = "insert into orderitem(price, amount, goods_id, order_id) values(?,?,?,?)";
		runner.update(conn, sql, item.getPrice(), item.getAmount(), item.getGoods().getId(), item.getOrder().getId());
	}
	
	public List<Order> selectOrder(int userid) throws SQLException {
		QueryRunner runner = new QueryRunner(DBUtil.getDataSource());
		String sql = "select * from `order` where user_id=? order by datetime desc";
		 return runner.query(sql, new BeanListHandler<Order>(Order.class), userid);
	}
	
	public List<OrderItem> selectOrderItems(int orderid) throws SQLException {
		QueryRunner runner = new QueryRunner(DBUtil.getDataSource());
		String sql = "select i.id, i.price, i.amount, g.name from orderitem i, goods g where order_id=? and i.goods_id=g.id";
		 return runner.query(sql, new BeanListHandler<OrderItem>(OrderItem.class), orderid);
	}

	public int getOrderCount(int status) throws SQLException {
		QueryRunner runner = new QueryRunner(DBUtil.getDataSource());
		String sql = "";
		if(status==0) {
			sql = "select count(*) from `order`";
			return runner.query(sql, new ScalarHandler<Long>()).intValue();
		}else {
			sql = "select count(*) from `order` where status = ?";
			return runner.query(sql, new ScalarHandler<Long>(),status).intValue();
		}
	}

	public List selectOrderList(int status, int pageNumber, int pageSize) throws SQLException {
		QueryRunner runner = new QueryRunner(DBUtil.getDataSource());
		if(status==0) {
			String sql = "select o.id, o.total, o.amount, o.status, o.paytype, o.name, o.phone, o.address, o.datetime, u.username from `order` o, user u where o.user_id=u.id order by o.datetime desc limit ?,?";
			return runner.query(sql, new BeanListHandler<Order>(Order.class), (pageNumber-1)*pageSize, pageSize);
		}else {
			String sql = "select o.id, o.total, o.amount, o.status, o.paytype, o.name, o.phone, o.address, o.datetime, u.username from `order` o, user u where o.status = ? and o.user_id=u.id order by o.datetime desc limit ?,?";
			return runner.query(sql, new BeanListHandler<Order>(Order.class), status, (pageNumber-1)*pageSize, pageSize);
		}
	}
	
	public void updateStatus(int id, int status) throws SQLException { 
		QueryRunner runner = new QueryRunner(DBUtil.getDataSource());
		String sql = "update `order` set status=? where id=?";
		runner.update(sql, status, id);	
	}
	
	public void deleteOrder(Connection conn, int id) throws SQLException {
		QueryRunner runner = new QueryRunner();
		String sql = "delete from `order` where id=?";
		runner.update(conn, sql, id);	
	}
	
	public void deleteOrderItem(Connection conn, int id) throws SQLException { 
		QueryRunner runner = new QueryRunner();
		String sql = "delete from orderitem where order_id=?";
		runner.update(conn, sql, id);	
	}
	
}
