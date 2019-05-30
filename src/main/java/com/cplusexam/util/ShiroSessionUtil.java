package com.cplusexam.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.cplusexam.bean.system.User;

public class ShiroSessionUtil {

	public static User getLoginUser() {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		User user = (User) session.getAttribute("loginUser");
		return user;
	}
	
}
