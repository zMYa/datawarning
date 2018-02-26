package com.feihe.controller.warning;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feihe.domain.warning.Setting;
import com.feihe.service.warning.SettingService;

@Controller  
@RequestMapping("/setting")  
public class SettingController {  
    
	@Resource  
    private SettingService settingService;
	
    /**
     * 获取预警配置列表
     * @param request
     * @return
     */
    @RequestMapping(value="/settingList")
    public String settingList(HttpServletRequest request,Model model){
    	
    	List<Setting> settingList = null;
		try {
			settingList = this.settingService.getSettingList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	model.addAttribute("settingList", settingList);
    	return "config";
    }
    
    /**
     * 添加配置信息
     * @param request
     * @return
     */
    @RequestMapping(value="/settingAdd")
    public String settingAdd(HttpServletRequest request,Setting setting,Model model){
    	
    	int num = 0;
		try {
			num = this.settingService.settingAdd(setting);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(num==1){
    		return "redirect:/setting/settingList";
    	}
		return null;
    }
    
    /**
     * 修改配置信息
     * @param request
     * @return
     */
    @RequestMapping(value="/settingUpdate")
    public String settingUpdate(HttpServletRequest request,Setting setting,Model model){
    	
    	int num = 0;
		try {
			num = this.settingService.settingUpdate(setting);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(num==1){
    		return "redirect:/setting/settingList";
    	}
		return null;
    }
    
    /**
     * 删除配置信息
     * @param request
     * @return
     */
    @RequestMapping(value="/settingDelete")
    public String settingDelete(HttpServletRequest request,Model model){
    	String id = request.getParameter("id");
    	int num = 0;
		try {
			num = this.settingService.settingDelete(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(num==1){
    		return "redirect:/setting/settingList";
    	}
		return null;
    }
    
}  