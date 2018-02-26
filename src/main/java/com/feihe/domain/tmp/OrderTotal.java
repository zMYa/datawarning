package com.feihe.domain.tmp;

/**
 * 某个片区某段时间的订单总数，单日最高数，日均数
 * @author 若水
 *
 */
public class OrderTotal {
	
	private String name;//片区名称
    
    private int areaId;//片区主键
    
    private int type;//片区类别   1 一级片区，2 二级片区，3 三级片区，4 四级片区，5 五级片区
    
    private String startDate;//开始时间  yyyy-MM-dd
    
    private String endDate;//截止时间 yyyy-MM-dd
    
    private int orderTotal;//订单总数
    
    private int orderMaxTotal;//单日最高订单数
    
    private String orderMaxDay;//单日最高日期
    
    private int averageDay;//日均订单数

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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(int orderTotal) {
		this.orderTotal = orderTotal;
	}

	public int getOrderMaxTotal() {
		return orderMaxTotal;
	}

	public void setOrderMaxTotal(int orderMaxTotal) {
		this.orderMaxTotal = orderMaxTotal;
	}

	public String getOrderMaxDay() {
		return orderMaxDay;
	}

	public void setOrderMaxDay(String orderMaxDay) {
		this.orderMaxDay = orderMaxDay;
	}

	public int getAverageDay() {
		return averageDay;
	}

	public void setAverageDay(int averageDay) {
		this.averageDay = averageDay;
	}
	
}
