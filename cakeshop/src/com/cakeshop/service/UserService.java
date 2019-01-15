package com.cakeshop.service;

import java.sql.SQLException;
import java.util.List;

import com.cakeshop.dao.UserDao;
import com.cakeshop.model.Page;
import com.cakeshop.model.User;

public class UserService {
	
	private UserDao userDao = new UserDao();
	
	public boolean register(User user ) {
		try {
			if (userDao.isUsernameExist(user.getUsername())) {
				return false;
			}else if (userDao.isEmailExist(user.getEmail())) {
				return false;
			}else {
				userDao.addUser(user);
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public User login(String ue, String password) {
		User user = null;
		try {
			user = userDao.selectByUsername(ue, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (user!=null) {
			return user;
		}
		try {
			user = userDao.selectByEmail(ue, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (user!=null) {
			return user;
		}
		return null;
		
	}
	
	public void updateUserAddress(User user) {
		try {
			userDao.updateUserAddress(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void changePassword(User user) {
		try {
			userDao.changePassword(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Page getUserPage(int pageNumber) {
		Page page = new Page();
		page.setPageNumber(pageNumber);
		int pageSize = 8;
		int totalCount = 0;
		try {
			totalCount = userDao.selectUserCount(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		page.setPageSizeAndTotalCount(pageSize,totalCount);
		List list = null;
		try {
			list = userDao.selectUserList(pageNumber, pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		page.setList(list);
		return page;
	}
	
	public User selectById(int id) {
		User u = null;
		try {
			u = userDao.selectById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	
	public boolean isDeleteUser(int id) {
		try {
			userDao.deleteUser(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
}
