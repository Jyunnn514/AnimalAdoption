package gjun.project.animaladoption.Utils;

public class User {


	private int id;
	private String name;
	private String account;
	private String password;
	private String password2;
	
	public User() {}

	public User (int id, String name, String account, String password, String password2) {
		
		this.id = id;
		this.name = name;
		this.account = account;
		this.password = password;
		this.password2 = password2;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	

	
}
