package com.dao;



import java.util.List;

import com.model.Bus;






public interface BusDao {
	
	
	public void insertbean(Bus bean);
		
	
	public void deletebean(Bus bean);
		
	
	
	public void updatebean(Bus bean);
	

	public List<Bus> selectBeanlist(final int start,final int limit,final String where);
	
   
	public long selectBeanCount(final String where);
	
	
	public Bus selectBean(final String where);
}
