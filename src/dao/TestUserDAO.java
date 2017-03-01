package dao;

import com.opensymphony.xwork2.util.ResolverUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;
import model.TestUser;

/**
 * Created by QQZhao on 2/28/17.
 */
public class TestUserDAO {

    public void saveTestUser(TestUser testUser){

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();

        try{

            session.save(testUser);
            tx.commit();

        }catch (Exception ex){
            if(null != tx){
                tx.rollback();
            }
        }finally {
            HibernateUtil.close(session);
        }
    }

    public TestUser getTestUser(int primaryKey){

        TestUser testUser = null;

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();

        try{

            testUser = (TestUser) session.get(TestUser.class, primaryKey);
            tx.commit();

        }catch (Exception ex){
            if(null != tx){
                tx.rollback();
            }
        }finally {
            HibernateUtil.close(session);
        }

        return testUser;
    }

    public void deleteTestUser(int primaryKey){

        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();

        try{

            TestUser testUser = (TestUser) session.get(TestUser.class, primaryKey);
            session.delete(testUser);
            tx.commit();

        }catch (Exception ex){
            if(null != tx){
                tx.rollback();
            }

        }finally {
            HibernateUtil.close(session);
        }
    }

    public void updateTestUser(TestUser testUser){
        Session session = HibernateUtil.openSession();

        Transaction tx = session.beginTransaction();

        try{
            session.update(testUser);
            tx.commit();

        }catch (Exception ex){
            if(null != tx){
                tx.rollback();
            }

        }finally {
            HibernateUtil.close(session);
        }
    }
}
