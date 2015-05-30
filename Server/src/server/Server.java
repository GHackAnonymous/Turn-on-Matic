/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Aulas;

public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
    ServerSocket server;
    Socket client = null;
    Log log = new Log();
    
     
    Aulas aulas = new Aulas();
    
    try{
        
           server = new ServerSocket(5500);
           
    while(true) 
    {
      Socket c = (Socket) server.accept();
      
      Hilo hilo = new Hilo(c, aulas, log);
      hilo.start();
    }
    
    } catch (IOException ex) { Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);}
    
    }
}
        