import java.util.ArrayList;
public class OrderList 
{
    private ArrayList<Order> orderList;

    public OrderList() 
    {
        orderList = new ArrayList<Order>();
    }

    public void addOrder(Order order) 
    {
        orderList.add(order);
    }

    public void removeOrder(Order order) 
    {
        orderList.remove(order);
    }

    public void showAllOrders()  // 输出所有订单
    {
        for (Order order : orderList) 
        {
            System.out.println(order.getOrderId() + " " + order.getItem().getProduct().getName() + " " + order.getItem().getQuantity() + " " + order.getTotalAmount());
        }
    }
}
