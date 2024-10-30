package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("Cliente avviato!");
        Socket s = new Socket("localhost", 3000);
        System.out.println("Client connesso!");
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        do{
            Scanner scan = new Scanner(System.in);
 System.out.println("Inserisci il nome di una agenzia ");
            String agenzia= scan.nextLine();
            System.out.println("Digita N: se vuoi info, BUY se vuoi comprare e QUIT per uscire ");
            String stringaDigitata = scan.nextLine();
            
            do{
            try{ String testo = stringaDigitata;
                switch (testo) {
                    case "N":
                        out.writeBytes(testo + "\n");
                        break;
                
                    case "BUY":
                    System.out.println("Inserisci il numero di biglietti che vuoi compare");
                       String compra = scan.nextLine();
                       int comp =  Integer.parseInt(compra);
                       out.writeByte(comp);
                     break;

                     case  "QUIT":
                     out.writeBytes(testo + "\n");
                     break;
                }
               
                break;
        }catch(Exception e){
            System.out.println("non hai inserito una stringa");}
            }while (true);
            
           String stringaRicevuta = in.readLine(); 

           System.out.println(stringaRicevuta);
           s.close();

       }while(true);
    }
}