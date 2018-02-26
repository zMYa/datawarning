package com.feihe.domain.tmp;

/**
 * 某个片区昨日订单总数、销量
 * @author 若水
 *
 */
public class YesterdayNum {
	
	private String name;//片区名称
    
    private int areaId;//片区主键
    
    private int type;//片区类别   1 一级片区，2 二级片区，3 三级片区，4 四级片区，5 五级片区
	
	private int yesOrderNums;//昨日订单数
	
	private int yesSaleNums;//昨日销量(金额)

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

	public int getYesOrderNums() {
		return yesOrderNums;
	}

	public void setYesOrderNums(int yesOrderNums) {
		this.yesOrderNums = yesOrderNums;
	}

	public int getYesSaleNums() {
		return yesSaleNums;
	}

	public void setYesSaleNums(int yesSaleNums) {
		this.yesSaleNums = yesSaleNums;
	}

}
