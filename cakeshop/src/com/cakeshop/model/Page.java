package com.cakeshop.model;

import java.util.List;

public class Page {
	
	private int pageNumber;		//当前页数
	private int pageSize;		//单页显示记录
	private int totalCount;		//总记录数
	private int totalPage;		//总页数
	
	private List<Object> list;	//存储的数据
	
	//获得总页数
	public void setPageSizeAndTotalCount(int pageSize, int totalCount) {
		totalPage = (int)Math.ceil((double)totalCount/pageSize);
		this.pageSize = pageSize;
		this.totalCount = totalCount;
	}
	
	//Getter、Setter
	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}
	
}
