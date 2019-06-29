package com.store.service;

import java.util.List;

import com.store.domain.PageModel;
import com.store.domain.Product;

public interface ProductService {

	List<Product> findHots() throws Exception;

	List<Product> findNews() throws Exception;

	Product findProductByPid(String pid) throws Exception;

	PageModel findProductsByCidWithPage(String cid, Integer curNum) throws Exception;

	PageModel findAllProductsWithPage(Integer curNum) throws Exception;

	void saveProduct(Product product) throws Exception;

	void edit(Product product) throws Exception;

	List<Product> findPushDownProduct() throws Exception;

	void pushDown(Product product) throws Exception;

	void upper(Product product) throws Exception;

}
