package com.company;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class IOCommand {
    private BufferedReader lectureEcran;
    private PrintStream ecritureEcran;
    private Socket socket;
    private boolean isRunning;
    private Utility u;


    public IOCommand(Utility u) throws IOException {
       this.u = u;
    }

    public void ecrireEcran(String texte) {
        System.out.println(texte);
    }

    public void ecrireEcran(ArrayList<String> texte) {
        System.out.println(texte);
    }

    public String lireEcran(String text) {
        Scanner sc = new Scanner(System.in);
        String str;
        if (!text.equals("")) System.out.println(text);

        String chaine = sc.nextLine();
        System.out.println(chaine);
        return chaine;
    }

    public String lireEcran() {
        return lireEcran("Rentrer un texte");
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
                        if (retourServ.length() > 8 && retourServ.substring(0, 8).equals("!couleur")) u.setCouleurDisponible(retourServ);
                        if (retourServ.length() > 7 && retourServ.substring(0, 7).equals("!indice")) u.getIndiceCouleur(retourServ);
                        if (retourServ.length() > 4 && retourServ.substring(0, 4).equals("!win")) u.win(retourServ);
                        if (retourServ.length() > 6 && retourServ.substring(0, 6).equals("!loose")) u.loose(retourServ);
                        if (retourServ.length() > 6 && retourServ.substring(0, 6).equals("!start")) u.level(retourServ);
//                        ecrireEcran("serv> " + retourServ);
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
