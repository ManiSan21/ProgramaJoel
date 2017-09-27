package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatHandler extends Thread
{
    private BufferedReader in;
    private PrintWriter out;
    private Socket toClient;
    private String name;
    ChatHandler(Socket s) 
    {
        toClient = s;
    } 
    public void run() 
    {
        try 
        {
            // Welcome new client to the Chat Room.
            in = new BufferedReader(new InputStreamReader(toClient.getInputStream()));
            out = new PrintWriter(toClient.getOutputStream(), true);
            out.println("*** Welcome to the Chatter ***");
            out.println("Type BYE to end");
            out.print("What is your name? ");
            out.flush();
            String name = in.readLine();
            ChatServer.broadcast(name + " has joined the discussion.","Chatter");
            
            while(true) 
            {
                String s = in.readLine();
                if (s.startsWith("BYE")) 
                {
                    ChatServer.broadcast(name +" has left the discussion.","Chatter");
                    break;
                }
                ChatServer.broadcast(s, name);
            }
            ChatServer.remove(toClient);
            toClient.close();
        }
       
        
        catch (Exception e)
        {
            System.out.println("Chatter error: "+e);
        }
    }
}

