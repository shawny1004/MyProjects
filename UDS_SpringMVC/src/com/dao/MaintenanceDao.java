package com.dao;



import java.util.List;

import com.model.Maintenance;






public interface MaintenanceDao {
	
	
	public void insertbean(Maintenance bean);
		
	
	public void deletebean(Maintenance bean);
		
	
	
	public void updatebean(Maintenance bean);
	
	
	
	public List<Maintenance> selectBeanlist(final int start,final int limit,final String where);
	
    
	public long selectBeanCount(final String where);
	
	
	public Maintenance selectBean(final String where);
}
