package com.feihe.service.impl.sale;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.feihe.dao.report.AreaDaySaleDao;
import com.feihe.domain.report.AreaDaySale;
import com.feihe.domain.tmp.OrderTotal;
import com.feihe.domain.tmp.SaleDetailTotal;
import com.feihe.domain.tmp.SaleSeriesDetailTotal;
import com.feihe.domain.tmp.SaleTotal;
import com.feihe.domain.tmp.YesterdayNum;
import com.feihe.service.sale.SaleService;
import com.feihe.util.HttpClientUtil;
import com.feihe.util.PropertiesUtil;

@Service("baseService") 
public class SaleServiceImpl implements SaleService {

	@Resource
	private AreaDaySaleDao areaDaySaleDao;
	
	@Override
	public YesterdayNum getYesterdayNums(int areaId,int areaType) throws Exception {
		// TODO Auto-generated method stub
		YesterdayNum num = new YesterdayNum();
		num.setAreaId(areaId);
		num.setType(areaType);
		AreaDaySale sale = null;
		if(areaType==3){//三级片区
			sale = areaDaySaleDao.selectSaleNumsByDistrictId(areaId);
			num.setName(sale.getDistrictName());
		}else if(areaType==4){//四级片区
			sale = areaDaySaleDao.selectSaleNumsByCityGroupId(areaId);
			num.setName(sale.getCitygroupName());
		}else if(areaType==5){//五级片区
			sale = areaDaySaleDao.selectSaleNumsByCityId(areaId);
			num.setName(sale.getCityName());
		}
		num.setYesOrderNums(sale.getSaleNums()-sale.getRefundsNums());
		num.setYesSaleNums(sale.getSalePrices().add(sale.getRefundsPrices().multiply(new BigDecimal(-1))).intValue());
		return num;
	}
	
	@Override
	public List<AreaDaySale> loadAreaDaySaleList(String day) {
		// TODO Auto-generated method stub
		String path = this.getClass().getResource("/").getPath()+"interface.properties";
		String url = PropertiesUtil.loadProperties(path,"areaDaySaleUrl");

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
        nvps.add(new BasicNameValuePair("day", day));  
        
		String text = HttpClientUtil.post(url, nvps);
		List<AreaDaySale> list = JSONArray.parseArray(text, AreaDaySale.class);
		return list;
	}

	@Override
	public int saveAreaDaySale(AreaDaySale areaDaySale) throws Exception {
		// TODO Auto-generated method stub
		areaDaySale.setCreateTime(new Date());
		int i = areaDaySaleDao.insert(areaDaySale);
		return i;
	}

	@Override
	public List<SaleTotal> getSaleNums(int areaId, int areaType, String startDate,
			String endDate) throws Exception {
		// TODO Auto-generated method stub
		List<SaleTotal> saleList=new ArrayList<SaleTotal>();
		
		List<AreaDaySale> list = null;
		if(areaType==3){//三级片区
			list = areaDaySaleDao.selectSaleNumsByDistrictIdTime(areaId,startDate,endDate);
		}else if(areaType==4){//四级片区
			list = areaDaySaleDao.selectSaleNumsByCityGroupIdTime(areaId,startDate,endDate);
		}else if(areaType==5){//五级片区
			list = areaDaySaleDao.selectSaleNumsByCityIdTime(areaId,startDate,endDate);
		}
		for(AreaDaySale areaDaySale : list){
			SaleTotal saleTotal = new SaleTotal();
			saleTotal.setAreaId(areaId);
			saleTotal.setType(areaType);
			saleTotal.setOrderNums(areaDaySale.getSaleNums()-areaDaySale.getRefundsNums());
			saleTotal.setSaleNums(areaDaySale.getSalePrices().add(areaDaySale.getRefundsPrices().multiply(new BigDecimal(-1))).intValue());
			saleTotal.setDate(new SimpleDateFormat("yyyy-MM-dd").format(areaDaySale.getReportTime()));
			if(areaType==3){//三级片区
				saleTotal.setName(areaDaySale.getDistrictName());
			}else if(areaType==4){//四级片区
				saleTotal.setName(areaDaySale.getCitygroupName());
			}else if(areaType==5){//五级片区
				saleTotal.setName(areaDaySale.getCityName());
			}
			saleList.add(saleTotal);
		}
		
		return saleList;
	}

	@Override
	public OrderTotal getOrderTotal(int areaId, int areaType, String startDate,
			String endDate) throws Exception {
		// TODO Auto-generated method stub
		
		List<SaleTotal> saleList=this.getSaleNums(areaId, areaType, startDate, endDate);
		OrderTotal orderTotal = new OrderTotal();
		orderTotal.setAreaId(areaId);
		orderTotal.setType(areaType);
		orderTotal.setStartDate(startDate);
		orderTotal.setEndDate(endDate);
		orderTotal.setName(saleList.get(0).getName());
		
		int total=0;//订单总数
		String maxDay = null;//最大日期
		int maxTotal = 0;//最大订单数
		for(SaleTotal saleTotal : saleList){
			total+=saleTotal.getOrderNums();
			if(saleTotal.getOrderNums()>maxTotal){
				maxTotal=saleTotal.getOrderNums();
				maxDay=saleTotal.getDate();
			}
		}
		
		orderTotal.setOrderTotal(total);
		orderTotal.setAverageDay(total/saleList.size());
		orderTotal.setOrderMaxDay(maxDay);
		orderTotal.setOrderMaxTotal(maxTotal);
		return orderTotal;
	}

	@Override
	public List<SaleDetailTotal> getSaleDetailNums(int areaId, int areaType,
			String startDate, String endDate) throws Exception {
		// TODO Auto-generated method stub
		List<SaleDetailTotal> saleList=new ArrayList<SaleDetailTotal>();
		
		List<AreaDaySale> list = null;
		if(areaType==3){//三级片区
			list = areaDaySaleDao.selectSaleNumsByDistrictIdTime(areaId,startDate,endDate);
		}else if(areaType==4){//四级片区
			list = areaDaySaleDao.selectSaleNumsByCityGroupIdTime(areaId,startDate,endDate);
		}else if(areaType==5){//五级片区
			list = areaDaySaleDao.selectSaleNumsByCityIdTime(areaId,startDate,endDate);
		}
		for(AreaDaySale areaDaySale : list){
			SaleDetailTotal saleTotal = new SaleDetailTotal();
			saleTotal.setAreaId(areaId);
			saleTotal.setType(areaType);
			saleTotal.setOrderNums(areaDaySale.getSaleNums());
			saleTotal.setSaleNums(areaDaySale.getSalePrices().intValue());
			saleTotal.setRefundsNums(areaDaySale.getRefundsNums());
			saleTotal.setRefundsPrices(areaDaySale.getRefundsPrices().intValue());
			saleTotal.setDate(areaDaySale.getReportTime().substring(0,10));
			if(areaType==3){//三级片区
				saleTotal.setName(areaDaySale.getDistrictName());
			}else if(areaType==4){//四级片区
				saleTotal.setName(areaDaySale.getCitygroupName());
			}else if(areaType==5){//五级片区
				saleTotal.setName(areaDaySale.getCityName());
			}
			saleList.add(saleTotal);
		}
		return saleList;
	}

	@Override
	public List<SaleDetailTotal> getSaleDetailNumsByMonth(int areaId,
			int areaType, String startDate, String endDate) throws Exception {
		// TODO Auto-generated method stub
		List<SaleDetailTotal> saleList=new ArrayList<SaleDetailTotal>();
		
		List<AreaDaySale> list = null;
		if(areaType==3){//三级片区
			list = areaDaySaleDao.selectSaleNumsByDistrictIdMonthTime(areaId,startDate,endDate);
		}else if(areaType==4){//四级片区
			list = areaDaySaleDao.selectSaleNumsByCityGroupIdMonthTime(areaId,startDate,endDate);
		}else if(areaType==5){//五级片区
			list = areaDaySaleDao.selectSaleNumsByCityIdMonthTime(areaId,startDate,endDate);
		}
		for(AreaDaySale areaDaySale : list){
			SaleDetailTotal saleTotal = new SaleDetailTotal();
			saleTotal.setAreaId(areaId);
			saleTotal.setType(areaType);
			saleTotal.setOrderNums(areaDaySale.getSaleNums());
			saleTotal.setSaleNums(areaDaySale.getSalePrices().intValue());
			saleTotal.setRefundsNums(areaDaySale.getRefundsNums());
			saleTotal.setRefundsPrices(areaDaySale.getRefundsPrices().intValue());
			saleTotal.setDate(areaDaySale.getReportTime());
			if(areaType==3){//三级片区
				saleTotal.setName(areaDaySale.getDistrictName());
			}else if(areaType==4){//四级片区
				saleTotal.setName(areaDaySale.getCitygroupName());
			}else if(areaType==5){//五级片区
				saleTotal.setName(areaDaySale.getCityName());
			}
			saleList.add(saleTotal);
		}
		return saleList;
	}
	
	@Override
	public List<SaleSeriesDetailTotal> getSeriesSaleDetailNums(int areaId, int areaType,String seriesId,
			String startDate, String endDate) throws Exception {
		// TODO Auto-generated method stub
		List<SaleSeriesDetailTotal> saleList=new ArrayList<SaleSeriesDetailTotal>();
		
		Integer id = null;
		
		if(seriesId!=null&&!seriesId.equals("")){
			id = Integer.parseInt(seriesId);
		}
		
		List<AreaDaySale> list = null;
		if(areaType==3){//三级片区
			list = areaDaySaleDao.selectSeriesSaleNumsByDistrictIdTime(areaId,id,startDate,endDate);
		}else if(areaType==4){//四级片区
			list = areaDaySaleDao.selectSeriesSaleNumsByCityGroupIdTime(areaId,id,startDate,endDate);
		}else if(areaType==5){//五级片区
			list = areaDaySaleDao.selectSeriesSaleNumsByCityIdTime(areaId,id,startDate,endDate);
		}
		for(AreaDaySale areaDaySale : list){
			SaleSeriesDetailTotal saleTotal = new SaleSeriesDetailTotal();
			saleTotal.setAreaId(areaId);
			saleTotal.setType(areaType);
			saleTotal.setSeriesId(areaDaySale.getSeriesId());
			saleTotal.setSeriesName(areaDaySale.getSeriesName());
			saleTotal.setOrderNums(areaDaySale.getSaleNums());
			saleTotal.setSaleNums(areaDaySale.getSalePrices().intValue());
			saleTotal.setRefundsNums(areaDaySale.getRefundsNums());
			saleTotal.setRefundsPrices(areaDaySale.getRefundsPrices().intValue());
			saleTotal.setDate(areaDaySale.getReportTime().substring(0, 10));
			if(areaType==3){//三级片区
				saleTotal.setName(areaDaySale.getDistrictName());
			}else if(areaType==4){//四级片区
				saleTotal.setName(areaDaySale.getCitygroupName());
			}else if(areaType==5){//五级片区
				saleTotal.setName(areaDaySale.getCityName());
			}
			saleList.add(saleTotal);
		}
		return saleList;
	}

	@Override
	public List<SaleSeriesDetailTotal> getSeriesSaleDetailNumsByMonth(
			int areaId, int areaType, String seriesId, String startDate,
			String endDate) throws Exception {
		// TODO Auto-generated method stub
		List<SaleSeriesDetailTotal> saleList=new ArrayList<SaleSeriesDetailTotal>();
		
		Integer id = null;
		
		if(seriesId!=null&&!seriesId.equals("")){
			id = Integer.parseInt(seriesId);
		}
		
		List<AreaDaySale> list = null;
		if(areaType==3){//三级片区
			list = areaDaySaleDao.selectSeriesSaleNumsByDistrictIdMonthTime(areaId,id,startDate,endDate);
		}else if(areaType==4){//四级片区
			list = areaDaySaleDao.selectSeriesSaleNumsByCityGroupIdMonthTime(areaId,id,startDate,endDate);
		}else if(areaType==5){//五级片区
			list = areaDaySaleDao.selectSeriesSaleNumsByCityIdMonthTime(areaId,id,startDate,endDate);
		}
		for(AreaDaySale areaDaySale : list){
			SaleSeriesDetailTotal saleTotal = new SaleSeriesDetailTotal();
			saleTotal.setAreaId(areaId);
			saleTotal.setType(areaType);
			saleTotal.setSeriesId(areaDaySale.getSeriesId());
			saleTotal.setSeriesName(areaDaySale.getSeriesName());
			saleTotal.setOrderNums(areaDaySale.getSaleNums());
			saleTotal.setSaleNums(areaDaySale.getSalePrices().intValue());
			saleTotal.setRefundsNums(areaDaySale.getRefundsNums());
			saleTotal.setRefundsPrices(areaDaySale.getRefundsPrices().intValue());
			saleTotal.setDate(areaDaySale.getReportTime());
			if(areaType==3){//三级片区
				saleTotal.setName(areaDaySale.getDistrictName());
			}else if(areaType==4){//四级片区
				saleTotal.setName(areaDaySale.getCitygroupName());
			}else if(areaType==5){//五级片区
				saleTotal.setName(areaDaySale.getCityName());
			}
			saleList.add(saleTotal);
		}
		return saleList;
	}

}
