package com.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dao.ApplyDao;
import com.model.Apply;




public class ApplyDaoImpl extends HibernateDaoSupport implements ApplyDao{

	public void deletebean(Apply bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertbean(Apply bean) {
		this.getHibernateTemplate().save(bean);
	}

	@SuppressWarnings("unchecked")
	public Apply selectBean(String where) {
		List<Apply> list = this.getHibernateTemplate().find("from Apply" + where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public long selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Apply"+where).get(0);
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<Apply> selectBeanlist(final int start, final int limit, final String where) {
		return (List<Apply>)this.getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				List<Apply> list = session.createQuery("from Apply"+ where).setFirstResult(start).setMaxResults(limit).list();
				return list;
			}
			
		});
	
	}

	public void updatebean(Apply bean) {
		this.getHibernateTemplate().update(bean);
		
	}

	
		

}
