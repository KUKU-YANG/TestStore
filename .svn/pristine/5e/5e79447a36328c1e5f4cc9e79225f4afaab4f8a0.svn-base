package com.store.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
	private String oid;			//订单编号
	private Date ordertime;		//下单时间
	private Double total;		//总计
	private Integer state;		//状态
	private String address;		//收货地址
	private String name;		//收货人姓名
	private String telephone;	//收货人电话
	//此处应该对象与对象连系，而不是对象的值与对象连系
	//设计Order的目的是让order携带订单的数据向service，dao传递，user对象可以携带更多数据
	private User user;
	//程序中体现订单对象和订单项之间的关系，查询订单的同时还需要获取订单下所有的订单项
	private List<OrderItem> list=new ArrayList<OrderItem>();
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<OrderItem> getList() {
		return list;
	}
	public void setList(List<OrderItem> list) {
		this.list = list;
	}
}
