import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

enum OrderStatus {
    // 枚举常量及其关联的值
    CREATED(0, "已创建"),
    PAID(1, "已支付"),
    SHIPPED(2, "已发货"),
    RECEIVED(3, "已收货"),
    CANCELLED(-1, "已取消");

    // 枚举的字段
    private final int code;
    private final String description;

    // 枚举构造方法（默认私有）
    OrderStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    // Getter 方法获取值
    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    // 通过code查找枚举
    public static OrderStatus getByCode(int code) {
        for (OrderStatus status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        return null;
    }

    // 通过description查找枚举
    public static OrderStatus getByDescription(String description) {
        for (OrderStatus status : values()) {
            if (status.description.equals(description)) {
                return status;
            }
        }
        return null;
    }
}

public class OrderList 
{
    private final ArrayList<Order> orderList;

    public OrderList() 
    {
        orderList = new ArrayList<>();
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
                    OrderStatus status = OrderStatus.getByCode(order.getStatus());
                    String description;
                    if(status!=null){
                        description = status.getDescription();
                    }
                    else{
                        description = "状态异常";
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String time = sdf.format(order.getOrderTime());
                    System.out.println(order.getOrderId() + " " + order.getItem().getProduct().getName() + " " + order.getItem().getQuantity() + " " + order.getTotalAmount() + " " + description + " " +time);
                }
            }
        }
        else
            System.out.println("没有订单!");
    }
    public ArrayList<Order> getOrderList(){
        return orderList;
    }
    //依据订单号查询
    public Order getOrderByOrderId(String orderId){
        for(Order order : orderList){
            if(order.getOrderId().equals(orderId)){
                return order;
            }
        }
        return null;
    }

    List<Order> getOrdersByStatus(OrderStatus status) {
        List<Order> result = new ArrayList<>();
        for (Order order : orderList) {
            if (order.getStatus() == status.getCode()) { // 枚举比较用 ==
                result.add(order);
            }
        }
        return result;
    }
}
