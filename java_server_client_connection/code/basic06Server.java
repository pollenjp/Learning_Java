import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Date;
public class basic06Server {
    public static void main(String[] args) {
        try{
            ServerSocket server = new ServerSocket(3838, 5);
            int order = 0;              // add
            while(true){
                System.out.println("サーバーは稼働しています。");
                Socket socket = server.accept();
                order += 1;             // add
				Scanner input = new Scanner(socket.getInputStream());
				System.out.println("クライアントから送られてきたメッセージは「");   // change
                String getString = input.nextLine();
                while(!getString.equals("QUIT")){                                   // change:"QUIT"の文字列が来たら終了
                    System.out.println(getString);                                  // change
                    getString = input.nextLine();                                   // add
                }
				System.out.println("」です");                                       // change
                String clientIpAddress = socket.getRemoteSocketAddress().toString();
                System.out.println( "クライアントのIPアドレスとport番号は「" + clientIpAddress + "」");
                String clientIpAddr = socket.getInetAddress().getHostAddress().toString();
                System.out.println( "クライアントのIPアドレスとport番号は「" + clientIpAddr + "」");
                Date time = new Date();
                System.out.println( "時刻：「" + time + "」");
                System.out.println( "このクライアントは「" + order + "」番目です。");
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
