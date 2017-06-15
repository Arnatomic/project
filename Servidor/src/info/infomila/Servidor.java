/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila;

import info.infomila.gestio.UserOnline;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mr. Robot
 */
public class Servidor implements Runnable{
    
    private int port = 1234;
    private ServerSocket socket;
    private UserOnline user;
    private IComponentSGBD dbConnection;
    private Thread currentThread;
    
    public Servidor(int port, IComponentSGBD dbConnection){
        this.port = port;
        this.dbConnection = dbConnection;
    }

    @Override
    public void run() {
        
        synchronized(this){
            currentThread = Thread.currentThread();
        }
        
        try {
            socket = new ServerSocket(port);
            System.out.println("SERVIDOR EN MARXA");
            System.out.println("Envia 'Exit:' per aturar el servidor");
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        
        while(true){        
            Socket socketClient = null;            
            try {
                socketClient = socket.accept();
                System.out.println("Client connectat");
            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
                break;
            }
            new Thread(new FilGestorClients(socketClient,dbConnection)).start();        
            
        
        }
        
    }
    
    
    
    
    
}
