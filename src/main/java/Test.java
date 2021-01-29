import org.omg.CORBA.ORB;

import java.io.InputStream;
import java.rmi.MarshalledObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Test {
    static int count=0;
    static Product carts[]=new Product[3];//创建购物车
    static Map<Integer,Integer> ammount=new HashMap<Integer,Integer>();
    static Map<Integer,Float> totalAmountPerProduct=new HashMap<Integer,Float>();
    public static void main(String[] args) throws ClassNotFoundException {
        boolean bo=true;
        while (bo) {
            System.out.println("请输入用户名");
            Scanner sc = new Scanner(System.in);
            String username = sc.next();
            System.out.println("你输入的用户名为 :" + username);

            System.out.println("请输入密码");
            String password = sc.next();
            System.out.println("你输入的密码为 :" + password);
            //File file=new File("C:\\Users\\ASUS\\IdeaProjects\\ConsoleShop\\src\\Users.xlsx");
            InputStream in = Class.forName("Test").getResourceAsStream("/Users.xlsx");
            InputStream inpro = Class.forName("Test").getResourceAsStream("/Product.xlsx");
            ReadUsersExcel readExcel = new ReadUsersExcel();
            User Users[] = readExcel.readExcel(in);
            for (int i = 0; i < Users.length; i++) {
                if (username.equals(Users[i].getUsername()) && password.equals(Users[i].getPassword())) {
                    System.out.println("登陆成功");
                    shopping(sc);
                    while (true) {
                        System.out.println("查看购物车请按1");
                        System.out.println("继续购物请按2");
                        System.out.println("结账请按3");
                        System.out.println("退出请按4");
                        int choose = sc.nextInt();
                        if (choose==1) {
                            viewcarts(sc);
                        }
                        else if(choose==2){
                            shopping(sc);
                        }
                        else if(choose==3){
                              /*
                            1、产生订单（必须有订单类）
                            2、用POI创建Order.xlsx文件
                            3、把购物车里的商品写入Order.xlsx文件
                             */
                            Order order = new Order();
                            order.setUser(Users[i]);//订单关联用户
                            Product products[]=new Product[count];
                            int num=0;
                            /*
                            实际买了2个商品，怎样把carts中的2个Product对象放入products
                             */
                            for(int j=0;j<carts.length;j++){
                                if(carts[j]!=null){
                                    products[j]=carts[j];
                                }
                            }
                            order.setProducts(products);//订单关联商品：实际上应该进行处理，把数组中为null的去除
                            //下订单（创建Excel）
                            order.setAmmount(ammount);//关联购买数量
                           for (Product product:products){
                               int cou=ammount.get(Integer.parseInt(product.getProductID()));
                               totalAmountPerProduct.put(Integer.parseInt(product.getProductID()),new Float(Integer.parseInt(product.getProductPrice())*cou));
                           }
                            order.setTotalAmountPerProduct(totalAmountPerProduct);//关联总价
                            CreateOrder.createOrder(order);

                        }
                        else if(choose==4){
                            break;
                        }
                    }

                    bo=false;
                    break;
                } else {
                    System.out.println("登录失败");
                }
            }
        }
    }
    public static void shopping(Scanner sc) throws ClassNotFoundException {
        InputStream inPro = Class.forName("Test").getResourceAsStream("/product.xlsx");//  /表示的就是classpath
        ReadproductExcel readProductExcel = new ReadproductExcel();
        Product products[] = readProductExcel.readAllExcel(inPro);
        for (Product product : products) {
            System.out.print(product.getProductID());
            System.out.print("\t" + product.getProductName());
            System.out.print("\t\t" + product.getProductPrice());
            System.out.println("\t\t" + product.getProductDescribe());
        }
                    /*
                    遍历数组
                     */

        System.out.println("请输入商品ID以及购买数量,商品ID和数量用逗号隔开,例:1111,4, 把该商品加入购物车：");
        String pInformation=sc.next();
        String str[]=pInformation.split(",");
        String pId=str[0];
        String num=str[1];
        ammount.put(Integer.parseInt(pId),Integer.parseInt(num));

        ReadproductExcel readProductExcel1 = new ReadproductExcel();
        inPro = null;
        inPro = Class.forName("Test").getResourceAsStream("/product.xlsx");//  /表示的就是classpath
        Product product = readProductExcel1.getProductByid(pId, inPro);
        if (product != null) {
                        /*
                        把商品加入购物车
                         */
            carts[count++] = product;
        }

    }
    public static void viewcarts(Scanner sc){
        for (Product product : carts) {
            if (product != null) {
                System.out.print(product.getProductID());
                System.out.print("\t" + product.getProductName());
                System.out.print("\t" + product.getProductPrice());
                System.out.println("\t" + product.getProductDescribe());
            }
        }
    }
}

