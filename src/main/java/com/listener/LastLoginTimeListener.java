package com.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.manager.model.ManagerVO;
import com.manager.model.service.impl.ManagerServiceImpl;
import com.util.BeansFactory;

@WebListener
public class LastLoginTimeListener implements HttpSessionAttributeListener{
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		if("admin".equals(event.getName())) {
			ManagerVO vo = (ManagerVO)event.getSession().getAttribute("admin");
			BeansFactory.getInstance("ManagerServiceImpl", ManagerServiceImpl.class).updateLoginTime(vo.getManager_id());
		}
	}
}
