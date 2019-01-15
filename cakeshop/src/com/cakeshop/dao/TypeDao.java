package com.cakeshop.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cakeshop.model.Type;
import com.cakeshop.utils.DBUtil;

public class TypeDao { 
	
	public List<Type> selectAll() throws Exception {
		QueryRunner qRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "select * from type";
		return qRunner.query(sql, new BeanListHandler<Type>(Type.class));
	}
	
	public Type selecType(int id) throws SQLException {
		QueryRunner qRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "select * from type where id = ?";
		return qRunner.query(sql, new BeanHandler<Type>(Type.class), id);
	}
	
	public void insertType(Type t) throws SQLException {
		QueryRunner qRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "insert into type(name) values(?)";
		qRunner.update(sql, t.getName());
	}
	
	public void updateType(Type t) throws SQLException {
		QueryRunner qRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "update type set name=? where id=?";
		qRunner.update(sql, t.getName(), t.getId());
	}
	
	public void deleteType(int id) throws SQLException {
		QueryRunner qRunner = new QueryRunner(DBUtil.getDataSource());
		String sql = "delete from  type where id=?";
		qRunner.update(sql, id);
	}
	
}
