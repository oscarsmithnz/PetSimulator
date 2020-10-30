package cats;

import java.io.Serializable;
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
 * represents a cat, with variables for its stats. parent class of different breeds of cats, which are concrete classes.
 */
public abstract class Cat implements Serializable {

    private int age, happiness, hunger, sleepiness;
    protected String name, gender;
    protected float weight;
    private boolean isAlive;
    protected CatBreed breed;

    /*
    constructor sets initial values, generates a random number to decide on gender, and then chooses a random name.
    */
    protected Cat() {
        Random rand = new Random();
        isAlive = true;
        age = 1;
        happiness = 50;
        hunger = 100;
        sleepiness = 100;
        if (rand.nextInt(2) == 0) {
            gender = "Male";
            name = CatNames.getInstance().getMaleName(rand);
        } else {
            gender = "Female";
            name = CatNames.getInstance().getFemaleName(rand);
        }
    }

    /*
    returns an int array for the different values of happiness, hunger, and sleepiness
    */
    public int[] checkStatus() {
        int[] status = new int[3];
        status[0] = isHappy();
        status[1] = isHungry();
        status[2] = isTired();
        return status;
    }

    /*
    age, happiness++
    hunger, sleepiness--
    */
    public boolean play() {
        if (age >= 100 || hunger <= 0) {
            isAlive = false;
            return false;
        }
        if (happiness < 85) {
            happiness += 15;
        } else {
            happiness = 100;
        }
        minusHunger();
        minusSleepiness();
        age++;
        return true;
    }

    /*
    age, hunger++
    happiness, sleepiness--
    */
    public boolean eat() {
        if (age >= 100) {
            isAlive = false;
            return false;
        }
        if (hunger < 67) {
            hunger += 33;
        } else {
            hunger = 100;
        }
        minusHappiness();
        minusSleepiness();
        age++;
        return true;
    }

    /*
    age, sleepiness++
    hunger, happiness--
    */
    public boolean sleep() {
        if (age >= 100){
            isAlive = false;
            return false;
        }
        sleepiness = 100;
        minusHappiness();
        minusHunger();
        age++;
        return true;
    }

    public void minusHappiness() {
        if (happiness <= 5) {
            happiness = 0;
        } else {
            happiness -= 5;
        }
    }

    private void minusHunger() {
        if (hunger <= 21) {
            hunger = 1;
        } else {
            hunger -= 20;
        }
    }

    private void minusSleepiness() {
        if (sleepiness <= 10) {
            sleepiness = 0;
        } else {
            sleepiness -= 10;
        }
    }

    /*
    used by the different breed subclasses to build their toString after adding their breed.
    */
    public String toString(){
        String out = ", weighing " + String.format("%.1f", weight) + " kgs.\n"
                + name + " is " + age + " years old, and is at " + happiness + "/100 happiness, "
                + hunger + "/100 hunger, " + "and " + sleepiness + "/100 sleepiness.";
        
        return out;
    }
    
    public int isHappy() {
        return happiness;
    }

    public int isHungry() {
        return hunger;
    }

    public int isTired() {
        return sleepiness;
    }

    public boolean isAlive(){
        return isAlive;
    }
    
    public String getName() {
        return name;
    }

    public int getAge(){
        return age;
    }
    
    public void setName(String n) {
        this.name = n;
    }

    public void setGender(String g) {
        this.gender = g;
    }
}
