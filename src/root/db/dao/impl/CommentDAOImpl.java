package root.db.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import root.db.dao.model.CommentDAO;
import root.db.model.Comment;
import root.utils.HibernateUtil;

/**
 *
 * @author Max
 */
public class CommentDAOImpl implements CommentDAO {

    @Override
    public void addComment(final Comment comment) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            session.saveOrUpdate(comment);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    @Override
    public void updateComment(final Comment comment) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            session.update(comment);
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
    public Comment getCommentById(final Long id) throws SQLException {
        Session session = null;
        Comment comment = null;
        try {
            session = HibernateUtil.getSession();
            comment = (Comment) session.load(Comment.class, id);
            Hibernate.initialize(comment);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        
        return comment;
    }

    @Override
    public List getAllComments() throws SQLException {
        Session session = null;
        List<Comment> comments = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            comments = session.createCriteria(Comment.class).list();
            Hibernate.initialize(comments);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        
        return comments;
    }
    
    @Override
    public List getAllCommentsByProductId(Long id) throws SQLException {
        Session session = null;
        List<Comment> comments = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            comments = session.createCriteria(Comment.class)
                    .add(Restrictions.eq("productId", id)).list();
            Hibernate.initialize(comments);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        
        return comments;
    }

    @Override
    public void deleteComment(final Comment comment) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            session.delete(comment);
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
