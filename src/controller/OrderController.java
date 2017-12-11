package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import Impls.AddressServiceImpl;
import Impls.CartServiceImpl;
import Impls.OrderServiceImpl;
import Impls.ProductServiceImpl;
import entity.Address;
import entity.Cart;
import entity.Orders;
import entity.Product;
import entity.ProductType;
import entity.User;

@Controller
public class OrderController {
	@Resource
	private OrderServiceImpl osi;
	@Resource
	private AddressServiceImpl asi;
	@Resource
	private CartServiceImpl csi;
	@Resource
	private ProductServiceImpl psi;
	@Resource
	private SessionFactory sessionFactory;
	
	@RequestMapping("addOrder/{cid}")
	public ModelAndView addOrder(@PathVariable int cid){
		
		Cart c = csi.findCartById(cid);
		Product p= psi.findProductById(c.getProductId());
		int count = c.getCount();
		
		ModelAndView m = new ModelAndView("checkorder");
		List<Address> list = new ArrayList<Address>();
		String hql = "from entity.Address where userid='"+c.getUserId()+"'" ;
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		list = (List<Address>)query.list();
		//for(Product p:list){
		//	System.out.println(p.getName());
		//}
		session.flush();
		session.close();
		
		m.addObject("addresses",list);
		m.addObject("p",p);
		m.addObject("count",count);
		return m;	
	}
	
	@RequestMapping("addOrder/orderadd")	
	public ModelAndView orderAdd(@RequestParam(value="aid",required=false)int aid,@RequestParam(value="pid",required=false)int pid,HttpSession session){
		
		User u = (User)session.getAttribute("user");
		
		Cart c = csi.findCartByUserIdAndProductId(u.getUserId(), pid);
		Address a = asi.findAddressById(aid);
		
		osi.addOrder(a, c);
		ModelAndView m = new ModelAndView("orderview");
		List<Orders> orderlist = osi.findOrderByUserId(u.getUserId());
		Map productmap = new HashMap();
		for(Orders o:orderlist){
			Product p = psi.findProductById(o.getProductid());
			productmap.put(o.getId(), p);
		}
		m.addObject("p",productmap);
		m.addObject("order",orderlist);
		
		return m;
		
	}

	
}
