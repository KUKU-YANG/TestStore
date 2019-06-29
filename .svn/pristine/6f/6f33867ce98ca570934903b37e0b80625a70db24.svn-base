package com.store.domain;
/**
 * 
 * 放入购物车的商品的实体
 *
 */
public class CartItem {
	private Product product;	//商品信息（图片，名称，价格）
	private Integer num;		//数量
	private Double subTotal;	//小计
	
	//通过计算可得,不需要set
	public Double getSubTotal() {
		return product.getShop_price()*num;
	}
	
	@Override
	public String toString() {
		return "CartItem [product=" + product + ", num=" + num + ", subTotal=" + subTotal + "]";
	}
	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CartItem(Product product, Integer num, Double subTotal) {
		super();
		this.product = product;
		this.num = num;
		this.subTotal = subTotal;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
}
