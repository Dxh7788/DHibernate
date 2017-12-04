package com.work.hn.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author xh.d
 * @date 2017/12/4 15:35
 */
public class DBUtil {

    private static final SessionFactory sessionFactory;
    public static final ThreadLocal session = new ThreadLocal();
    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }catch (Exception e){
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }
    public static Session currentSession() throws HibernateException {
        Session s = (Session) session.get();
        if (s == null || !s.isOpen()) {
            s = sessionFactory.openSession();
            session.set(s);
        }
        return s;
    }

    public static void closeSession() throws HibernateException {
         Session s = (Session) session.get();
         session.set(null);
         if (s != null) {
             s.close();
         }
    }
    public SessionFactory getSessionFactory() {
     return sessionFactory;
    }
}
