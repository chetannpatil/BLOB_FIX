package com.blob.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.blob.model.UserProfile;


public interface UserProfileDao extends CrudRepository<UserProfile, Long>
{

	//List<UserProfile> findByName();

	List<UserProfile> findByName(String name);

	
}
