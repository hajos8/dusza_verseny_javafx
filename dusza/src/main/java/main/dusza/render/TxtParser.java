package main.dusza.render;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TxtParser {
    public static ArrayList<String> data = parseTxt("src/main/java/main/dusza/render/in.txt");

    public static ArrayList<String> parseTxt(String path){
        ArrayList<String> list = new ArrayList<>();

        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                list.add(line);
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
