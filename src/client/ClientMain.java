package src.client;

import src.common.interfaces.OrderDTO;
import src.common.interfaces.OrderItem;
import src.common.interfaces.OrderService;

import java.rmi.Naming;
import java.util.Arrays;

public class ClientMain {
    public static void main(String[] args) {
        try {
            OrderService service = (OrderService) Naming.lookup("rmi://localhost:1099/OrderService");

            OrderItem item1 = new OrderItem(1, 2, 200.0);
            OrderDTO order = new OrderDTO(1, 2, Arrays.asList(item1));

            String response = service.placeOrder(order);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
