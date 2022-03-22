package tw.com.huafan.project.dao;

import tw.com.huafan.project.projo.User;

public interface UserDao {

	public boolean login (String name, String password); 
	
	public boolean register(User us);
	
	public User findUser(String name);
	
	public boolean updateName(User user);
	
	public boolean updatePass(User user);
	
	public String getName(String account);
}
