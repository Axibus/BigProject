package impls;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import dao.ProductDao;
import entity.Product;
@Repository
@Transactional
public class productDaoImpl implements ProductDao{

	@Resource
	private SessionFactory sessionFactory; 
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public Product findProductById(int id) {
		String hql = "select * from product where id=?";
		Query query = getSession().createQuery(hql).setInteger(0, id);
		Product p = (Product) query.uniqueResult();
		return p;
	}

	@Override
	public void saveProduct(Product product) {
		getSession().save(product);
		
	}
	
}
