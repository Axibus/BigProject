package dao;

import entity.User;

public interface UserDao {
	public User findUserByNameOrEmail(String nameoremail);
	public void saveUser(User user);
}
