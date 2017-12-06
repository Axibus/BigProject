package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
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
		if(u!=null){
			if(u.getAdmin()!=null){
				mAndView = new ModelAndView("forward:listview.jsp");
			}else{
				mAndView = new ModelAndView("forward:listviewforcustomer.jsp");
			}
		}else{
			mAndView = new ModelAndView("forward:listviewforcustomer.jsp");
		}
		//mAndView.addObject("list",list);
		return mAndView;
		
	}
	
	//新增产品
	@RequestMapping(value="/addproduct", method=RequestMethod.POST)
	public ModelAndView addProduct(@RequestParam(value="name",required=false)String name,
			@RequestParam(value="pid",required=false)int pid,
			@RequestParam(value="price",required=false)double price,
			@RequestParam(value="img1",required=false)CommonsMultipartFile img1,
			@RequestParam(value="img2",required=false)CommonsMultipartFile img2,
			@RequestParam(value="img3",required=false)CommonsMultipartFile img3,
			@RequestParam(value="intro",required=false)String intro,
			HttpServletRequest request
			) throws IllegalStateException, IOException{
		Product p = new Product();
		p.setName(name);
		p.setProTypeId(pid);
		p.setPrice(price);
		p.setIntro(intro);
		
		//文件上传
		String path = request.getContextPath();  
		String projectpath = "E:/JAVAworkspace"+path;  
		
		System.out.println("fileName："+img1.getOriginalFilename());
        String path1=projectpath+"/Upload/"+img1.getOriginalFilename();
        String realpath1="/file/"+img1.getOriginalFilename();
        p.setImg1(realpath1);
        File image1=new File(path1);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        img1.transferTo(image1);
        
        
        System.out.println("fileName："+img2.getOriginalFilename());
        String path2=projectpath+"/Upload/"+img2.getOriginalFilename();
        String realpath2="/file/"+img2.getOriginalFilename();
        p.setImg2(realpath2);
        File image2=new File(path2);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        img2.transferTo(image2);
        
        
        System.out.println("fileName："+img3.getOriginalFilename());
        String path3=projectpath+"/Upload/"+img3.getOriginalFilename();
        String realpath3="/file/"+img3.getOriginalFilename();
        p.setImg3(realpath3);
        File image3=new File(path3);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        img3.transferTo(image3);
		
		
		
		
		psi.addProduct(p);
		System.out.println(p.getImg1());
		ModelAndView mAndView = new ModelAndView("forward:listview.jsp");
		//List<Product> list = psi.findAllProduct();
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
