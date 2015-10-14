package com.ykk.data.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ykk.data.persistence.dao.ProductNetValueMapper;
import com.ykk.data.persistence.dao.UserMapper;
import com.ykk.data.persistence.model.Product;
import com.ykk.data.persistence.model.ProductNetValue;
import com.ykk.data.persistence.model.ProductNetValueLog;
import com.ykk.data.persistence.model.User;

@Service
public class ProductNetValueService {

	@Autowired
	private ProductNetValueMapper productNetValueMapper;
	@Autowired
	private ProductNetValueLogService productNetValueLogService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserMapper userMapper;

	@Transactional
	public Map<String, Object> insert(Integer productId, Integer type,
			BigDecimal value, Date dateTime, String name) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		// type只能输入1或2
		if (type == 1 || type == 2) {
			// 通过name获取user实体
			User user = userMapper.selectByName(name);
			if (user == null) {
				map.put("status", 0);
				map.put("errorMessage", "name输入无效");
			} else {
				Product product = productService.selectByPrimaryKey(productId);

				if (product == null || product.getUserId() == null) {
					map.put("status", 0);
					map.put("errorMessage", "productId输入无效");
				} else {
					String[] userIdArray = product.getUserId().split(",");
					boolean flag = false;
					for (String userId : userIdArray) {
						if (userId.equals("" + user.getId())) {
							flag = true;
						}
					}

					if (flag) {
						// 更新product_net_value表的条数
						int count = 0;

						// 一、查看是已有记录
						ProductNetValue productNetValueDb = this
								.selectByProductIdAndDate(productId, dateTime);
						// 初始化ProductNetValue实例
						ProductNetValue productNetValue = new ProductNetValue();
						productNetValue.setProductId(productId);
						productNetValue.setValue(value);
						productNetValue.setDateTime(dateTime);
						productNetValue.setType(type);
						productNetValue.setUserId(user.getId());

						// 1)如果有，product_net_value表，则更新
						if (productNetValueDb != null) {
							productNetValue.setId(productNetValueDb.getId());
							count = productNetValueMapper
									.updateByPrimaryKeySelective(productNetValue);
						} else {// 2)如果没有，则插入product_net_value表
							count = productNetValueMapper
									.insertSelective(productNetValue);
						}

						// 二、如果product_net_value表插入或者更新，product_net_value_log表插入，否则不插入
						if (count == 1) {
							// 初始化ProductNetValueLog实例
							ProductNetValueLog productNetValueLog = new ProductNetValueLog();
							productNetValueLog.setProductId(productId);
							productNetValueLog.setValue(value);
							productNetValueLog.setDateTime(dateTime);
							productNetValueLog.setType(type);
							productNetValueLog.setUserId(user.getId());
							productNetValueLog.setCreateTime(new Date());

							int count2 = productNetValueLogService
									.insert(productNetValueLog);
							if (count2 == 1) {
								map.put("status", 1);
								map.put("message", "插入成功");
							} else {
								// product_net_value_log表插入失败
								throw new RuntimeException();
							}
						} else {
							// product_net_value表插入或更新失败
							throw new RuntimeException();
						}
					} else {
						map.put("status", 0);
						map.put("errorMessage", "您没有productId是" + productId
								+ "产品的权限");
					}
				}
			}
		} else {
			map.put("status", 0);
			map.put("errorMessage", "type输入无效");
		}

		return map;
	};

	public ProductNetValue selectByProductIdAndDate(Integer productId,
			Date dateTime) {
		return productNetValueMapper.selectByProductIdAndDateTime(productId,
				dateTime);
	}
}
