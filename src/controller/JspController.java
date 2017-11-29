package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JspController {
	
	@RequestMapping(value="/index")
	public ModelAndView index(){
		ModelAndView m = new ModelAndView();
		m.setViewName("index");
		return m;
	}

	@RequestMapping(value="account")
	public String myAccount(){
		System.out.println("aaaaa");
		return "myaccount";
	}
}
