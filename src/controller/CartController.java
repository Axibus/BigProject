package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import Impls.CartServiceImpl;
import Impls.ProductServiceImpl;
import entity.Cart;
import entity.Product;
import entity.User;

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
		ModelAndView m = new ModelAndView("cart");
		if(cartlist!=null){
			
			Map productmap = new HashMap();
			for(Cart c:cartlist){
				Product p = psi.findProductById(c.getProductId());
				productmap.put(c.getId(), p);
			}
			m.addObject("cart",cartlist);
			m.addObject("product",productmap);
			return m;
		}else{
			return m;
		}
	}
	
	@RequestMapping("deletecart/{cid}")
	public ModelAndView deleteCart(@PathVariable int cid){
		csi.deleteCart(cid);
		ModelAndView m = new ModelAndView("redirect:/viewcart");
		return m;
	}
	
	@RequestMapping("deleteallcart")
	public ModelAndView deleteAllCart(HttpSession session){
		User u = (User)session.getAttribute("user");
		List<Cart> cart =csi.findCartByUserId(u.getUserId());
		for(Cart c:cart){
			csi.deleteCart(c.getId());
		}
		
		ModelAndView m = new ModelAndView("redirect:/viewcart"); 
		return m;
		
	}
	
	@RequestMapping("deletpartcart")
	public ModelAndView delEduList(@RequestParam("delcart") int[] ids){
        
		//String result = "{\"result\":\"success\"}";
		
        List<Cart> cartlist = csi.findCartByIds(ids);
        for(Cart c:cartlist){
            csi.deleteCart(c.getId());
        }
        //PrintWriter out = null;
        //response.setContentType("application/json");
        
        //try {
        //    out=response.getWriter();
        //    out.write(result);
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
        
        ModelAndView m = new ModelAndView("redirect:/viewcart");
        return m;
       
    }  
	
	
}
		

	
	
	
	

