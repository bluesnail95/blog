package gdut.ff.xshell;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import org.apache.xalan.xsltc.compiler.util.StringStack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * @Author bluesnail95
 * @Date 2020/5/23 23:11
 * @Description
 */
public class TestConnectLinux {

    public static void main(String[] args) {

//        Connection conn = new Connection("193.112.47.238",22);
//        Session session = null;
//
//        try {
//            //连接到主机
//            conn.connect();
//            //使用用户名和密码校验
//            boolean isconn = conn.authenticateWithPassword("root", "!#19950821abc");
//            if (!isconn) {
//                System.out.println("用户名称或者是密码不正确");
//            } else {
//                System.out.println("已经连接OK");
//            }
//
//            session = conn.openSession();
//            session.execCommand("tail -n 100 /usr/download/blog.log");
//
//            InputStream stdout = session.getStdout();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
//            while(true) {
//                String readLine = reader.readLine();
//                if(null == readLine) {
//                    break;
//                }
//                System.out.println(readLine);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        String version1 = "12";
//        String version2 = "8";
//        System.out.println(version1.compareTo(version2));


        translateNumberToString(1);
        translateNumberToString(12);
        translateNumberToString(123);
        translateNumberToString(1234);
        translateNumberToString(12345);
        translateNumberToString(123456);
        translateNumberToString(1234567);
        translateNumberToString(12345678);
        translateNumberToString(123456789);

    }

    public static void translateNumberToString(int number) {
        String value = String.valueOf(number);
        int k = 0;
        Stack<String> stack = new Stack<>();
        for (int i = value.length() - 1; i >= 0; i--) {
            k++;
            stack.push(String.valueOf(value.charAt(i)));
            if(k == 3 && i != 0) {
                stack.push(",");
                k = 0;
            }
        }

        System.out.print("result is :");
        while(!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
        System.out.println();
    }

}
