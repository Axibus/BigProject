package Impls;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import entity.User;
import service.UserService;
@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserDaoImpl udi;
	
	@Override
	public User findUserByNameOrEmail(String nameoremail) {
		
		return this.udi.findUserByNameOrEmail(nameoremail);
	}

	@Override
	public void regist(User user) {
		this.udi.saveUser(user);
		
	}

	@Override
	public void deleteUser(int id) {
		udi.deleteUser(id);
		
	}
	

}
