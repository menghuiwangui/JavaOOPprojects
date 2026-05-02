# 项目概述与功能展示

## 项目简介
一个具有完整购物流程的简易电商购物车系统，采用Java面向对象设计，包含商品管理、购物车操作、订单处理等核心功能。

## 功能模块

### 管理员功能
| 功能 | 说明 |
|------|------|
| 商品上架 | 添加新商品到商品库（ID、名称、价格、库存、分类） |
| 商品下架 | 根据商品ID下架商品 |
| 商品浏览 | 查看所有商品、按名称查询、按类别筛选 |

### 用户功能
| 功能 | 说明 |
|------|------|
| 商品浏览 | 查看所有商品、按名称查询、按类别筛选 |
| 加入购物车 | 添加商品到购物车，支持数量设置和库存校验 |
| 购物车管理 | 浏览、删除、修改数量、清空购物车 |
| 订单生成 | 从购物车选择商品生成订单 |
| 订单管理 | 支付、发货、收货、取消订单 |
| 订单查询 | 按订单号查询、按状态筛选 |

### 核心业务流程
```
商品上架 → 商品浏览 → 加入购物车 → 生成订单 → 支付 → 发货 → 收货
```

## 技术栈
- **语言**: Java
- **数据结构**: HashMap存储商品、ArrayList存储购物车项和订单
- **设计模式**: 工厂模式（ProductFactory）、接口编程（IProduct）
- **异常处理**: StackOverflowError捕获、IllegalArgumentException处理

# UML类图
![UML类图](image/myUML.png)

## 类图说明

| 类/接口 | 职责 |
|---------|------|
| `IProduct` | 商品接口，定义商品基本操作 |
| `Product` | 商品实现类 |
| `ProductFactory` | 工厂类，创建商品实例 |
| `ProductHouse` | 商品库，管理所有商品（HashMap存储） |
| `CartItem` | 购物车项，包含商品和数量 |
| `ShoppingCart` | 购物车，管理购物车项列表 |
| `Order` | 订单类，包含订单号、订单项、时间、金额、状态 |
| `OrderList` | 订单列表，管理所有订单 |
| `OrderStatus` | 订单状态枚举（已创建/已支付/已发货/已收货/已取消） |
| `PaymentMethod` | 支付方式枚举（微信/支付宝/取消） |
| `Main` | 主程序入口，处理菜单交互 |

# 核心代码片段讲解（5处亮点）

## 亮点1：工厂模式 + 接口编程

使用 `ProductFactory` 工厂类创建商品对象，通过 `IProduct` 接口实现解耦：

```java
// ProductFactory.java
public class ProductFactory {
    static Product createProduct(String id, String name, double price, int stock, String category) {
        return new Product(id, name, price, stock, category);
    }
}

// IProduct接口定义
interface IProduct {
    String getId();
    String getName();
    double getPrice();
    int getStock();
    String getCategory();
    void setStock(int stock);
    void setCategory(String category);
    void setName(String name);
    void setPrice(double price);
    void setId(String id);
}
```

**优点**：对象创建与使用分离，便于扩展其他商品类型。

---

## 亮点2：多层菜单导航与权限分离

管理员与用户拥有独立的菜单系统，角色切换时自动调整功能集：

```java
// Main.java - 权限标志
static boolean usAndma = false;  // manager->true  user->false

public static void productsBrowse() {
    // ... 商品浏览逻辑
    if(usAndma){
        managerMune();  // 管理员返回管理员菜单
    }
    else{
        userMenu();     // 用户返回用户菜单
    }
}
```

**优点**：菜单结构清晰，角色权限分明，用户体验良好。

---

## 亮点3：购物车库存校验与合并逻辑

加入购物车时自动检查库存，已存在商品时合并数量：

```java
// ShoppingCart.java - addProducts方法
void addProducts(CartItem item, Map<String, IProduct> products) {
    boolean exist = false;
    for (CartItem i : items) {
        if (i.getProduct().getId().equals(item.getProduct().getId())
            && (i.getQuantity() + item.getQuantity()) <= products.get(item.getProduct().getId()).getStock()) {
            modifyQuantity(i, i.getQuantity() + item.getQuantity());  // 合并数量
            return;
        }
    }
    if (!exist && products.containsKey(item.getProduct().getId())
            && item.getQuantity() <= products.get(item.getProduct().getId()).getStock()) {
        items.add(item);  // 新增商品
    }
}
```

**优点**：避免重复添加商品，库存校验确保数据一致性。

---

## 亮点4：枚举实现订单状态与支付方式管理

使用枚举清晰管理订单状态和支付方式：

```java
// OrderStatus.java
enum OrderStatus {
    CREATED(0, "已创建"),
    PAID(1, "已支付"),
    SHIPPED(2, "已发货"),
    RECEIVED(3, "已收货"),
    CANCELLED(-1, "已取消");

    private final int code;
    private final String description;

    OrderStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static OrderStatus getByCode(int code) {
        for (OrderStatus status : values()) {
            if (status.code == code) return status;
        }
        return null;
    }
}

// PaymentMethod.java
enum PaymentMethod {
    WECHAT("微信"), ALIPAY("支付宝"), CANCEL("取消支付");
    private final String description;
    PaymentMethod(String description) { this.description = description; }
    public String getDescription() { return description; }
}
```

**优点**：类型安全、可读性强、易于扩展。

---

## 亮点5：防御式编程与空值检查

多处使用空值检查和防御性判断，防止NullPointerException：

```java
// ProductHouse.java - addProduct方法
boolean addProduct(IProduct product) {
    if (!products.containsKey(product.getId()) && product.getId() != null
        && product.getName() != null && product.getPrice() != 0
        && product.getStock() != 0 && product.getCategory() != null) {
        products.put(product.getId(), product);
        return true;
    }
    return false;
}

// ShoppingCart.java - calculateTotalAmount方法
public String calculateTotalAmount() {
    double totalamount = 0.0;
    for (CartItem item : items) {
        if (item != null) {  // 空值检查
            totalamount += item.calculateSubtotal();
        }
    }
    return new DecimalFormat("#.00").format(totalamount);
}

// Main.java - 常量在前比较防NPE
if ("yes".equalsIgnoreCase(scanner.nextLine())) {
    sc.emptyCart();
}
```

**优点**：增强代码健壮性，减少运行时异常。

# 遇到的问题与解决方案

## 问题1：Scanner输入缓冲区问题

**现象**：使用 `scanner.nextInt();` 后直接使用 `scanner.nextLine();` 导致第二次读取的内容异常。

**原因**：`nextInt()` 只会读取整数，不会读取输入时的回车符（`\n`）。回车符留在缓冲区中，后续 `nextLine()` 会立即读取这个回车符。

**解决方案**：在 `nextInt()` 之后添加 `nextLine()` 来消耗掉回车符。

```java
int num = scanner.nextInt();
scanner.nextLine();  // 吃掉回车符
String text = scanner.nextLine();  // 正常读取
```

---

## 问题2：总价计算重复累加

**现象**：订单总价计算结果比预期大。

**原因**：`item.calculateSubtotal()` 已经是小计金额（单价 × 数量），代码中又乘了一遍数量。

**解决方案**：直接使用 `calculateSubtotal()` 方法结果，无需再乘数量。

```java
totalamount += item.calculateSubtotal();  // 正确：直接使用小计
```

---

## 问题3：迭代遍历时修改集合

**现象**：从购物车生成订单时，遍历过程中删除元素导致异常。

**解决方案**：使用 `Iterator` 迭代器进行安全删除。

```java
Iterator<CartItem> iterator = items.iterator();
while (iterator.hasNext()) {
    CartItem item = iterator.next();
    if (item != null && item.getProduct() != null && product != null) {
        if (item.getProduct().getId().equals(product.getId())) {
            try {
                Order order = new Order(item);
                System.out.println("已生成订单: " + order.getOrderId());
                iterator.remove();  // 使用迭代器安全删除
                return order;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
```

---

## 问题4：递归调用导致栈溢出

**现象**：菜单返回时使用递归，长时间运行后可能出现 `StackOverflowError`。

**原因**：每次菜单切换都通过递归实现，返回时递归层级不断加深。

**解决方案**：添加异常捕获防止程序崩溃，并考虑改用循环结构替代递归。

---

## 问题5：空指针风险

**现象**：某些情况下获取商品或订单信息时出现空值。

**解决方案**：添加完善的空值检查。

```java
if (item != null && item.getProduct() != null && product != null) {
    if (item.getProduct().getId().equals(product.getId())) {
        // 安全操作
    }
}
```

# 项目总结与改进方向

## 项目总结

### 优点

| 优点 | 说明 |
|------|------|
| 分层清晰 | 商品库、购物车、订单分离，职责明确 |
| 工厂模式 | 使用ProductFactory创建产品，解耦对象创建 |
| 接口设计 | IProduct接口设计合理，便于扩展 |
| 状态管理 | 订单状态、用户/管理员状态清晰，使用枚举管理 |
| 异常处理 | 捕获StackOverflowError防止程序崩溃 |
| 代码健壮 | 多处空值检查，防御式编程 |

### 不足

| 不足 | 说明 |
|------|------|
| 硬编码严重 | 菜单选项数字硬编码，可维护性差 |
| 代码重复 | 多处有重复的菜单显示和输入处理 |
| 缺乏持久化 | 数据内存存储，重启丢失 |
| 线程不安全 | 静态变量在多线程下不安全 |
| 缺少日志 | 只有控制台输出，无日志记录 |
| 递归风险 | 菜单返回使用递归，可能导致栈溢出 |

## 改进方向

### 1. 架构改进

| 类/模块 | 改进方向 |
|---------|----------|
| MenuManager | 菜单管理类，统一处理菜单显示和用户输入 |
| SessionManager | 会话管理类，管理用户登录状态和会话信息 |
| FileStorage | 文件存储类，实现数据持久化（JSON/序列化） |
| DataValidator | 数据验证类，统一验证用户输入数据 |
| Logger | 日志类，记录系统操作和异常信息 |

### 2. 代码质量提升

**使用枚举代替常量**：
```java
enum UserRole { ADMIN, USER }
enum MenuOption { ADD_PRODUCT, REMOVE_PRODUCT, BROWSE }
```

**使用集合类型替代静态变量**：
```java
// 替代 static boolean usAndma
private UserRole currentRole;
```

**消除重复代码**：
- 提取公共方法，如菜单显示、输入读取
- 使用策略模式处理不同角色的菜单

### 3. 用户体验优化

| 优化项 | 说明 |
|--------|------|
| 颜色输出 | 使用ANSI转义码实现彩色输出 |
| 进度条显示 | 加载数据时显示进度条 |
| 清屏功能 | 操作后清屏，保持界面整洁 |
| 快捷键支持 | 支持数字快捷键快速选择 |
| 输入验证 | 实时验证用户输入，提示格式错误 |
| 分页显示 | 商品列表过多时分页展示 |

### 4. 功能扩展

| 扩展功能 | 说明 |
|----------|------|
| 用户登录 | 添加用户注册、登录功能 |
| 折扣系统 | 支持优惠券、满减活动 |
| 商品推荐 | 基于购买历史的推荐 |
| 数据统计 | 销售统计、库存预警 |
| GUI界面 | Swing/JavaFX图形界面 |
| 数据库 | MySQL/SQLite数据存储 |