import java.net.Socket;
import java.util.Scanner;
public class basic01Client {
    public static void main(String[] args) {
        try{
            Socket socket = new Socket("127.0.0.1", 3838);          // localhost server ���g�p
            Scanner input = new Scanner(socket.getInputStream());
            System.out.println("�T�[�o�[����̃��b�Z�[�W�́u" + input.nextLine() + "�v");       // �T�[�o�[����̕ԓ����o��
            input.close();          // Scanner��close()�ŕ���̂���{
            socket.close();         // Socket��close()�ŕ���̂���{
        } catch (Exception e){
            System.out.println(e);
        }
    }
}

