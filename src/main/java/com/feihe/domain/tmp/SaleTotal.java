package com.feihe.domain.tmp;

/**
 * 某个片区某天的订单总数、销量
 * @author 若水
 *
 */
public class SaleTotal {
	
	private String name;//片区名称
    
    private int areaId;//片区主键
    
    private int type;//片区类别   1 一级片区，2 二级片区，3 三级片区，4 四级片区，5 五级片区
    
    private String date;//统计日期 yyyy-MM-dd
	
	private int orderNums;//订单数
	
	private int saleNums;//销量(金额)

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getOrderNums() {
		return orderNums;
	}

	public void setOrderNums(int orderNums) {
		this.orderNums = orderNums;
	}

	public int getSaleNums() {
		return saleNums;
	}

	public void setSaleNums(int saleNums) {
		this.saleNums = saleNums;
	}

}
