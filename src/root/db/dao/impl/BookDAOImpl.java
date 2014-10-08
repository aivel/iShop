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
import root.db.dao.model.BookDAO;
import root.db.model.Book;
import root.utils.HibernateUtil;

/**
 *
 * @author Max
 */
public class BookDAOImpl implements BookDAO {

    @Override
    public void addBook(final Book book) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            session.saveOrUpdate(book);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    @Override
    public void updateBook(final Book book) throws SQLException {
        Session session = null;
            try {
                session = HibernateUtil.getSession();
                session.beginTransaction();
                session.update(book);
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
    public Book getBookById(final Long id) throws SQLException {
        Session session = null;
        Book book = null;
        try {
            session = HibernateUtil.getSession();
            book = (Book) session.load(Book.class, id);
            Hibernate.initialize(book);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        
        return book;
    }

    @Override
    public List<Book> getAllBooks() throws SQLException {
        Session session = null;
        List<Book> books = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            books = session.createCriteria(Book.class).list();
            Hibernate.initialize(books);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        
        return books;
    }

    @Override
    public void deleteBook(final Book book) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            session.delete(book);
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
