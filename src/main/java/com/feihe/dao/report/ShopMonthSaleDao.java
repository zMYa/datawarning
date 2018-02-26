package com.feihe.dao.report;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.feihe.domain.report.ShopMonthSale;

public interface ShopMonthSaleDao {
    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(ShopMonthSale record) throws Exception;

    int insertSelective(ShopMonthSale record) throws Exception;

    ShopMonthSale selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKeySelective(ShopMonthSale record) throws Exception;

    int updateByPrimaryKey(ShopMonthSale record) throws Exception;

    /**
     * 查询门店月销量列表
     * @param storeSet 门店主键集合
     * @return
     */
	List<ShopMonthSale> selectByStoreIds(@Param("set")Set<Integer> storeSet) throws Exception;
}