package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import entity.Product;
import impls.ProductServiceImpl;

@Controller
public class ProductController {
	@Resource
	private ProductServiceImpl psi;
	
	@RequestMapping(value="productlist")
	public ModelAndView productList(){
		List<Product> list = new ArrayList<Product>();
		list=psi.findAllProduct();
		ModelAndView mAndView = new ModelAndView("forward:listview.jsp");
		mAndView.addObject("list",list);
		return mAndView;
		
	}
	
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
	@RequestMapping(value="editProduct/{id}",method=RequestMethod.POST)
	public ModelAndView editProduct(@PathVariable int id){
		Product p = new Product();
		p = psi.findProductById(id);
		ModelAndView mAndView = new ModelAndView("forward:editproduct.jsp");
		mAndView.addObject("product",p);
		return mAndView;
		
	}
	@RequestMapping(value="edit",method=RequestMethod.GET)
	public ModelAndView productEdit(@RequestParam(value="id",required=false)int id,@RequestParam(value="name",required=false)String name,@RequestParam(value="pid",required=false)int pid,@RequestParam(value="price",required=false)double price){
		Product p = psi.findProductById(id);
		p.setName(name);
		p.setProTypeId(pid);
		p.setPrice(price);
		ModelAndView mAndView = new ModelAndView("listview.jsp");
		return mAndView;
	}
	
	@RequestMapping(value="deleteproduct/{id}",method=RequestMethod.GET)
	public ModelAndView deleteProduct(@PathVariable int id){
		psi.deleteProduct(id);
		ModelAndView mAndView = new ModelAndView("listview.jsp");
		return mAndView;
	}
	
	
}
