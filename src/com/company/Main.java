package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        IOCommand command = new IOCommand();
//        boolean connexion = command.connexion("192.168.1.131", 6002);
//        boolean connexion = command.connexion("192.168.1.24", 6003);
//        boolean connexion = command.connexion("192.168.1.131", 6001);
        boolean connexion = command.connexion("192.168.1.25", 6002);


//        int Min = 0;
//        int Max = 2;
//
//        System.out.println(Min + (int)(Math.random() * ((Max - Min) + 1)));

        if(!connexion) return;
        command.run();
        String str = "";
        String choix = "";
        String speak = "";
        String nom;
        String envoieA;
        boolean commandeConnue = false;
        Object test = new Object();


        command.ecrireEcran("Quel est ton login ?");
        command.ecrireReseau(command.lireEcran());
        Thread.sleep(100);

        do {
            command.ecrireEcran("Que voulez vous faire ?");
            command.ecrireEcran("1. /serveur\n2. /clientDispo\n3. /speak + login\n4. /all\n5. /quit");
            choix = command.lireEcran();
            speak = choix;

        } while(!choix.equals("/quit"));
        command.ecrireReseau("quit");
        command.deconnexion();
    }
}