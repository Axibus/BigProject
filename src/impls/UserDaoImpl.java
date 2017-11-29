package impls;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import dao.UserDao;
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
	
}
