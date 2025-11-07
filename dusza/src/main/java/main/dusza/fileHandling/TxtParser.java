package main.dusza.fileHandling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TxtParser {

    public static boolean isRunningTest = false;

    public static ArrayList<String> parseTxt(String path){
        ArrayList<String> list = new ArrayList<>();

        try{
            if(!isRunningTest){
                BufferedReader reader = new BufferedReader(new FileReader(path));

                String line;

                while ((line = reader.readLine()) != null) {
                    list.add(line);
                }
                reader.close();
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
