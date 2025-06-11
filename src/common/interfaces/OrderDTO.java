package src.common.interfaces;

import java.io.Serializable;
import java.util.List;

public class OrderDTO implements Serializable {
    private int customerId;
    private int branchId;
    private List<OrderItem> items;

    public OrderDTO(int customerId, int branchId, List<OrderItem> items) {
        this.customerId = customerId;
        this.branchId = branchId;
        this.items = items;
    }

    public int getCustomerId() { return customerId; }
    public int getBranchId() { return branchId; }
    public List<OrderItem> getItems() { return items; }
}
