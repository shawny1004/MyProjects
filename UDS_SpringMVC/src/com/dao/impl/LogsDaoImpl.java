package com.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dao.LogsDao;
import com.model.Logs;




public class LogsDaoImpl extends HibernateDaoSupport implements LogsDao{

	public void deletebean(Logs bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertbean(Logs bean) {
		this.getHibernateTemplate().save(bean);
	}

	@SuppressWarnings("unchecked")
	public Logs selectBean(String where) {
		List<Logs> list = this.getHibernateTemplate().find("from Logs" + where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public long selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Logs"+where).get(0);
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<Logs> selectBeanlist(final int start, final int limit, final String where) {
		return (List<Logs>)this.getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				List<Logs> list = session.createQuery("from Logs"+ where).setFirstResult(start).setMaxResults(limit).list();
				return list;
			}
			
		});
	
	}

	public void updatebean(Logs bean) {
		this.getHibernateTemplate().update(bean);
		
	}

	
		

}
