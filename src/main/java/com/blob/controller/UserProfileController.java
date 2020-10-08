package com.blob.controller;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.util.Base64;

import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.blob.model.UserProfile;
import com.blob.service.UserProfileService;


//@Controller(value = "/blobFix")
//@RequestMapping(value = "/blobFix")
//
//@RequestMapping(value = "/blobFix")
//@RestController
@Controller
public class UserProfileController 
{

	@Autowired
	private UserProfileService userProfileService;
	
	private static final Logger LOGGER = LogManager.getLogger(UserProfileController.class);

	private UserProfile updateProfilePic(UserProfile userBean)
	{
		LOGGER.info("\n UserProfileController -updateProfilePic -  \n");
		
		UserProfile updatedProfile = userProfileService.updateProfilePic(userBean);
		
		return updatedProfile;
	}
	
	
	
	//@GetMapping(value = "/")
	@RequestMapping(value = "/")
	public String main()
	{
		LOGGER.info("\n UserProfileController -login -  \n");
		
		return "LogIn";
		//return "Home";
		//modelAndView.setViewName("LogIn");
		
		//return modelAndView ;
	}
	

	
	@GetMapping(value = "/openRegisterView")
	public ModelAndView openRegisterView(ModelAndView modelAndView)
	{
		LOGGER.info("\n UserProfileController -openRegisterView -  \n");
		//userProfile
		modelAndView.addObject("userProfile", new UserProfile());
		
		//model.add
		//return "Register";
		//return "Home";
		modelAndView.setViewName("Register");
				return modelAndView ;
	}
	//@GetMapping(value = "/home")
	@RequestMapping(value = "/home",method = RequestMethod.GET)
	public String testHome()
	{
		LOGGER.info("\n UserProfileController -testHome -  \n");
		return "Home";
	}
	
	//register
	@PostMapping(value = "/register")
	public ModelAndView register(UserProfile userProfile, ModelAndView modelAndView)
	{
		//register
		LOGGER.info("\n UserProfileController -register -  \n");
		
		UserProfile profile = userProfileService.create(userProfile);
		
		LOGGER.info(" created profile = \n "+profile);
		
		modelAndView.addObject("successMessage", "created profile = \n "+profile);
		modelAndView.setViewName("LogIn");
		return modelAndView ;
	}
	
	@PostMapping(value = "/logIn")
	public ModelAndView logIn(ModelAndView modelAndView,@RequestParam String name,HttpSession session)
	{
		
		try
		{
			UserProfile userProfile = userProfileService.LoadUserProfile(name);
			
			if(userProfile == null)
			{
				throw new Exception("invalid credetials");
			}
			
			LOGGER.info("\n UserProfileController -logIn - with user=  \n"+userProfile);
			
			modelAndView.addObject("successMessage", "LOGGED IN  "+userProfile.getName());
			
			session.setAttribute("userBean", userProfile);
			modelAndView.addObject("userBean",userProfile);
	
			
			if(userProfile.getProfilePic() != null )
			{
				LOGGER.info("UserProfileController -logIn dp exists = \n "+userProfile.getProfilePic());
				
				String base64Image = Base64.getEncoder().encodeToString(userProfile.getProfilePic());
	             
              
	              userProfile.setBase64Image(base64Image);
			}
		
              
              
			modelAndView.setViewName("Galary");
			return modelAndView ;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			LOGGER.error("UserProfileController -logIn - Exception ",e);
			//errorMessage
			modelAndView.addObject("errorMessage", e.getLocalizedMessage());
			//return "";
			modelAndView.setViewName("LogIn");
			return modelAndView ;
		}
		
	}
	
	//signOut
	@GetMapping(value = "/signOut")
	public ModelAndView signOut(HttpSession session, ModelAndView modelAndView)
	{
		LOGGER.info("\n UserProfileController -signOut -  \n");
		//signOut
		session.removeAttribute("userBean");
		
		session.invalidate();
		modelAndView.addObject("successMessage", "LOGGED out baby ");
		modelAndView.setViewName("LogIn");
		return modelAndView ;
	}
	
	//uploadProfilePic
	     @PostMapping("/uploadProfilePic")
		public ModelAndView uploadProfilePic(@RequestParam("profilePic") MultipartFile profilePic,ModelAndView modelAndView,
				HttpSession session)
		{
			LOGGER.info("\n UserProfileController -uploadProfilePic -  \n");
			try
			{
				if(profilePic == null || profilePic.isEmpty())
				{
					throw new Exception("please upload image photo");
				}
//				  if (!profilePic.isEmpty())
//				  {
//			           
//			            		
//				  }  
				 byte[] byteArray = profilePic.getBytes();
				 
				  // blob = new SerialBlob(myArray );
				// Blob photo = new SerialBlob(byteArray);
				 
				String dpStatusStr = "";
				UserProfile userBean = (UserProfile)session.getAttribute("userBean");
				
				//userBean.setProfilePic(photo);
					
				//first remove old pic
				//userBean.setProfilePic(null);
				
				//now add new one
				userBean.setProfilePic(byteArray);
				
				LOGGER.info("\n before updatedProfile ,, after set profile= \n"+userBean);
				
				//call local api update
				UserProfile updatedProfile = updateProfilePic(userBean);
				
			
				
				LOGGER.info("\n updatedProfile = \n"+updatedProfile);
				
				dpStatusStr = "Dp updated succesessionfully to ";
				//successMessage
				modelAndView.addObject("successMessage", dpStatusStr);
				
				modelAndView.setViewName("Galary");
				return modelAndView ;
			}
			catch (Exception e)
			{
				e.printStackTrace();
				LOGGER.error("UserProfileController -uploadProfilePic - Exception ",e);
				//m.addAttribute("errorMessagae", e.getLocalizedMessage());
				
				modelAndView.addObject("errorMessagae", e.getLocalizedMessage());
				//return "";
				modelAndView.setViewName("Error");
				return modelAndView ;
			}
		}




	     
	     
	     
	 	

	
}
