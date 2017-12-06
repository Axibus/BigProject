package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import entity.Product;
import entity.User;
import impls.ProductServiceImpl;

@Controller
public class ProductController {
	@Resource
	private ProductServiceImpl psi;
	
	//产品列表
	@RequestMapping(value="productlist")
	public ModelAndView productList(HttpSession httpSession){
		//List<Product> list = new ArrayList<Product>();
		//list=psi.findAllProduct();
		ModelAndView mAndView;
		User u =(User) httpSession.getAttribute("user");
		if(u.getAdmin()!=null){
			mAndView = new ModelAndView("forward:listview.jsp");
		}else{
			mAndView = new ModelAndView("forward:listviewforcustomer.jsp");
		}
		//mAndView.addObject("list",list);
		return mAndView;
		
	}
	
	//新增产品
	@RequestMapping(value="/addproduct", method=RequestMethod.POST)
	public ModelAndView addProduct(@RequestParam(value="name",required=false)String name,@RequestParam(value="pid",required=false)int pid,@RequestParam(value="price",required=false)double price){
		Product p = new Product();
		p.setName(name);
		p.setProTypeId(pid);
		p.setPrice(price);
		psi.addProduct(p);
		ModelAndView mAndView = new ModelAndView("forward:productsedit.jsp");
		List<Product> list = psi.findAllProduct();
		return mAndView;
		
		
	}
	
	//进入修改产品
	@RequestMapping(value="editProduct/{id}",method=RequestMethod.GET)
	public ModelAndView editProduct(@PathVariable int id){
		Product p = psi.findProductById(id);
		ModelAndView mAndView = new ModelAndView("editproduct");
		mAndView.addObject("p",p);
		return mAndView;
		
	}
	
	//修改产品
	@RequestMapping(value="editProduct/productedit",method=RequestMethod.POST)
	public String productEdit(@RequestParam(value="id",required=false)int id,@RequestParam(value="name",required=false)String name,@RequestParam(value="pid",required=false)int pid,@RequestParam(value="price",required=false)double price){
		Product p = psi.findProductById(id);
		p.setName(name);
		p.setProTypeId(pid);
		p.setPrice(price);
		psi.updateProduct(p);
		//ModelAndView mAndView = new ModelAndView("listview");
		return "listview";
	}
	//删除产品
	@RequestMapping(value="deleteproduct/{id}",method=RequestMethod.GET)
	public String deleteProduct(@PathVariable int id){
		psi.deleteProduct(id);
		return "listview";
	}
	//单个产品展示
	@RequestMapping(value="productview/{id}")
	public ModelAndView singleProductView(@PathVariable int id){
		Product p = psi.findProductById(id);
		ModelAndView m = new ModelAndView("simpleproduct");
		m.addObject("p",p);
		return m;
	}
}
