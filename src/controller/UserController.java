package controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import entity.User;
import impls.UserServiceImpl;

@Controller
//@RequestMapping("/user")
public class UserController {

	@Resource
	private UserServiceImpl usi;
	
	
	//登陆
	@RequestMapping(value="/login" , method=RequestMethod.POST)
	public ModelAndView login(@RequestParam(value="nameoremail",required=false)String nameoremail,@RequestParam(value="password",required=false)String password,HttpSession httpSession){
		
		
		
		User u =usi.findUserByNameOrEmail(nameoremail);
		if(u!=null){
			if(password.equals(u.getPassword())){
				
				ModelAndView mAndView = new ModelAndView("forward:loginok.jsp");
				httpSession.setAttribute("user", u);
				mAndView.addObject("user",u);
				return mAndView;
				
				
			}else{
				ModelAndView mAndView = new ModelAndView("forward:faillogin.jsp");
				mAndView.addObject("user",u);
				return mAndView;
			}
			
		}else{
			ModelAndView mAndView = new ModelAndView("forward:faillogin.jsp");
			mAndView.addObject("user",u);
			return mAndView;
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
		u.setAdmin(null);
		usi.regist(u);
		return "registok";
		}
	}
	@RequestMapping(value="listviewforcustomer",method=RequestMethod.POST)
	public ModelAndView listView(){
		ModelAndView mAndView = new ModelAndView("forward:listviewforcustomer.jsp");
		
		return mAndView;
	}
	@RequestMapping(value="deleteuser/{id}")
	public String deleteUser(@PathVariable int id){
		usi.deleteUser(id);
		
		return "userlist";
	}
	
	@RequestMapping("cancellation")
	public String userCancellation(HttpSession session){
		session.removeAttribute("user");
		
		return "myaccount";
	}
	
}
