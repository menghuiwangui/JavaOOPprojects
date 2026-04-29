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
        if (!orderList.isEmpty()) 
        {
            for (Order order : orderList) 
            {
                if (order != null)
                {
                    System.out.println(order.getOrderId() + " " + order.getItem().getProduct().getName() + " " + order.getItem().getQuantity() + " " + order.getTotalAmount());
                }
            }
        }
        else
            System.out.println("没有订单!");
    }
    public ArrayList<Order> returnOrders(){
        return orderList;
    }
}
