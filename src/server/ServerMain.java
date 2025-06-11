package src.server;

import src.common.interfaces.OrderService;
import src.server.impl.OrderServiceImpl;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServerMain {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            OrderService service = new OrderServiceImpl();
            Naming.rebind("rmi://localhost:1099/OrderService", service);
            System.out.println("✅ RMI Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
