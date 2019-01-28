package com.myhopu.service;

import com.myhopu.entity.User;

public interface UserService extends BaseService<User>{
	//根据账号密码登录
		boolean checkpass(User User);
		//校验手机号
		boolean checktel(String phoneNumber);
		//根据账号密码登录
		User loginbypass(User User);
		//根据手机号获取用户
		User getuserbytel(String phoneNumber);
}
