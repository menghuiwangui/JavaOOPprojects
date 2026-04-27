import java.util.Scanner;
public class Main {
    static ProductHouse  ph = new ProductHouse();
    public static void main(String[] args) {
        mainMenu();
    }
    public static void mainMenu(){
        System.out.println("=".repeat(20)+"简易电商购物车系统"+"=".repeat(20));
        System.out.println("选择你的身份(输入数字 1 or 2 )：");
        System.out.println("1.管理员");
        System.out.println("2.用户");
        System.out.println("3.退出");
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
        System.out.println("5.退出");
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
        System.out.println("7.退出");
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
        ph.addProduct(product);
    }
    public static void productRemove(){

    }
    public static void cartAdd(){

    }
    public static void cartManager(){

    }
    public static void orderPlace(){

    }
    public static void myOrder(){

    }
}