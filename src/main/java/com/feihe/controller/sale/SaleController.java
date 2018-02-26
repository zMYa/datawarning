package com.feihe.controller.sale;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feihe.domain.tmp.OrderTotal;
import com.feihe.domain.tmp.SaleDetailTotal;
import com.feihe.domain.tmp.SaleSeriesDetailTotal;
import com.feihe.domain.tmp.SaleTotal;
import com.feihe.domain.tmp.YesterdayNum;
import com.feihe.service.sale.SaleService;

/**
 * 获取销售统计相关数据
 * @author 若水
 *
 */
@Controller  
@RequestMapping("/sale")  
public class SaleController {  
    
	@Resource  
    private SaleService saleService;
	
	/**
     * 获取某区域下的昨日订单数、销量（已减去退款订单）
     * @param request
     * @return
     */
    @RequestMapping(value="/getYesterdayNums")
    @ResponseBody
    public YesterdayNum getYesterdayNums(HttpServletRequest request){
    	String areaId = request.getParameter("areaId");
    	String type = request.getParameter("type");
    	YesterdayNum yesterdayNum = null;
		try {
			yesterdayNum = this.saleService.getYesterdayNums(Integer.parseInt(areaId), Integer.parseInt(type));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return yesterdayNum;
    }
    
    /**
     * 获取某区域下的某段时间每天的订单数，销量（已减去退款订单）
     * @param request
     * @return
     */
    @RequestMapping(value="/getSaleNums")
    @ResponseBody
    public List<SaleTotal> getSaleNums(HttpServletRequest request){
    	String areaId = request.getParameter("areaId");
    	String type = request.getParameter("type");
    	String startDate =request.getParameter("startDate");//yyyy-MM-dd
    	String endDate =request.getParameter("endDate");//yyyy-MM-dd
    	List<SaleTotal> list = null;
		try {
			list = this.saleService.getSaleNums(Integer.parseInt(areaId), Integer.parseInt(type),startDate,endDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
    }
    
    /**
     * 获取某区域下的某段时间每天的订单数，销量，退款订单数，退款销量
     * @param request
     * @return
     */
    @RequestMapping(value="/getSaleDetailNums")
    @ResponseBody
    public List<SaleDetailTotal> getSaleDetailNums(HttpServletRequest request){
    	String areaId = request.getParameter("areaId");
    	String type = request.getParameter("type");
    	String startDate =request.getParameter("startDate");//yyyy-MM-dd
    	String endDate =request.getParameter("endDate");//yyyy-MM-dd
    	List<SaleDetailTotal> list = null;
		try {
			list = this.saleService.getSaleDetailNums(Integer.parseInt(areaId), Integer.parseInt(type),startDate,endDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
    }
    
    /**
     * 获取某区域下的某段时间每月的订单数，销量，退款订单数，退款销量
     * @param request
     * @return
     */
    @RequestMapping(value="/getSaleDetailNumsByMonth")
    @ResponseBody
    public List<SaleDetailTotal> getSaleDetailNumsByMonth(HttpServletRequest request){
    	String areaId = request.getParameter("areaId");
    	String type = request.getParameter("type");
    	String startDate =request.getParameter("startDate");//yyyy-MM
    	String endDate =request.getParameter("endDate");//yyyy-MM
    	List<SaleDetailTotal> list = null;
		try {
			list = this.saleService.getSaleDetailNumsByMonth(Integer.parseInt(areaId), Integer.parseInt(type),startDate,endDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
    }
    
    /**
     * 获取某区域下的某段时间的订单总数，单日最高数，日均数
     * @param request
     * @return
     */
    @RequestMapping(value="/getOrderTotal")
    @ResponseBody
    public OrderTotal getOrderTotal(HttpServletRequest request){
    	String areaId = request.getParameter("areaId");
    	String type = request.getParameter("type");
    	String startDate =request.getParameter("startDate");//yyyy-MM-dd
    	String endDate =request.getParameter("endDate");//yyyy-MM-dd
    	OrderTotal orderTotal = null;
		try {
			orderTotal = this.saleService.getOrderTotal(Integer.parseInt(areaId), Integer.parseInt(type),startDate,endDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return orderTotal;
    }
    
    /**
     * 获取某区域下的某段时间分品项每天的订单数，销量，退款订单数，退款销量
     * @param request
     * @return
     */
    @RequestMapping(value="/getSeriesSaleDetailNums")
    @ResponseBody
    public List<SaleSeriesDetailTotal> getSeriesSaleDetailNums(HttpServletRequest request){
    	String areaId = request.getParameter("areaId");
    	String type = request.getParameter("type");
    	String seriesId = request.getParameter("seriesId");//品项id
    	String startDate =request.getParameter("startDate");//yyyy-MM-dd
    	String endDate =request.getParameter("endDate");//yyyy-MM-dd
    	List<SaleSeriesDetailTotal> list = null;
		try {
			list = this.saleService.getSeriesSaleDetailNums(Integer.parseInt(areaId), Integer.parseInt(type),seriesId,startDate,endDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
    }
    
    /**
     * 获取某区域下的某段时间分品项每月的订单数，销量，退款订单数，退款销量
     * @param request
     * @return
     */
    @RequestMapping(value="/getSeriesSaleDetailNumsByMonth")
    @ResponseBody
    public List<SaleSeriesDetailTotal> getSeriesSaleDetailNumsByMonth(HttpServletRequest request){
    	String areaId = request.getParameter("areaId");
    	String type = request.getParameter("type");
    	String seriesId = request.getParameter("seriesId");//品项id
    	String startDate =request.getParameter("startDate");//yyyy-MM
    	String endDate =request.getParameter("endDate");//yyyy-MM
    	List<SaleSeriesDetailTotal> list = null;
		try {
			list = this.saleService.getSeriesSaleDetailNumsByMonth(Integer.parseInt(areaId), Integer.parseInt(type),seriesId,startDate,endDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
    }
    
}  