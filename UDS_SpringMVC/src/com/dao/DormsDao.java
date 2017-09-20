package com.dao;



import java.util.List;

import com.model.Dorms;






public interface DormsDao {
	
	
	public void insertbean(Dorms bean);
		
	
	public void deletebean(Dorms bean);
		
	
	
	public void updatebean(Dorms bean);
	
	
	
	public List<Dorms> selectBeanlist(final int start,final int limit,final String where);
	
    
	public long selectBeanCount(final String where);
	
	
	public Dorms selectBean(final String where);
}
