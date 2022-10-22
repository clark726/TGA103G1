package com.common;


import static com.common.HibernateUtil.getSessionFactory;

import org.hibernate.Session;

public interface CoreDao{
	
	default Session getSession() {
		return getSessionFactory().getCurrentSession();
	}
	
	
}
