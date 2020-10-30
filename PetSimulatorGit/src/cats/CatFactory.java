package cats;


import java.util.Random;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Oscar
 * 
 * a cat factory to randomly generate a cat. one static method, so is never instantiated.
 */
public class CatFactory {
    private final static int BREED_COUNT = 5;
    
    public static Cat getCat(){
        CatBreed rand = CatBreed.values()[new Random().nextInt(BREED_COUNT-1)];
        Cat outputCat = null;
        try {
            outputCat = (Cat)Class.forName("cats." + rand.toString()).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println("error with cat factory: " + e);
        }
        
        return outputCat;
    }
}
