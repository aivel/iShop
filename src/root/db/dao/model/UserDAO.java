/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package root.db.dao.model;

import java.sql.SQLException;
import java.util.List;
import root.db.model.User;

/**
 *
 * @author Max
 */
public interface UserDAO {
    public void addUser(final User user) throws SQLException;
    public void updateUser(final User user) throws SQLException;
    public User getUserById(final Long id) throws SQLException;
    public List getAllUsers() throws SQLException;
    public void deleteUser(final User user) throws SQLException;
}
