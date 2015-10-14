package com.ykk.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ykk.data.persistence.dao.ProductNetValueLogMapper;
import com.ykk.data.persistence.model.ProductNetValueLog;

@Service
public class ProductNetValueLogService {

	@Autowired
	private ProductNetValueLogMapper productNetValueLogMapper;

	public int insert(ProductNetValueLog productNetValueLog) {
		return productNetValueLogMapper.insertSelective(productNetValueLog);
	};
}
