package com.feihe.domain.report;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 统计片区销量，金额报表
 * @author 若水
 *
 */
public class AreaDaySale {
    private Integer id;//主键

    private Integer soId;//一级片区主键

    private String soName;//一级片区名称

    private Integer regionId;//二级片区主键

    private String regionName;//二级片区名称

    private Integer districtId;//三级片区主键

    private String districtName;//三级片区名称

    private Integer citygroupId;//四级片区主键

    private String citygroupName;//四级片区名称

    private Integer cityId;//五级片区主键

    private String cityName;//五级片区名称
    
    private Integer seriesId;//系列品项主键

    private String seriesName;//系列品项名称

    private Date createTime;///创建时间

    private String reportTime;//报表统计时间

    private Integer saleNums;//订单数量

    private BigDecimal salePrices;//订单金额

    private Integer refundsNums;//订单退款数量

    private BigDecimal refundsPrices;//订单退款金额

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSoId() {
        return soId;
    }

    public void setSoId(Integer soId) {
        this.soId = soId;
    }

    public String getSoName() {
        return soName;
    }

    public void setSoName(String soName) {
        this.soName = soName == null ? null : soName.trim();
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName == null ? null : districtName.trim();
    }

    public Integer getCitygroupId() {
        return citygroupId;
    }

    public void setCitygroupId(Integer citygroupId) {
        this.citygroupId = citygroupId;
    }

    public String getCitygroupName() {
        return citygroupName;
    }

    public void setCitygroupName(String citygroupName) {
        this.citygroupName = citygroupName == null ? null : citygroupName.trim();
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public Integer getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(Integer seriesId) {
		this.seriesId = seriesId;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public Integer getSaleNums() {
        return saleNums;
    }

    public void setSaleNums(Integer saleNums) {
        this.saleNums = saleNums;
    }

    public BigDecimal getSalePrices() {
        return salePrices;
    }

    public void setSalePrices(BigDecimal salePrices) {
        this.salePrices = salePrices;
    }

    public Integer getRefundsNums() {
        return refundsNums;
    }

    public void setRefundsNums(Integer refundsNums) {
        this.refundsNums = refundsNums;
    }

    public BigDecimal getRefundsPrices() {
        return refundsPrices;
    }

    public void setRefundsPrices(BigDecimal refundsPrices) {
        this.refundsPrices = refundsPrices;
    }
}