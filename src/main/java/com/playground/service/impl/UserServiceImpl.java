package com.playground.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.playground.entity.MUser;
import com.playground.repository.UserMapper;
import com.playground.service.UserService;

@Service 
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper mapper;
	
    private PasswordEncoder encoder = new BCryptPasswordEncoder();
    
	@Override 
	public boolean signup(MUser user) {  
		user.setRole("ROLE_GENERAL");
		String rawPassword = user.getPassword();
        user.setPassword(encoder.encode(rawPassword));
		
        try { mapper.insertOne(user); 
        }catch (DuplicateKeyException e) {
            return false;
        }
        
        return true;
	} 
	
	@Override
	public List<MUser> getUsers(MUser user) {
		return mapper.findMany(user); 
	}
	
	@Override 
	public MUser getUserOne(String userId) {
		return mapper.findOne(userId);
	}
	
	@Override
    public void updateUserOne(MUser user) {
		String encryptPassword = encoder.encode(user.getPassword());
		user.setPassword(encryptPassword);
        mapper.updateOne(user);
    }

    @Override
    public void deleteUserOne(String userId) {
        mapper.deleteOne(userId);
    }
    
    @Override
    public MUser getLoginUser(String userId) {
    	return mapper.findLoginUser(userId);
    }
}
