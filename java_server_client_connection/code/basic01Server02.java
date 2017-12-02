import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;       // 追加
public class basic01Server02 {
    public static void main(String[] args) {
        try{
            ServerSocket server = new ServerSocket(3838, 5);
            while(true){
                System.out.println("サーバーは稼働しています。");
                Socket socket = server.accept();
				Scanner input = new Scanner(socket.getInputStream());       // 追加:クライアントから受け取った文字列を格納
				System.out.println("クライアントから送られてきたメッセージは「" + input.nextLine() + "」です");   // 追加:格納した文字列を出力
                PrintWriter output = new PrintWriter(socket.getOutputStream());
                output.println("こんにちは！こちらはサーバーです！");
                output.close();
                socket.close();
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }
}

