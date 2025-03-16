package in.uttam.service;

import in.uttam.entity.User;

public interface UserService 
{
	public String regUser(User user);
	public User loginUser(String email,String password);
	public User uLoginUser(String username,String password);
	public User getuser(int id);
	public String passwordLength(String password);
	public String encPass(String password);
	public String updateUser(User user);
	public boolean deleteAccount(int id);
}
