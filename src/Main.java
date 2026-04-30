import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
public class Main 
{
    static ProductHouse ph;  //商品库
    static ShoppingCart sc;  //购物车
    static OrderList ol;
    static boolean usAndma = false;  // manager->true  user->false
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) 
    {
        sc = new ShoppingCart();
        ph = new ProductHouse();
        ol = new OrderList();
        try{
            mainMenu();
        }
        catch(StackOverflowError e){
            System.out.println("捕获到栈溢出错误");
        }
    }
    public static void mainMenu()  // 主菜单，暂无问题
    {
        while(true){
            System.out.println("\n\n" + "==========================" + "简易电商购物车系统" + "==========================");
            System.out.println("选择你的身份(输入数字 1 or 2 or 3):");
            System.out.println("1.管理员");
            System.out.println("2.用户");
            System.out.println("3.退出电商购物车系统");
            int n = scanner.nextInt();
            scanner.nextLine();
            switch(n)
            {
                case 1:
                    try{
                        managerMune();
                    }
                    catch(StackOverflowError e){
                        System.out.println("捕获到栈溢出错误");
                    }
                    break;
                case 2:
                    try{
                        userMenu();
                    }
                    catch(StackOverflowError e){
                        System.out.println("捕获到栈溢出错误");
                    }
                    break;
                case 3:
                    System.out.println("简易电商购物车系统正在退出，感谢您的使用");
                    break;
                default:
                    System.out.println("无效选择，请重新输入！");
            }
        }
    }
    public static void managerMune()  // 管理员菜单 暂无问题
    {
        usAndma = true;
        while (true)
        {
            System.out.println("\n\n" + "==========================" + "管理员:简易电商购物车系统" + "==========================");
            System.out.println("功能：");
            System.out.println("1.商品上架");
            System.out.println("2.商品下架");
            System.out.println("3.商品浏览");
            System.out.println("4.返回上一级菜单");
            System.out.println("5.退出电商购物车系统");
            int n = scanner.nextInt();
            scanner.nextLine();
            switch(n)
            {
                case 1:
                    productAdd();  // 添加商品至商品库
                    break;
                case 2:
                    productRemove(); // 删除商品
                    break;
                case 3:
                    productsBrowse(); // 浏览商品
                    break;
                case 4:
                    try{
                        mainMenu();
                    }
                    catch(StackOverflowError e){
                        System.out.println("捕获到栈溢出错误");
                    }
                    break;
                case 5:
                    System.out.println("简易电商购物车系统正在退出，感谢您的使用");
                default:
                    System.out.println("输入错误，请重新输入");
            }
        }
    }
    public static void userMenu()  // 用户菜单
    {
        usAndma = false;
        while (true)
        {
            System.out.println("\n\n" + "==========================" + "用户：简易电商购物车系统" + "==========================");
            System.out.println("功能：");
            System.out.println("1.商品浏览");
            System.out.println("2.购物车添加商品");
            System.out.println("3.购物车管理");
            System.out.println("4.订单生成");
            System.out.println("5.订单查询");
            System.out.println("6.订单管理");
            System.out.println("7.返回上一级菜单");
            System.out.println("8.退出电商购物车系统");
            int n = scanner.nextInt();
            scanner.nextLine();
            switch(n){
                case 1:  // 暂无问题
                    productsBrowse();
                    break;
                case 2:  // 暂无问题
                    cartAdd();
                    break;
                case 3:  // 用户购物车管理 暂无问题
                    cartManager();
                    break;
                case 4:
                    orderPlace();
                    break;
                case 5:
                    myOrder();
                    break;
                case 6:
                    ordermanager();
                    break;
                case 7:
                    mainMenu();
                    break;
                case 8:
                    System.out.println("简易电商购物车系统正在退出，感谢您的使用");
                    break;
            }
        }
    }
    public static void productsBrowse()  // 暂无问题
    {
        System.out.println("\n\n" + "==========================" + "商品查询" + "==========================");
        System.out.println("1.浏览所有商品");
        System.out.println("2.按名字查询");
        System.out.println("3.按类别筛选");
        System.out.println("4.退出电商购物车系统");
        int n = scanner.nextInt();
        scanner.nextLine();
        switch (n){
            case 1:
                ph.readProducts();
                break;
            case 2:
                String name = scanner.nextLine();
                ph.readNameProducts(name);
                break;
            case 3:
                String category = scanner.nextLine();
                ph.readSortedProducts(category);
                break;
            case 4:
                System.out.println("简易电商购物车系统正在退出，感谢您的使用");
                break;
        }
        if(usAndma){
            managerMune();
        }
        else
        {
            userMenu();
        }
    }
    public static void productAdd()  // 暂无问题
    {
        System.out.println("请输入上架商品ID:");
        String id = scanner.nextLine();
        System.out.println("请输入上架商品名字:");
        String name = scanner.nextLine();
        System.out.println("请输入上架商品的价格:");
        double price = scanner.nextDouble();
        System.out.println("请输入上架商品的库存:");
        int stock = scanner.nextInt();
        scanner.nextLine();
        System.out.println("请输入上架商品的分类:");
        String category = scanner.nextLine();
        IProduct product = ProductFactory.createProduct(id,name,price,stock,category);
        if(ph.addProduct(product) == false)
        {
            System.out.println("添加商品失败");
        }
        else
        {
            System.out.println("添加商品成功");
        }
    }
    public static void productRemove()  // 暂无问题
    {
        System.out.println("请输入要下架商品ID:");
        String id = scanner.nextLine();
        if(ph.removeProduct(id) == false){
            System.out.printf("商品:[%s %s] 下架失败%n", id, ph.getProduct(id).getName());
        }
    }
    public static void cartAdd()  // 暂无问题
    {
        System.out.println("请输入要加入购物车的商品ID");
        String id = scanner.nextLine();
        IProduct product = ph.getProduct(id);
        System.out.println("请输入需要购买的商品数量");
        int num = scanner.nextInt();
        scanner.nextLine();
        CartItem cartItem = new CartItem(product,num);
        sc.addProducts(cartItem,ph.returnProducts());
    }

    public static void cartManager()  // 用户购物车管理 暂无问题
    {
        while (true)
        {
            System.out.println("\n\n" + "==========================" + "用户：购物车管理" + "==========================");
            System.out.println("功能：");
            System.out.println("1.购物车商品浏览");
            System.out.println("2.删除商品");
            System.out.println("3.修改商品数量");
            System.out.println("4.计算总价");
            System.out.println("5.清空购物车");
            System.out.println("6.返回上一级菜单");
            System.out.println("7.退出电商购物车系统");
            int n = scanner.nextInt();
            scanner.nextLine();
            List<CartItem> items = sc.getItems();
            String id;
            switch (n)
            {
                case 1:  // 购物车商品浏览 暂无问题
                    sc.printItems(); 
                    break;
                case 2:  // 删除商品 暂无问题
                    System.out.println("请输入要删除的商品ID");
                    id = scanner.nextLine();
                    boolean delete = false;
                    for(CartItem item : items)
                    {
                        IProduct product = item.getProduct();
                        if(product != null && product.getId().equals(id))
                        {
                            delete = true;
                            sc.removeProduct(item);
                            break;
                        }
                    }
                    if(!delete){
                        System.out.println("商品不存在");
                    }
                    break;
                case 3:  // 修改商品数量 暂无问题
                    System.out.println("请输入要修改数量的商品ID");
                    id = scanner.nextLine();
                    System.out.println("请输入要修改的数量");
                    int num = scanner.nextInt();
                    scanner.nextLine();
                    boolean modify = false;
                    for(CartItem item : items){
                        IProduct product = item.getProduct();
                        if(product.getId().equals(id)){
                            sc.modifyQuantity(item,num);
                            modify = true;
                            break;
                        }
                        if (!modify)
                        {
                            System.out.println("商品不存在!");
                        }
                    }
                    break;
                case 4:  // 计算价格总数 暂无问题
                    System.out.println(sc.calculateTotalAmount());
                    break;
                case 5:  // 清空购物车 暂无问题
                    if(!sc.getItems().isEmpty())
                    {
                        System.out.println("尚有商品未下单，是否清空(yes or no)");
                        if("yes".equalsIgnoreCase(scanner.nextLine()))
                        {
                            sc.emptyCart();
                        }
                    }
                    break;
                case 6:
                    userMenu();
                    break;
                case 7:
                    System.out.println("简易电商购物车系统正在退出，感谢您的使用");
                    break;
            }
        }  
    }
public static void orderPlace(){
        System.out.println("\n\n" + "==========================" + "订单生成" + "==========================");
        System.out.println("1.全部生成");
        System.out.println("2.部分生成");
        int n = scanner.nextInt();
        scanner.nextLine();
        while (true)
        {
            switch (n)
            {
                case 1:  // 全部生成订单 暂无问题
                    List<CartItem> items = sc.getItems();
                    if(items.isEmpty()){
                        System.out.println("购物车内无商品");
                        break;
                    }
                    else{
                        List<Order> successfulOrders = new ArrayList<>();
                        Iterator<CartItem> iterator = items.iterator();
                        while (iterator.hasNext()) {
                            CartItem item = iterator.next();
                            IProduct product = item.getProduct();
                            try {
                                Order order = new Order(item);
                                successfulOrders.add(order);
                                iterator.remove();
                                System.out.println("已生成订单: " + order.getOrderId());
                            } catch (IllegalArgumentException e) {
                                System.err.println("生成订单失败 for product " + product.getId() + ": " + e.getMessage());
                            }
                        }
                        for (Order order : successfulOrders) {
                            ol.addOrder(order);
                        }
                    }
                    return;
                case 2:  // 部分生成订单
                    sc.printItems();
                    System.out.println("请输入想要下单的商品ID:(如果想结束下单请输入over)");
                    String id = scanner.nextLine();
                    while(!"over".equalsIgnoreCase(id)){
                        IProduct product = ph.getProduct(id);
                        Order order = sc.getOrder(product);
                        ol.addOrder(order);
                        id = scanner.nextLine();
                    }
                    return;
                default:
                    System.out.println("输入错误，请重新输入");
                    return;
            }
        }
    }
    public static void myOrder(){
        if(ol.returnOrders().isEmpty()){
            System.out.println("暂时没有订单");
        }
        else{
            ol.showAllOrders();
        }
    }
    public static void ordermanager(){
        while (true)
        {
            System.out.println("\n\n" + "==========================" + "订单管理" + "==========================");
            ol.showAllOrders();
            System.out.println("1.订单支付");
            System.out.println("2.订单发货");
            System.out.println("3.订单收货");
            System.out.println("4.订单取消");
            System.out.println("5.返回上一级菜单");
            System.out.println("6.退出电商购物车系统");
            int n = scanner.nextInt();
            scanner.nextLine();
            String orderId;
            Order order;
            switch(n){
                case 1:
                    System.out.println("请输入要支付的订单号");
                    orderId = scanner.nextLine();
                    order = ol.getOrder(orderId);
                    if(order == null){
                        System.out.println("未找到该订单");
                    }
                    else{
                        order.pay();
                    }
                    break;
                case 2:
                    System.out.println("请输入要发货的订单号");
                    orderId = scanner.nextLine();
                    order = ol.getOrder(orderId);
                    if(order == null){
                        System.out.println("未找到该订单");
                    }
                    else{
                        order.deliver(ph.returnProducts());
                    }
                    break;
                case 3:
                    System.out.println("请输入要收货的订单号");
                    orderId = scanner.nextLine();
                    order = ol.getOrder(orderId);
                    if(order == null){
                        System.out.println("未找到该订单");
                    }
                    else{
                        order.receive();
                    }
                    break;
                case 4:
                    System.out.println("请输入要取消的订单号");
                    orderId = scanner.nextLine();
                    order = ol.getOrder(orderId);
                    if(order == null){
                        System.out.println("未找到该订单");
                    }
                    else{
                        order.cancel(ph.returnProducts());
                    }
                case 5:
                    userMenu();
                case 6:
                    System.out.println("简易电商购物车系统正在退出，感谢您的使用");
                    break;
            }
        }
    }
}