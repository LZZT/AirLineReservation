package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by QQZhao on 2/28/17.
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static{
        try{

            sessionFactory = new Configuration()
                    .configure()
                    .buildSessionFactory();

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static Session openSession(){

        return sessionFactory.openSession();
    }

    public static void close(Session session){
        if (null != session){
            session.close();
        }
    }

}