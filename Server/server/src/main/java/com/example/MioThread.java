package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MioThread extends Thread{
    Socket s;
    int b;

    public MioThread(Socket s){
        this.s = s;
    }

    public MioThread(int b){
        this.b = b;
    }
    public void run(){
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            do{
                String stringaRicevuta = in.readLine();

                if(stringaRicevuta.equals("!")){
                    System.out.println("il client vuole chiudere");
                    s.close();
                    break;
                }
                System.out.println("Numero ricevuta sul thread" + Thread.currentThread().getName() + " : " + stringaRicevuta);
               
               switch (stringaRicevuta) {
                case "N":
                    String biglietto = Integer.toString(b);
                    out.writeBytes("Sono Disponibili: " + biglietto + "\n");
                    break;
                case "QUIT":
                System.out.println("il server sta terminando la connesione");
                out.writeBytes("Ok alla prossima" + "\n");
                s.close();
                break;
               }
            }while(true);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    
