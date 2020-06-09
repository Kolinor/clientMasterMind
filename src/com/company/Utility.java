package com.company;

import java.util.ArrayList;

public class Utility {
    private ArrayList<String> couleur = new ArrayList<>();
    private boolean isWin = false;
    private boolean isLoose = false;
    private int difficulty;

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

    public void getIndiceCouleur(String response) {
        String[] arrOfStr = response.split(" ");
        String str = "Indice : ";

        for (String s : arrOfStr) {
            if (!s.equals("!indice")) str += s + " ";
        }
        System.out.println(str);
    }

    public void win(String response) {
        String[] arrOfStr = response.split(" ");
        isWin = true;
        System.out.println("Tu as gagné en " + arrOfStr[1] + " essai(s) !");
    }

    public boolean getWin() throws InterruptedException {
        Thread.sleep(100);
        if (isWin) {
            isWin = false;
            return true;
        }
        return isWin;
    }

    public void loose(String response) {
        String[] arrOfStr = response.split(" ");
        isLoose = true;
        System.out.println("Tu as perdu en " + arrOfStr[1] + " essai(s) !");
    }

    public boolean getLoose() throws InterruptedException {
        Thread.sleep(100);
        if (isLoose) {
            isLoose = false;
            return true;
        }
        return isLoose;
    }

    public void level(String response) {
        String[] arrOfStr = response.split(" ");
        difficulty = Integer.parseInt(arrOfStr[1]);

        System.out.println("Vous allez débuter le niveau : " + arrOfStr[1]);
    }

    public int getDifficulty() {
        return difficulty;
    }
}
