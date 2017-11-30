package impls;

import java.util.List;

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
	public Product findProductByName(String name) {
		return pdi.findProductByName(name);
	}

	@Override
	public Boolean addProduct(Product product) {
		Product p = pdi.findProductByName(product.getName());
		if(p!=null){
			return false;
		}else{
		pdi.saveProduct(product);
		return true;
		}
	}

	@Override
	public Product findProductById(int id) {
		Product p = pdi.findProductById(id);
		return p;
	}

	@Override
	public List findAllProduct() {
		
		return pdi.findAllProduct();
	}

	@Override
	public void deleteProduct(int id) {
		pdi.deleteProduct(id);
		
	}
	
	
	
	
	
}
