package com.store.service.impl;

import java.util.List;

import com.store.dao.ProductDao;
import com.store.domain.PageModel;
import com.store.domain.Product;
import com.store.service.ProductService;
import com.store.utils.BeanFactory;

public class ProductServiceImpl implements ProductService {
	//解耦获取ProductDao对象
	ProductDao dao=(ProductDao) BeanFactory.createDao("ProductDao");
	Product product=null;
	List<Product> list=null;
	@Override
	public List<Product> findHots() throws Exception {
		
		list=dao.findHots();
		return list;
	}

	@Override
	public List<Product> findNews() throws Exception {
		
		list=dao.findNews();
		return list;
	}

	@Override
	public Product findProductByPid(String pid) throws Exception {
		
		product=dao.findProductByPid(pid);
		return product;
	}

	@Override
	public PageModel findProductsByCidWithPage(String cid, Integer curNum) throws Exception {
		//创建PageModel对象，接收分页参数
		Integer totalRecords=dao.findTotalRecords(cid);
		PageModel pm=new PageModel(curNum, totalRecords, 12);
		//关联集合
		List<Product> list=dao.findProductsByCidWithPage(cid, pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		//关联url
		pm.setUrl("ProductServlet?method=findProductsByCidWithPage&cid="+cid);
		return pm;
	}

	@Override
	public PageModel findAllProductsWithPage(Integer curNum) throws Exception {
		//创建对象
		Integer totalRecords=dao.findTotalRecords();
		PageModel pm=new PageModel(curNum,totalRecords,5);
		//关联集合
		List<Product> list=dao.findAllProductsWithPage(pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		//关联url
		pm.setUrl("AdminProductServlet?method=findAllProductsWithPage");
		return pm;
	}

	@Override
	public void saveProduct(Product product) throws Exception {
		dao.saveProduct(product);
	}

	@Override
	public void edit(Product product) throws Exception {
		dao.edit(product);
	}

	@Override
	public List<Product> findPushDownProduct() throws Exception {
		List<Product> list=dao.findPushDownProduct();
		return list;
	}

	@Override
	public void pushDown(Product product) throws Exception {
		product.setPflag(1);
		dao.pushDown(product);
	}

	@Override
	public void upper(Product product) throws Exception {
		product.setPflag(0);
		dao.upper(product);
	}


}
