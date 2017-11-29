package service;

import entity.Product;
import impls.ProductServiceImpl;

public class test {

	public ProductServiceImpl psi;
	Product p = psi.findProductById(1);
	public void main(){
		System.out.println(p.getName());
	}
		
}
