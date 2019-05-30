package com.cplusexam.util;


import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.cplusexam.bean.system.User;
import com.cplusexam.service.system.IUserService;

public class MyRealm extends AuthorizingRealm{

	private static Logger logger = Logger.getLogger(MyRealm.class);
	
	@Autowired
	private IUserService userService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String)principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		Set<String> set = new TreeSet<>();
		set.add(userService.getUserShiroEnNameById(username));
		authorizationInfo.setRoles(set);
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		logger.info("进入用户认证");
		String username = (String) token.getPrincipal();
		User user = userService.getUserById(username);
		AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getLoginName(),user.getPassword(),"xx");
		return authenticationInfo;
	}

}
