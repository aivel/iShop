/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package root.db.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import root.db.dao.model.UserDAO;
import root.db.model.User;
import root.utils.HibernateUtil;

/**
 *
 * @author Max
 */
public class UserDAOImpl implements UserDAO {

    @Override
    public void addUser(final User user) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    @Override
    public void updateUser(final User user) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            session.update(user);
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
    public User getUserById(final Long id) throws SQLException {
        Session session = null;
        User user = null;
        try {
            session = HibernateUtil.getSession();
            user = (User) session.load(User.class, id);
            Hibernate.initialize(user);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        
        return user;
    }

    @Override
    public List getAllUsers() throws SQLException {
        Session session = null;
        List<User> users = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            users = session.createCriteria(User.class).list();
            Hibernate.initialize(users);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        
        return users;
    }

    @Override
    public void deleteUser(final User user) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
}
