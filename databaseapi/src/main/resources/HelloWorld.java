import java.io.*;
/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 14.05.13
 * Time: 9:33
 * To change this template use File | Settings | File Templates.
 */
public class HelloWorld {
    public static void main(String[] ar) throws Exception{
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        StringBuilder builder = new StringBuilder();
        while((line=r.readLine())!=null){
            builder.append(line);
        }
        Thread.sleep(2000);
        System.out.println("Hello "+builder.toString());
        r.close();
    }
}
