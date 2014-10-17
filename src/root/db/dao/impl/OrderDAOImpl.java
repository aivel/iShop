package root.db.dao.impl;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import root.db.dao.model.OrderDAO;
import root.db.model.Order;
import root.utils.HibernateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 10/16/2014.
 */
public class OrderDAOImpl implements OrderDAO {
    @Override
    public void addOrder(final Order order) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            session.saveOrUpdate(order);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    @Override
    public void updateOrder(final Order order) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            session.update(order);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Order getOrderById(final Long id) throws SQLException {
        Session session = null;
        Order order = null;
        try {
            session = HibernateUtil.getSession();
            order = (Order) session.load(Order.class, id);
            Hibernate.initialize(order);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return order;
    }

    @Override
    public List getAllOrders() throws SQLException {
        Session session = null;
        List<Order> orders = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            orders = session.createCriteria(Order.class).list();
            Hibernate.initialize(orders);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return orders;
    }

    @Override
    public void deleteOrder(final Order order) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            session.delete(order);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<Order> getAllOrdersByBuyerId(final Long buyerId) throws SQLException {
        Session session = null;
        List<Order> orders = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            orders = session.createCriteria(Order.class)
                    .add(Expression.eq("buyerId", buyerId))
                    .list();
            Hibernate.initialize(orders);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return orders;
    }
}
