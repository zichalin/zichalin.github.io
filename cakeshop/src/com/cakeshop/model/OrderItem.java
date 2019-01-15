package com.cakeshop.model;

public class OrderItem {
	
	private int id;
	private float price;
	private int amount;
	private String goodsname;
	private Goods goods;
	private Order order;
	
	public void setName(String name) {
		this.goodsname = name;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public OrderItem() {
		super();
	}
	public OrderItem(float price, int amount, Goods goods, Order order) {
		super();
		this.price = price;
		this.amount = amount;
		this.goods = goods;
		this.order = order;
	}
	
}
