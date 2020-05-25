package com.company;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class IOCommand {
    private BufferedReader lectureEcran;
    private PrintStream ecritureEcran;
    private Socket socket;
    private boolean isRunning;



    public IOCommand() throws IOException {
        this(null);
    }

    public IOCommand(Socket socket) throws IOException {
        lectureEcran = null;
        ecritureEcran = null;
        this.socket = socket;
    }

    public void ecrireEcran(String texte) {
        System.out.println(texte);
    }

    public String lireEcran() {
        Scanner sc = new Scanner(System.in);
        String str;
        System.out.println("Rentrer un texte :");

        String chaine = sc.nextLine();
        System.out.println(chaine);
        return chaine;
    }

    public void ecrireReseau(String texte) throws IOException {
        ecritureEcran = new PrintStream(socket.getOutputStream(), false);
        ecritureEcran.print(texte);
        ecritureEcran.flush();
    }

    public String lireReseau() throws IOException {
        lectureEcran = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        return lectureEcran.readLine();
    }

    public boolean connexion(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void deconnexion() throws IOException, InterruptedException {
        this.isRunning = false;
        Thread.sleep(100);
        socket.close();
    }

    public void run() {
        this.isRunning = true;
        Thread t = new Thread(new Runnable() {
            public void run(){
                while(isRunning) {
                    try {
                        String retourServ = lireReseau();
                        ecrireEcran("serv> " + retourServ);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        t.start();
        if(!isRunning)
            t.stop();
    }
}
