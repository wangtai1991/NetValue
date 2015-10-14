package com.ykk.data.persistence.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.ykk.data.persistence.model.ProductNetValue;

public interface ProductNetValueMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(ProductNetValue record);

	int insertSelective(ProductNetValue record);

	ProductNetValue selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ProductNetValue record);

	int updateByPrimaryKey(ProductNetValue record);

	ProductNetValue selectByProductIdAndDateTime(
			@Param("productId") Integer productId,
			@Param("dateTime") Date dateTime);
}