/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
/**
 *
 * @author mustafa
 */
public class Kopru extends Thread{

    private ServerSocket Serversocket;
    String Text = null;
    
    ArrayList<Clients> threads = new ArrayList<Clients>();
    
    
    public Kopru(ServerSocket serverSocket,String text) {
        this.Serversocket = serverSocket;
        this.Text = text;
    }

    @Override
    public void run() {
        while(true){
            try {
                Socket socket = new Socket();
                socket= Serversocket.accept();  
                System.out.println("Kullanıcı Kabul Edildi");
                Clients clients = new Clients(socket,Text);
                threads.add(clients);
                clients.start();

            } catch (Exception e) {
            }
        }
        
    }
    
    
    
}
