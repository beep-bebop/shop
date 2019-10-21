package org.shop.service;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.shop.domain.Account;
import org.shop.domain.User;
import org.shop.util.HibernateUtil;

import java.util.List;

public class AccountService {
    public Account getAccount(String username) {
        Session session = HibernateUtil.getSession();
        Transaction ts = session.beginTransaction();
        Account account = (Account) session.get(Account.class,username);
        ts.commit();
        HibernateUtil.closeSession();
        return account;
    }

    public Account getAccount(String username, String password) {
        Session session = HibernateUtil.getSession();
        DetachedCriteria dc = DetachedCriteria.forClass(User.class);
        if (username != null) {
            dc.add(Restrictions.eq("userId",username));
        }
        if (password != null) {
            dc.add(Restrictions.eq("password", password));
        }
        Criteria c = dc.getExecutableCriteria(session);
        List<Account> list = c.list();
        if(list.size() == 0)
            return null;
        return list.get(0);
    }

    public void insertAccount(Account account) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        session.save(account);
        tx.commit();
        session.close();
    }

    public void updateAccount(Account account) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        session.update(account);
        tx.commit();
        session.close();
    }
}
