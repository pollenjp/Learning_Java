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
                System.out.println("�T�[�o�[�͉ғ����Ă��܂��B");
                Socket socket = server.accept();
                order += 1;             // add
				Scanner input = new Scanner(socket.getInputStream());
				System.out.println("�N���C�A���g���瑗���Ă������b�Z�[�W�́u");   // change
                String getString = input.nextLine();
                while(!getString.equals("QUIT")){                                   // change:"QUIT"�̕����񂪗�����I��
                    System.out.println(getString);                                  // change
                    getString = input.nextLine();                                   // add
                }
				System.out.println("�v�ł�");                                       // change
                String clientIpAddress = socket.getRemoteSocketAddress().toString();
                System.out.println( "�N���C�A���g��IP�A�h���X��port�ԍ��́u" + clientIpAddress + "�v");
                String clientIpAddr = socket.getInetAddress().getHostAddress().toString();
                System.out.println( "�N���C�A���g��IP�A�h���X��port�ԍ��́u" + clientIpAddr + "�v");
                Date time = new Date();
                System.out.println( "�����F�u" + time + "�v");
                System.out.println( "���̃N���C�A���g�́u" + order + "�v�Ԗڂł��B");
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
