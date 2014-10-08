/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package root.db.dao.model;

import java.sql.SQLException;
import java.util.List;
import root.db.model.Comment;

/**
 *
 * @author Max
 */
public interface CommentDAO {
    public void addComment(final Comment comment) throws SQLException;
    public void updateComment(final Comment comment) throws SQLException;
    public Comment getCommentById(final Long id) throws SQLException;
    public List getAllComments() throws SQLException;
    public List getAllCommentsByProductId(final Long id) throws SQLException;
    public void deleteComment(final Comment comment) throws SQLException;
}
