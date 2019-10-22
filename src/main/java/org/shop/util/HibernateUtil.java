package org.shop.util;

import org.hibernate.*;
import org.hibernate.cfg.*;
public class HibernateUtil
{
    private static final SessionFactory sessionFactory = buildSessionFactory();

    //用来初始化SessionFactory
    private static SessionFactory buildSessionFactory() {

        try {

            // Create the SessionFactory from hibernate.cfg.xml

            return new Configuration().configure().buildSessionFactory();

        }

        catch (Throwable ex) {

            // Make sure you log the exception, as it might be swallowed

            System.err.println("Initial SessionFactory creation failed." + ex);

            throw new ExceptionInInitializerError(ex);

        }

    }
    //得到SessionFactory
    public static SessionFactory getSessionFactory() {

        return sessionFactory;//返回SessionFactory的对象

    }
    private static ThreadLocal<Session> threadLocal = new ThreadLocal<Session>() ;

    public static Session getSession(){
        Session session = (Session) threadLocal.get();
        if( session == null || !session.isOpen() ){
            if( sessionFactory == null ){
                buildSessionFactory();
            }
            session = sessionFactory.openSession() ;
            threadLocal.set(session);
        }
        return session ;
    }

    public static void closeSession() throws HibernateException{
        Session session = threadLocal.get() ;
        threadLocal.set(null);
        if( session != null && session.isOpen()){
            session.close() ;
        }
    }

}