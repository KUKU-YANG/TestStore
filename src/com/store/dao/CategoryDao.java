package com.store.dao;

import java.util.List;

import com.store.domain.Category;

public interface CategoryDao {

	List<Category> getAllCats() throws Exception;

	void addCategory(Category category) throws Exception;

	Category findById(String cid) throws Exception;

	void edit(Category category) throws Exception;

	void delete(String cid) throws Exception;

}
