package com.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dao.BusDao;
import com.model.Bus;




public class BusDaoImpl extends HibernateDaoSupport implements BusDao{

	public void deletebean(Bus bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertbean(Bus bean) {
		this.getHibernateTemplate().save(bean);
	}

	@SuppressWarnings("unchecked")
	public Bus selectBean(String where) {
		List<Bus> list = this.getHibernateTemplate().find("from Bus" + where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public long selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Bus"+where).get(0);
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<Bus> selectBeanlist(final int start, final int limit, final String where) {
		return (List<Bus>)this.getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				List<Bus> list = session.createQuery("from Bus"+ where).setFirstResult(start).setMaxResults(limit).list();
				return list;
			}
			
		});
	
	}

	public void updatebean(Bus bean) {
		this.getHibernateTemplate().update(bean);
		
	}

	
		

}
