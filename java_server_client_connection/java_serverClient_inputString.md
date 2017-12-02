# Java�ŃT�[�o�[�̕׋�

# �ڕW
1. �T�[�o�[�̊�b�̂����炢�炢
    1. �����̃p�\�R�����ŁA�A�N�Z�X���Ă����N���C�A���g�ɕ������Ԃ��T�[�o�[���N������
    2. �����̃p�\�R�����ŃN���C�A���g�Ƃ��ăT�[�o�[�ɃA�N�Z�X���A��������󂯎��
    3. �N���C�A���g���瑗�M����Ă����������\������@�\���T�[�o�[�ɒǉ�
    4. ������𑗐M����@�\���N���C�A���g�ɒǉ�
2. ���͕�������T�[�o�[���ŕ\���\��
3. �N���C�A���g��IP�A�h���X���T�[�o�[���ŕ\������
4. �N���C�A���g���當���񂪑����Ă���������\������
5. �N���C�A���g�����當���񂪑����Ă�����A���ԖڂɃA�N�Z�X���Ă����N���C�A���g�����T�[�o�[���ŋL�������ĕ\��������i�T�[�o�[�N�����ɃJ�E���^��0�ɂ���B�j
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

�R���p�C�����āA�N������B

### 2. �����̃p�\�R�����ŃN���C�A���g�Ƃ��ăT�[�o�[�ɃA�N�Z�X���A��������󂯎��
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

���s���Ă݂悤

### 3. �N���C�A���g���瑗�M����Ă����������\������@�\���T�[�o�[�ɒǉ�

```java:basic01Server02.java
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


```

�܂��A���s�͂��Ȃ�


### 4. ������𑗐M����@�\���N���C�A���g�ɒǉ�

```java:basic01Client02.java
import java.net.Socket;
import java.util.Scanner;
import java.io.PrintWriter;     // add
import java.io.*;               // add
public class basic01Client02 {
    public static void main(String[] args) {
        try{
            Socket socket = new Socket("127.0.0.1", 3838);
			PrintWriter output = new PrintWriter(socket.getOutputStream());         // add
			output.println("�N���C�A���g���當����𑗐M");                         // add
			output.flush();
            Scanner input = new Scanner(socket.getInputStream());
            System.out.println("�T�[�o�[����̃��b�Z�[�W�́u" + input.nextLine() + "�v");
            input.close();
            output.close();         // add
            socket.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }
}


```

���s���Ă݂悤

## 2. ���͕�������T�[�o�[���ŕ\��
�N���C�A���g���̃t�@�C��������������B

```java:basic02Client.java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class basic02Client {
	public static void main(String args[]){
		try{
			Socket socket = new Socket("127.0.0.1", 3838);
			PrintWriter output = new PrintWriter(socket.getOutputStream());
			BufferedReader consoleIn = new BufferedReader( new InputStreamReader(System.in) );      // add
			String consoleInStr = consoleIn.readLine();         // add
			//output.println("----");       // remove
			output.println(consoleInStr);   // add
			output.flush();
			Scanner input = new Scanner(socket.getInputStream());
			System.out.println(input.nextLine());
			input.close();
			output.close();
			socket.close();
		} catch(Exception e){
			System.out.println(e);
		}
	}
}
```

�Q�l�F<a href="http://temosy.net/2011/03/17/java-%E3%82%AD%E3%83%BC%E3%83%9C%E3%83%BC%E3%83%89%E3%81%8B%E3%82%89%E6%96%87%E5%AD%97%E5%88%97%E3%82%92%E5%85%A5%E5%8A%9B%E3%81%99%E3%82%8B/">Java �L�[�{�[�h���當�������͂���</a>

## 3. �N���C�A���g��IP�A�h���X���T�[�o�[���ŕ\�����邷��

```java:basic03Server.java
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
```

�Q�l�F<a href="https://stackoverflow.com/questions/1840420/how-to-find-the-ip-address-of-client-connected-to-server">How to find the IP Address of Client connected to Server? - stack overflow</a>


## 4. �N���C�A���g���當���񂪑����Ă���������\������

```java:basic04Server.java
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Date;
public class basic04Server {
    public static void main(String[] args) {
        try{
            ServerSocket server = new ServerSocket(3838, 5);
            while(true){
                System.out.println("�T�[�o�[�͉ғ����Ă��܂��B");
                Socket socket = server.accept();
				Scanner input = new Scanner(socket.getInputStream());
				System.out.println("�N���C�A���g���瑗���Ă������b�Z�[�W�́u" + input.nextLine() + "�v�ł�");
                String clientIpAddress = socket.getRemoteSocketAddress().toString();
                System.out.println( "�N���C�A���g��IP�A�h���X��port�ԍ��́u" + clientIpAddress + "�v");
                String clientIpAddr = socket.getInetAddress().getHostAddress().toString();
                System.out.println( "�N���C�A���g��IP�A�h���X��port�ԍ��́u" + clientIpAddr + "�v");
                Date time = new Date();                                     // add
                System.out.println( "�����F�u" + time + "�v");              // add
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
```

## 5. �N���C�A���g�����當���񂪑����Ă�����A���ԖڂɃA�N�Z�X���Ă����N���C�A���g�����T�[�o�[���ŋL�������ĕ\��������i�T�[�o�[�N�����ɃJ�E���^��0�ɂ���B�j

```java:basic05Server.java
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Date;
public class basic05Server {
    public static void main(String[] args) {
        try{
            ServerSocket server = new ServerSocket(3838, 5);
            int order = 0;              // add
            while(true){
                System.out.println("�T�[�o�[�͉ғ����Ă��܂��B");
                Socket socket = server.accept();
                order += 1;             // add
				Scanner input = new Scanner(socket.getInputStream());
				System.out.println("�N���C�A���g���瑗���Ă������b�Z�[�W�́u" + input.nextLine() + "�v�ł�");
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
```


## 6. �N���C�A���g����T�[�o�[�ɑ��镶����𕡐��s�ɂ���

```java:basic06Server.java
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
```

```java:basic06Client.java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
public class basic06Client {
	public static void main(String args[]){
		try{
			Socket socket = new Socket("127.0.0.1", 3838);
			PrintWriter output = new PrintWriter(socket.getOutputStream());
			BufferedReader consoleIn = new BufferedReader( new InputStreamReader(System.in) );
			//String consoleInStr = consoleIn.readLine();       // remove
			String consoleInStr = "";                           // add
            while(!consoleInStr.equals("QUIT")){                // change:"QUIT"�Ɠ��͂���ƏI��
			    consoleInStr = consoleIn.readLine();
                output.println(consoleInStr);
                output.flush();
            }
			Scanner input = new Scanner(socket.getInputStream());
			System.out.println(input.nextLine());
			input.close();
			output.close();
			socket.close();
		} catch(Exception e){
			System.out.println(e);
		}
	}
}
```

�Q�l�F<a href="https://www.javadrive.jp/start/string/index4.html">������ƕ�����̔�r</a>


## 7. �T�[�o�[����N���C�A���g�ɑ��镶����𕡐��s�ɂ���

```java:basic07Server.java
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
```

```java:basic07Client.java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
public class basic07Client {
	public static void main(String args[]){
		try{
			Socket socket = new Socket("127.0.0.1", 3838);
			PrintWriter output = new PrintWriter(socket.getOutputStream());
			BufferedReader consoleIn = new BufferedReader( new InputStreamReader(System.in) );
			String consoleInStr = "";
            while(!consoleInStr.equals("QUIT")){
			    consoleInStr = consoleIn.readLine();
                output.println(consoleInStr);
                output.flush();
            }
			Scanner input = new Scanner(socket.getInputStream());
            String serverMessage = "";                              // add
            while(!serverMessage.equals("END SERVER MESSAGE")){     // add
                serverMessage = input.nextLine();                   // add
                System.out.println(serverMessage);                  // add
            }                                                       // add
			input.close();
			output.close();
			socket.close();
		} catch(Exception e){
			System.out.println(e);
		}
	}
}
```


