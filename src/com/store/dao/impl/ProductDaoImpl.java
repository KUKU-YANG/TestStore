package com.store.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.store.dao.ProductDao;
import com.store.domain.Product;
import com.store.utils.JDBCUtils;

public class ProductDaoImpl implements ProductDao {
	String sql=null;
	QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
	Product product=null;
	List<Product> list=null;
	Long totalRecords=null;
	@Override
	public List<Product> findHots() throws Exception {
		sql="select * from product where pflag=0 and is_hot =1 order by pdate desc limit 0,9";
		list=qr.query(sql, new BeanListHandler<Product>(Product.class));
		return list;
	}

	@Override
	public List<Product> findNews() throws Exception {
		sql="select * from product where pflag=0 order by pdate desc limit 0,9";
		list=qr.query(sql, new BeanListHandler<Product>(Product.class));
		return list;
	}

	@Override
	public Product findProductByPid(String pid) throws Exception {
		sql="select * from product where pid=?";
		product=qr.query(sql, new BeanHandler<Product>(Product.class),pid);
		return product;
	}

	@Override
	public Integer findTotalRecords(String cid) throws Exception {
		sql="select count(*) from product where cid=?";
		totalRecords=(Long) qr.query(sql, new ScalarHandler(),cid);
		return totalRecords.intValue();
	}

	@Override
	public Integer findTotalRecords() throws Exception {
		sql="select count(*) from product";
		totalRecords=(Long) qr.query(sql, new ScalarHandler());
		return totalRecords.intValue();
	}

	@Override
	public List<Product> findProductsByCidWithPage(String cid, int startIndex, int pageSize) throws Exception {
		sql="select * from product where cid=? and pflag=0 limit ?,?";
		list=qr.query(sql,new BeanListHandler<Product>(Product.class),cid,startIndex,pageSize);
		return list;
	}

	@Override
	public List<Product> findAllProductsWithPage(int startIndex, int pageSize) throws Exception {
		sql="select * from product where pflag=0 order by pdate desc limit ?,?";
		list=qr.query(sql, new BeanListHandler<Product>(Product.class),startIndex,pageSize);
		return list;
	}

	@Override
	public void saveProduct(Product product) throws Exception {
		sql="insert into product values(?,?,?,?,?,?,?,?,?,?)";
		Object[] params= {product.getPid(),product.getPname(),product.getMarket_price(),product.getShop_price(),product.getPimage(),product.getPdate(),product.getIs_hot(),product.getPdesc(),product.getPflag(),product.getCid()};
		qr.update(sql,params);
	}

	@Override
	public void edit(Product product) throws Exception {
		sql="update product set pname=?,market_price=?,shop_price=?,pimage=?,pdate=?,is_hot=?,pdesc=?,pflag=?,cid=? where pid=?";
		Object[] params= {product.getPname(),product.getMarket_price(),product.getShop_price(),product.getPimage(),product.getPdate(),product.getIs_hot(),product.getPdesc(),product.getPflag(),product.getCid(),product.getPid()};
		qr.update(sql,params);
	}
	
	@Override
	public List<Product> findPushDownProduct() throws Exception {
		sql="select * from product where pflag=1 order by pdate desc";
		list=qr.query(sql, new BeanListHandler<Product>(Product.class));
		return list;
	}

	@Override
	public void pushDown(Product product) throws Exception {
		sql="update product set pname=?,market_price=?,shop_price=?,pimage=?,pdate=?,is_hot=?,pdesc=?,pflag=?,cid=? where pid=?";
		Object[] params= {product.getPname(),product.getMarket_price(),product.getShop_price(),product.getPimage(),product.getPdate(),product.getIs_hot(),product.getPdesc(),product.getPflag(),product.getCid(),product.getPid()};
		qr.update(sql,params);
	}

	@Override
	public void upper(Product product) throws Exception {
		sql="update product set pname=?,market_price=?,shop_price=?,pimage=?,pdate=?,is_hot=?,pdesc=?,pflag=?,cid=? where pid=?";
		Object[] params= {product.getPname(),product.getMarket_price(),product.getShop_price(),product.getPimage(),product.getPdate(),product.getIs_hot(),product.getPdesc(),product.getPflag(),product.getCid(),product.getPid()};
		qr.update(sql,params);
	}


}
