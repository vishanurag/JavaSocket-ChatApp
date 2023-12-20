import java.util.*;
import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(" Server Started...\n");
        boolean isLive = true;
        Scanner in = new Scanner(System.in);
        ServerSocket ss = new ServerSocket(6932);
        Socket s = ss.accept();

        System.out.println(" Client Connected...\n");
        while(isLive) {

            // fetching data from Client...
            DataInputStream dataIn = new DataInputStream(s.getInputStream());
            String res = (String) dataIn.readUTF();
            System.out.println(" Client Says:       " + res);


            // ending the conversation...
            if(res.equals("bye")) {
                isLive = false;
                break;
            }

            // replying to the Client...
            System.out.print(" Reply to Client->  ");
            String rep = in.nextLine();

            // sending reply to the Client...
            DataOutputStream dataOut = new DataOutputStream(s.getOutputStream());
            dataOut.writeUTF(rep);
            dataOut.flush();
            // dataOut.close();
            // dataIn.close();

        }
        DataOutputStream dataOut = new DataOutputStream(s.getOutputStream());
        dataOut.writeUTF("bye");
        dataOut.flush();
        dataOut.close();
        s.close();

        System.out.println("\n Conversation has been ended...\n");
        
    }
}