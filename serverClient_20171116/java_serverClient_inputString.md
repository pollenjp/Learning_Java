# Javaでサーバーの勉強

# 目標
1. サーバーの基礎のおさらいらい
    1. 自分のパソコン内で、アクセスしてきたクライアントに文字列を返すサーバーを起動する
    2. 自分のパソコン内でクライアントとしてサーバーにアクセスし、文字列を受け取る
    3. クライアントから送信されてきた文字列を表示する機能をサーバーに追加
    4. 文字列を送信する機能をクライアントに追加
2. 入力文字列をサーバー側で表示表示
3. クライアントのIPアドレスをサーバー側で表示する
4. クライアントから文字列が送られてきた時刻を表示する
5. クライアント側から文字列が送られてきたら、何番目にアクセスしてきたクライアントかをサーバー内で記憶させて表示させる（サーバー起動時にカウンタを0にする。）
6. クライアントからサーバーに送る文字列を複数行にする
7. サーバーからクライアントに送る文字列を複数行にする

# 本編
## 1. サーバーの基礎のおさらいらい
### 1. 自分のパソコン内で、クライアント側に文字列を表示するサーバーを起動する
以下のファイルを作成作成

```java:basic01Server.java
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class basic01Server {
    public static void main(String[] args) {
        try{
            ServerSocket server = new ServerSocket(3838, 5);
            while(true){
                System.out.println("サーバーは稼働しています。");
                Socket socket = server.accept();
                PrintWriter output = new PrintWriter(socket.getOutputStream());
                output.println("こんにちは！こちらはサーバーです！");
                output.close();     // PrintWriterはclose()で閉じるのが基本
                socket.close();     // Socketはclose()で閉じるのが基本
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
```

コンパイルして、起動する。

### 2. 自分のパソコン内でクライアントとしてサーバーにアクセスし、文字列を受け取る
以下のファイルを作成

```java:basic01Client.java
import java.net.Socket;
import java.util.Scanner;
public class basic01Client {
    public static void main(String[] args) {
        try{
            Socket socket = new Socket("127.0.0.1", 3838);          // localhost server を使用
            Scanner input = new Scanner(socket.getInputStream());
            System.out.println("サーバーからのメッセージは「" + input.nextLine() + "」");
            input.close();          // Scannerはclose()で閉じるのが基本
            socket.close();         // Socketはclose()で閉じるのが基本
        } catch (Exception e){
            System.out.println(e);
        }
    }
}

```

実行してみよう

### 3. クライアントから送信されてきた文字列を表示する機能をサーバーに追加

```java:basic01Server02.java
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;       // 追加
public class basic01Server02 {
    public static void main(String[] args) {
        try{
            ServerSocket server = new ServerSocket(3838, 5);
            while(true){
                System.out.println("サーバーは稼働しています。");
                Socket socket = server.accept();
				Scanner input = new Scanner(socket.getInputStream());       // 追加:クライアントから受け取った文字列を格納
				System.out.println("クライアントから送られてきたメッセージは「" + input.nextLine() + "」です");   // 追加:格納した文字列を出力
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


```

まだ、実行はしない


### 4. 文字列を送信する機能をクライアントに追加

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
			output.println("クライアントから文字列を送信");                         // add
			output.flush();
            Scanner input = new Scanner(socket.getInputStream());
            System.out.println("サーバーからのメッセージは「" + input.nextLine() + "」");
            input.close();
            output.close();         // add
            socket.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }
}


```

実行してみよう

## 2. 入力文字列をサーバー側で表示
クライアント側のファイルを書き換える。

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

参考：<a href="http://temosy.net/2011/03/17/java-%E3%82%AD%E3%83%BC%E3%83%9C%E3%83%BC%E3%83%89%E3%81%8B%E3%82%89%E6%96%87%E5%AD%97%E5%88%97%E3%82%92%E5%85%A5%E5%8A%9B%E3%81%99%E3%82%8B/">Java キーボードから文字列を入力する</a>

## 3. クライアントのIPアドレスをサーバー側で表示するする

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
```

参考：<a href="https://stackoverflow.com/questions/1840420/how-to-find-the-ip-address-of-client-connected-to-server">How to find the IP Address of Client connected to Server? - stack overflow</a>


## 4. クライアントから文字列が送られてきた時刻を表示する

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
                System.out.println("サーバーは稼働しています。");
                Socket socket = server.accept();
				Scanner input = new Scanner(socket.getInputStream());
				System.out.println("クライアントから送られてきたメッセージは「" + input.nextLine() + "」です");
                String clientIpAddress = socket.getRemoteSocketAddress().toString();
                System.out.println( "クライアントのIPアドレスとport番号は「" + clientIpAddress + "」");
                String clientIpAddr = socket.getInetAddress().getHostAddress().toString();
                System.out.println( "クライアントのIPアドレスとport番号は「" + clientIpAddr + "」");
                Date time = new Date();                                     // add
                System.out.println( "時刻：「" + time + "」");              // add
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
```

## 5. クライアント側から文字列が送られてきたら、何番目にアクセスしてきたクライアントかをサーバー内で記憶させて表示させる（サーバー起動時にカウンタを0にする。）

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
                System.out.println("サーバーは稼働しています。");
                Socket socket = server.accept();
                order += 1;             // add
				Scanner input = new Scanner(socket.getInputStream());
				System.out.println("クライアントから送られてきたメッセージは「" + input.nextLine() + "」です");
                String clientIpAddress = socket.getRemoteSocketAddress().toString();
                System.out.println( "クライアントのIPアドレスとport番号は「" + clientIpAddress + "」");
                String clientIpAddr = socket.getInetAddress().getHostAddress().toString();
                System.out.println( "クライアントのIPアドレスとport番号は「" + clientIpAddr + "」");
                Date time = new Date();
                System.out.println( "時刻：「" + time + "」");
                System.out.println( "このクライアントは「" + order + "」番目です。");
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
```


## 6. クライアントからサーバーに送る文字列を複数行にする

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
                System.out.println("サーバーは稼働しています。");
                Socket socket = server.accept();
                order += 1;             // add
				Scanner input = new Scanner(socket.getInputStream());
				System.out.println("クライアントから送られてきたメッセージは「");   // change
                String getString = input.nextLine();
                while(!getString.equals("QUIT")){                                   // change:"QUIT"の文字列が来たら終了
                    System.out.println(getString);                                  // change
                    getString = input.nextLine();                                   // add
                }
				System.out.println("」です");                                       // change
                String clientIpAddress = socket.getRemoteSocketAddress().toString();
                System.out.println( "クライアントのIPアドレスとport番号は「" + clientIpAddress + "」");
                String clientIpAddr = socket.getInetAddress().getHostAddress().toString();
                System.out.println( "クライアントのIPアドレスとport番号は「" + clientIpAddr + "」");
                Date time = new Date();
                System.out.println( "時刻：「" + time + "」");
                System.out.println( "このクライアントは「" + order + "」番目です。");
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
            while(!consoleInStr.equals("QUIT")){                // change:"QUIT"と入力すると終了
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

参考：<a href="https://www.javadrive.jp/start/string/index4.html">文字列と文字列の比較</a>


## 7. サーバーからクライアントに送る文字列を複数行にする

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


