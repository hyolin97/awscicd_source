package kr.co.mz.comm.security.model;

public enum UserProfileType {
	USER("USER"),
	ADMIN("ADMIN"),
	SUPERADMIN("SUPERADMIN");
	
	String userProfileType;
	
	private UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType(){
		return userProfileType;
	}
}
