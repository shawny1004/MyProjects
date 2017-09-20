package com.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dao.MessageDao;
import com.model.Message;




public class MessageDaoImpl extends HibernateDaoSupport implements MessageDao{

	public void deletebean(Message bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertbean(Message bean) {
		this.getHibernateTemplate().save(bean);
	}

	@SuppressWarnings("unchecked")
	public Message selectBean(String where) {
		List<Message> list = this.getHibernateTemplate().find("from Message" + where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public long selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Message"+where).get(0);
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<Message> selectBeanlist(final int start, final int limit, final String where) {
		return (List<Message>)this.getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				List<Message> list = session.createQuery("from Message"+ where).setFirstResult(start).setMaxResults(limit).list();
				return list;
			}
			
		});
	
	}

	public void updatebean(Message bean) {
		this.getHibernateTemplate().update(bean);
		
	}

	
		

}
