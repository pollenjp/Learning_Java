import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
public class basic03Server {
    public static void main(String[] args) {
        try{
            ServerSocket server = new ServerSocket(3838, 5);
            while(true){
                System.out.println("�T�[�o�[�͉ғ����Ă��܂��B");
                Socket socket = server.accept();
				Scanner input = new Scanner(socket.getInputStream());
				System.out.println("�N���C�A���g���瑗���Ă������b�Z�[�W�́u" + input.nextLine() + "�v�ł�");
                String clientIpAddress = socket.getRemoteSocketAddress().toString();                        // add:1
                System.out.println( "�N���C�A���g��IP�A�h���X��port�ԍ��́u" + clientIpAddress + "�v");     // add
                String clientIpAddr = socket.getInetAddress().getHostAddress().toString();                  // add:2
                System.out.println( "�N���C�A���g��IP�A�h���X��port�ԍ��́u" + clientIpAddr + "�v");        // add
                PrintWriter output = new PrintWriter(socket.getOutputStream());
                output.println("����ɂ��́I������̓T�[�o�[�ł��I");
                output.close();
                socket.close();
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
