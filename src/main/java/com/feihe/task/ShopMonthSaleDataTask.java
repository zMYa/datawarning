package com.feihe.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.feihe.domain.report.ShopMonthSale;
import com.feihe.service.sale.ShopSaleService;

/**
 * 查询emp数据库门店月销量数据并导入本地库中
 * @author 若水
 *
 */
@Component
public class ShopMonthSaleDataTask {
	
	@Resource
	private ShopSaleService shopSaleService;
	
	@Scheduled(cron="0 49 14 * * ?")
	//@Scheduled(cron="0/25 * * * * ? ")
	//@Scheduled(cron="0 0 2 26 * ?") 每月执行
	public void task(){
		
		//每月执行
		System.out.println("定时任务执行开始");
		
		Calendar calendar = new GregorianCalendar();
		Date date = calendar.getTime();
		String month = new SimpleDateFormat("yyyy-MM").format(date);
		List<ShopMonthSale> shopMonthSaleList;
		try {
			shopMonthSaleList = shopSaleService.loadShopMonthSaleList(month);
			for(ShopMonthSale sale : shopMonthSaleList){
				int num = shopSaleService.saveShopMonthSale(sale);
				System.out.println(num);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("定时任务执行结束");
		
		
		
		//首次导入数据执行
		/*System.out.println("定时任务执行开始");
		
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.MONTH, -120);
		Date date = calendar.getTime();
		String month = new SimpleDateFormat("yyyy-MM").format(date);
		while (!month.equals("2018-02")) {
			List<ShopMonthSale> shopMonthSaleList = shopSaleService.loadShopMonthSaleList(month);
			for(ShopMonthSale sale : shopMonthSaleList){
				int num = shopSaleService.saveShopMonthSale(sale);
				System.out.println(num);
			}
			calendar.add(Calendar.MONTH, 1);
			Date date1 = calendar.getTime();
			month = new SimpleDateFormat("yyyy-MM").format(date1);
		}
		
		System.out.println("定时任务执行结束");*/
	}
}
