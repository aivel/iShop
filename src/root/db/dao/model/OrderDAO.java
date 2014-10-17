package root.db.dao.model;

import root.db.model.Order;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Max on 10/16/2014.
 */
public interface OrderDAO {
    public void addOrder(final Order order) throws SQLException;
    public void updateOrder(final Order order) throws SQLException;
    public Order getOrderById(final Long id) throws SQLException;
    public List getAllOrders() throws SQLException;
    public void deleteOrder(final Order order) throws SQLException;
    public List<Order> getAllOrdersByBuyerId(final Long buyerId) throws SQLException;
}
