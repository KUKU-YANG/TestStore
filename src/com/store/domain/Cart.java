package com.store.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
/**
 * 
 * 购物车
 *
 */
public class Cart {
	private Double total;		//总价
	private Map<String, CartItem> map=new HashMap<String, CartItem>();	//各个商品
	
	//添加商品(加入之前判断一下之前是否加入过购物车,加入过就将数量相加)
	public void addCartItem(CartItem cartItem) {
		//获取正在向购物车中添加商品的pid
		String pid=cartItem.getProduct().getPid();
		//判断有没有加入过这个商品
		if(map.containsKey(pid)) {
			CartItem oldCartItem=map.get(pid);
			oldCartItem.setNum(oldCartItem.getNum()+cartItem.getNum());
		}else {
			map.put(pid, cartItem);
		}
	}

	//返回map中所有的值
	public Collection<CartItem> getCartItems() {
		return map.values();
	}
	
	
	//删除某商品
	public void removeCartItem(String pid) {
		map.remove(pid);
	}
	//清空购物车
	public void clearCart() {
		map.clear();
	}
	
	//总价可以计算得出,不需要set
	public Double getTotal() {
		total=0.0;
		Collection<CartItem> values=map.values();
		for (CartItem cartItem : values) {
			total+=cartItem.getSubTotal();
		}
		return total;
	}
	
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	
}
