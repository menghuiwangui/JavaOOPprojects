

// 商品类接口 (可放入折扣运算)
interface IProduct {
    public String getId();
    public String getName();
    public double getPrice();
    public int getStock();
    public String getCategory();
    public void setStock(int stock);
    public void setCategory(String category);
    public void setName(String name);
    public void setPrice(double price);
    public void setId(String id);
}

// 商品类
class Product implements IProduct {
    private String id;           // 商品编号
    private String name;         // 商品名称
    private double price;        // 单价
    private int stock;           // 库存
    private String category;     // 分类   后续可用于商品搜索

    public Product(String id, String name, double price, int stock, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getCategory() {
        return category;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setId(String id) {
        this.id = id;
    }
}

// 商品工厂类
public class ProductFactory {
    public static IProduct createProduct(String id, String name, double price, int stock, String category) {
        return new Product(id, name, price, stock, category);
    }
}