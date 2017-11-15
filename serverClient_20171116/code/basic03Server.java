import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
public class basic03Server {
    public static void main(String[] args) {
        try{
            ServerSocket server = new ServerSocket(3838, 5);
            while(true){
                System.out.println("サーバーは稼働しています。");
                Socket socket = server.accept();
				Scanner input = new Scanner(socket.getInputStream());
				System.out.println("クライアントから送られてきたメッセージは「" + input.nextLine() + "」です");
                String clientIpAddress = socket.getRemoteSocketAddress().toString();                        // add:1
                System.out.println( "クライアントのIPアドレスとport番号は「" + clientIpAddress + "」");     // add
                String clientIpAddr = socket.getInetAddress().getHostAddress().toString();                  // add:2
                System.out.println( "クライアントのIPアドレスとport番号は「" + clientIpAddr + "」");        // add
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
