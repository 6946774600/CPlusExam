package com.cplusexam.util;

import org.apache.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

public class ShiroSessionListener extends SessionListenerAdapter{

	private static Logger logger = Logger.getLogger(ShiroSessionListener.class);
	
	@Override
	public void onStart(Session session) {
		logger.debug("会话创建：" + session.getId()); 
	}

	@Override
	public void onStop(Session session) {
		logger.info("会话停止：" + session.getId());
	}

	@Override
	public void onExpiration(Session session) {
		logger.debug("会话过期：" + session.getId());
	}

	
	
}
