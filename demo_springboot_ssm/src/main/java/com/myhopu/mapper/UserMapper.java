package com.myhopu.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.myhopu.entity.User;

@Repository
public interface UserMapper extends BaseMapper<User>{

	List<User> loginbypass(String phoneNumber, String userPwd);

	List<User> loginbytel(String phoneNumber);
	
}
