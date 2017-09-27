package src;
import java.io.*; 
import java.net.*;
import java.util.Scanner;
public class ClienteHilos {
    static final String HOST = "localhost";//"localhost"; 
    static final int PUERTO=5001;
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        try{   
            
            String cad="", vocales="", consonantes="";
            Socket skCliente = new Socket( HOST , PUERTO ); 
            while(!cad.equals("bye"))
            {
              // leer.nextLine();
                System.out.println("Ingresa una palabra: ");
                cad = leer.nextLine();// leer cadena                
                                
                OutputStream salida = skCliente.getOutputStream();
                DataOutputStream flujos = new DataOutputStream( salida );
                InputStream entrada = skCliente.getInputStream();
                DataInputStream flujoe = new DataInputStream( entrada );
                flujos.writeUTF( cad );
                  
                  
                  //Aqui se hace lo que se tenga que hacer
                vocales = flujoe.readUTF();
                consonantes = flujoe.readUTF();
                System.out.println("Las vocales fueron: " + vocales);
                System.out.println("Las consonantes fueron: " + consonantes);
                if(cad.equals("bye"))
                  skCliente.close();
            } 
         } catch( Exception e ) 
	       { 
            System.out.println( e.getMessage() ); 
          } 
    }
} 