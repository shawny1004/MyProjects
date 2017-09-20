package com.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dao.RouteDao;
import com.model.Route;




public class RouteDaoImpl extends HibernateDaoSupport implements RouteDao{

	public void deletebean(Route bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertbean(Route bean) {
		this.getHibernateTemplate().save(bean);
	}

	@SuppressWarnings("unchecked")
	public Route selectBean(String where) {
		List<Route> list = this.getHibernateTemplate().find("from Route" + where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public long selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Route"+where).get(0);
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<Route> selectBeanlist(final int start, final int limit, final String where) {
		return (List<Route>)this.getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				List<Route> list = session.createQuery("from Route"+ where).setFirstResult(start).setMaxResults(limit).list();
				return list;
			}
			
		});
	
	}

	public void updatebean(Route bean) {
		this.getHibernateTemplate().update(bean);
		
	}

	
		

}
