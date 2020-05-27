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
    private ArrayList<String> couleur = new ArrayList<>();


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

    public void couleurDisponible() {
        for (int i = 0; i < couleur.size(); i++) {
            System.out.println(i + " : " + couleur.get(i));
        }
    }

    public void setCouleurDisponible(String response) {
        String[] arrOfStr = response.split(" ");

        for (String s : arrOfStr)
            if (!s.equals("!couleur")) couleur.add(s);
    }

    public void run() {
        this.isRunning = true;
        Thread t = new Thread(new Runnable() {
            public void run(){
                while(isRunning) {
                    try {
                        String retourServ = lireReseau();
                        if (retourServ.length() > 8 && retourServ.substring(0, 8).equals("!couleur")) setCouleurDisponible(retourServ);
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
