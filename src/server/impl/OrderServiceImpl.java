package src.server.impl;

import src.common.interfaces.OrderDTO;
import src.common.interfaces.OrderItem;
import src.common.interfaces.OrderService;
import src.server.db.DBConnection;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class OrderServiceImpl extends UnicastRemoteObject implements OrderService {

    public OrderServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String placeOrder(OrderDTO order) throws RemoteException {
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            String orderSQL = "INSERT INTO `order` (customer_id, branch_id, order_date) VALUES (?, ?, NOW())";
            PreparedStatement orderStmt = conn.prepareStatement(orderSQL, PreparedStatement.RETURN_GENERATED_KEYS);
            orderStmt.setInt(1, order.getCustomerId());
            orderStmt.setInt(2, order.getBranchId());
            orderStmt.executeUpdate();

            var keys = orderStmt.getGeneratedKeys();
            if (!keys.next()) throw new RuntimeException("Failed to insert order");
            int orderId = keys.getInt(1);

            String itemSQL = "INSERT INTO `order_item` (order_id, drink_id, quantity, total_price) VALUES (?, ?, ?, ?)";
            PreparedStatement itemStmt = conn.prepareStatement(itemSQL);

            for (OrderItem item : order.getItems()) {
                itemStmt.setInt(1, orderId);
                itemStmt.setInt(2, item.getDrinkId());
                itemStmt.setInt(3, item.getQuantity());
                itemStmt.setDouble(4, item.getTotalPrice());
                itemStmt.addBatch();
            }

            itemStmt.executeBatch();
            conn.commit();
            return "✅ Order placed successfully (Order ID: " + orderId + ")";
        } catch (Exception e) {
            e.printStackTrace();
            return "❌ Failed to place order: " + e.getMessage();
        }
    }
}
