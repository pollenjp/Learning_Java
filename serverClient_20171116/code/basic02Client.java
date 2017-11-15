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
