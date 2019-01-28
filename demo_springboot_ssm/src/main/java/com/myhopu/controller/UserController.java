package com.myhopu.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.myhopu.entity.User;
import com.myhopu.service.UserService;
import com.zhenzi.sms.ZhenziSmsClient;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController<User> {
	@Resource
	private UserService userservice;

	@RequestMapping("/register")
	@ResponseBody
	public String register(User entity, HttpSession session, @PathParam("pnum") String pnum) {
		// TODO Auto-generated method stub
		String mailcode = null;
		if (session.getAttribute("mailcode") != null) {
			mailcode = (String) session.getAttribute("mailcode");
		} else {
			return FAIL;
		}
		if (mailcode.equals(pnum)) {
			return super.saveT(entity);
		}
		return FAIL;
	}

	@RequestMapping("/loginbypass")
	@ResponseBody
	public String loginbypass(User user, HttpSession session) {
		User user1 = userservice.loginbypass(user);
		if (user1 != null) {
			session.setAttribute("user", user1);
			if(user1.getRecipientInfoList().size()!=0&&user1.getRecipientInfoList().get(0).getRecipientAddress()!=null) {
				session.setAttribute("Address", user1.getRecipientInfoList().get(0).getRecipientAddress());
				session.setAttribute("recipientId", user1.getRecipientInfoList().get(0).getRecipientId());
			}
			if(user1.getUserName().equals("admin")) {
				return "admin";
			}
			return SUCCESS;
		}
		return FAIL;

	}

	@RequestMapping("/checktel")
	@ResponseBody
	public String loginbytel(@PathParam("phoneNumber") String phoneNumber) {
		if (userservice.checktel(phoneNumber)) {
			return FAIL;
		}
		return SUCCESS;

	}

	@RequestMapping("/getmessage")
	@ResponseBody
	public void getmessage(@PathParam("phoneNumber") String phoneNumber, HttpSession session) throws Exception {
		ZhenziSmsClient client = new ZhenziSmsClient("https://sms_developer.zhenzikj.com", "100384",
				"7ddb1f0c-cf02-4308-a471-784ae4e3c677");
		String randomNum = createRandomNum(6);// 随机生成6位数的验证码
		client.send(phoneNumber, "您好！您的短信验证码:" + randomNum + " 打死也不能告诉别人！一分钟有效，请及时填写");
		session.setAttribute("messagecode", randomNum);
		session.setMaxInactiveInterval(60);
	}

	@RequestMapping("/login")
	@ResponseBody
	public String login(User entity, HttpSession session, @PathParam("pnum") String pnum,
			@PathParam("phoneNumber") String phoneNumber) {
		// TODO Auto-generated method stub
		String messagecode = null;
		if (session.getAttribute("messagecode") != null) {
			messagecode = (String) session.getAttribute("messagecode");
		} else {
			return FAIL;
		}
		if (messagecode.equals(pnum)) {
			User macuser = userservice.getuserbytel(phoneNumber);
			session.setAttribute("user", macuser);
			if(macuser.getRecipientInfoList()!=null&&macuser.getRecipientInfoList().get(0).getRecipientAddress()!=null&&macuser.getRecipientInfoList()!=null) {
				session.setAttribute("Address", macuser.getRecipientInfoList().get(0).getRecipientAddress());
				session.setAttribute("recipientId", macuser.getRecipientInfoList().get(0).getRecipientId());
			}
			if(macuser.getUserName().equals("admin")) {
				return "admin";
			}
			return SUCCESS;
		}
		return FAIL;
	}

	/**
	 * 生成随机数
	 * 
	 * @param num 位数
	 * @return
	 */
	public static String createRandomNum(int num) {
		String randomNumStr = "";
		for (int i = 0; i < num; i++) {
			int randomNum = (int) (Math.random() * 10);
			randomNumStr += randomNum;
		}
		return randomNumStr;
	}
}
