package service;

import entity.User;

public interface UserService {
	
	public User findUserByNameOrEmail(String nameoremail);
	public User findUserById(int id);
	public void regist(User user);
	public void deleteUser(int id);
}
