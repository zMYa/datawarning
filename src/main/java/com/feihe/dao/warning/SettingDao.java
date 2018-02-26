package com.feihe.dao.warning;

import java.util.List;

import com.feihe.domain.warning.Setting;

/**
 * 预警设置数据库操作
 * @author 若水
 *
 */
public interface SettingDao {
    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(Setting record) throws Exception;

    int insertSelective(Setting record) throws Exception;

    Setting selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKeySelective(Setting record) throws Exception;

    int updateByPrimaryKey(Setting record) throws Exception;
    
    /**
     * 查询出所有预警配置信息
     * @return
     */
    List<Setting> selectAll() throws Exception;

    /**
     * 根据销量获取符合条件的记录（新客预警人数）
     * @param saleNum 销量
     * @return
     */
	Integer selectWarningNumBySaleNum(int saleNum) throws Exception;
}