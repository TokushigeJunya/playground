package com.playground.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.playground.entity.MUser;

@Mapper
public interface UserMapper {
	
    public int insertOne(MUser user);
    
    public List<MUser> findMany(MUser user);
    
    public MUser findOne(String userId);

    public void updateOne(MUser user);
    public int deleteOne(@Param("userId") String userId);
    
    public MUser findLoginUser(String userId);
    
}