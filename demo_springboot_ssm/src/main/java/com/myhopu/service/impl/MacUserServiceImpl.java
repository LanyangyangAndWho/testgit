package com.myhopu.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.myhopu.entity.User;
import com.myhopu.mapper.UserMapper;
import com.myhopu.service.UserService;

@Service
@Transactional
public class MacUserServiceImpl extends BaseServiceImpl<User> implements UserService{
	@Autowired
	UserMapper userMapper;
	public boolean checkpass(User user) {
		// TODO Auto-generated method stub
		List<User> list= userMapper.loginbypass(user.getPhoneNumber(),user.getUserPwd());
		
		if(list!=null&&list.size()>0) {
			return true;
		}
		return false;
		
	}
	public boolean checktel(String phoneNumber) {
		// TODO Auto-generated method stub
		List<User> list= userMapper.loginbytel(phoneNumber);
		if(list!=null&&list.size()>0) {
			return true;
		}
		return false;
	}
	@Override
	public User loginbypass(User user) {
		// TODO Auto-generated method stub\
		User user1 = null;
		if(userMapper.loginbypass(user.getPhoneNumber(),user.getUserPwd())!=null&&(userMapper.loginbypass(user.getPhoneNumber(),user.getUserPwd())).size()>0) {
				user1= userMapper.loginbypass(user.getPhoneNumber(),user.getUserPwd()).get(0);
		}
		return user1;
	}
	@Override
	public User getuserbytel(String phoneNumber) {
		// TODO Auto-generated method stub
		User user=userMapper.loginbytel(phoneNumber).get(0);
		return user;
	}
	

}
