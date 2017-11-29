package service;

import entity.Product;

public interface ProductService {
	public Product findProductById(int id);
	public void saveProduct(Product product);
}
