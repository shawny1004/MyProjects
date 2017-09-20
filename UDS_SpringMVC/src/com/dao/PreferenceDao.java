package com.dao;



import java.util.List;

import com.model.Preference;






public interface PreferenceDao {
	
	
	public void insertbean(Preference bean);
		
	
	public void deletebean(Preference bean);
		
	
	
	public void updatebean(Preference bean);
	
	
	
	public List<Preference> selectBeanlist(final int start,final int limit,final String where);
	
    
	public long selectBeanCount(final String where);
	
	
	public Preference selectBean(final String where);
}
