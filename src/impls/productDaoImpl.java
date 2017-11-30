package impls;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
		
	//用名字查产品
	@Override
	public Product findProductByName(String name) {
		
		String hql = "from entity.Product where name='"+name+"'";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		Product p = (Product)query.uniqueResult();
		session.flush();
		session.close();
		return p;
	}
	//保存产品
	@Override	
	public void saveProduct(Product product) {
		Session session = null;
        Transaction tran = null;

        try {
            session = sessionFactory.openSession();
            tran = session.beginTransaction();// 开启事务

            // 保存用户
            session.save(product);

            tran.commit();// 提交事务

        } catch (Exception e) {
            tran.rollback();// 回滚事务
       } 
            finally {
            session.close();// 关闭session
        }
		
	}
	
	@Override
	public Product findProductById(int id) {

		String hql = "from entity.Product where id='"+id+"'";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		Product p = (Product)query.uniqueResult();
		session.flush();
		session.close();
		return p;
	};
	
	@Override
	//查询所有产品
	public List findAllProduct() {
		List<Product> list=new ArrayList<Product>();
		String hql = "from entity.Product";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		list = (List<Product>)query.list();
		//for(Product p:list){
		//	System.out.println(p.getName());
		//}
		session.flush();
		session.close();
		if(list.isEmpty()){
			return null;
		}else{
		return list;
		}
	}
	
	//删除产品
	@Override
	public Boolean deleteProduct(int id) {
		Product p = this.findProductById(id);
		if(p!=null){
			Session session = null;
	        Transaction tran = null;

	        try {
	            session = sessionFactory.openSession();
	            tran = session.beginTransaction();// 开启事务

	            // 保存用户
	            session.delete(p);

	            tran.commit();// 提交事务

	        } catch (Exception e) {
	           tran.rollback();// 回滚事务
	       } 
	            finally {
	            	session.flush();
	            	session.close();// 关闭session
	        }
	        return true;
		}else{
			return false;
		}
		
		
	}
	@Override
	public void updateProduct(Product p) {
		
		Session session = null;
        Transaction tran = null;

        try {
            session = sessionFactory.openSession();
            tran = session.beginTransaction();// 开启事务

            // 保存用户
            session.update(p);

            tran.commit();// 提交事务

        } catch (Exception e) {
           tran.rollback();// 回滚事务
       } 
            finally {
            	session.flush();
            	session.close();// 关闭session
        }
		
	}
	
	
	
	
}
