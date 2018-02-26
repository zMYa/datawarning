package com.feihe.controller.warning;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feihe.domain.tmp.StoreWarningBaseData;
import com.feihe.domain.tmp.StoreWarningData;
import com.feihe.service.warning.DataService;
import com.feihe.util.ExcelUtil;

/**
 * 预警数据相关
 * @author 若水
 *
 */
@Controller  
@RequestMapping("/data")  
public class DataController {  
    
	@Resource  
    private DataService dataService;
	
    /**
     * 查询预警明细数据(太慢，120s,需优化)(3s)
     * @param request
     * @return
     */
    @RequestMapping(value="/dataDetailList")
    @ResponseBody
    public List<StoreWarningData> dataDetailList(HttpServletRequest request,Model model){
    	
    	String userName = request.getParameter("userName");//省区经理、地区经理用户名（根据用户名获取管辖的片区列表）
        String storeName = request.getParameter("storeName");//门店名称
        String storeId = request.getParameter("storeId");//门店Id
        String guideName = request.getParameter("guideName");//导购姓名
        String guideId = request.getParameter("guideId");//导购Id
        String pageIndex = request.getParameter("pageIndex");//第几页
        String pageSize = request.getParameter("pageSize");//每页多少条记录
        
        String areaName = request.getParameter("areaName");//片区名称（用户管辖的片区）
        String type = request.getParameter("type");//片区类别   3 三级片区，4 四级片区，5 五级片区
        
        String nextAreaName = request.getParameter("nextAreaName");//下级片区名称（用户管辖的片区的下级片区）（为null查询全部）
        
        String month = request.getParameter("month");//统计月（预警月份）
        
        if(pageIndex==null||pageIndex.equals("")){
        	pageIndex="0";
        }
        if(pageSize==null||pageSize.equals("")){
        	pageSize="1000";
        }
        List<StoreWarningData> dataList = null;
		try {
			dataList = this.dataService.getDataDetailList
					(userName,storeName,storeId,guideName,guideId,
							Integer.parseInt(pageIndex),Integer.parseInt(pageSize),
							areaName,type,nextAreaName,month);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    	return dataList;
    }
    
    /**
     * 查询预警简要数据(太慢，120s,需优化)(3s)
     * @param request
     * @return
     */
    @RequestMapping(value="/dataList")
    @ResponseBody
    public List<StoreWarningBaseData> dataList(HttpServletRequest request,Model model){
    	
    	String userName = request.getParameter("userName");//省区经理、地区经理用户名（根据用户名获取管辖的片区列表）
        String storeName = request.getParameter("storeName");//门店名称
        String storeId = request.getParameter("storeId");//门店Id
        String guideName = request.getParameter("guideName");//导购姓名
        String guideId = request.getParameter("guideId");//导购Id
        String pageIndex = request.getParameter("pageIndex");//第几页
        String pageSize = request.getParameter("pageSize");//每页多少条记录
        
        String areaName = request.getParameter("areaName");//片区名称（用户管辖的片区）
        String type = request.getParameter("type");//片区类别   3 三级片区，4 四级片区，5 五级片区
        
        String nextAreaName = request.getParameter("nextAreaName");//下级片区名称（用户管辖的片区的下级片区）（为null查询全部）
        
        String month = request.getParameter("month");//统计月（预警月份）
        
        if(pageIndex==null||pageIndex.equals("")){
        	pageIndex="0";
        }
        if(pageSize==null||pageSize.equals("")){
        	pageSize="1000";
        }
        List<StoreWarningBaseData> dataList = null;
		try {
			dataList = this.dataService.getDataList
					(userName,storeName,storeId,guideName,guideId,
							Integer.parseInt(pageIndex),Integer.parseInt(pageSize),
							areaName,type,nextAreaName,month);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    	return dataList;
    }
    
    /**
     * 导出报表
     * @return
     */
    @RequestMapping(value = "/exportDataList")
    @ResponseBody
    public void export(HttpServletRequest request,HttpServletResponse response){
    	//获取数据
    	List<StoreWarningData> list = new ArrayList<StoreWarningData>();
    	
    	
    	
    	String userName = request.getParameter("userName");//省区经理、地区经理用户名
        String storeName = request.getParameter("storeName");//门店名称
        String storeId = request.getParameter("storeId");//门店Id
        String guideName = request.getParameter("guideName");//导购姓名
        String guideId = request.getParameter("guideId");//导购Id
        String pageIndex = request.getParameter("pageIndex");//第几页
        String pageSize = request.getParameter("pageSize");//每页多少条记录
        String areaName = request.getParameter("areaName");//片区名称（用户管辖的片区）
        String type = request.getParameter("type");//片区类别   3 三级片区，4 四级片区，5 五级片区
        
        String nextAreaName = request.getParameter("nextAreaName");//下级片区名称（用户管辖的片区的下级片区）（为null查询全部）
        
        String month = request.getParameter("month");//统计月（预警月份）
        
        if(pageIndex==null||pageIndex.equals("")){
        	pageIndex="0";
        }
        if(pageSize==null||pageSize.equals("")){
        	pageSize="1000";
        }
        List<StoreWarningData> dataList = null;
		try {
			dataList = this.dataService.getDataDetailList
					(userName,storeName,storeId,guideName,guideId,
							Integer.parseInt(pageIndex),Integer.parseInt(pageSize),
							areaName,type,nextAreaName,month);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        list=dataList;
        
        

    	//excel标题
    	String[] title = {"开始时间","截止时间","省区","城市","地区","门店名称","门店ID","导购姓名","导购ID","活动名称","门店新客预警标准","新客开发人数","超出预警线新客人数"};
    	//excel文件名
    	String fileName = "门店新客预警表.xls";
    	//sheet名
    	String sheetName = "门店新客预警数据";
    	String[][] content = new String[list.size()][title.length];
    	for (int i = 0; i < list.size(); i++) {
    		//content[i] = new String[title.length];
    		StoreWarningData data = list.get(i);
            content[i][0] = data.getStartDate();
            content[i][1] = data.getEndDate();
            content[i][2] = data.getProvinceName();
            content[i][3] = data.getCityName();
            content[i][4] = data.getDistrictName();
            content[i][5] = data.getStoreName();
            content[i][6] = String.valueOf(data.getStoreId());
            content[i][7] = data.getGuideName()==null?"-":data.getGuideName();
            content[i][8] = String.valueOf(data.getGuideId());
            content[i][9] = data.getActiveName()==null?"-":data.getActiveName();
            content[i][10] = String.valueOf(data.getStandardNum());
            content[i][11] = String.valueOf(data.getNewNum());
            content[i][12] = String.valueOf(data.getOutNum());
    	}

        //创建HSSFWorkbook 
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
        //响应到客户端
        try {
        	this.setResponseHeader(response, fileName);
        	OutputStream os = response.getOutputStream();
        	wb.write(os);
        	os.flush();
        	os.close();
        }catch(Exception e){
        	e.printStackTrace();
        }
    }

    //发送响应流方法
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = URLEncoder.encode(fileName, "utf-8");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }   
    
}  