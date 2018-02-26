package com.feihe.domain.tmp;

/**
 * 门店预警统计数据 
 * @author 若水
 *
 */
public class StoreWarningBaseData {
	
	private String finishMonth;//新客月份 
	
	private String startDate;//开始时间
	
	private String endDate;//截止时间
	
	private String provinceName;//省区
	
	private String cityName;//城市
	
	private String districtName;//地区
	
	private String storeName;//门店名称
	
	private Integer storeId;//门店Id
	
	private Integer standardNum;//门店新客预警标准
	
	private Integer newNum;//新客开发人数
	
	private Integer outNum;//超出预警线新客人数

	public String getFinishMonth() {
		return finishMonth;
	}

	public void setFinishMonth(String finishMonth) {
		this.finishMonth = finishMonth;
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

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getStandardNum() {
		return standardNum;
	}

	public void setStandardNum(Integer standardNum) {
		this.standardNum = standardNum;
	}

	public Integer getNewNum() {
		return newNum;
	}

	public void setNewNum(Integer newNum) {
		this.newNum = newNum;
	}

	public Integer getOutNum() {
		return outNum;
	}

	public void setOutNum(Integer outNum) {
		this.outNum = outNum;
	}

}
