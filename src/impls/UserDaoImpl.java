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
import dao.UserDao;
import entity.Product;
import entity.User;
@Repository
@Transactional
public class UserDaoImpl implements UserDao{
	
	@Resource
	private SessionFactory sf;
	
	@Override
	public User findUserByNameOrEmail(String nameoremail) {
		String hql = "from entity.User where username='"+nameoremail+"'";
		String hql2 = "from entity.User where email='"+nameoremail+"'";
		Session session = sf.openSession();
		User u = new User();
		Query query = session.createQuery(hql);
		u = (User)query.uniqueResult();
		if(u!=null){
			return u;
		}else{
			
			User u2 = new User();
			Query query2 = session.createQuery(hql2);
			u2 = (User)query.uniqueResult();
			return u2;
		}
		
		
	}

	@Override
	public void saveUser(User user) {
		
		Session session = null;
        Transaction tran = null;

        try {
            session = sf.openSession();
            tran = session.beginTransaction();// 开启事务

            // 保存用户
            session.save(user);

            tran.commit();// 提交事务

        //} catch (Exception e) {
         //   tran.rollback();// 回滚事务
       } 
            finally {
            session.close();// 关闭session
        }
		
	}

	
	
	//找到所有用户
	@Override
	public List findAllUser() {
		List<User> list=new ArrayList<User>();
		String hql = "from entity.Product";
		Session session = sf.openSession();
		Query query = session.createQuery(hql);
		list = (List<User>)query.list();
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
	public Boolean deleteUser(int id) {
		User p = this.findUserById(id);
		if(p!=null){
			Session session = null;
	        Transaction tran = null;

	        try {
	            session = sf.openSession();
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
	public User findUserById(int id) {
		String hql = "from entity.User where id='"+id+"'";
		Session session = sf.openSession();
		Query query = session.createQuery(hql);
		User p = (User)query.uniqueResult();
		session.flush();
		session.close();
		return p;
	}
	
}
