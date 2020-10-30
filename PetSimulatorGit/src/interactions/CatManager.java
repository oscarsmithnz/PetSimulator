/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interactions;

import Client.VirtualPetDatabase;
import cats.Cat;

/**
 *
 * @author Oscar
 *
 * this class is used to manage a cat. has methods for interacting with the cat,
 * saving & loading the cat to/from file, & changing which cat is being managed.
 */
public class CatManager {

    private Cat cat;
    private static final String filePath = "MyCats";

    public CatManager() {
    }

    
    //TODO: make gui version
    public String play() {
        String out = "You play with " + cat.getName() + ".";
        if (!cat.play()) {
            out += "Your cat has died. Rest in peace " + cat.getName() + "\nCat summary: \n" + cat.toString();
        } else {
            out += "Cat summary: \n" + cat.toString();
        }
        return out;
    }

    //TODO: make gui version
    public String eat() {
        String out = "   ۸     ۸         \n"
                + "ⴈ (=ﹾ ﻨﹾ =)    (  (  (\n"
                + "| |      |     )  )  )    \n"
                + "| ( ﮟ   ﮟ )    >+++°>\n"
                + "    ͝͝     ͝  "
                + "You feed " + cat.getName() + ".";
        if (!cat.eat()) {
            out += "Your cat has died. Rest in peace " + cat.getName()
                    + "\nCat summary: \n" + cat.toString();
        } else {
            out += "Cat summary: \n" + cat.toString();
        }
        return out;
    }

    //TODO: make gui version
    public String sleep() {
        String out = "Zzzzz      |\\      _,,,--,,_\n"
                + "           /,`.-'`'   ._  \\-;;,_\n"
                + "           |,4-  ) )_   .;.(  `'-'\n"
                + "           '---''(_/._)-'(_\\_)"
                + "You give " + cat.getName() + " a rest.";
        if (!cat.sleep()) {
            out += "Your cat has died. Rest in peace " + cat.getName()
                    + "\nCat summary: \n" + cat.toString();
        } else {
            out += "Cat summary: \n" + cat.toString();
        }
        return out;
    }

    /*
    inserts cat into database
     */
    public boolean saveCat() {
        return VirtualPetDatabase.getInstance().insertPet(cat);
    }

    public void setCat(Cat c) {
        this.cat = c;
    }

    public Cat getCat() {
        return cat;
    }
}
