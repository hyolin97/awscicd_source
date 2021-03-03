package kr.co.mz.comm.security.model;

public class UserProfileVO {
	/*
	 * id: 1, 2, 3
	 * roletype: USER, ADMIN, SUPERADMIN
	 */
	private int id;
	private String roletype = UserProfileType.USER.getUserProfileType();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoletype() {
		return roletype;
	}
	public void setRoletype(String roletype) {
		this.roletype = roletype;
	}
	
}
