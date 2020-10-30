/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cats;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Oscar
 * 
 * manages the names of cats for cat generation, reading from two files (for the different genders) to find a random name for a generated cat.
 * this class is a singleton, with a private constructor and a getInstance method.
 */
public class CatNames {
    private static CatNames instance = null;
    private ArrayList<String> maleNames;
    private ArrayList<String> femaleNames;
    
    private CatNames(){
        maleNames = new ArrayList<>();
        femaleNames = new ArrayList<>();
        loadNames();
    }
    
    public String getMaleName(Random rand){
        int randNum = rand.nextInt(maleNames.size());
        return maleNames.get(randNum);
    }
    
    public String getFemaleName(Random rand){
        int randNum = rand.nextInt(femaleNames.size());
        return femaleNames.get(randNum);
    }
    
    /*
    loads the names from file into two arraylists for the genders
    */
    private void loadNames(){
        try{
            FileReader read = new FileReader("malepetnames.txt");
            BufferedReader inStream = new BufferedReader(read);
            
            while (inStream.ready()){
                maleNames.add(inStream.readLine());
            }
            
            read = new FileReader("femalepetnames.txt");
            inStream = new BufferedReader(read);
            
            while (inStream.ready()){
                femaleNames.add(inStream.readLine());
            }
            
        }catch (IOException e){
            System.out.println("file not found: " + e);
        }
    }
    
    public static CatNames getInstance(){
        if (instance == null) {
            instance = new CatNames();
        }
        return instance;
    }
}
