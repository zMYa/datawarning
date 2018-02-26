package com.feihe.service.impl.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import com.feihe.service.user.UserService;
import com.feihe.util.HttpClientUtil;
import com.feihe.util.PropertiesUtil;

@Service("userService") 
public class UserServiceImpl implements UserService {

	@Override
	public int checkUserInfo(String userName, String password) {
		// TODO Auto-generated method stub
		String path = this.getClass().getResource("/").getPath()+"interface.properties";
		String url = PropertiesUtil.loadProperties(path,"loginurl");

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
        nvps.add(new BasicNameValuePair("userName", userName));  
        nvps.add(new BasicNameValuePair("password", password));  
        
		String text = HttpClientUtil.post(url, nvps);
		return Integer.parseInt(text);
	}

}
