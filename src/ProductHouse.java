import java.util.Map;
import java.util.HashMap;

// 商品库
public class ProductHouse {
    private Map<String, IProduct> products; // 商品库
    public ProductHouse(){
        products = new HashMap<>();
    }

    void addProduct(IProduct product)  // 添加商品
    {
        products.put(product.getId(), product);
    }

    IProduct getProduct(String id)  // 按名字查询
    {
        return products.get(id);
    }

    public void removeProduct(String id)  // 删除商品
    {
        products.remove(id);
    }

    public void readProducts() // 查看所有商品
    {
        for (IProduct product : products.values()) {
            System.out.println("商品类别: " + product.getCategory() + " " + product.getId() + " " + product.getName() + " " + product.getPrice() + " " + product.getStock());
        }
    }

    public void readSortedProducts(String category)  // 按类别查询
    {
        for (IProduct product : products.values()) {
            if (product.getCategory().equals(category))
                System.out.println("商品类别: " + product.getCategory() + " " + product.getId() + " " + product.getName() + " " + product.getPrice() + " " + product.getStock());
        }
    }
}