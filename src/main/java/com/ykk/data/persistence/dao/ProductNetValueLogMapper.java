package com.ykk.data.persistence.dao;

import com.ykk.data.persistence.model.ProductNetValueLog;

public interface ProductNetValueLogMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(ProductNetValueLog record);

	int insertSelective(ProductNetValueLog record);

	ProductNetValueLog selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ProductNetValueLog record);

	int updateByPrimaryKey(ProductNetValueLog record);
}