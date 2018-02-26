package com.feihe.service.warning;

import java.util.List;

import com.feihe.domain.tmp.StoreWarningBaseData;
import com.feihe.domain.tmp.StoreWarningData;

/**
 * 获取门店新客预警数据相关的业务逻辑
 * @author 若水
 *
 */
public interface DataService {
	
	/**
	 * 获取门店新客预警明细列表
	 * @param userName 用户名
	 * @param storeName 门店名称
	 * @param storeId 门店Id
	 * @param guideName 导购姓名
	 * @param guideId 导购Id
	 * @param pageIndex 获取第几页数据
	 * @param pageSize 每页多少数据
	 * @param month 统计月份
	 * @param nextAreaName 下级片区名称
	 * @param type 片区类别
	 * @param areaName 片区名称
	 * @return
	 */
	public List<StoreWarningData> getDataDetailList(String userName,
			String storeName, String storeId, String guideName,String guideId, Integer pageIndex,
			Integer pageSize, String areaName, String type, String nextAreaName, String tjMonth) throws Exception;

	/**
	 * 获取门店新客预警简要信息列表
	 * @param userName 用户名
	 * @param storeName 门店名称
	 * @param storeId 门店Id
	 * @param guideName 导购姓名
	 * @param guideId 导购Id
	 * @param pageIndex 获取第几页数据
	 * @param pageSize 每页多少数据
	 * @param month 统计月份
	 * @param nextAreaName 下级片区名称
	 * @param type 片区类别
	 * @param areaName 片区名称
	 * @return
	 */
	public List<StoreWarningBaseData> getDataList(String userName,
			String storeName, String storeId, String guideName,String guideId, Integer pageIndex,
			Integer pageSize, String areaName, String type, String nextAreaName, String tjMonth) throws Exception;

}
