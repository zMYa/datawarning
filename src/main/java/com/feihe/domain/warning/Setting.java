package com.feihe.domain.warning;

import java.util.Date;

/**
 * 数据预警配置表
 * @author 若水
 *
 */
public class Setting {
    private Integer id;//主键

    private Date createDate;//创建时间

    private Date updateDate;//更新时间

    private Integer storeNumMin;//门店最低销量
    
    private Integer storeNumMax;//门店最高销量

    private Integer warningNewNum;//预警新客人数

    private Integer createUserId;//创建人主键

    private String createUserName;//创建人名称

    private Integer updateUserId;//更新人主键

    private String updateUserName;//更新人名称

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getWarningNewNum() {
        return warningNewNum;
    }

    public void setWarningNewNum(Integer warningNewNum) {
        this.warningNewNum = warningNewNum;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName == null ? null : updateUserName.trim();
    }

	public Integer getStoreNumMin() {
		return storeNumMin;
	}

	public void setStoreNumMin(Integer storeNumMin) {
		this.storeNumMin = storeNumMin;
	}

	public Integer getStoreNumMax() {
		return storeNumMax;
	}

	public void setStoreNumMax(Integer storeNumMax) {
		this.storeNumMax = storeNumMax;
	}
}