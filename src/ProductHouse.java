
import java.util.Map;

// 商品库
public class ProductHouse {
    private Map<String, Product> products; // 商品库

    public void addProduct(Product product)  // 添加商品
    {
        products.put(product.getId(), product);
    }

    public Product getProduct(String id)  // 按名字查询
    {
        return products.get(id);
    }

    public void removeProduct(String id)  // 删除商品
    {
        products.remove(id);
    }

    public void readProducts() // 查看所有商品
    {
        for (Product product : products.values()) {
            System.out.println("商品类别: " + product.getCategory() + " " + product.getId() + " " + product.getName() + " " + product.getPrice() + " " + product.getStock());
        }
    }

    public void readSortedProducts(String category)  // 按类别查询
    {
        for (Product product : products.values()) {
            if (product.getCategory().equals(category))
                System.out.println("商品类别: " + product.getCategory() + " " + product.getId() + " " + product.getName() + " " + product.getPrice() + " " + product.getStock());
        }
    }
}