package kr.co.mz.comm.security.model;

public enum AccountState {
	ACTIVE("Active"),
	INACTIVE("Inactive"),
	DELETED("Deleted"),
	LOCKED("Locked");
	
	private String Accountstate;
	
	private AccountState(final String Accountstate){
		this.Accountstate = Accountstate;
	}
	
	public String getAccountstate() {
		return this.Accountstate;
	}

	@Override
	public String toString(){
		return this.Accountstate;
	}

	public String getName(){
		return this.name();
	}
}
