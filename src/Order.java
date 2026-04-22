
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Map;

// 订单
public class Order {
    private String orderId;       // 订单号
    private CartItem item;        // 订单项
    private Date orderTime;       // 下单时间
    private double totalAmount;   // 总金额
    private Short status;   // 状态：0-下单；1-支付；2-发货；3-收货；-1-取消

    public Order(CartItem item) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdHHmmss");
        Date createTime = new Date(System.currentTimeMillis());
        orderId = formatter.format(createTime.toString() + item.getProduct().getId());  // 订单号为当前时间 + 商品编号
        orderTime = new Date();
        totalAmount = item.calculateSubtotal();
        status = 0;
    }

    public void pay() // 支付
    {
        System.out.println("你需要支付 : " + totalAmount + " 元!");
        status = 1;
    }

    public void deliver(Map<String, Product> products) // 发货
    {
        System.out.println("商品已发货!");
        products.get(item.getProduct().getId()).setStock(products.get(item.getProduct().getId()).getStock() - item.getQuantity()); // 发货后，数量出库
        status = 2;
    }

    public void receive() // 收货
    {
        System.out.println("商品已收货!");
        status = 3;
    }

    public void cancel(Map<String, Product> products) // 取消
    {
        System.out.println("订单已取消!");
        products.get(item.getProduct().getId()).setStock(products.get(item.getProduct().getId()).getStock() + item.getQuantity()); // 取消后，数量入库
        status = -1;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getOrderId() {
        return orderId;
    }

    public CartItem getItem() {
        return item;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public Short getStatus() {
        return status;
    }
    // ... 构造器、计算方法

    // 主程序中用户在购物车中生成订单，调用订单方法，如：下单，支付，发货，收货。
    // 订单类中包含订单号、订单项、下单时间、总金额、状态等信息，并提供相应方法。
}