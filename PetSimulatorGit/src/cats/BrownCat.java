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
public class BrownCat extends Cat{
    public BrownCat(){
        super();
        this.breed = CatBreed.BrownCat;
        //weight 4-6.5
        super.weight = (new Random().nextFloat()*2.5f) + 4f;
    }
    
    public String toString(){
        String out = super.name + " is a " + super.gender + " brown cat" + super.toString();
        return out;
    }
}
