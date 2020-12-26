import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        System.out.println("请输入用户名");
        Scanner sc=new Scanner(System.in);
        String UserName=sc.next();
        System.out.println("你输入的用户名为 :" + UserName);

        System.out.println("请输入密码");
        String PassWord=sc.next();
        System.out.println("你输入的密码为 :" + PassWord);
    }
}
