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
public class BlackCat extends Cat{
    public BlackCat(){
        super();
        this.breed = CatBreed.BlackCat;
        //weight 5-7
        super.weight = (new Random().nextFloat()*2) + 5;
    }
    
    public String toString(){
        String out = super.name + " is a " + super.gender + " black cat" + super.toString();
        return out;
    }
}
