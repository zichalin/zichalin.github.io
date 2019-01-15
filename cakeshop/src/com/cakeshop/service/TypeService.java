package com.cakeshop.service;

import java.sql.SQLException;
import java.util.List;

import com.cakeshop.dao.TypeDao;
import com.cakeshop.model.Type;

public class TypeService {
	
	TypeDao tDao = new TypeDao();
	
	public List<Type> selectAll(){
		List<Type> list = null;
		try {
			list = tDao.selectAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public Type selecType(int id) {
		Type type = null;
		try {
			type = tDao.selecType(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return type;
	}
	
	public void insertType(Type t) {
		try {
			tDao.insertType(t);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateType(Type t) {
		try {
			tDao.updateType(t);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteType(int id) {
		try {
			tDao.deleteType(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
