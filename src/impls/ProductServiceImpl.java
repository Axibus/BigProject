package impls;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.ProductDao;
import entity.Product;
import service.ProductService;


@Service
public class ProductServiceImpl implements ProductService{

	@Resource
	private productDaoImpl pdi;
	
	@Override
	public Product findProductById(int id) {
		return pdi.findProductById(id);
	}

	@Override
	public void saveProduct(Product product) {
		pdi.saveProduct(product);
		
	}
	
	
}
