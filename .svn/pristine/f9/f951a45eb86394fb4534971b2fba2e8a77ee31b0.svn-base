package com.store.dao;

import java.util.List;

import com.store.domain.PageModel;
import com.store.domain.Product;

public interface ProductDao {

	List<Product> findHots() throws Exception;

	List<Product> findNews() throws Exception;

	Product findProductByPid(String pid) throws Exception;
	
	Integer findTotalRecords(String cid) throws Exception;
	
	Integer findTotalRecords() throws Exception;

	List<Product> findProductsByCidWithPage(String cid, int startIndex, int pageSize) throws Exception;

	List<Product> findAllProductsWithPage(int startIndex, int pageSize) throws Exception;

	void saveProduct(Product product) throws Exception;

	void edit(Product product) throws Exception;

	List<Product> findPushDownProduct() throws Exception;

	void pushDown(Product product) throws Exception;

	void upper(Product product) throws Exception;

}
