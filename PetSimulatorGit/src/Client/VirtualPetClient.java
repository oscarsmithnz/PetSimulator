/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import cats.*;
import interactions.CatManager;
import java.util.Scanner;

/**
 *
 * @author Oscar
 * 
 * the client that is run to use the program. 
 */
public class VirtualPetClient {

    private CatManager manager;
    private Scanner scan;

    public VirtualPetClient() {
        scan = new Scanner(System.in);
    }

    public static void main(String[] args) {
        VirtualPetClient client = new VirtualPetClient();
        client.start();
    }

    /*
    called from main when the program starts, to open the main menu prompting getting a new cat, loading one from file, or exiting.
    Also called from the CatManager from the cats interaction menu when going back to the main menu is selected.
    */
    public void start() {
        manager = new CatManager(this, scan);
        printMenu();

        do {
            int answer = scan.nextInt();
            scan.nextLine();
            switch (answer) {
                case 1: //adopt
                    adoptNewCat();
                    break;
                case 2: //load cat
                    loadCat();
                    break;
                case 3: //exit
                    System.exit(0);
                default:
                    break;
            }
        }while (manager.getCat() == null);
        manager.run();
    }

    /*
    generate a new cat from the cat factory, and see if the user wants to adopt it.
    stays in a while loop generating cats until the user has one they are happy with.
    */
    private void adoptNewCat() {
        boolean happyWithCat = false;
        Cat tempCat = null;
        while (!happyWithCat) {
            tempCat = CatFactory.getCat();
            System.out.println("Cat up for adoption:\n" + tempCat.toString() + "\n\nWould you like to adopt this cat?\n1) Yes\n2) No");
            int answer = scan.nextInt();
            scan.nextLine();
            switch (answer) {
                case 1:
                    happyWithCat = true;
                    break;
                case 2:
                    System.out.println("Cat turned down, looking for new cat...");
                    break;
                default:
                    System.out.println("Invalid input. Cat no longer up for adoption...");
                    break;
            }
        }
        manager.setCat(tempCat);
    }

    /*
    when load cat from file is selected, prints out the list of cats that are saved and then puts the user in a while loop to choose what cat they want
    will go to cat adoption if no cats are saved.
    */
    public void loadCat() {
        String[] savedCats = CatManager.savedCats();
        if (savedCats.length == 0) {
            System.out.println("No cats saved locally, going to adoption center...");
            adoptNewCat();
        } else {
            for (int i = 0; i < savedCats.length; i++) {
                System.out.println((i+1) + ") " + savedCats[i]);
            }
            boolean catLoaded = false;
            while (!catLoaded) {
                System.out.println("Please enter the cat's name that you wish to load: ");
                String input = scan.nextLine().trim();
                catLoaded = manager.readCat(input);
            }
        }
    }
    
    /*
    prints the main menu
    */
    public void printMenu(){
        System.out.println(
                  "          ۸_____۸       \n"
                + "         (=ﹾ ﻨ ﹾ=)\n"
                + " __________ﮟ __͝___ﮟ_________\n"
                + "|                           |\n"
                + "| Welcome to Virtual Pet!   |\n"
                + "|                           |\n"
                + "| Please select an item     |\n"
                + "| from the menu below:      |\n"
                + "|                           |\n"
                + "| 1) Adopt from the shelter |\n"
                + "| 2) My Cat                 |\n"
                + "| 3) Exit                   |\n"
                + "|                           |\n"
                + "|___________________________|\n"); 
    }
}
