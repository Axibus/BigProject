package service;

import entity.User;

public interface UserService {
	
	public User findUserByNameOrEmail(String nameoremail);
	public void regist(User user);
}
