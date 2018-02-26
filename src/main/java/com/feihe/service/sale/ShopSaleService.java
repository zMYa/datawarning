package com.feihe.service.sale;

import java.util.List;

import com.feihe.domain.report.ShopMonthSale;

/**
 * 获取门店的销售汇总数据
 * @author 若水
 *
 */
public interface ShopSaleService {
	
	/**
	 * 获取指定某月的所有emp数据库门店销量情况统计数据
	 * @param month 月份 yyyy-MM
	 * @return
	 */
	public List<ShopMonthSale> loadShopMonthSaleList(String month) throws Exception;
	
	/**
	 * 保存数据
	 * @param areaDaySale 数据
	 * @return
	 */
	public int saveShopMonthSale(ShopMonthSale shopMonthSale) throws Exception;

}
