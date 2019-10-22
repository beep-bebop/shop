package org.shop.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.shop.domain.Category;
import org.shop.domain.Item;
import org.shop.domain.LineItem;
import org.shop.domain.Product;
import org.shop.util.HibernateUtil;
import java.util.List;

public class LineItemService {
    public static void insertLineItem(LineItem lineItem) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        session.update(lineItem);
        tx.commit();
        HibernateUtil.closeSession();
    }

    public static List<LineItem> getLineItemsByOrderId(int orderId) {
        Session session=HibernateUtil.getSession();
        Transaction tx=session.beginTransaction();
        Query query=session.createQuery("from LineItem lineitem where lineitem.LineItemPK.orderId=?");
        query.setParameter(0, orderId);
        List<LineItem> list=query.list();
        tx.commit();
        HibernateUtil.closeSession();
        return list;
    }

}
