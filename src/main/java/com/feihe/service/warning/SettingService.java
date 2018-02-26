package com.feihe.service.warning;

import java.util.List;

import com.feihe.domain.warning.Setting;

/**
 * 处理门店预警配置相关的业务逻辑
 * @author 若水
 *
 */
public interface SettingService {
	
	/**
	 * 获取门店预警配置列表
	 * @return
	 */
	public List<Setting> getSettingList() throws Exception;

	/**
	 * 添加预警配置信息
	 * @param setting 预警配置信息
	 * @return
	 */
	public int settingAdd(Setting setting) throws Exception;

	/**
	 * 修改预警配置信息
	 * @param setting 预警配置信息
	 * @return
	 */
	public int settingUpdate(Setting setting) throws Exception;
	
	/**
	 * 删除预警配置信息
	 * @param id 预警配置信息主键
	 * @return
	 */
	public int settingDelete(int id) throws Exception;
	
}
