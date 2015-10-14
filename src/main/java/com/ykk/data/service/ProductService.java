package com.ykk.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ykk.data.persistence.dao.ProductMapper;
import com.ykk.data.persistence.model.Product;

@Service
public class ProductService {

	@Autowired
	private ProductMapper productMapper;

	public Product selectByPrimaryKey(Integer productId) {
		return productMapper.selectByPrimaryKey(productId);
	}

}
