package org.shop.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.shop.domain.Category;
import org.shop.domain.Inventory;
import org.shop.domain.Item;
import org.shop.util.HibernateUtil;

public class ItemService {
    public static Item getItem(String itemId) {
        Session session = HibernateUtil.getSession();
        Transaction ts = session.beginTransaction();
        Item item = (Item)session.get(Item.class,itemId);
        ts.commit();
        HibernateUtil.closeSession();
        return item;
    }

    public static int getInventoryQuantity(String itemId) {
        Session session = HibernateUtil.getSession();
        Transaction ts = session.beginTransaction();
        Inventory inventory = (Inventory) session.get(Inventory.class,itemId);
        ts.commit();
        HibernateUtil.closeSession();
        return inventory.getQuantity();
    }
}
