package com.dao;



import java.util.List;

import com.model.Route;






public interface RouteDao {
	
	
	public void insertbean(Route bean);
		
	
	public void deletebean(Route bean);
		
	
	
	public void updatebean(Route bean);
	
	
	
	public List<Route> selectBeanlist(final int start,final int limit,final String where);
	
    
	public long selectBeanCount(final String where);
	
	
	public Route selectBean(final String where);
}
