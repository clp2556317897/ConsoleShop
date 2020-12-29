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
            ReadUsersExcel readExcel = new ReadUsersExcel();
            User Users[] = readExcel.readExcel(in);
            for (int i = 0; i < Users.length; i++) {
                if (username.equals(Users[i].getUsername()) && password.equals(Users[i].getPassword())) {
                    System.out.println("登陆成功");
                    bo=false;
                    break;
                } else {
                    System.out.println("登录失败");
                }
            }
        }
    }
}
