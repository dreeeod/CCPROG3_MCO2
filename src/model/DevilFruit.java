package model;

import java.util.ArrayList;
import java.util.List;

public class DevilFruit {

    private int fruitID;
    private String name;
    private String category;
    private String abilityDescription;
    private Character owner;
    private List<Character> history = new ArrayList<>(); // used for when DevilFruit object is assigned to a character

    /**
     * First constructor method for the Devil Fruit class with input parameter
     * @param id Is the value to be assigned to fruitID attribute
     * @param name Is the value to be assigned to name attribute
     * @param category Is the value to be assigned to category attribute
     * @param ability Is the value to be assigned to abilityDescription attribute
     * @param owner Is the value to be assigned to owner attribute
     */
    public DevilFruit(int id, String name, String category, String ability, Character owner) {
        fruitID = id;
        this.name = name;
        this.category = category;
        abilityDescription = ability;
        this.owner = owner;
    }

    /**
     * Second constructor method for the Devil Fruit class without owner parameter
     * @param id Is the value to be assigned to fruitID attribute
     * @param name Is the value to be assigned to name attribute
     * @param category Is the value to be assigned to category attribute
     * @param ability Is the value to be assigned to abilityDescription attribute
     */
    public DevilFruit(int id, String name, String category, String ability) { // for the case the user does not want to assign a character directly
        fruitID = id;
        this.name = name;
        this.category = category;
        abilityDescription = ability;
        this.owner = null;
    }

    // GETTERS

    /**
     * Getter method for the current fruit ID of DevilFruit object
     * @return int
     */
    public int getFruitID() {
        return this.fruitID;
    }

    /**
     * Getter method for current name of DevilFruit object
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter method for current category of DevilFruit object
     * @return String
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Getter method for the current ability Description of DevilFruit object
     * @return String
     */
    public String getAbilityDescription() {
        return this.abilityDescription;
    }

    /**
     * Getter method for current assigned owner of DevilFruit object
     * @return Character
     */
    public Character getOwner() {
        return this.owner;
    }

    /**
     * Getter method for the history log list of DevilFruit object
     * @return List<Character>
     */
    public List<Character> getHistory(){

        return history;
    }

    //SETTERS

    /**
     * Setter method for owner attribute of DevilFruit
     * @param owner Is the new Character reference to be assigned to the owner attribute
     */
    public void setOwner(Character owner) {
        this.owner = owner;
    }

    //Methods

    /**
     * Assigns the Devil Fruit to a new owner Character object
     * @param newOwner The Character object to be assigned as the new owner of the Devil Fruit
     */
    public void assignNewUser(Character newOwner){

        if(this.owner != null){

            System.out.println(this.name  + " is already owned by: " + this.owner.getName() + "!");
            System.out.println("The Devil Fruit must be released first before it can be assigned.");
            return;
        }

        if(newOwner.getStatus().equals("Dead")){
            System.out.println(newOwner.getName() + " is already dead and cannot wield a Devil Fruit power!");
            return;
        }

        if(newOwner.getDevilFruitPower() != null){

            System.out.println(newOwner.getName() + " already has a Devil Fruit power!");
            return;
        }

        this.owner = newOwner;
        newOwner.setDevilFruitPower(this);
        System.out.println(this.name + " is now being wielded by " + newOwner.getName() + "!");
    }

    /**
     * Releases the Devil Fruit upon the owner's death,
     * adds the deceased owner to the Devil Fruit's historical owner's list,
     * and marks the fruit as available for redistribution to a new owner
     */
    public void triggerReincarnation(){

        if(this.owner == null){
            System.out.println(this.name + " currently has no owner — there is nothing to reincarnate.");
            return;
        }

        if(!this.owner.getStatus().equals("Dead")){

            System.out.println(this.owner.getName() + " is not dead — reincarnation cannot be triggered.");
            return;
        }

        //Add current owner to historical owner list
        history.add(this.owner);
        System.out.println(this.owner.getName() + " has been added to " + this.name + "'s historical owners.");

        //Release the Fruit
        this.owner.setDevilFruitPower(null);
        this.owner = null;
        System.out.println(this.name + " has been released and is now available for ownership!");

    }

}
