import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class basic01Server {
    public static void main(String[] args) {
        try{
            ServerSocket server = new ServerSocket(3838, 5);
            while(true){
                System.out.println("�T�[�o�[�͉ғ����Ă��܂��B");
                Socket socket = server.accept();
                PrintWriter output = new PrintWriter(socket.getOutputStream());
                output.println("����ɂ��́I������̓T�[�o�[�ł��I");       // ���̕����񂪕Ԃ����
                output.close();     // PrintWriter��close()�ŕ���̂���{
                socket.close();     // Socket��close()�ŕ���̂���{
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }
}

