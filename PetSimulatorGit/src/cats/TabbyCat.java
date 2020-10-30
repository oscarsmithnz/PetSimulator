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
public class TabbyCat extends Cat{
    public TabbyCat(){
        super();
        this.breed = CatBreed.TabbyCat;
        //weight 5.5-7
        super.weight = (new Random().nextFloat()*1.5f) + 5;
    }
    
    public String toString(){
        String out = super.name + " is a " + super.gender + " tabby cat" + super.toString();
        return out;
    }
}
