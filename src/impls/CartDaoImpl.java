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

import dao.CartDao;
import entity.Cart;
import entity.Product;



@Repository
@Transactional
public class CartDaoImpl implements CartDao{
	
	@Resource
	private SessionFactory sessionFactory; 
	

	@Override
	public void addCart(Cart c) {
		Session session = null;
        Transaction tran = null;

        try {
            session = sessionFactory.openSession();
            tran = session.beginTransaction();// 开启事务

            // 保存用户
            session.save(c);

            tran.commit();// 提交事务

        } catch (Exception e) {
            tran.rollback();// 回滚事务
       } 
            finally {
            session.close();// 关闭session
        }
		
	}


	@Override
	public Cart findCartById(int id) {
		String hql = "from entity.Cart where id='"+id+"'";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		Cart c = (Cart)query.uniqueResult();
		session.flush();
		session.close();
		return c;
	}


	@Override
	public List findAllCart() {
		List<Cart> list=new ArrayList<Cart>();
		String hql = "from entity.Cart";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		list = (List<Cart>)query.list();
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


	@Override
	public List findCartByUserId(int id) {
		List<Cart> list=new ArrayList<Cart>();
		String hql = "from entity.Cart where userid='"+id+"'";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		list = (List<Cart>)query.list();
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


	@Override
	public Boolean deleteCart(int id) {
		Cart p = this.findCartById(id);
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
	public Cart findCartByUserIdAndProductId(int uid, int pid) {
		String hql = "from entity.Cart where userid='"+uid+"'"+"and productid='"+pid+"'";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		Cart c = (Cart)query.uniqueResult();
		session.flush();
		session.close();
		return c;
	}


	@Override
	public void updateCart(Cart c) {
		Session session = null;
        Transaction tran = null;

        try {
            session = sessionFactory.openSession();
            tran = session.beginTransaction();// 开启事务

            // 保存用户
            session.update(c);

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
