package com.blob.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blob.controller.UserProfileController;
import com.blob.dao.UserProfileDao;
import com.blob.model.UserProfile;

@Service
public class UserProfileService {

	@Autowired
	private UserProfileDao userProfileDao;

	private static final Logger LOGGER = LogManager.getLogger(UserProfileService.class);
	
	static
	{
		LOGGER.info("\n UserProfileService -static -I.I -e\n");
		
		
	}
	
	{
		
		LOGGER.info("\n UserProfileService - -I.I - trying to insert userprofile\n");
		
//		UserProfile userProfile = new UserProfile();
//		
//		userProfile.setName("a");
//		
//		UserProfile savedProfile = userProfileDao.save(userProfile);
//		
//		LOGGER.info("\n UserProfileService - -I.I -afterinsertion savedProfile =\n"+savedProfile);
		
	}
	public UserProfile create(UserProfile userProfile) 
	{

		UserProfile user = userProfileDao.save(userProfile);
		
		return user;
	}

	public UserProfile LoadUserProfile(String name) 
	{
		LOGGER.info("\n UserProfileService -LoadUserProfile -name  \n"+name);
		List<UserProfile> list = userProfileDao.findByName(name);
		
		if(list == null || list.isEmpty())
		{
			LOGGER.info("\n UserProfileService -LoadUserProfile list is null or empty- \n"+name);
			return null;
		}
		else
		{
			LOGGER.info("\n UserProfileService -LoadUserProfile -name  \n"+name+" exist");
			return list.get(0);
		}
	
		
		
	}

	public UserProfile update(UserProfile userBean) 
	{

		LOGGER.info("\n UserProfileService -update -UserProfile  \n"+userBean+" ");
		
		UserProfile savedprofile = userProfileDao.save(userBean);
		
		return savedprofile;
	}

	public UserProfile updateProfilePic(UserProfile userBean)
	{
		//load userbean
		LOGGER.info("\n UserProfileservice - updateProfilePic - passed userbane = \n"+userBean);
		
		LOGGER.info("\n UserProfileservice - updateProfilePic - passed userbane dp size = \n"+userBean.getProfilePic().length);
		Optional<UserProfile> optional = userProfileDao.findById(userBean.getId());
		
		if(optional != null && optional.isPresent())
		{
			//remove old pic
			
			UserProfile userProfile = optional.get();
			
			if(userProfile.getProfilePic() != null)
			{
				LOGGER.info("\n UserProfileservice - updateProfilePic - old userbane dp size = "
						+ "\n"+userProfile.getProfilePic().length);
			}
			else
			{
				LOGGER.info("\n UserProfileservice - updateProfilePic - old userbane dp  = "
						+ "\n"+userProfile.getProfilePic());
			}
			
			userProfile.setProfilePic(null);
			
			//update old to empty
			UserProfile userProfile2= userProfileDao.save(userProfile);
			
			LOGGER.info("\n UserProfileservice - updateProfilePic -aftre setting null bean dp size = "
					+ "\n"+userProfile2.getProfilePic());
			
			
			//again get
			//Optional<UserProfile> optional2 = userProfileDao.findById(userProfile.getId());
			
			//UserProfile userProfile2 = optional2.get();
			
			
			userProfile2.setProfilePic(userBean.getProfilePic());
			
			
			//update new one
			UserProfile updateduserProfile3 = userProfileDao.save(userProfile2);
			
			LOGGER.info("\n UPrfi service - updateProfilePic - updateduserProfile3 = \n"+updateduserProfile3);
			
			LOGGER.info("\n UserProfileservice - updateProfilePic -aftre setting new dp, bean dp size = "
					+ "\n"+updateduserProfile3.getProfilePic().length);
			
			
			return updateduserProfile3;
			
		}
		else
			return null;
	
	}

}
