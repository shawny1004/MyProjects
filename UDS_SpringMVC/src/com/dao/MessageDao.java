package com.dao;



import java.util.List;

import com.model.Message;






public interface MessageDao {
	
	
	public void insertbean(Message bean);
		
	
	public void deletebean(Message bean);
		
	
	
	public void updatebean(Message bean);
	
	
	
	public List<Message> selectBeanlist(final int start,final int limit,final String where);
	
    
	public long selectBeanCount(final String where);
	
	
	public Message selectBean(final String where);
}
