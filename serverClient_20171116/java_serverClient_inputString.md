# Java�ŃT�[�o�[�̕׋�

# �ڕW
1. �T�[�o�[�̊�b�̂����炢�炢
    1. �����̃p�\�R�����ŁA�N���C�A���g���ɕ������\������T�[�o�[���N������
    2. �����̃p�\�R�����ŃN���C�A���g�Ƃ��ăT�[�o�[�ɃA�N�Z�X���A������𑗐M����B
2. ���͕�������T�[�o�[���ŕ\���\��
3. �N���C�A���g��IP�A�h���X���T�[�o�[���ŕ\�����邷��
4. �N���C�A���g���當���񂪑����Ă���������\�����邷��
5. �N���C�A���g�����當���񂪑����Ă�����A���ԖڂɃA�N�Z�X���Ă����N���C�A���g�����T�[
�o�[���ŋL�������ĕ\��������i�T�[�o�[�N�����ɃJ�E���^��0�ɂ���B�j
6. �N���C�A���g����T�[�o�[�ɑ��镶����𕡐��s�ɂ���
7. �T�[�o�[����N���C�A���g�ɑ��镶����𕡐��s�ɂ���

# �{��
## 1. �T�[�o�[�̊�b�̂����炢�炢
### 1. �����̃p�\�R�����ŁA�N���C�A���g���ɕ������\������T�[�o�[���N������
�ȉ��̃t�@�C�����쐬�쐬

```java:basic01Server.java
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
                output.println("����ɂ��́I������̓T�[�o�[�ł��I");
                output.close();     // PrintWriter��close()�ŕ���̂���{
                socket.close();     // Socket��close()�ŕ���̂���{
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
```

### 2. �����̃p�\�R�����ŃN���C�A���g�Ƃ��ăT�[�o�[�ɃA�N�Z�X���A������𑗐M����B
�ȉ��̃t�@�C�����쐬

```java:basic01Client.java
import java.net.Socket;
import java.util.Scanner;
public class basic01Client {
    public static void main(String[] args) {
        try{
            Socket socket = new Socket("127.0.0.1", 3838);          // localhost server ���g�p
            Scanner input = new Scanner(socket.getInputStream());
            System.out.println("�T�[�o�[����̃��b�Z�[�W�́u" + input.nextLine() + "�v");
            input.close();          // Scanner��close()�ŕ���̂���{
            socket.close();         // Socket��close()�ŕ���̂���{
        } catch (Exception e){
            System.out.println(e);
        }
    }
}

```


