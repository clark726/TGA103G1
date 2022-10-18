package com.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

import com.forum_message.model.Forum_messageJNDIDAO;

@WebListener
public class MessageCount implements ServletRequestAttributeListener{
	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		if(srae.getName().equals("postMessage")) {
//			String memberId = srae.getServletRequest().getParameter("memberId");
            String forumId = srae.getServletRequest().getParameter("forumId");
//            String content = srae.getServletRequest().getParameter("content");
            new Forum_messageJNDIDAO().updateMeassage(Integer.parseInt(forumId));
		}
	}
	
}
