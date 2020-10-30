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
 */
public class WhiteCat extends Cat{
    public WhiteCat(){
        super();
        this.breed = CatBreed.WhiteCat;
        //weight 6-6.5
        super.weight = (new Random().nextFloat()/2) + 6;
    }
    
    public String toString(){
        String out = super.name + " is a " + super.gender + " white cat" + super.toString();
        return out;
    }
}
