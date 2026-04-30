import java.util.Map;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.text.DecimalFormat;

// 购物车项
class CartItem 
{
    private final IProduct product;     // 商品
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
public class ShoppingCart 
{
    private final List<CartItem> items;  // 购物车项
    private Date createTime;       // 创建时间

    public ShoppingCart() {
        items = new ArrayList<>();
        createTime = new Date();
    }

    public void setCreateTime(Date createTime) 
    {
        this.createTime = createTime;
    }

    public Date getCreateTime() 
    {
        return createTime;
    }

    void addProducts(CartItem item, Map<String, IProduct> products)  // 添加商品
    {
        boolean exist = false;
        for (CartItem i : items)  // 判断购物车内是否已经存在该商品
        {
            if (i != null && i.getProduct() != null && item != null && item.getProduct() != null)
            {
                if (i.getProduct().getId().equals(item.getProduct().getId()))  // 有则直接修改数量
                {
                    modifyQuantity(i,i.getQuantity() + item.getQuantity());
                    exist = true;
                    return;
                }
            }
        }
        if (!exist)  // 无则添加
        {
            if (products.containsKey(item.getProduct().getId())
                        && item.getQuantity() <= products.get(item.getProduct().getId()).getStock())  // 判断库存，有则加入购物车
                {
                    items.add(item);
                } 
                else 
                {
                    System.out.println("库存不足！");
                }
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

    public String calculateTotalAmount()  // 计算总价  ####后续考虑折扣问题
    {
        double totalamount = 0.0;
        for (CartItem item : items) {
            if (item != null){
                totalamount += item.calculateSubtotal();
            }
        }
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(totalamount);
    }

    public void emptyCart()  // 清空购物车
    {
        items.clear();
    }

    List<CartItem> getItems()
    {
        return items;
    }

Order getOrder(IProduct product) {
        Iterator<CartItem> iterator = items.iterator();
        while (iterator.hasNext()) {
            CartItem item = iterator.next();
            if (item != null && item.getProduct() != null && product != null) {
                if (item.getProduct().getId().equals(product.getId())) {
                    try {
                        Order order = new Order(item);
                        System.out.println("已生成订单: " + order.getOrderId());
                        iterator.remove();
                        return order;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        System.out.println("商品不存在！");
        return null;
    }

    public void printItems()  // 打印购物车项
    {
        if(items.isEmpty()){
            System.out.println("购物车内无商品");
            return;
        }
        for (CartItem item : items) 
        {
            if (item != null){
                IProduct product = item.getProduct();
                System.out.println(product.getId()+" "+product.getName()+" "+product.getPrice()+" "+product.getCategory()+" "+item.getQuantity());
            }
        }
    }
    // ... 添加商品、删除商品、修改数量、计算总价
}