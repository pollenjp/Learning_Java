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
                System.out.println("�T�[�o�[�͉ғ����Ă��܂��B");
                Socket socket = server.accept();
                order += 1;             // add
				Scanner input = new Scanner(socket.getInputStream());
				System.out.println("�N���C�A���g���瑗���Ă������b�Z�[�W�́u");
                String getString = input.nextLine();
                while(!getString.equals("QUIT")){
                    System.out.println(getString);
                    getString = input.nextLine();
                }
				System.out.println("�v�ł�");
                String clientIpAddress = socket.getRemoteSocketAddress().toString();
                System.out.println( "�N���C�A���g��IP�A�h���X��port�ԍ��́u" + clientIpAddress + "�v");
                String clientIpAddr = socket.getInetAddress().getHostAddress().toString();
                System.out.println( "�N���C�A���g��IP�A�h���X��port�ԍ��́u" + clientIpAddr + "�v");
                Date time = new Date();
                System.out.println( "�����F�u" + time + "�v");
                System.out.println( "���̃N���C�A���g�́u" + order + "�v�Ԗڂł��B");
                PrintWriter output = new PrintWriter(socket.getOutputStream());
                // �N���C�A���g�ɂ��������b�Z�[�W�𕡐��ɂ���
                output.println( "START SERVER MESSAGE");                // add
                output.println( "-------------------------------");     // add
                output.println( "����ɂ��́I" );                       // add
                output.println( "������̓T�[�o�[�ł��B" );             // add
                output.println( "�����s�̕�����𑗂�܂��B");          // add   
                output.println( "-------------------------------");     // add   
                output.println( "END SERVER MESSAGE");                  // add   
                //output.println("����ɂ��́I������̓T�[�o�[�ł��I");         // remove
                output.close();
                socket.close();
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
