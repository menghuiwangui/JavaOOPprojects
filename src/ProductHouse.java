import java.util.Map;
import java.util.HashMap;

// 商品库
public class ProductHouse {
    private Map<String, IProduct> products; // 商品库
    public ProductHouse(){
        products = new HashMap<>();
    }

    public boolean addProduct(IProduct product)  // 添加商品  ######
    {
        if (!products.containsKey(product.getId())){
            System.out.println("添加商品成功!");
            System.out.println("商品类别: " + product.getCategory() + " " + product.getId() + " " + product.getName() + " " + product.getPrice() + " " + product.getStock());
            products.put(product.getId(), product);
            return true;
        }
        else 
        {
            System.out.println("商品已存在!");
             System.out.println("存在的商品类别: " + product.getCategory() + " " + product.getId() + " " + product.getName() + " " + product.getPrice() + " " + product.getStock());
            return false;
        }
    }

    public IProduct getProduct(String id)  // 按名字查询
    {
        return products.get(id);
    }

    public boolean removeProduct(String id)  // 删除商品  ######
    {
        if (products.containsKey(id))
        {
            products.remove(id);
            System.out.println("删除商品成功!");
            return true;
        }
        else
        {
            System.out.println("商品不存在!");
            return false;
        }
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