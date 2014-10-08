/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package root.test;

import root.db.dao.Factory;
import root.db.model.Book;
import root.db.model.Comment;
import root.db.model.User;

import java.sql.SQLException;

/**
 *
 * @author Max
 */
public class FillDB {
    public static void addBooks() throws SQLException {
        Book b1 = new Book();
        Book b2 = new Book();
        
        b1.setAuthorName("George Orwell");
        b2.setAuthorName("Mikhail Bulgakov");
        
        b1.setDateWritten(1955534215L);
        b2.setDateWritten(1967892613L);
        
        b1.setTitle("Animal Farm");
        b2.setTitle("The Master and Margarita");
        
        b1.setCoverUrl("resources/static/img/animal_farm.jpg");
        b2.setCoverUrl("resources/static/img/master_and_margarita.jpg");

        b1.setPrice(17.88);
        b2.setPrice(413.36);

        b1.setPriceLocale("en_US");
        b2.setPriceLocale("ru_RU");
        
        Factory.getInstance().getBookDAO().addBook(b1);
        Factory.getInstance().getBookDAO().addBook(b2);
    }
    
    public static void addComments() throws SQLException {
        Comment c1 = new Comment();
        Comment c2 = new Comment();
        Comment c3 = new Comment();
        
        c1.setAuthorId(1L);
        c2.setAuthorId(2L);
        c3.setAuthorId(2L);
        
        c1.setProductId(1L);
        c2.setProductId(1L);
        c3.setProductId(2L);
        
        c1.setText("very interesting book");
        c2.setText("must to read");
        c3.setText("I like it.");
        
        c1.setTimestamp(12851735233L);
        c2.setTimestamp(12352518436L);
        c3.setTimestamp(19812742844L);
        
        Factory.getInstance().getCommentDAO().addComment(c1);
        Factory.getInstance().getCommentDAO().addComment(c2);
        Factory.getInstance().getCommentDAO().addComment(c3);
    }
    
    public static void addUsers() throws SQLException {
        User u1 = new User();
        User u2 = new User();
        User u3 = new User();
        
        u1.setBalance(999142345437L);
        u2.setBalance(0L);
        u3.setBalance(42513L);
        
        u1.setUsername("admin");
        u2.setUsername("Anon");
        u3.setUsername("Frank");
        
        u1.setPassword( String.valueOf( "qwerty".hashCode() ));
        u2.setPassword( String.valueOf( "123456".hashCode() ));
        u3.setPassword( String.valueOf( "PASSWORD".hashCode() ));
        
        
        Factory.getInstance().getUserDAO().addUser(u1);
        Factory.getInstance().getUserDAO().addUser(u2);
        Factory.getInstance().getUserDAO().addUser(u3);
    }
    
    public static void main(String[] args) throws SQLException {
        addBooks();
//        addUsers();
//        addComments();
    }
}
