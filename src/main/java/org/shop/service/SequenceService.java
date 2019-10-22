package org.shop.service;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.shop.domain.Sequence;
import org.shop.util.HibernateUtil;

import java.util.List;

public class SequenceService {
    public static Sequence getSequence(Sequence sequence) {
        Session session = HibernateUtil.getSession();
        DetachedCriteria dc = DetachedCriteria.forClass(Sequence.class);
        if (sequence.getName() != null) {
            dc.add(Restrictions.eq("name",sequence.getName()));
        }
        dc.add(Restrictions.eq("nextid", sequence.getNextId()));
        Criteria c = dc.getExecutableCriteria(session);
        List<Sequence> list = c.list();
        HibernateUtil.closeSession();
        if(list.size() == 0)
            return null;
        return list.get(0);
    }
    public static void updateSequence(Sequence sequence) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        session.update(sequence);
        tx.commit();
        HibernateUtil.closeSession();
    }
}
