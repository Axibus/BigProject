package dao;

import java.util.List;

import entity.User;

public interface UserDao {
	public User findUserById(int id);
	public User findUserByNameOrEmail(String nameoremail);
	public void saveUser(User user);
	public List findAllUser();
	public Boolean deleteUser(int id);
}
