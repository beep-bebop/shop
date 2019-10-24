package org.shop.domain;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.shop.util.HibernateUtil;

public class App {
    public static void main(String[] args) {

        //创建对象
        User user = new User();
        user.setPassword("111");
        user.setCellphone("111");
        user.setUsername("give me the fucking success");

        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
        HibernateUtil.closeSession();

    }
}