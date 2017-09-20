package com.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dao.DormsDao;
import com.model.Dorms;




public class DormsDaoImpl extends HibernateDaoSupport implements DormsDao{

	public void deletebean(Dorms bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertbean(Dorms bean) {
		this.getHibernateTemplate().save(bean);
	}

	@SuppressWarnings("unchecked")
	public Dorms selectBean(String where) {
		List<Dorms> list = this.getHibernateTemplate().find("from Dorms" + where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public long selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Dorms"+where).get(0);
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<Dorms> selectBeanlist(final int start, final int limit, final String where) {
		return (List<Dorms>)this.getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				List<Dorms> list = session.createQuery("from Dorms"+ where).setFirstResult(start).setMaxResults(limit).list();
				return list;
			}
			
		});
	
	}

	public void updatebean(Dorms bean) {
		this.getHibernateTemplate().update(bean);
		
	}

	
		

}
