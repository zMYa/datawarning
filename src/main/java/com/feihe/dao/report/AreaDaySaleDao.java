package com.feihe.dao.report;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.feihe.domain.report.AreaDaySale;

public interface AreaDaySaleDao {
    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(AreaDaySale record) throws Exception;

    int insertSelective(AreaDaySale record) throws Exception;

    AreaDaySale selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKeySelective(AreaDaySale record) throws Exception;

    int updateByPrimaryKey(AreaDaySale record) throws Exception;

    /**
     * 根据三级片区查询销售统计信息
     * @param districtId 三级片区主键
     * @return
     */
	AreaDaySale selectSaleNumsByDistrictId(Integer districtId) throws Exception;

	/**
	 * 根据四级片区查询销售统计信息
	 * @param cityGroupId 四级片区主键
	 * @return
	 */
	AreaDaySale selectSaleNumsByCityGroupId(Integer cityGroupId) throws Exception;

	/**
	 * 根据五级片区查询销售统计信息
	 * @param cityId 五级片区主键
	 * @return
	 */
	AreaDaySale selectSaleNumsByCityId(Integer cityId) throws Exception;
	
	/**
	 * 查询某段时间三级片区的销售统计信息
	 * @param districtId 三级片区主键
	 * @param startDate 开始时间
	 * @param endDate 截止时间
	 * @return
	 */
	List<AreaDaySale> selectSaleNumsByDistrictIdTime(@Param("districtId")Integer districtId,
			@Param("startDate")String startDate, @Param("endDate")String endDate) throws Exception;

	/**
	 * 查询某段时间四级片区的销售统计信息
	 * @param cityGroupId 四级片区主键
	 * @param startDate 开始时间
	 * @param endDate 截止时间
	 * @return
	 */
	List<AreaDaySale> selectSaleNumsByCityGroupIdTime(@Param("cityGroupId")Integer cityGroupId,
			@Param("startDate")String startDate, @Param("endDate")String endDate) throws Exception;

	/**
	 * 查询某段时间五级片区的销售统计信息
	 * @param cityId 五级片区主键
	 * @param startDate 开始时间
	 * @param endDate 截止时间
	 * @return
	 */
	List<AreaDaySale> selectSaleNumsByCityIdTime(@Param("cityId")Integer cityId, @Param("startDate")String startDate,
			@Param("endDate")String endDate) throws Exception;

	/**
	 * 查询某段时间三级片区的销售统计信息
	 * @param districtId 三级片区主键
	 * @param startDate 开始时间(月份)
	 * @param endDate 截止时间(月份)
	 * @return
	 */
	List<AreaDaySale> selectSaleNumsByDistrictIdMonthTime(@Param("districtId")Integer districtId,
			@Param("startDate")String startDate, @Param("endDate")String endDate) throws Exception;

	/**
	 * 查询某段时间四级片区的销售统计信息
	 * @param cityGroupId 四级片区主键
	 * @param startDate 开始时间(月份)
	 * @param endDate 截止时间(月份)
	 * @return
	 */
	List<AreaDaySale> selectSaleNumsByCityGroupIdMonthTime(@Param("cityGroupId")Integer cityGroupId,
			@Param("startDate")String startDate, @Param("endDate")String endDate) throws Exception;

	/**
	 * 查询某段时间五级片区的销售统计信息
	 * @param cityId 五级片区主键
	 * @param startDate 开始时间(月份)
	 * @param endDate 截止时间(月份)
	 * @return
	 */
	List<AreaDaySale> selectSaleNumsByCityIdMonthTime(@Param("cityId")Integer cityId,
			@Param("startDate")String startDate, @Param("endDate")String endDate) throws Exception;

	/**
	 * 查询某段时间三级片区的销售统计信息
	 * @param districtId 三级片区主键
	 * @param seriesId 品项id
	 * @param startDate 开始时间
	 * @param endDate 截止时间
	 * @return
	 */
	List<AreaDaySale> selectSeriesSaleNumsByDistrictIdTime(@Param("districtId")Integer districtId,
			@Param("seriesId")Integer seriesId, @Param("startDate")String startDate, @Param("endDate")String endDate) throws Exception;

	/**
	 * 查询某段时间四级片区的销售统计信息
	 * @param cityGroupId 四级片区主键
	 * @param seriesId 品项id
	 * @param startDate 开始时间
	 * @param endDate 截止时间
	 * @return
	 */
	List<AreaDaySale> selectSeriesSaleNumsByCityGroupIdTime(@Param("cityGroupId")Integer cityGroupId,
			@Param("seriesId")Integer seriesId, @Param("startDate")String startDate, @Param("endDate")String endDate) throws Exception;

	/**
	 * 查询某段时间五级片区的销售统计信息
	 * @param cityId 五级片区主键
	 * @param seriesId 品项id
	 * @param startDate 开始时间
	 * @param endDate 截止时间
	 * @return
	 */
	List<AreaDaySale> selectSeriesSaleNumsByCityIdTime(@Param("cityId")Integer cityId,
			@Param("seriesId")Integer seriesId, @Param("startDate")String startDate, @Param("endDate")String endDate) throws Exception;
	
	/**
	 * 查询某段时间三级片区的销售统计信息
	 * @param districtId 三级片区主键
	 * @param seriesId 品项id
	 * @param startDate 开始时间(月份)
	 * @param endDate 截止时间(月份)
	 * @return
	 */
	List<AreaDaySale> selectSeriesSaleNumsByDistrictIdMonthTime(@Param("districtId")Integer districtId,
			@Param("seriesId")Integer seriesId, @Param("startDate")String startDate, @Param("endDate")String endDate) throws Exception;

	/**
	 * 查询某段时间四级片区的销售统计信息
	 * @param cityGroupId 四级片区主键
	 * @param seriesId 品项id
	 * @param startDate 开始时间(月份)
	 * @param endDate 截止时间(月份)
	 * @return
	 */
	List<AreaDaySale> selectSeriesSaleNumsByCityGroupIdMonthTime(@Param("cityGroupId")Integer cityGroupId,
			@Param("seriesId")Integer seriesId, @Param("startDate")String startDate, @Param("endDate")String endDate) throws Exception;

	/**
	 * 查询某段时间五级片区的销售统计信息
	 * @param cityId 五级片区主键
	 * @param seriesId 品项id
	 * @param startDate 开始时间(月份)
	 * @param endDate 截止时间(月份)
	 * @return
	 */
	List<AreaDaySale> selectSeriesSaleNumsByCityIdMonthTime(@Param("cityId")Integer cityId,
			@Param("seriesId")Integer seriesId, @Param("startDate")String startDate, @Param("endDate")String endDate) throws Exception;
}