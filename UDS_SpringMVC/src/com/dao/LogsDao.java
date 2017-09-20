package com.dao;



import java.util.List;

import com.model.Logs;






public interface LogsDao {
	
	
	public void insertbean(Logs bean);
		
	
	public void deletebean(Logs bean);
		
	
	
	public void updatebean(Logs bean);
	
	
	
	public List<Logs> selectBeanlist(final int start,final int limit,final String where);
	
    
	public long selectBeanCount(final String where);
	
	
	public Logs selectBean(final String where);
}
