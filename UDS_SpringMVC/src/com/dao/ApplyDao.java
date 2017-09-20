package com.dao;



import java.util.List;

import com.model.Apply;






public interface ApplyDao {
	
	
	public void insertbean(Apply bean);
		
	
	public void deletebean(Apply bean);
		
	
	
	public void updatebean(Apply bean);
	
	
	
	public List<Apply> selectBeanlist(final int start,final int limit,final String where);
	
    
	public long selectBeanCount(final String where);
	
	
	public Apply selectBean(final String where);
}
