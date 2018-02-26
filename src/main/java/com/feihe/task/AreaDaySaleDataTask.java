package com.feihe.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.feihe.domain.report.AreaDaySale;
import com.feihe.service.sale.SaleService;

/**
 * 查询迈至数据库各片区前一天的销量汇总数据并导入本地库中
 * @author 若水
 *
 */
@Component
public class AreaDaySaleDataTask {
	
	@Resource
	private SaleService saleService;
	
	//@Scheduled(cron="0/25 * * * * ? ")
	@Scheduled(cron="30 08 21 * * ?")
	public void task(){
		
		//每天执行
		/*System.out.println("定时任务执行开始");
		
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date date = calendar.getTime();
		String day = new SimpleDateFormat("yyyy-MM-dd").format(date);
		List<AreaDaySale> areaDaySaleList = saleService.loadAreaDaySaleList(day);
		for(AreaDaySale sale : areaDaySaleList){
			int num = saleService.saveAreaDaySale(sale);
			System.out.println(num);
		}
		
		System.out.println("定时任务执行结束");*/
		
		
		
		//首次导入数据执行
		System.out.println("定时任务执行开始");
		
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DAY_OF_MONTH, -13);
		Date date = calendar.getTime();
		String day = new SimpleDateFormat("yyyy-MM-dd").format(date);
		while (!day.equals("2018-02-24")) {
			List<AreaDaySale> areaDaySaleList = null;
			try {
				areaDaySaleList = saleService.loadAreaDaySaleList(day);
				for(AreaDaySale sale : areaDaySaleList){
					int num = saleService.saveAreaDaySale(sale);
					System.out.println(num);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			Date date1 = calendar.getTime();
			day = new SimpleDateFormat("yyyy-MM-dd").format(date1);
		}
		
		System.out.println("定时任务执行结束");
	}
}
