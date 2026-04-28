import java.util.Scanner;
public class Main {
    static ProductHouse  ph = new ProductHouse();  //商品库
    static ShoppingCart sc = new ShoppingCart();   //购物车
    public static void main(String[] args) {
        mainMenu();
    }
    public static void mainMenu(){
        System.out.println("=".repeat(20)+"简易电商购物车系统"+"=".repeat(20));
        System.out.println("选择你的身份(输入数字 1 or 2 or 3)：");
        System.out.println("1.管理员");
        System.out.println("2.用户");
        System.out.println("3.退出电商购物车系统");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        switch(n){
            case 1:
                managerMune();
                break;
            case 2:
                userMenu();
                break;
            case 3:
                System.out.println("简易电商购物车系统正在退出，感谢您的使用");
                System.exit(0);
        }
    }
    public static void managerMune(){
        System.out.println("=".repeat(20)+"管理员:简易电商购物车系统"+"=".repeat(20));
        System.out.println("功能：");
        System.out.println("1.商品上架");
        System.out.println("2.商品下架");
        System.out.println("3.商品浏览");
        System.out.println("4.返回上一级菜单");
        System.out.println("5.退出电商购物车系统");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        switch(n){
            case 1:
                productAdd();
                break;
            case 2:
                productRemove();
                break;
            case 3:
                productsBrowse();
                break;
            case 4:
                mainMenu();
                break;
            case 5:
                System.out.println("简易电商购物车系统正在退出，感谢您的使用");
                System.exit(0);
        }
        managerMune();
    }
    public static void userMenu(){
        System.out.println("=".repeat(20)+"用户：简易电商购物车系统"+"=".repeat(20));
        System.out.println("功能：");
        System.out.println("1.商品浏览");
        System.out.println("2.购物车添加商品");
        System.out.println("3.购物车管理");
        System.out.println("4.下单结算");
        System.out.println("5.订单查询");
        System.out.println("6.返回上一级菜单");
        System.out.println("7.退出电商购物车系统");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        switch(n){
            case 1:
                productsBrowse();
                break;
            case 2:
                cartAdd();
                break;
            case 3:
                cartManager();
                break;
            case 4:
                orderPlace();
                break;
            case 5:
                myOrder();
                break;
            case 6:
                mainMenu();
                break;
            case 7:
                System.out.println("简易电商购物车系统正在退出，感谢您的使用");
                System.exit(0);
        }
        userMenu();
    }
    public static void productsBrowse(){
        ph.readProducts();
    }
    public static void productAdd(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入上架商品ID:");
        String id = scanner.nextLine();
        System.out.println("请输入上架商品名字:");
        String name = scanner.nextLine();
        System.out.println("请输入上架商品的价格:");
        double price = scanner.nextDouble();
        System.out.println("请输入上架商品的库存:");
        int stock = scanner.nextInt();
        System.out.println("请输入上架商品的分类:");
        String category = scanner.nextLine();
        IProduct product = ProductFactory.createProduct(id,name,price,stock,category);
        if(ph.addProduct(product) == false){
            System.out.println("添加商品失败");
        }
    }
    public static void productRemove(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要下架商品ID:");
        String id = scanner.nextLine();
        if(ph.removeProduct(id) == false){
            System.out.println("商品:[{id} {ph.getProduct(id).getName()}] 下架失败");
        }
    }
    public static void cartAdd(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要加入购物车的商品ID");
        String id = scanner.nextLine();
        IProduct product = ph.getProduct(id);
        System.out.println("请输入需要购买的商品数量");
        int num = scanner.nextInt();
        CartItem cartItem = new CartItem(product,num);
        sc.addProducts(cartItem,ph.returnProducts());
    }
    public static void cartManager(){
        System.out.println("=".repeat(20)+"用户：购物车管理"+"=".repeat(20));
        System.out.println("功能：");
        System.out.println("1.购物车商品浏览");
        System.out.println("2.删除商品");
        System.out.println("3.修改商品数量");
        System.out.println("4.计算总价");
        System.out.println("5.清空购物车");
        System.out.println("6.返回上一级菜单");
        System.out.println("7.退出电商购物车系统");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        switch (n){
            case 1:
                //ShoppingCart类中浏览全部的接口
            case 2:
                //选择购物项，再删除/
                //接口不好
                CartItem item = null;
                sc.removeProduct(item);
        }

    }
    public static void orderPlace(){

    }
    public static void myOrder(){

    }
}