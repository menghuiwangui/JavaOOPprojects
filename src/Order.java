
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Scanner;

enum PaymentMethod {
    WECHAT("微信"),
    ALIPAY("支付宝"),
    CANCEL("取消支付");

    private final String description;

    // 构造方法
    PaymentMethod(String description) {
        this.description = description;
    }

    // 获取显示的名称
    public String getDescription() {
        return description;
    }


    public static PaymentMethod fromChoice(int choice) {
        switch (choice) {
            case 1:
                return PaymentMethod.WECHAT;
            case 2:
                return PaymentMethod.ALIPAY;
            case 3:
                return PaymentMethod.CANCEL;
            default:
                throw new IllegalArgumentException("无效的选项: " + choice);
        }
        /* PlantUML parse解析不了
        return switch (choice) {
            case 1 -> WECHAT;
            case 2 -> ALIPAY;
            case 3 -> CANCEL;
            default -> throw new IllegalArgumentException("无效的选项: " + choice);
        };
         */
    }
}

// 订单
public class Order {
    private  final String orderId;       // 订单号
    private final CartItem item;        // 订单项
    private  Date orderTime;       // 下单时间
    private final double totalAmount;   // 总金额
    private Short status;   // 状态：0-下单；1-支付；2-发货；3-收货；-1-取消

    Order(CartItem item)
    {
        // 空判断
        if (item == null || item.getProduct() == null) 
        {
            throw new IllegalArgumentException("购物项不能为空");
        }

        this.item = item;

        // 时间格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date now = new Date();
        String time = sdf.format(now);

        // 唯一订单号
        orderId = time + item.getProduct().getId() + (int)(Math.random()*900+100);

        orderTime = now;
        totalAmount = item.calculateSubtotal();
        status = 0;
    }

    public void pay(Scanner scanner) // 支付
    {
        setOrderTime();
        System.out.println("你需要支付 : " + totalAmount + " 元!");
        System.out.println("请选择你的支付方式");
        System.out.println("1.微信");
        System.out.println("2.支付宝");
        System.out.println("3.取消支付");
        int n = scanner.nextInt();
        scanner.nextLine();
        try{
            PaymentMethod selectedMethod = PaymentMethod.fromChoice(n);
            if (selectedMethod == PaymentMethod.WECHAT) {
                System.out.println("您选择了微信支付...");
                System.out.println("支付完成");
            } else if (selectedMethod == PaymentMethod.ALIPAY) {
                System.out.println("您选择了支付宝支付...");
                System.out.println("支付完成");
            } else if (selectedMethod == PaymentMethod.CANCEL) {
                System.out.println("已取消支付");
                return;
            }
        }catch (IllegalArgumentException e) {
            System.out.println("输入错误：" + e.getMessage());
        }
        status = 1;
    }

    void deliver(Map<String, IProduct> products) // 发货
    {
        setOrderTime();
        System.out.println("商品已发货!");
        products.get(item.getProduct().getId()).setStock(products.get(item.getProduct().getId()).getStock() - item.getQuantity()); // 发货后，数量出库
        status = 2;
    }

    public void receive() // 收货,对应的OrderList类中的订单也要删除
    {
        setOrderTime();
        System.out.println("商品已收货!");
        status = 3;
    }

    void cancel(Map<String, IProduct> products) // 取消,对应的OrderList类中的订单也要删除
    {
        setOrderTime();
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

    CartItem getItem() {
        return item;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public Short getStatus() {
        return status;
    }
    // ... 构造器、计算方法

    public void setOrderTime() {
        this.orderTime = new Date();
    }


    // 主程序中用户在购物车中生成订单，调用订单方法，如：下单，支付，发货，收货。
    // 订单类中包含订单号、订单项、下单时间、总金额、状态等信息，并提供相应方法。
}