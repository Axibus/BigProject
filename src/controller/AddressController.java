package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import entity.Address;
import entity.User;
import impls.AddressServiceImpl;

@Controller
public class AddressController {
	@Resource
	private AddressServiceImpl asi;
	
	//增加地址
	@RequestMapping("addaddress")
	public ModelAndView addAddress(@RequestParam("address")String address,HttpSession session){
		Address a = new Address();
		a.setAddress(address);
		User u = (User)session.getAttribute("user");
		u.getAddressSet().add(a);
		a.setUser(u);
		asi.addAddress(a);
		Set s = asi.findAllAddress(session);
		ModelAndView m = new ModelAndView("forward:addresslist.jsp");
		m.addObject("list",s);
		return m;
	}
	//删除地址
	@RequestMapping("deleteaddress/{id}")
	public String deleteAddress(@PathVariable int id){
		asi.deleteAddress(id);
		return "addresslist";
	}
	//修改地址
		@RequestMapping("editaddress/{id}")
		public ModelAndView editAddress(@PathVariable int id){
			Address a =asi.findAddressById(id);
			ModelAndView m = new ModelAndView("addressedit");
			m.addObject("address",a);
			return m;
		}
		@RequestMapping("editaddress/addressedit")
		public String addressEdit(@RequestParam("id")int id,@RequestParam("address")String address,HttpSession session){
			Address a = asi.findAddressById(id);
			a.setAddress(address);
			asi.updateAddress(a);
			return "addresslist";
		}
	
}
