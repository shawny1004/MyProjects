package com.dao;



import java.util.List;
import com.model.User;






public interface UserDao {
	
	
	public void insertbean(User bean);
		
	
	public void deletebean(User bean);
		
	
	
	public void updatebean(User bean);
	
	
	
	public List<User> selectBeanlist(final int start,final int limit,final String where);
	
    
	public long selectBeanCount(final String where);
	
	
	public User selectBean(final String where);
}
