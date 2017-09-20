package com.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dao.MaintenanceDao;
import com.model.Maintenance;




public class MaintenanceDaoImpl extends HibernateDaoSupport implements MaintenanceDao{

	public void deletebean(Maintenance bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertbean(Maintenance bean) {
		this.getHibernateTemplate().save(bean);
	}

	@SuppressWarnings("unchecked")
	public Maintenance selectBean(String where) {
		List<Maintenance> list = this.getHibernateTemplate().find("from Maintenance" + where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public long selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Maintenance"+where).get(0);
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<Maintenance> selectBeanlist(final int start, final int limit, final String where) {
		return (List<Maintenance>)this.getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				List<Maintenance> list = session.createQuery("from Maintenance"+ where).setFirstResult(start).setMaxResults(limit).list();
				return list;
			}
			
		});
	
	}

	public void updatebean(Maintenance bean) {
		this.getHibernateTemplate().update(bean);
		
	}

	
		

}
