import java.util.Map;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

// 购物车项
class CartItem {
    private IProduct product;     // 商品
    private int quantity;        // 数量

    public CartItem(IProduct product,int quantity){
        this.product = product;
        this.quantity = quantity;
    }
    // 修改数量
    public void changeQuantity(int quantity)  {
        this.quantity = quantity;
    }
    // 计算小计金额的方法
    public double calculateSubtotal() {
        return product.getPrice() * quantity;
    }
    public IProduct getProduct() {
        return product;
    }
    public int getQuantity() {
        return quantity;
    }
}

// 购物车
public class ShoppingCart {
    private List<CartItem> items;  // 购物车项
    private Date createTime;       // 创建时间

    public ShoppingCart() {
        items = new ArrayList<CartItem>();
        createTime = new Date();
    }

    void addProducts(CartItem item, Map<String, IProduct> products)  // 添加商品
    {
        if (products.containsKey(item.getProduct().getId())
                && item.getQuantity() < products.get(item.getProduct().getId()).getStock())  // 判断库存，有则加入购物车
        {
            items.add(item);
        } else {
            System.out.println("库存不足！或商品不存在！");
        }
        // 时间获取应为本地时间,用于后续订单号使用
        // 直接使用 formatter.format(createTime) 即可获取格式化的时间字符串
    }

    void removeProduct(CartItem item)  // 删除商品
    {
        items.remove(item);
    }

    void modifyQuantity(CartItem item, int quantity)  // 修改数量
    {
        items.get(items.indexOf(item)).changeQuantity(quantity);
    }

    public double calculateTotalAmount()  // 计算总价  ####后续考虑折扣问题
    {
        double totalamount = 0.0;
        for (CartItem item : items) {
            totalamount += item.calculateSubtotal() * item.getQuantity();
        }
        return totalamount;
    }

    public void emptyCart()  // 清空购物车
    {
        for (CartItem item : items) {
            items.remove(item);
        }
    }

    List<CartItem> getItems() {
        return items;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime)  // 时间获取应为本地时间,用于后续订单号使用  ####是否有必要有这个函数?
    {
        this.createTime = createTime;
    }

    Order getOrder(IProduct product)  // 生成订单
    {
        for (CartItem item : items) {
            if (item.getProduct().getId().equals(product.getId())) {
                Order order = new Order(item);
                return order;
            }
        }
        System.out.println("商品不存在！");
        return null;
    }


    // ... 添加商品、删除商品、修改数量、计算总价
}