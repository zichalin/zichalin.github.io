package com.cakeshop.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.cakeshop.model.Goods;
import com.cakeshop.model.Recommend;
import com.cakeshop.utils.DBUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.regexp.internal.recompile;

public class GoodsDao {
	
	//热销品查询
	public List<Map<String,Object>> getGoodsList(int recommendType) throws Exception {
		QueryRunner runner = new QueryRunner(DBUtil.getDataSource());
		String sql = "select g.id, g.name, g.cover, g.price, t.name typename from recommend r, goods g, type t where type=? and r.goods_id=g.id and g.type_id=t.id";
		return runner.query(sql, new MapListHandler(), recommendType);
	}
	
	public Map<String,Object> getScrollList() throws Exception {
		QueryRunner runner = new QueryRunner(DBUtil.getDataSource());
		String sql = "select g.id, g.name, g.cover, g.price from recommend r, goods g where type=1 and r.goods_id=g.id";
		return runner.query(sql, new MapHandler());
	}
	
	public List<Goods> selectGoods(int typeId, int pagenNumber, int pageSize) throws SQLException{
		QueryRunner runner = new QueryRunner(DBUtil.getDataSource());
		if(typeId==0) {
			String sql = "select * from goods limit ?,?";
			return runner.query(sql, new BeanListHandler<Goods>(Goods.class), (pagenNumber-1)*pageSize, pageSize);
		}else {
			String sql = "select * from goods where type_id = ? limit ?,?";
			return runner.query(sql, new BeanListHandler<Goods>(Goods.class), typeId, (pagenNumber-1)*pageSize, pageSize);
		}
	}
	
	public int getGoodsCount(int typeId) throws SQLException {
		QueryRunner runner = new QueryRunner(DBUtil.getDataSource());
		String sql = "";
		if(typeId==0) {
			sql = "select count(*) from goods";
			return runner.query(sql, new ScalarHandler<Long>()).intValue();
		}else {
			sql = "select count(*) from goods where type_id = ?";
			return runner.query(sql, new ScalarHandler<Long>(),typeId).intValue();
		}
	}
	
	public List<Goods> selectGoodsRecommend(int type, int pageNumber, int pageSize) throws SQLException{
		QueryRunner runner = new QueryRunner(DBUtil.getDataSource());
		if (type == 0) {
			//当不添加推荐类型限制 
			String sql = "select g.id, g.name, g.cover, g.image1, g.image2, g.intro, g.price, g.stock, t.name typename from goods g, type t where g.type_id=t.id limit ?,?";
			return runner.query(sql, new BeanListHandler<Goods>(Goods.class), (pageNumber-1)*pageSize, pageSize); 
		} else {
			String sql = "select g.id, g.name, g.cover, g.image1, g.image2, g.intro, g.price, g.stock, t.name typename from goods g, recommend r, type t where g.id=r.goods_id and g.type_id=t.id and r.type=? limit ?,?";
			return runner.query(sql, new BeanListHandler<Goods>(Goods.class), type, (pageNumber-1)*pageSize, pageSize); 
		}
	}
	
	public int selectGoodsRecommendCount(int type) throws SQLException{
		if( type == 0) {return getGoodsCount(0);}
		QueryRunner runner = new QueryRunner(DBUtil.getDataSource());
		String sql = "select count(*) from recommend where type=?";
		return runner.query(sql, new ScalarHandler<Long>(), type).intValue(); 
	}
	
	public Goods getGoodsById(int id) throws SQLException {
		QueryRunner runner = new QueryRunner(DBUtil.getDataSource());
		String sql = "select g.id,g.name,g.cover,g.image1,g.image2,g.price,g.intro,g.stock,t.id typeid,t.name typename from goods g,type t where g.id = ? and g.type_id=t.id";
		return runner.query(sql, new BeanHandler<Goods>(Goods.class), id);
	}

	public int getSearchCount(String keyword) throws SQLException {
		QueryRunner runner = new QueryRunner(DBUtil.getDataSource());
		String sql = "select count(*) from goods where name like ?";
		return runner.query(sql, new ScalarHandler<Long>(), "%"+keyword+"%").intValue(); 
	}

	public List<Goods> selectSearchGoods(String keyword, int pageNumber, int pageSize) throws SQLException {
		QueryRunner runner = new QueryRunner(DBUtil.getDataSource());
		String sql = "select * from goods where name like ? limit ?,?";
		return runner.query(sql, new BeanListHandler<Goods>(Goods.class), "%"+keyword+"%", (pageNumber-1)*pageSize, pageSize); 
	}
	
	private boolean isRecommend(Goods g, int type) throws SQLException {
		QueryRunner runner = new QueryRunner(DBUtil.getDataSource());
		String sql = "select * from recommend where type=? and goods_id=?";
		Recommend recommend = runner.query(sql, new BeanHandler<Recommend>(Recommend.class), type, g.getId());
		if (recommend == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean isScroll(Goods g) throws SQLException { 
		return isRecommend(g, 1);
	}
	
	public boolean isHot(Goods g) throws SQLException { 
		return isRecommend(g, 2);
	}
	
	public boolean isNew(Goods g) throws SQLException { 
		return isRecommend(g, 3);
	}
	
	public void addRecommend(int id, int type) throws SQLException {
		QueryRunner runner = new QueryRunner(DBUtil.getDataSource());
		String sql = "insert into recommend(type, goods_id) values(?, ?)";
		runner.update(sql, type, id);
	}
	
	public void removeRecommend(int id, int type) throws SQLException {
		QueryRunner runner = new QueryRunner(DBUtil.getDataSource());
		String sql = "delete from recommend where type=? and goods_id=?";
		runner.update(sql, type, id);
	}
	
	public void addGoods(Goods g) throws SQLException {
		QueryRunner runner = new QueryRunner(DBUtil.getDataSource());
		String sql = "insert into goods(name,cover,image1,image2,price,intro,stock,type_id) values(?,?,?,?,?,?,?,?)";
		runner.update(sql,g.getName(),g.getCover(),g.getImage1(),g.getImage2(),g.getPrice(),g.getIntro(),g.getStock(),g.getType().getId());
	}
	
	public void updateGoods(Goods g) throws SQLException {
		QueryRunner runner = new QueryRunner(DBUtil.getDataSource());
		String sql = "update goods set name=?,cover=?,image1=?,image2=?,price=?,intro=?,stock=?,type_id=? where id=?";
		runner.update(sql,g.getName(),g.getCover(),g.getImage1(),g.getImage2(),g.getPrice(),g.getIntro(),g.getStock(),g.getType().getId(),g.getId());
	}
	
	public void deleteGoods(int id) throws SQLException {
		QueryRunner runner = new QueryRunner(DBUtil.getDataSource());
		String sql = "delete from goods where id=?";
		runner.update(sql, id);
	
	}
	
}
