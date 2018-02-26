package com.feihe.domain.tmp;

/**
 * 某个片区分品项某天的订单总数、销量、退款订单数、退款销量
 * @author 若水
 *
 */
public class SaleSeriesDetailTotal {
	
	private String name;//片区名称
    
    private int areaId;//片区主键
    
    private int type;//片区类别   1 一级片区，2 二级片区，3 三级片区，4 四级片区，5 五级片区
    
    private int seriesId;//品项id
    
    private String seriesName;//品项名称
    
    private String date;//统计日期 yyyy-MM-dd
	
	private int orderNums;//订单数
	
	private int saleNums;//销量(金额)
	
	private int refundsNums;//订单退款数量

    private int refundsPrices;//订单退款金额

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

	public int getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(int seriesId) {
		this.seriesId = seriesId;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
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

	public int getRefundsNums() {
		return refundsNums;
	}

	public void setRefundsNums(int refundsNums) {
		this.refundsNums = refundsNums;
	}

	public int getRefundsPrices() {
		return refundsPrices;
	}

	public void setRefundsPrices(int refundsPrices) {
		this.refundsPrices = refundsPrices;
	}

}
