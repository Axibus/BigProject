package dao;

import java.util.List;

import entity.Product;

public interface ProductDao {
	public Product findProductById(int id);
	public Product findProductByName(String name);
	
	public void saveProduct(Product product);
	public List findAllProduct();
	public Boolean deleteProduct(int id);
	public void updateProduct(Product p);
}
