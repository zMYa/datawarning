package com.feihe.service.sale;

import java.util.List;

import com.feihe.domain.report.AreaDaySale;
import com.feihe.domain.tmp.OrderTotal;
import com.feihe.domain.tmp.SaleDetailTotal;
import com.feihe.domain.tmp.SaleSeriesDetailTotal;
import com.feihe.domain.tmp.SaleTotal;
import com.feihe.domain.tmp.YesterdayNum;

/**
 * 获取地区的销售汇总数据
 * @author 若水
 *
 */
public interface SaleService {
	
	/**
	 * 根据区域Id获取该区域昨日订单数，销量
	 * @param areaId 区域主键
	 * @param areaType 片区类型 1 一级片区，2 二级片区，3 三级片区，4 四级片区，5 五级片区
	 * @return
	 */
	public YesterdayNum getYesterdayNums(int areaId,int areaType) throws Exception;
	
	/**
	 * 获取指定某天的所有迈至数据库销量情况统计数据
	 * @param day 日期 yyyy-MM-dd
	 * @return
	 */
	public List<AreaDaySale> loadAreaDaySaleList(String day) throws Exception;
	
	/**
	 * 保存数据
	 * @param areaDaySale 数据
	 * @return
	 */
	public int saveAreaDaySale(AreaDaySale areaDaySale) throws Exception;

	/**
	 * 获取某段时间的每天的销量情况
	 * @param areaId 区域主键
	 * @param areaType 片区类型 1 一级片区，2 二级片区，3 三级片区，4 四级片区，5 五级片区
	 * @param startDate 开始时间
	 * @param endDate 截止时间
	 * @return
	 */
	public List<SaleTotal> getSaleNums(int areaId, int areaType, String startDate,
			String endDate) throws Exception;

	/**
	 * 获取某区域下的某段时间的订单总数，单日最高数，日均数
	 * @param areaId 区域主键
	 * @param areaType 片区类型 1 一级片区，2 二级片区，3 三级片区，4 四级片区，5 五级片区
	 * @param startDate 开始时间
	 * @param endDate 截止时间
	 * @return
	 */
	public OrderTotal getOrderTotal(int areaId, int areaType,
			String startDate, String endDate) throws Exception;

	/**
	 * 获取某段时间的每天的销量情况（订单数，销量，退款订单数，退款销量）
	 * @param areaId 区域主键
	 * @param areaType 片区类型 1 一级片区，2 二级片区，3 三级片区，4 四级片区，5 五级片区
	 * @param startDate 开始时间
	 * @param endDate 截止时间
	 * @return
	 */
	public List<SaleDetailTotal> getSaleDetailNums(int areaId, int areaType,
			String startDate, String endDate) throws Exception;

	/**
	 * 获取某段时间的每月的销量情况（订单数，销量，退款订单数，退款销量）
	 * @param areaId 区域主键
	 * @param areaType 片区类型 1 一级片区，2 二级片区，3 三级片区，4 四级片区，5 五级片区
	 * @param startDate 开始时间 （月份）
	 * @param endDate 截止时间（月份）
	 * @return
	 */
	public List<SaleDetailTotal> getSaleDetailNumsByMonth(int areaId,
			int areaType, String startDate, String endDate) throws Exception;
	
	/**
	 * 获取某区域下的某段时间分品项每天的订单数，销量，退款订单数，退款销量
	 * @param areaId 区域主键
	 * @param areaType 片区类型 1 一级片区，2 二级片区，3 三级片区，4 四级片区，5 五级片区
	 * @param seriesId 品项id
	 * @param startDate 开始时间 （月份）
	 * @param endDate 截止时间（月份）
	 * @return
	 * @throws Exception
	 */
	public List<SaleSeriesDetailTotal> getSeriesSaleDetailNums(int areaId,
			int areaType, String seriesId, String startDate, String endDate) throws Exception;

	/**
	 * 获取某区域下的某段时间分品项每月的订单数，销量，退款订单数，退款销量
	 * @param areaId 区域主键
	 * @param areaType 片区类型 1 一级片区，2 二级片区，3 三级片区，4 四级片区，5 五级片区
	 * @param seriesId 品项id
	 * @param startDate 开始时间 （月份）
	 * @param endDate 截止时间（月份）
	 * @return
	 * @throws Exception
	 */
	public List<SaleSeriesDetailTotal> getSeriesSaleDetailNumsByMonth(
			int areaId, int areaType, String seriesId, String startDate,
			String endDate) throws Exception;
	

}
