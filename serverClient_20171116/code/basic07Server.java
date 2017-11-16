import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Date;
public class basic07Server {
    public static void main(String[] args) {
        try{
            ServerSocket server = new ServerSocket(3838, 5);
            int order = 0;              // add
            while(true){
                System.out.println("サーバーは稼働しています。");
                Socket socket = server.accept();
                order += 1;             // add
				Scanner input = new Scanner(socket.getInputStream());
				System.out.println("クライアントから送られてきたメッセージは「");
                String getString = input.nextLine();
                while(!getString.equals("QUIT")){
                    System.out.println(getString);
                    getString = input.nextLine();
                }
				System.out.println("」です");
                String clientIpAddress = socket.getRemoteSocketAddress().toString();
                System.out.println( "クライアントのIPアドレスとport番号は「" + clientIpAddress + "」");
                String clientIpAddr = socket.getInetAddress().getHostAddress().toString();
                System.out.println( "クライアントのIPアドレスとport番号は「" + clientIpAddr + "」");
                Date time = new Date();
                System.out.println( "時刻：「" + time + "」");
                System.out.println( "このクライアントは「" + order + "」番目です。");
                PrintWriter output = new PrintWriter(socket.getOutputStream());
                // クライアントにかえすメッセージを複数にする
                output.println( "START SERVER MESSAGE");                // add
                output.println( "-------------------------------");     // add
                output.println( "こんにちは！" );                       // add
                output.println( "こちらはサーバーです。" );             // add
                output.println( "複数行の文字列を送ります。");          // add   
                output.println( "-------------------------------");     // add   
                output.println( "END SERVER MESSAGE");                  // add   
                //output.println("こんにちは！こちらはサーバーです！");         // remove
                output.close();
                socket.close();
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
