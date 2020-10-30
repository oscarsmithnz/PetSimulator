/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cats;

import java.util.Random;

/**
 *
 * @author Oscar
 */
public class GingerCat extends Cat{
    public GingerCat(){
        super();
        this.breed = CatBreed.GingerCat;
        //weight 3.5-6
        super.weight = (new Random().nextFloat()*2.5f) + 3.5f;
    }
    
    public String toString(){
        String out = super.name + " is a " + super.gender + " ginger cat" + super.toString();
        return out;
    }
}
