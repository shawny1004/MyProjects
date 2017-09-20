package com.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dao.PreferenceDao;
import com.model.Preference;




public class PreferenceDaoImpl extends HibernateDaoSupport implements PreferenceDao{

	public void deletebean(Preference bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertbean(Preference bean) {
		this.getHibernateTemplate().save(bean);
	}

	@SuppressWarnings("unchecked")
	public Preference selectBean(String where) {
		List<Preference> list = this.getHibernateTemplate().find("from Preference" + where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public long selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Preference"+where).get(0);
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<Preference> selectBeanlist(final int start, final int limit, final String where) {
		return (List<Preference>)this.getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				List<Preference> list = session.createQuery("from Preference"+ where).setFirstResult(start).setMaxResults(limit).list();
				return list;
			}
			
		});
	
	}

	public void updatebean(Preference bean) {
		this.getHibernateTemplate().update(bean);
		
	}

	
		

}
