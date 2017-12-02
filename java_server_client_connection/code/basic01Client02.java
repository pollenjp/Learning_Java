import java.net.Socket;
import java.util.Scanner;
import java.io.PrintWriter;     // add
import java.io.*;               // add
public class basic01Client02 {
    public static void main(String[] args) {
        try{
            Socket socket = new Socket("127.0.0.1", 3838);
			PrintWriter output = new PrintWriter(socket.getOutputStream());         // add
			output.println("クライアントから文字列を送信");                         // add
			output.flush();
            Scanner input = new Scanner(socket.getInputStream());
            System.out.println("サーバーからのメッセージは「" + input.nextLine() + "」");
            input.close();
            output.close();         // add
            socket.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }
}


