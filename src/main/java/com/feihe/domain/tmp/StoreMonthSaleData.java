package com.feihe.domain.tmp;

/**
 * 门店近三个月的平均销量
 * @author 若水
 *
 */
public class StoreMonthSaleData {
	
	private String storeId;//店铺主键 
	
	private String month;//月份（yyyy-MM）
	
	private double money;//销量金额（三个月份的平均销量）

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

}
