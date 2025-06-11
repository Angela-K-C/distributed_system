package src.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface OrderService extends Remote {
    String placeOrder(OrderDTO order) throws RemoteException;
}