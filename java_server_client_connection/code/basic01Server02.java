import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;       // �ǉ�
public class basic01Server02 {
    public static void main(String[] args) {
        try{
            ServerSocket server = new ServerSocket(3838, 5);
            while(true){
                System.out.println("�T�[�o�[�͉ғ����Ă��܂��B");
                Socket socket = server.accept();
				Scanner input = new Scanner(socket.getInputStream());       // �ǉ�:�N���C�A���g����󂯎������������i�[
				System.out.println("�N���C�A���g���瑗���Ă������b�Z�[�W�́u" + input.nextLine() + "�v�ł�");   // �ǉ�:�i�[������������o��
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

