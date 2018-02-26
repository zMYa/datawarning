package com.feihe.domain.report;

import java.math.BigDecimal;

/**
 * 门店月销量
 * @author 若水
 *
 */
public class ShopMonthSale {
    private Integer id;

    private Integer shopNo;//门店主键

    private String name;//会计月（yyyy-MM）

    private BigDecimal billMoney;//销售额

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShopNo() {
        return shopNo;
    }

    public void setShopNo(Integer shopNo) {
        this.shopNo = shopNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getBillMoney() {
        return billMoney;
    }

    public void setBillMoney(BigDecimal billMoney) {
        this.billMoney = billMoney;
    }
}