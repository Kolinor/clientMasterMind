package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        IOCommand command = new IOCommand();
        boolean connexion = command.connexion("192.168.1.25", 6002);

        if(!connexion) return;
        command.run();
        String str = "";
        String choix = "";
        String speak = "";
        String nom;
        String envoieA;
        boolean commandeConnue = false;
        Object test = new Object();
        int difficulty;

        command.ecrireEcran("Quel est ton login ?");
        command.ecrireReseau(command.lireEcran(""));
        Thread.sleep(100);

        do {
            command.ecrireEcran("Que voulez vous faire ?");
            command.ecrireEcran("1. Mode classique\n2. Mode compétition\n3. Mode aventure\n5. quit");
            choix = command.lireEcran("");
            speak = choix;

            if (choix.equals("1")) {
                str = command.lireEcran("Quel niveaux de difficultés voulez-vous ?");
                difficulty = Integer.parseInt(str);
                System.out.println(str);
                command.ecrireReseau("!1 " + str);

                while (true) {
                    command.ecrireEcran("Choisir une combinaison");
                    command.couleurDisponible();
                    if (command.getWin()) break;
                    if (command.getLoose()) break;
                    str = command.lireEcran("Rentrer une combinaison de " + difficulty + " couleurs ou 'quit'");
                    if (str.equals("quit")) {
                        command.ecrireReseau("stop");
                        break;
                    }
                    command.ecrireReseau("!1 " + str);
                }
            } else if (choix.equals("2")) {

            } else if (choix.equals("3")) {
                command.ecrireEcran("Bienvenue dans le mode aventure !");
                command.ecrireReseau("!3");
            }

        } while(!choix.equals("5"));
        command.ecrireReseau("quit");
        command.deconnexion();
    }
}