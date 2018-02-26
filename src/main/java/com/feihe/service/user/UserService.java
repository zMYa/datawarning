package com.feihe.service.user;

/**
 * 处理用户相关的业务逻辑
 * @author 若水
 *
 */
public interface UserService {
	
	/**
	 * 校验用户名密码是否正确
	 * @param userName 用户名
	 * @param password 密码
	 * @return 0 管理员   1 省区经理、地区经理  -1 用户名密码错误
	 */
	public int checkUserInfo(String userName,String password) throws Exception;
	
}
