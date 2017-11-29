package controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import impls.ProductServiceImpl;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Resource
	private ProductServiceImpl psi;
	
	
}
