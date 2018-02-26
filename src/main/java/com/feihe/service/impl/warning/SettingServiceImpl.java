package com.feihe.service.impl.warning;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.feihe.dao.warning.SettingDao;
import com.feihe.domain.warning.Setting;
import com.feihe.service.warning.SettingService;

@Service("settingService") 
public class SettingServiceImpl implements SettingService {

	@Resource
	private SettingDao settingDao; 
	
	@Override
	public List<Setting> getSettingList() throws Exception {
		// TODO Auto-generated method stub
		List<Setting> settingList = settingDao.selectAll();
		return settingList;
	}

	@Override
	public int settingAdd(Setting setting) throws Exception {
		// TODO Auto-generated method stub
		int res = settingDao.insertSelective(setting);
		return res;
	}

	@Override
	public int settingUpdate(Setting setting) throws Exception {
		// TODO Auto-generated method stub
		int res = settingDao.updateByPrimaryKeySelective(setting);
		return res;
	}

	@Override
	public int settingDelete(int id) throws Exception {
		// TODO Auto-generated method stub
		int res = settingDao.deleteByPrimaryKey(id);
		return res;
	}

}
