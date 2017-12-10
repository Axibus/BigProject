package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entity.Cart;
import entity.Product;
import entity.User;
import impls.CartServiceImpl;
import impls.ProductServiceImpl;

@Controller
public class CartController {

	@Resource
	private CartServiceImpl csi;
	@Resource
	private ProductServiceImpl psi;
	
	
	@RequestMapping(value="addtocart/{pid}&{uid}")
	public ModelAndView addCart(@PathVariable int pid,@PathVariable int uid,HttpSession session){
		User u = (User) session.getAttribute("user");
		if(uid!=u.getUserId()){
			ModelAndView m = new ModelAndView("loginfirst");
			return m;
		}
		if(csi.findCartByUserIdAndProductId(uid, pid)!=null){
			Cart c = csi.findCartByUserIdAndProductId(uid, pid);
			int count = c.getCount();
			count++;
			csi.updateCart(c, count);
		}else{
			csi.addCart(uid, pid);
		}
		ModelAndView m = new ModelAndView("addcartok");
		return m;
	}
	@RequestMapping("viewcart")
	public ModelAndView cartView(HttpSession session){
		User u = (User) session.getAttribute("user");
		List<Cart> cartlist = csi.findCartByUserId(u.getUserId());
		Map productmap = new HashMap();
		for(Cart c:cartlist){
			Product p = psi.findProductById(c.getProductId());
			productmap.put(c.getId(), p);
		}
		
		ModelAndView m = new ModelAndView("cart");
		m.addObject("cart",cartlist);
		m.addObject("product",productmap);
		return m;
	}
	
}
