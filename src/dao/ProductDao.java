package dao;

import entity.Product;

public interface ProductDao {
	public Product findProductById(int id);
	public void saveProduct(Product product);
}
