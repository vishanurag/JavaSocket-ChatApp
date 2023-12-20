import java.util.*;
import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(" Client Started...\n");
        boolean isLive = true;
        Scanner in = new Scanner(System.in);
        Socket s = new Socket("localhost", 6932);

        System.out.println(" Server Connected...\n");
        while(isLive) {

            // replying to Server...
            System.out.print(" Reply to Server->     ");
            String rep = in.nextLine();

            // sending reply to Srever...
            DataOutputStream dataOut = new DataOutputStream(s.getOutputStream());
            dataOut.writeUTF(rep);
            dataOut.flush();
            // dataOut.close();
            

            // fetching the response from Server...
            DataInputStream dataIn = new DataInputStream(s.getInputStream());
            String res = (String) dataIn.readUTF();
            System.out.println(" Server Says:          " + res);
            // dataIn.close();
            

            // ending the conversation...
            if(res.equals("bye")) {
                isLive = false;
                break;
            }
           
        }
        DataOutputStream dataOut = new DataOutputStream(s.getOutputStream());
        dataOut.writeUTF("bye");
        dataOut.flush();
        dataOut.close();
        s.close();

        System.out.println("\n Conversation has been ended...\n");
        
    }
} 