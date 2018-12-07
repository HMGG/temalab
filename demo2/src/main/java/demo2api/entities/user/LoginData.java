package demo2api.entities.user;

public class LoginData {
	
	public String email;
	
	public String password;
	
	public long sid;
	
	public LoginData() {
	}

	public LoginData(String email, String password, long sid) {
		super();
		this.email = email;
		this.password = password;
		this.sid = sid;
	}
}
