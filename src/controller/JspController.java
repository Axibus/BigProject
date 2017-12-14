package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import entity.ProductType;
import entity.User;

@Controller
public class JspController {
	@Resource
	private SessionFactory sessionFactory;
	
	
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
	
	@RequestMapping("selectproduct/{method}")
	public ModelAndView selectProduct(@PathVariable String method,HttpServletRequest request){
		ModelAndView m = new ModelAndView("listviewforcustomer");
		
		
		
		List<ProductType> list = new ArrayList<ProductType>();
		String hql = "from entity.ProductType";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		list = (List<ProductType>)query.list();
		//for(Product p:list){
		//	System.out.println(p.getName());
		//}
		session.flush();
		session.close();

		m.addObject("typelist",list);
		
		
		
		request.setAttribute("m",method);
		return m;
		
	}
	
	@RequestMapping("selectprotype")
	public ModelAndView selectProductType(@RequestParam("pid")String pid,HttpServletRequest req){
		String pt =" where proTypeId = "+pid;
		ModelAndView m = new ModelAndView("forward:/listviewforcustomer.jsp");
		
		
		List<ProductType> list = new ArrayList<ProductType>();
		String hql = "from entity.ProductType";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		list = (List<ProductType>)query.list();
		//for(Product p:list){
		//	System.out.println(p.getName());
		//}
		session.flush();
		session.close();

		m.addObject("typelist",list);
		
		
		req.setAttribute("pt",pt);
		return m;
	}
	
	
}
