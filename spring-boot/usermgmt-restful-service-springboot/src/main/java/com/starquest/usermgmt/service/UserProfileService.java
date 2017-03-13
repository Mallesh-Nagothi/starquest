package com.starquest.usermgmt.service;
import java.util.List;

import com.starquest.usermgmt.vo.UserProfile;

public interface UserProfileService {
	
	public List<UserProfile> getUserProfileById(int size);
	public UserProfile createUserProfile(UserProfile userProfile);

}
