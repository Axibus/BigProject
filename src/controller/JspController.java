package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import entity.User;

@Controller
public class JspController {
	
	@RequestMapping(value="/index")
	public ModelAndView index(){
		ModelAndView m = new ModelAndView();
		m.setViewName("index");
		return m;
	}

	@RequestMapping(value="account")
	public String myAccount(HttpSession session){
		User u = (User)session.getAttribute("user");
		if(u!=null){
			return "userinfo";
		}else{
		return "myaccount";
		}
	}
}
