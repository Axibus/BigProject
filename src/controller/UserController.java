package controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import entity.User;
import impls.UserServiceImpl;

@Controller
//@RequestMapping("/user")
public class UserController {

	@Resource
	private UserServiceImpl usi;
	
	@RequestMapping(value="/login" , method=RequestMethod.POST)
	public String login(@RequestParam("nameoremail")String nameoremail,@RequestParam("password")String password){
		User u =usi.findUserByNameOrEmail(nameoremail);
		if(u!=null){
			if(password.equals(u.getPassword())){
				return "loginok";
			}else{
				return"faillogin";
			}
			
		}else{
			return "faillogin";
		}
		
	}
	
	
	@RequestMapping(value="/regist", method=RequestMethod.POST)
	public String regist(@RequestParam("username")String name,@RequestParam("email")String email,@RequestParam("password")String password){
		User u = new User();
		//查重并注册
		if(usi.findUserByNameOrEmail(name)!=null){
			return"nameexist";
		}else if(usi.findUserByNameOrEmail(email)!=null){
			return"emailexist";
		}else{
		u.setUserName(name);
		u.seteMail(email);
		u.setPassword(password);
		usi.regist(u);
		return "registok";
		}
	}
}
