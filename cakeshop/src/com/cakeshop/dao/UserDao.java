package com.cakeshop.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.cakeshop.model.Goods;
import com.cakeshop.model.User;
import com.cakeshop.utils.DBUtil;

public class UserDao {
	
	public void addUser(User user) throws SQLException {
		QueryRunner runner = new QueryRunner(DBUtil.getDataSource());
		String sql = "insert into user(username,email,password,name,phone,address,isadmin,isvalidate) values(?,?,?,?,?,?,?,?)";
		runner.update(sql,user.getUsername(),user.getEmail(),user.getPassword(),user.getName(),user.getPhone(),user.getAddress(),user.isIsadmin(),user.isIsvalidate());
	}
	
	public boolean isUsernameExist(String username) throws SQLException {
		QueryRunner runner = new QueryRunner(DBUtil.getDataSource());
		String sql = "select * from user where username = ?";
		User user = runner.query(sql, new BeanHandler<User>(User.class), username);
		if (user == null) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean isEmailExist(String email) throws SQLException {
		QueryRunner runner = new QueryRunner(DBUtil.getDataSource());
		String sql = "select * from user where email = ?";
		User user = runner.query(sql, new BeanHandler<User>(User.class), email);
		if (user == null) {
			return false;
		}else {
			return true;
		}
	}
	
	public User selectByUsername(String username, String password) throws SQLException {
		QueryRunner runner = new QueryRunner(DBUtil.getDataSource());
		String sql = "select * from user where username=? and password=?";
		return runner.query(sql, new BeanHandler<User>(User.class), username, password);
	}
	
	public User selectByEmail(String email, String password) throws SQLException {
		QueryRunner runner = new QueryRunner(DBUtil.getDataSource());
		String sql = "select * from user where email=? and password=?";
		return runner.query(sql, new BeanHandler<User>(User.class), email, password);
	}
	
	public void updateUserAddress(User user) throws SQLException {
		QueryRunner runner = new QueryRunner(DBUtil.getDataSource());
		String sql = "update user set name=?,phone=?,address=? where id=?";
		runner.update(sql, user.getName(), user.getPhone(), user.getAddress(), user.getId());
	}
	
	public void changePassword(User user) throws SQLException {
		QueryRunner runner = new QueryRunner(DBUtil.getDataSource());
		String sql = "update user set password=? where id=?";
		runner.update(sql, user.getPassword(), user.getId());
	}

	public int selectUserCount() throws SQLException {
		QueryRunner runner = new QueryRunner(DBUtil.getDataSource());
		String sql = "select count(*) from user";
		return runner.query(sql, new ScalarHandler<Long>()).intValue();
	}

	public List<User> selectUserList(int pageNumber, int pageSize) throws SQLException {
		QueryRunner runner = new QueryRunner(DBUtil.getDataSource());
		String sql = "select * from user limit ?,?";
		return runner.query(sql, new BeanListHandler<User>(User.class), (pageNumber-1)*pageSize, pageSize);
	}
	
	public User selectById(int id) throws SQLException {
		QueryRunner runner = new QueryRunner(DBUtil.getDataSource());
		String sql = "select * from user where id=?";
		return runner.query(sql, new BeanHandler<User>(User.class), id);
	}
	
	public void deleteUser(int id) throws SQLException {
		QueryRunner runner = new QueryRunner(DBUtil.getDataSource());
		String sql = "delete from user where id=?";
		runner.update(sql, id);
	}
	
}
