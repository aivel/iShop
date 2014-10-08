/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package root.db.dao.model;

import java.sql.SQLException;
import java.util.List;
import root.db.model.Book;

/**
 *
 * @author Max
 */
public interface BookDAO {
    public void addBook(final Book book) throws SQLException;
    public void updateBook(final Book book) throws SQLException;
    public Book getBookById(final Long id) throws SQLException;
    public List getAllBooks() throws SQLException;
    public void deleteBook(final Book book) throws SQLException;
}
