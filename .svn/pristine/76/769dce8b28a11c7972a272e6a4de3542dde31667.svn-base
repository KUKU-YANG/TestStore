package com.store.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 废案
 *
 */
public class XCartX {
	private Double total;		//总价
	private List<CartItem> list=new ArrayList();	//各个商品
	
	//将pid和num发送给服务端
	public void addCartItem(CartItem cartItem) {
		//加入之前判断一下之前是否加入过购物车
		Boolean flag=false;
		CartItem old=null;	//存储原先的商品信息
		for (CartItem oldCartItem : list) {
			if(oldCartItem.getProduct().getPid().equals(cartItem.getProduct().getPid())) {
				flag=true;
				old=oldCartItem;
			}
		}
		
		if(flag==false) {
			list.add(cartItem);
		}else {
			//将商品新增的数量加到原数量上
			old.setNum(old.getNum()+cartItem.getNum());
		}
	}
	
	
	
	public void removeCartItem(String pid) {
		//遍历list，看哪个product的pid与接收的相同
		for (CartItem cartItem : list) {
			if(cartItem.getProduct().getPid().equals(pid)) {
				//正常无法删除，要用迭代器删除
			}
		}
	}
	
}
