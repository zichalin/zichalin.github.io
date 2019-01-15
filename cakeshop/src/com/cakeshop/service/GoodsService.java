package com.cakeshop.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.cakeshop.dao.GoodsDao;
import com.cakeshop.model.Goods;
import com.cakeshop.model.Page;

public class GoodsService {
	
	GoodsDao gDao = new GoodsDao();
	
	public List<Map<String,Object>> getHotGoodsList() {
		List<Map<String,Object>> list = null;
		try {
			list = gDao.getGoodsList(2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Map<String,Object>> getNewGoodsList(){
		List<Map<String,Object>> list = null;
		try {
			list = gDao.getGoodsList(3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public Map<String, Object> getScrollGoodsMap(){
		Map<String, Object> map = null;
		try {
			map = gDao.getScrollList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
//	public List<Goods> selectGoods(int typeId, int pageNumber, int pageSize){
//		List<Goods> list = null;
//		try {
//			list = gDao.selectGoods(typeId, pageNumber, pageSize);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return list;
//	}
	
	public Page getGoodsPage(int typeId, int pageNumber) {
		Page page = new Page();
		page.setPageNumber(pageNumber);
		int totalCount = 0;
		try {
			totalCount = gDao.getGoodsCount(typeId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		page.setPageSizeAndTotalCount(8,totalCount);
		List list = null;
		try {
			list = gDao.selectGoods(typeId, pageNumber, 8);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		page.setList(list);
		return page;
	}
	
	public Page getGoodsRecommendPage(int type, int pageNumber) {
		Page page = new Page();
		page.setPageNumber(pageNumber);
		int totalCount = 0;
		try {
			totalCount = gDao.selectGoodsRecommendCount(type);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		page.setPageSizeAndTotalCount(8,totalCount);
		List list = null;
		try {
			list = gDao.selectGoodsRecommend(type, pageNumber, 8);
			for (Goods goods : (List<Goods>)list) {
				goods.setScroll(gDao.isScroll(goods));
				goods.setHot(gDao.isHot(goods));
				goods.setNew(gDao.isNew(goods));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		page.setList(list);
		return page;
	}
	
	public Goods getGoodsById(int id) {
		Goods goods = null;
		try {
			goods = gDao.getGoodsById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goods;
	}
	
	public Page getSearchGoodsPage(String keyword, int pageNumber) {
		Page page = new Page();
		page.setPageNumber(pageNumber);
		int totalCount = 0;
		try {
			totalCount = gDao.getSearchCount(keyword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		page.setPageSizeAndTotalCount(8,totalCount);
		List list = null;
		try {
			list = gDao.selectSearchGoods(keyword, pageNumber, 8);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		page.setList(list);
		return page;
	}
	
	public void addRecommend(int id, int type) {
		try {
			gDao.addRecommend(id, type);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removeRecommend(int id, int type) {
		try {
			gDao.removeRecommend(id, type);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addGoods(Goods goods) {
		try {
			gDao.addGoods(goods);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateGoods(Goods goods) {
		try {
			gDao.updateGoods(goods);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteGoods(int id) {
		try {
			gDao.deleteGoods(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
