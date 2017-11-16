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
