package kr.co.mz.comm.security.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.mz.comm.security.model.AccountState;
import kr.co.mz.comm.security.model.UserProfileType;
import kr.co.mz.comm.security.model.UserProfileVO;
import kr.co.mz.comm.security.model.UserVO;
import kr.co.mz.comm.security.service.UserService;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.debug("loadUserByUsername");
		
		//UserVO userVO = CustomUserDetailsService.getAccountOne(username);
		UserVO userVO = userService.findByusername(username);
		if( userVO == null ) {
			logger.info("User Not Found!");
			throw new UsernameNotFoundException("Username Not Found!");
		}
		
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(
				userVO.getUsername(), userVO.getPassword(), userVO.getAccountstate().contentEquals("Active"),
				true, true, true, getGrantedAuthorities(userVO)
				);
		
		return userDetails;
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(UserVO userVO) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(UserProfileVO userProfileVO : userVO.getUserProfiles()){
			logger.debug("userProfileVO : "+userProfileVO);
			authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfileVO.getRoletype()));
		}
		logger.debug("authorities :"+authorities);
		return authorities;
	}

	public static UserVO getAccountOne(String username) {
		UserVO userVO = new UserVO();
		if( "user".equals(username) ) {
			// username=user, password=user123
			userVO.setId(1);
			userVO.setUsername(username);
			userVO.setPassword("$2a$10$voEFBcKwKO75Wn/E724y3eaR4WtHWzshsNouAiOqgkuNYDORMO3Oq");
			userVO.setAccountstate(AccountState.ACTIVE.getAccountstate());
			
			UserProfileVO userprofileVO = new UserProfileVO();
			userprofileVO.setId(1);
			userprofileVO.setRoletype(UserProfileType.USER.getUserProfileType());
			
			Set<UserProfileVO> userprofile = new HashSet<UserProfileVO>();
			userprofile.add(userprofileVO);
			
			userVO.setUserProfiles(userprofile);
		} else if( "admin".equals(username) ) {
			// username=admin, password=admin123
			userVO.setId(2);
			userVO.setUsername(username);
			userVO.setPassword("$2a$10$uE69EFGue7WV4rqYijEKs..xHDHuB2F1bNBvR.HOV6v2uoc9EMe0m");
			userVO.setAccountstate(AccountState.ACTIVE.getAccountstate());
			
			UserProfileVO userprofileVO = new UserProfileVO();
			userprofileVO.setId(2);
			userprofileVO.setRoletype(UserProfileType.ADMIN.getUserProfileType());
			
			Set<UserProfileVO> userprofile = new HashSet<UserProfileVO>();
			userprofile.add(userprofileVO);
			
			userVO.setUserProfiles(userprofile);
		} else if( "superadmin".equals(username) ) {
			// username=superadmin, password=
			userVO.setId(3);
			userVO.setUsername(username);
			userVO.setPassword("$2a$10$WfaHU6TZ36w5qX32XnwVn.costzGbni5xjZAn/gNUQXw/1NeZNPnG");
			userVO.setAccountstate(AccountState.ACTIVE.getAccountstate());
			
			UserProfileVO userprofileVO = new UserProfileVO();
			userprofileVO.setId(2);
			userprofileVO.setRoletype(UserProfileType.SUPERADMIN.getUserProfileType());
			
			Set<UserProfileVO> userprofile = new HashSet<UserProfileVO>();
			userprofile.add(userprofileVO);
			
			userVO.setUserProfiles(userprofile);
		} else {
			userVO = null;
		}
		return userVO;
	}
}
