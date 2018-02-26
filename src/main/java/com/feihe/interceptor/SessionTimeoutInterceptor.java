package com.feihe.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.feihe.exception.SessionTimeoutException;

/**
 * session拦截器
 * @author 若水
 *
 */
public class SessionTimeoutInterceptor implements HandlerInterceptor{

	/**
	 * 不拦截的地址
	 */
	private String[] allowUrls;
	
	public void setAllowUrls(String[] allowUrls) {  
        this.allowUrls = allowUrls;  
    }  
	
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");
		if (null != allowUrls && allowUrls.length >= 1){
			for (String url : allowUrls) {
                if (requestUrl.contains(url)) {
                    return true;
                }
            }
		}
        String userName = (String)request.getSession().getAttribute("user");  
        if (userName != null) {  
            return true; // 返回true，则这个方面调用后会接着调用postHandle(), afterCompletion()  
        } else {  
            // 未登录 跳转到登录页面  
            throw new SessionTimeoutException();// 返回到配置文件中定义的路径  
        }  
	}

}
