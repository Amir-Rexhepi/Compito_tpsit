package com.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Server avviato");
        ServerSocket ss = new ServerSocket(3000);
        do{
            Biglietto b = new Biglietto();
            Socket s = ss.accept();
            System.out.println("un client si è collegato");
            MioThread t = new MioThread(s);
            t.start();
         } while (true);
    }
}