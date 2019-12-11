/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server;

import java.net.*;
import java.io.*;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Timer;

/**
 *
 * @author mustafa
 */
public class Clients extends Thread{

    DataInputStream dis;
    DataOutputStream dos;
    Socket socket = new Socket();
    String text = null,eski = null,gelen = null;
    Timer timer;
    TimerTask task;
    
    int sayac = 0;
    File file;
    FileWriter fileWriter;
    BufferedWriter bufferedWriter;
    
    public Clients(Socket socket,String text) {
        this.socket = socket;
        this.text = text;
    }

    @Override
    public void run() {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                System.out.println(sayac);
                sayac++;
                if(sayac == 500){
                    gonder("Bağlantı Koparıldı...");
                    try {
                        file = new File(text);
                        fileWriter = new FileWriter(file,true);
                        bufferedWriter = new BufferedWriter(fileWriter);
                        bufferedWriter.write("Bağlantı Koparıldı");
                        bufferedWriter.close();
                        fileWriter.close();
                        socket.close();

                    } catch (Exception e) {
                    }
                    timer.cancel();
                    timer.purge();
                }
            }
        };
        timer.schedule(task,0 ,1000);
        try {
            String temp;
            dis= new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            
            while(true){
                temp=dis.readUTF();
                sayac = 0;
                file = new File(text);
                fileWriter = new FileWriter(file,true);
                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(temp+"\n");
                bufferedWriter.close();
                fileWriter.close();
            }
            
            
        } catch (IOException ex) {
        }
        
    }
    
    public void gonder(String text){
        try {
            dos.writeUTF(text);
        } catch (Exception e) {
        }
    }
    
    
    
}
