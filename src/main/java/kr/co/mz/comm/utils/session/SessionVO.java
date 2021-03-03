package kr.co.mz.comm.utils.session;

import org.springframework.security.core.Authentication;

import kr.co.mz.comm.security.model.UserVO;

public class SessionVO {
	private String sessionID;
	
	private UserVO user;
	private Authentication auth;
	
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	public UserVO getUser() {
		return user;
	}
	public void setUser(UserVO user) {
		this.user = user;
	}
	public Authentication getAuth() {
		return auth;
	}
	public void setAuth(Authentication auth) {
		this.auth = auth;
	}
	
}
