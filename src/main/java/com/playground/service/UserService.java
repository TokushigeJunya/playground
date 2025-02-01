package com.playground.service;

import java.util.List;

import com.playground.entity.MUser;

public interface UserService {
	
	public boolean signup(MUser user);
	
	public List<MUser> getUsers(MUser user);
	
	public MUser getUserOne(String userId);
	
	public void updateUserOne(MUser user);

    public void deleteUserOne(String userId);
    
    public MUser getLoginUser(String userId);
	
}
