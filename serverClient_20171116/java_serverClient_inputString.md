# Javaでサーバーの勉強

# 目標
1. サーバーの基礎のおさらいらい
    1. 自分のパソコン内で、クライアント側に文字列を表示するサーバーを起動する
    2. 自分のパソコン内でクライアントとしてサーバーにアクセスし、文字列を送信する。
2. 入力文字列をサーバー側で表示表示
3. クライアントのIPアドレスをサーバー側で表示するする
4. クライアントから文字列が送られてきた時刻を表示するする
5. クライアント側から文字列が送られてきたら、何番目にアクセスしてきたクライアントかをサー
バー内で記憶させて表示させる（サーバー起動時にカウンタを0にする。）
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

### 2. 自分のパソコン内でクライアントとしてサーバーにアクセスし、文字列を送信する。
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


