package Impls;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import dao.OrderDao;
import entity.Cart;
import entity.Orders;


@Repository
@Transactional
public class OrderDaoImpl implements OrderDao{
	
	@Resource
	private SessionFactory sessionFactory; 

	@Override
	public void addOrder(Orders c) {
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
	public Orders findOrderById(int id) {
		String hql = "from entity.Orders where id='"+id+"'";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		Orders c = (Orders)query.uniqueResult();
		session.flush();
		session.close();
		return c;
	}

	@Override
	public List findAllOrder() {
		List<Orders> list=new ArrayList<Orders>();
		String hql = "from entity.Orders";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		list = (List<Orders>)query.list();
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
	public List findOrderByUserId(int id) {
		List<Orders> list=new ArrayList<Orders>();
		String hql = "from entity.Orders where userid='"+id+"'";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		list = (List<Orders>)query.list();
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
	public Boolean deleteOrder(int id) {
		Orders p = this.findOrderById(id);
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
	public List findOrderByUserIdAndProductId(int uid, int pid) {
		String hql = "from entity.Orders where userid='"+uid+"'"+"and productid='"+pid+"'";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		List<Orders> c = query.list();
		session.flush();
		session.close();
		return c;
	}

	@Override
	public void updateOrder(Orders c) {
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

