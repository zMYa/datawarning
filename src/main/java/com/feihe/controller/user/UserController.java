package com.feihe.controller.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import com.feihe.service.user.UserService;

@Controller  
@RequestMapping("/user")  
public class UserController {  
    
	@Resource  
    private UserService userService;
	
    /**
     * 用户登录
     * @param request
     * @return
     */
    @RequestMapping(value="/login")
    public String login(HttpServletRequest request,Model model){
    	String userName = request.getParameter("userName");
    	String password = request.getParameter("password");
    	int status = -1;
		try {
			status = this.userService.checkUserInfo(userName, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(status==-1){//用户名密码错误
    		return "redirect:/login.jsp";
    	}else if(status==0){//管理员
    		WebUtils.setSessionAttribute(request, "user", userName);
    		return "redirect:/setting/settingList";
    	}else if(status==1){//省区经理、地区经理
    		WebUtils.setSessionAttribute(request, "user", userName);
    		return "redirect:/";
    	}
    	return "error";
    }
    
}  