package com.feihe.service.impl.warning;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.feihe.dao.report.ShopMonthSaleDao;
import com.feihe.dao.warning.SettingDao;
import com.feihe.domain.report.ShopMonthSale;
import com.feihe.domain.tmp.Area;
import com.feihe.domain.tmp.StoreWarningBaseData;
import com.feihe.domain.tmp.StoreWarningData;
import com.feihe.domain.warning.Setting;
import com.feihe.service.warning.DataService;
import com.feihe.util.HttpClientUtil;
import com.feihe.util.PropertiesUtil;

@Service("dataService") 
public class DataServiceImpl implements DataService {
	
	@Resource
	private SettingDao settingDao;
	@Resource
	private ShopMonthSaleDao shopMonthSaleDao;
	
	@Override
	public List<StoreWarningData> getDataDetailList(String userName,
			String storeName, String storeId, String guideName,
			String guideId, Integer pageIndex, Integer pageSize, 
			String areaName, String type, String nextAreaName, String tjMonth) throws Exception {
		// TODO Auto-generated method stub
		String path = this.getClass().getResource("/").getPath()+"interface.properties";
		
		//调用迈至数据库服务接口，根据用户名获取该用户管辖的区域列表
		String areaListUrl = PropertiesUtil.loadProperties(path, "getUserAreaListUrl");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("userName", userName));
		String areaListStr = HttpClientUtil.post(areaListUrl, nvps);
		if(areaListStr==null||areaListStr.trim().equals("")){
			return null;
		}
		List<Area> areaList = JSON.parseArray(areaListStr, Area.class);
		
		List<Integer> areaIdList = new ArrayList<Integer>();
		for(Area area : areaList){
			areaIdList.add(area.getAreaId());
		}
		//调用点开sdc数据库服务接口，把迈至区域列表转化成点开区域列表
		String changeAreaListUrl = PropertiesUtil.loadProperties(path, "changeAreaListUrl");
		nvps.clear();
		nvps.add(new BasicNameValuePair("areaList", JSON.toJSONString(areaIdList)));
		String changeAreaList = HttpClientUtil.post(changeAreaListUrl, nvps);
		if(changeAreaList==null||changeAreaList.trim().equals("")){
			return null;
		}
		List<Integer> dkAreaList = JSON.parseArray(changeAreaList, Integer.class);
		
		//调用点开sdc数据库服务接口，获取预警数据
		String warningDataUrl = PropertiesUtil.loadProperties(path, "warningDataUrl");
		nvps.clear();
		nvps.add(new BasicNameValuePair("areaList", JSON.toJSONString(dkAreaList)));
		nvps.add(new BasicNameValuePair("storeName", storeName));
		nvps.add(new BasicNameValuePair("storeId", storeId));
		nvps.add(new BasicNameValuePair("guideName", guideName));
		nvps.add(new BasicNameValuePair("guideId", guideId));
		nvps.add(new BasicNameValuePair("pageIndex", String.valueOf(pageIndex)));
		nvps.add(new BasicNameValuePair("pageSize", String.valueOf(pageSize)));
		
		nvps.add(new BasicNameValuePair("month", tjMonth));
		
		String warningDataStr = HttpClientUtil.post(warningDataUrl, nvps);
		if(warningDataStr==null||warningDataStr.trim().equals("")){
			return null;
		}
		List<StoreWarningData> dataList = JSON.parseArray(warningDataStr, StoreWarningData.class);
		
		//调用点开scrm数据库服务接口，获取所有贺礼活动表 <id,title>
		String campaignKVUrl = PropertiesUtil.loadProperties(path, "campaignKVUrl");
		String campaignKVStr = HttpClientUtil.get(campaignKVUrl);
		if(campaignKVStr==null||campaignKVStr.trim().equals("")){
			return null;
		}
		Map<Integer, String> campaignMap = (Map<Integer, String>)JSON.parse(campaignKVStr);
		
		//把月份转化为日期范围,活动id转换为活动名称
		for(StoreWarningData data : dataList){
			String dataStr = data.getFinishMonth();
			String year = dataStr.substring(0, 4);
			String month = dataStr.substring(5,7);
			Calendar calendar = new GregorianCalendar();
			calendar.set(Calendar.YEAR, Integer.parseInt(year));
			calendar.set(Calendar.MONTH,Integer.parseInt(month)-2);
			Date date = calendar.getTime();
			String startDate = new SimpleDateFormat("yyyy-MM").format(date)+"-26 00:00:00";
			String endDate = dataStr+"-25 23:59:59";
			data.setStartDate(startDate);
			data.setEndDate(endDate);
			data.setActiveName(campaignMap.get(data.getActiveId()));
		}
		
		//调用emp接口，获取emp连续三个月的门店月均销量列表.....
		Map<String, Double> saleAvgNumMap = new HashMap<String, Double>();//<门店id,年份-月份,月均销量>
		Map<String, Double> saleMonthNumMap = new HashMap<String, Double>();//<门店id,年份-月份,月销量>
		Set<Integer> storeSet = new HashSet<Integer>();
		for(StoreWarningData data : dataList){
			storeSet.add(data.getStoreId());
		}
		
		List<ShopMonthSale> shopMonthSaleList = shopMonthSaleDao.selectByStoreIds(storeSet);
		for(ShopMonthSale sale : shopMonthSaleList){
			saleMonthNumMap.put(sale.getShopNo()+","+sale.getName(), sale.getBillMoney().doubleValue());
		}
		
		Set<Entry<String, Double>> saleMonthNumSet = saleMonthNumMap.entrySet();
		Iterator<Entry<String, Double>> saleMonthNumIt = saleMonthNumSet.iterator();
		while (saleMonthNumIt.hasNext()) {
			Entry<String, Double> entry = saleMonthNumIt.next();
			String key = entry.getKey();//门店id,年份-月份
			Double total1 = entry.getValue();//月销量
			String shopId = key.split(",")[0];//门店id
			String month1 = key.split(",")[1];//年份-月份
			Calendar calendar = new GregorianCalendar();
			calendar.set(Calendar.YEAR, Integer.parseInt(month1.split("-")[0]));
			calendar.set(Calendar.MONTH, Integer.parseInt(month1.split("-")[1])-1);
			
			calendar.add(Calendar.MONTH, -1);
			String month2 = new SimpleDateFormat("yyyy-MM").format(calendar.getTime());
			Double total2 = saleMonthNumMap.get(shopId+","+month2);
			if(total2==null){
				total2=0d;
			}
			
			calendar.add(Calendar.MONTH, -1);
			String month3 = new SimpleDateFormat("yyyy-MM").format(calendar.getTime());
			Double total3 = saleMonthNumMap.get(shopId+","+month3);
			if(total3==null){
				total3=0d;
			}
			Double avgTotal = (total1+total2+total3)/3;
			saleAvgNumMap.put(key, avgTotal);
		}
		
		
		Map<String, Integer> warningNumMap = new HashMap<String, Integer>();//<门店id,年份-月份,预警新客人数>
		//获取所有预警信息
		List<Setting> settingList = settingDao.selectAll();
		Set<Entry<String, Double>> set = saleAvgNumMap.entrySet();
		Iterator<Entry<String, Double>> it = set.iterator();
		while (it.hasNext()) {
			Entry<String,Double> entry = it.next();
			String key = entry.getKey();//门店id,年份-月份
			Double value = entry.getValue();//销量
			loop:for(Setting setting : settingList){
				Integer min = setting.getStoreNumMin();//预警最小值
				Integer max = setting.getStoreNumMax();//预警最大值
				if(max==null){//null代表最大值
					max=999999999;
				}
				if(value>=min&&value<=max){
					warningNumMap.put(key, setting.getWarningNewNum());
					break loop;
				}
			}
		}
		
		//设置门店预警新客人数-门店预警标准 （每个月的预警标准不一样）
		//计算每个门店的新客开发总人数
		Map<String, Integer> newNumMap = new HashMap<String, Integer>();//<门店id,年份-月份,新客开发总人数>
		for(StoreWarningData data : dataList){
			String month = data.getFinishMonth();
			data.setStandardNum(warningNumMap.get(data.getStoreId()+","+month));//设置门店预警新客人数
			Integer newNum = newNumMap.get(data.getStoreId()+","+month);
			if(newNum==null){
				newNumMap.put(data.getStoreId()+","+month, data.getNewNum());
			}else{
				newNumMap.put(data.getStoreId()+","+month, newNum+data.getNewNum());
			}
		}
		
		//设置超出预警线新客人数
		for(StoreWarningData data : dataList){
			String month = data.getFinishMonth();
			data.setOutNum(newNumMap.get(data.getStoreId()+","+month)-data.getStandardNum());
		}
		
		//去除未超出预警线数据
		Iterator<StoreWarningData> storeWarningIt = dataList.iterator();
		while (storeWarningIt.hasNext()) {
			StoreWarningData data = storeWarningIt.next();
			if (data.getOutNum()<=0) {
				storeWarningIt.remove();
			}
		}
		
		//去除不符合条件的记录
		if(areaName!=null && !areaName.equals("") && type!=null && !type.equals("")){//用户查询管辖的某个片区下的预警数据
			if(type.equals("3")){//三级片区
				if(nextAreaName!=null && !nextAreaName.equals("")){//查询某个子片区
					storeWarningIt = dataList.iterator();
					while (storeWarningIt.hasNext()) {
						StoreWarningData data = storeWarningIt.next();
						if (!data.getProvinceName().equals(areaName) || !data.getCityName().equals(nextAreaName)) {
							storeWarningIt.remove();
						}
					}
				}else{
					storeWarningIt = dataList.iterator();
					while (storeWarningIt.hasNext()) {
						StoreWarningData data = storeWarningIt.next();
						if (!data.getProvinceName().equals(areaName)) {
							storeWarningIt.remove();
						}
					}
				}
			}if(type.equals("4")){//四级片区
				if(nextAreaName!=null && !nextAreaName.equals("")){//查询某个子片区
					storeWarningIt = dataList.iterator();
					while (storeWarningIt.hasNext()) {
						StoreWarningData data = storeWarningIt.next();
						if (!data.getCityName().equals(areaName) || !data.getDistrictName().equals(nextAreaName)) {
							storeWarningIt.remove();
						}
					}
				}else{
					storeWarningIt = dataList.iterator();
					while (storeWarningIt.hasNext()) {
						StoreWarningData data = storeWarningIt.next();
						if (!data.getCityName().equals(areaName)) {
							storeWarningIt.remove();
						}
					}
				}
			}if(type.equals("5")){//五级片区
				storeWarningIt = dataList.iterator();
				while (storeWarningIt.hasNext()) {
					StoreWarningData data = storeWarningIt.next();
					if (!data.getDistrictName().equals(areaName)) {
						storeWarningIt.remove();
					}
				}
			}
		}
		
		return dataList;
	}

	@Override
	public List<StoreWarningBaseData> getDataList(String userName,
			String storeName, String storeId, String guideName, String guideId,
			Integer pageIndex, Integer pageSize, String areaName, String type,
			String nextAreaName, String tjMonth) throws Exception {
		// TODO Auto-generated method stub
		List<StoreWarningData>  dataDetailList = this.getDataDetailList(userName, storeName, storeId, guideName, guideId, pageIndex, pageSize, areaName, type, nextAreaName, tjMonth);
		
		Map<String, StoreWarningBaseData> map = new HashMap<String, StoreWarningBaseData>();// k=门店id,年-月
		
		for(StoreWarningData data : dataDetailList){
			String key = data.getStoreId()+","+data.getFinishMonth();
			if(map.containsKey(key)){
				StoreWarningBaseData baseData = map.get(key);
				baseData.setNewNum(baseData.getNewNum()+data.getNewNum());
			}else{
				StoreWarningBaseData baseData = new StoreWarningBaseData();
				baseData.setCityName(data.getCityName());
				baseData.setDistrictName(data.getDistrictName());
				baseData.setEndDate(data.getEndDate());
				baseData.setFinishMonth(data.getFinishMonth());
				baseData.setNewNum(data.getNewNum());
				baseData.setOutNum(data.getOutNum());
				baseData.setProvinceName(data.getProvinceName());
				baseData.setStandardNum(data.getStandardNum());
				baseData.setStartDate(data.getStartDate());
				baseData.setStoreId(data.getStoreId());
				baseData.setStoreName(data.getStoreName());
				map.put(key, baseData);
			}
		}
		
		List<StoreWarningBaseData> list = new ArrayList<StoreWarningBaseData>();
		Set<Entry<String, StoreWarningBaseData>> set = map.entrySet();
		Iterator<Entry<String, StoreWarningBaseData>> it = set.iterator();
		while (it.hasNext()) {
			Entry<String, StoreWarningBaseData> entry = it.next();
			list.add(entry.getValue());
		}
		
		return list;
	} 
	
}
