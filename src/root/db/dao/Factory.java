/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package root.db.dao;

import root.db.dao.impl.BookDAOImpl;
import root.db.dao.impl.CommentDAOImpl;
import root.db.dao.impl.UserDAOImpl;
import root.db.dao.model.BookDAO;
import root.db.dao.model.CommentDAO;
import root.db.dao.model.UserDAO;

/**
 *
 * @author Max
 */
public class Factory {
    private static BookDAO bookDAO = null;
    private static CommentDAO commentDAO = null;
    private static UserDAO userDAO = null;
    private static Factory instance = null;
    
    public static synchronized Factory getInstance() {
        if (instance == null)
            instance = new Factory();
        
        return instance;
    }
    
    public BookDAO getBookDAO() {
        if (bookDAO == null)
            bookDAO = new BookDAOImpl();
        
        return bookDAO;
    }
    
    public CommentDAO getCommentDAO() {
        if (commentDAO == null)
            commentDAO = new CommentDAOImpl();
        
        return commentDAO;
    }
    
    public UserDAO getUserDAO() {
        if (userDAO == null)
            userDAO = new UserDAOImpl();
        
        return userDAO;
    }
}
