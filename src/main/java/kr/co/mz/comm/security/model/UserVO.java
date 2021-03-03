package kr.co.mz.comm.security.model;

import java.util.HashSet;
import java.util.Set;

public class UserVO {
	private int id;
	private String username;
	private String password;
	private String accountstate = AccountState.ACTIVE.getAccountstate();
	private Set<UserProfileVO> userProfiles = new HashSet<UserProfileVO>();
	
	private String first_name;
	private String last_name;
	private String email;
	private String reg_date;
	private String upp_date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccountstate() {
		return accountstate;
	}
	public void setAccountstate(String accountstate) {
		this.accountstate = accountstate;
	}
	public Set<UserProfileVO> getUserProfiles() {
		return userProfiles;
	}
	public void setUserProfiles(Set<UserProfileVO> userProfiles) {
		this.userProfiles = userProfiles;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getUpp_date() {
		return upp_date;
	}
	public void setUpp_date(String upp_date) {
		this.upp_date = upp_date;
	}
	
}
