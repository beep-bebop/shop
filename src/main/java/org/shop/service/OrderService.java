package org.shop.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.shop.domain.*;
import org.shop.util.HibernateUtil;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {
    public void insertOrder(Order order) {
        order.setOrderId(getNextId("ordernum"));
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        session.save(order);
        tx.commit();
        HibernateUtil.closeSession();


//        orderMapper.insertOrderStatus(order);
//        先不做order status




        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItem lineItem = (LineItem) order.getLineItems().get(i);
            lineItem.setOrderId(order.getOrderId());
            LineItemService.insertLineItem(lineItem);
        }
    }

    @Transactional
    public Order getOrder(int orderId) {
        Session session = HibernateUtil.getSession();
        Transaction ts = session.beginTransaction();
        Order order = (Order) session.get(Order.class,orderId);
        ts.commit();
        HibernateUtil.closeSession();
        order.setLineItems(LineItemService.getLineItemsByOrderId(orderId));

        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItem lineItem = (LineItem) order.getLineItems().get(i);
            Item item = ItemService.getItem(lineItem.getItemId());
            item.setQuantity(ItemService.getInventoryQuantity(lineItem.getItemId()));
            lineItem.setItem(item);
        }

        return order;
    }

    public List<Order> getOrdersByUsername(String username) {
        Session session=HibernateUtil.getSession();
        Transaction tx=session.beginTransaction();
        Query query=session.createQuery("from Order order where order.userId=?");
        query.setParameter(0, username);
        List<Order> list=query.list();
        tx.commit();
        HibernateUtil.closeSession();
        return list;
    }

    public int getNextId(String name) {
        Sequence sequence = new Sequence(name, -1);
        sequence = (Sequence) SequenceService.getSequence(sequence);
        if (sequence == null) {
            throw new RuntimeException("Error: A null sequence was returned from the database (could not get next " + name
                    + " sequence).");
        }
        Sequence parameterObject = new Sequence(name, sequence.getNextId() + 1);
        SequenceService.updateSequence(parameterObject);
        return sequence.getNextId();
    }
}
