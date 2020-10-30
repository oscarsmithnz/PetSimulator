/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interactions;

import Client.VirtualPetClient;
import cats.Cat;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

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
    private VirtualPetClient client;
    private boolean end;

    public CatManager(VirtualPetClient c) {
        this.client = c;
    }

    //TODO: make gui version
//    private void printMenu() {
//        int[] stats = cat.checkStatus();
//        System.out.println("          ۸_____۸       \n"
//                + "         (=ﹾ ﻨ ﹾ=)\n"
//                + " __________ﮟ __͝___ﮟ_________\n"
//                + "|                           |\n"
//                + "| Hi, I'm " + cat.getName() + " !        |\n"
//                + "|                           |\n"
//                + "| Please select an item     |\n"
//                + "| from the menu below:      |\n"
//                + "|                           |\n"
//                + "|        1) Play    " + stats[0] + "/100  |\n"
//                + "|        2) Eat     " + stats[1] + "/100  |\n"
//                + "|        3) Sleep   " + stats[2] + "/100  |\n"
//                + "|        4) Save            |\n"
//                + "|        5) Main menu       |\n"
//                + "|___________________________|\n");
//        int answer = scan.nextInt();
//        scan.nextLine();
//        switch (answer) {
//            case 1: //play
//                this.play();
//                break;
//            case 2: //feed
//                this.eat();
//                break;
//            case 3: //sleep
//                this.sleep();
//                break;
//            case 4: //save
//                this.saveCat();
//                break;
//            case 5: //main menu
//                end = true;
//                client.start();
//                break;
//            default:
//                break;
//        }
//    }
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
    public void eat() {
        System.out.println("   ۸     ۸         \n"
                + "ⴈ (=ﹾ ﻨﹾ =)    (  (  (\n"
                + "| |      |     )  )  )    \n"
                + "| ( ﮟ   ﮟ )    >+++°>\n"
                + "    ͝͝     ͝  "
                + "You feed " + cat.getName() + ".");
        if (!cat.eat()) {
            System.out.println("Your cat has died. Rest in peace " + cat.getName()
                    + "\nCat summary: \n" + cat.toString());
        } else {
            System.out.println("Cat summary: \n" + cat.toString());
        }
    }

    //TODO: make gui version
    public void sleep() {
        System.out.println("Zzzzz      |\\      _,,,--,,_\n"
                + "           /,`.-'`'   ._  \\-;;,_\n"
                + "           |,4-  ) )_   .;.(  `'-'\n"
                + "           '---''(_/._)-'(_\\_)"
                + "You give " + cat.getName() + " a rest.");
        if (!cat.sleep()) {
            System.out.println("Your cat has died. Rest in peace " + cat.getName()
                    + "\nCat summary: \n" + cat.toString());
        } else {
            System.out.println("Cat summary: \n" + cat.toString());
        }
    }

    /*
    save the cat object to file, using an ObjectOutputStream
     */
    //TODO: make DB version
    public boolean saveCat() {
        try {
            File file = new File(filePath + "\\" + cat.getName() + ".cat");
            if (!file.createNewFile()) {
                System.out.println("Writing over previous file...");
            }
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(cat);
            objOut.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error with saveCat: " + e);
            return false;
        }
    }

    /*
    return the list of saved cats, i.e. the names of all files in the directory cats are saved in.
     */
    //TODO: make DB version
    public static String[] savedCats() {
        File directory = new File(filePath);
        return directory.list();
    }

    /*
    attempt to read a cat file with the input strings value. 
    returns false if the cat was not found, i.e. the file doesn't exist, or true if it was found & sets the cat being managed to that cat.
     */
    //TODO: make DB version
    public boolean readCat(String name) {
        try {
            FileInputStream fileIn = new FileInputStream(filePath + "\\" + name + ".cat");
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            this.setCat((Cat) objIn.readObject());
            objIn.close();
            if (!cat.isAlive()) {
                System.out.println("That cat is dead.");
                return false;
            }
            return true;
        } catch (IOException | ClassNotFoundException e) {
            if (e instanceof FileNotFoundException) {
                System.out.println("A cat with that name does not exist.");
                return false;
            } else {
                System.out.println("Error with saveCat: " + e);
                return false;
            }
        }
    }

    public void setCat(Cat c) {
        this.cat = c;
    }

    public Cat getCat() {
        return cat;
    }
}
