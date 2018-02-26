package com.feihe.service.impl.sale;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.feihe.dao.report.ShopMonthSaleDao;
import com.feihe.domain.report.ShopMonthSale;
import com.feihe.service.sale.ShopSaleService;
import com.feihe.util.HttpClientUtil;
import com.feihe.util.PropertiesUtil;

@Service("shopSaleService") 
public class ShopSaleServiceImpl implements ShopSaleService {

	@Resource
	private ShopMonthSaleDao shopMonthSaleDao;
	
	@Override
	public List<ShopMonthSale> loadShopMonthSaleList(String month) {
		// TODO Auto-generated method stub
		String path = this.getClass().getResource("/").getPath()+"interface.properties";
		String url = PropertiesUtil.loadProperties(path,"shopMonthSaleUrl");

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
        nvps.add(new BasicNameValuePair("month", month));  
        
		String text = HttpClientUtil.post(url, nvps);
		List<ShopMonthSale> list = JSONArray.parseArray(text, ShopMonthSale.class);
		return list;
	}

	@Override
	public int saveShopMonthSale(ShopMonthSale shopMonthSale) throws Exception {
		// TODO Auto-generated method stub
		int i = shopMonthSaleDao.insert(shopMonthSale);
		return i;
	}

}
