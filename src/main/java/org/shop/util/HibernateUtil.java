package org.shop.util;

import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
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
    public static final ThreadLocal<Session> session = new ThreadLocal<Session>();

    public static Session getSession() throws HibernateException{
        Session s = session.get();
        // 如果该线程还没有Session,则创建一个新的Session
        if (s == null){
            s = sessionFactory.openSession();
            // 将获得的Session变量存储在ThreadLocal变量session里
            session.set(s);
        }
        return s;
    }

    public static void closeSession() throws HibernateException{
        Session s = session.get();
        if (s != null)
            s.close();
        session.set(null);
    }

}