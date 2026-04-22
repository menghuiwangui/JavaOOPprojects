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
                //上架


                break;
            case 2:
                //下架


                break;
            case 3:
                //商品浏览

                break;
            case 4:
                mainMenu();
                break;
            case 5:
                System.out.println("简易电商购物车系统正在退出，感谢您的使用");
                System.exit(0);
        }
    }
    public static void userMenu(){
        System.out.println("=".repeat(20)+"用户：简易电商购物车系统"+"=".repeat(20));
        System.out.println("功能：");
        System.out.println("1.商品浏览");
        System.out.println("2.添加购物车");
        System.out.println("3.购物车管理");
        System.out.println("4.下单结算");
        System.out.println("5.订单查询");
        System.out.println("6.返回上一级菜单");
        System.out.println("7.退出");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        switch(n){
            case 1:
                //
                break;
            case 2:
                //
                break;
            case 3:
                //
                break;
            case 4:
                //
                break;
            case 5:
                //
                break;
            case 6:
                mainMenu();
                break;
            case 7:
                System.out.println("简易电商购物车系统正在退出，感谢您的使用");
                System.exit(0);
        }
    }
}