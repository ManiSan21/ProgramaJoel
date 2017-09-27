package src;
import java.io.*;
import java.net.*;
import java.util.*;
public class ChatServer 
{
    private static LinkedList<Socket> clientList = new LinkedList<Socket>();
    public static void main(String args[]) throws IOException
    {
        ServerSocket listener = new ServerSocket(5001);
        while(true)
        {
            Socket client = listener.accept();
            System.out.println("Hola");
            new ChatHandler(client).start();
            System.out.println("New client on client's port " + client.getPort());
            clientList.add(client);            
        }
    }
    static synchronized void broadcast(String message, String name)throws IOException 
    {
        System.out.println("Entre");
        // Sends the message to every client including the sender.
        Socket s;
        PrintWriter p;
        for (int i = 0; i < clientList.size(); i++)
        {
            s = clientList.get(i);
            p = new PrintWriter(s.getOutputStream(), true);
            p.println(name + ": " + message);
        }
    }
    static synchronized void remove(Socket s) 
    {
        clientList.remove(s);
    }
    
}

