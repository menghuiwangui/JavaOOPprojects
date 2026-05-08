import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

// 商品库
public class ProductHouse {
    private final Map<String, IProduct> products; // 商品库
    public ProductHouse(){
        products = new HashMap<>();
    }

    boolean addProduct(IProduct product)  // 添加商品  ######
    {
        if (!products.containsKey(product.getId()) && product.getId()!= null && product.getName()!= null && product.getPrice()!= 0 && product.getStock()!= 0 && product.getCategory()!= null){
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

    IProduct getProduct(String id)  // 按id查询
    {
        if (products.containsKey(id))
            return products.get(id);
        else 
            System.out.println("商品不存在!");
        return null;
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
        if(products.isEmpty()){
            System.out.println("当前商品库里暂无商品!");
            return;
        }
        for (IProduct product : products.values()) {
            System.out.println("商品类别: " + product.getCategory() + " " + product.getId() + " " + product.getName() + " " + product.getPrice() + " " + product.getStock());
        }
    }

    public void readSortedProducts(String category)  // 按类别查询
    {
        boolean found = false;
        for (IProduct product : products.values()) {
            if (Objects.equals(product.getCategory(),category))
            {
                System.out.println("商品类别: " + product.getCategory() + " " + product.getId() + " " + product.getName() + " " + product.getPrice() + " " + product.getStock());
                found = true;
            }
            else if (product.getName().equalsIgnoreCase(category))  // 添加模糊查询功能
            {
                System.out.println("商品类别: " + product.getCategory() + " " + product.getId() + " " + product.getName() + " " + product.getPrice() + " " + product.getStock());
                found = true;
            }
        }
        
        if (!found)
        {
            System.out.println("没有找到类别为: " + category + " 的商品!");
        }
    }

    public void readNameProducts(String name)  // 按名字查询
    {
        boolean found = false;
        for (IProduct product : products.values()) {
            if (product.getName().equalsIgnoreCase(name)){  // 添加模糊查询功能
                System.out.println("商品类别: " + product.getCategory() + " " + product.getId() + " " + product.getName() + " " + product.getPrice() + " " + product.getStock());
                found = true;
            }
        }
        if (!found){
            System.out.println("没有找到名称为: " + name + " 的商品!");
        }
    }

    Map<String, IProduct> returnProducts(){
        return this.products;
    }
}