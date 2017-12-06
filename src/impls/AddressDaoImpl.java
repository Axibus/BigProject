package impls;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dao.AddressDao;
import entity.Address;
import entity.Product;
import entity.User;
@Repository
@Transactional
public class AddressDaoImpl implements AddressDao{
	
	
	@Resource
	private SessionFactory sessionFactory; 

	@Override
	public void addAddress(Address address) {
		Session session = null;
        Transaction tran = null;

        try {
            session = sessionFactory.openSession();
            tran = session.beginTransaction();// 开启事务

            // 保存用户
            session.save(address);

            tran.commit();// 提交事务

        } catch (Exception e) {
            tran.rollback();// 回滚事务
       } 
            finally {
            session.close();// 关闭session
        }
		
	}

	@Override
	public Boolean deleteAddress(int id) {
		Address p = this.findAddressById(id);
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
	public void updateAddress(Address address) {
		Session session = null;
        Transaction tran = null;

        try {
            session = sessionFactory.openSession();
            tran = session.beginTransaction();// 开启事务

            // 保存用户
            session.update(address);

            tran.commit();// 提交事务

        } catch (Exception e) {
           tran.rollback();// 回滚事务
       } 
            finally {
            	session.flush();
            	session.close();// 关闭session
        }
		
	}

	@Override
	public Address findAddressById(int id) {
		String hql = "from entity.Address where id='"+id+"'";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		Address a = (Address)query.uniqueResult();
		session.flush();
		session.close();
		return a;
	}

	@Override
	public Set findAllAddress(HttpSession session) {
		User u = (User)session.getAttribute("user");
		Set address = u.getAddressSet();
		return address;
	}

}
