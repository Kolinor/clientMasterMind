package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Fichier {
    private File fichier;
    private FileReader reader;

    public Fichier() throws FileNotFoundException {
        fichier = null;
        reader = new FileReader("C:\\Users\\Niloc\\IdeaProjects\\clientTelnet\\src\\com\\company\\text.txt");
    }

    public String lire() throws IOException {

        int i;
        String str = "";
        while((i=reader.read())!=-1) {
            str += (char)i;
        }
        return str;
    }

    public void ls() {
        String adr = "C:\\Users\\Niloc\\IdeaProjects\\clientTelnet\\src\\com\\company";
        System.out.println("Repertoire courant : " + adr);

        try (Stream<Path> walk = Files.walk(Paths.get(adr))) {
            List<String> result = walk.filter(Files::isRegularFile)
                    .map(x -> x.toString()).collect(Collectors.toList());
            result.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
