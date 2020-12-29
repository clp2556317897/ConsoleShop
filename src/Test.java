import java.io.InputStream;
import java.util.Scanner;

public class Test {
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
                    ReadproductExcel readproductExcel = new ReadproductExcel ();
                    Product products []=readproductExcel.readAllExcel(inpro);
                    for(Product product:products){
                        System.out.print(product.getProductID());
                        System.out.print("\t"+product.getProductName());
                        System.out.print("\t"+product.getProductPrice());
                        System.out.println("\t"+product.getProductDescribe());
                    }
                    int count=0;
                    Product productes[]=new Product[3];//创建购物车
                    System.out.println("请输入商品ID，把该商品加入购物车:");
                    String productid=sc.next();
                    ReadproductExcel readproductExcel1=new ReadproductExcel();
                    inpro=null;
                    inpro = Class.forName("Test").getResourceAsStream("/Product.xlsx");
                    Product product=readproductExcel1.getProductByid(productid,inpro);
                    if(product!=null){
                        productes[count++]=product;


                    }
                    bo=false;
                    break;
                } else {
                    System.out.println("登录失败");
                }
            }
        }
    }
}
