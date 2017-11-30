package service;

import java.util.List;

import entity.Product;

public interface ProductService {
	public Product findProductByName(String name);
	public Boolean addProduct(Product product);
	public Product findProductById(int id);
	public List findAllProduct();
	public void deleteProduct(int id);
}
