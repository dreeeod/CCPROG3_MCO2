package model;

import model.exceptions.LowRankException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Marine extends Character{

    private String rank;
    private MarineCorps corps;

    /**
     * First constructor method for Marine class with input parameters
     * @param id Is the value to be assigned to characterID attribute in the super class
     * @param name Is the value to be assigned to name attribute in the super class
     * @param alias Is the value to be assigned to alias attribute in the super class
     * @param origin Is the value to be assigned to origin attribute in the super class
     * @param status Is the value to be assigned to status attribute in the super class
     * @param wallet Is the value to be assigned to wallet attribute in the super class
     * @param rank Is the value to be assigned to rank attribute
     * @param corps Is the value to be assigned to corps attribute
     */
    public Marine (int id, String name, String alias, String origin, String status, double wallet, String rank, MarineCorps corps) {
        super(id, name, alias, origin, status, wallet);
        this.rank = rank;
        this.corps = corps;
    }

    /**
     * Second constructor method for Marine class with no input parameters
     */
    public Marine () {
        super();
        this.rank = "Unknown";
        this.corps = null;
    }

    /**
     * Reassigns the rank of the Marine object that calls this method
     * @param rank Passed chosen String object for rank
     */
    public void promoteRank(String rank) throws LowRankException {
        List<String> ranks = new ArrayList<>(List.of("Fleet Admiral", "Admiral", "Vice-Admiral", "Rear Admiral", "Commodore", "Captain",
                                            "Commander", "Ensign", "Warrant Officer", "Petty Officer", "Seaman", "Chore Boy"));

        int origRank = ranks.indexOf(this.rank);
        int newRank = ranks.indexOf(rank);

        if (newRank >= origRank) {
            throw new LowRankException("");
        }

        this.rank = rank;

    }

    /**
     * Assigns the Marine object that calls this method to an EXISTING MarineCorps object
     * A MarineCorps object exists if it is present in the past List of MarineCorps
     * @param corps Is the MarineCorps object to be assigned as this Marine object's MarineCorps
     */
    public void assignMarineCorps(MarineCorps corps) {
        if (this.corps == null) {
            corps.recruitMarine(this);
            return;
        }
        else if (this.corps == corps) {
            System.out.println("Marine " + this.getName() + " is already part of " + this.corps.getBaseLoc() + "!\n");
            return;
        }

        //re-assignment of Marine to corps from old assigned MarineCorps
        this.corps.dischargeMarine(this); // discharges Marine from their current MarineCorps
        corps.recruitMarine(this); // recruits this Marine to the passed MarineCorps

    }

    // GETTERS
    /**
     * Getter method for current rank of Marine object
     * @return String
     */
    public String getRank() {
        return this.rank;
    }

    /**
     * Getter method for current assigned MarineCorps object of Marine object
     * @return MarineCorps
     */
    public MarineCorps getCorps() {
        return this.corps;
    }

    //SETTERS
    /**
     * Setter method for rank attribute of Marine
     * @param rank Is the new String value to be assigned to the rank attribute
     */
    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
     * Setter method for corps attribute of Marine
     * @param corps Is the new MarineCorps reference to be assigned to the corps attribute
     */
    public void setCorps(MarineCorps corps) {
        this.corps = corps;
    }

    @Override
    public String fileString() {
        return "Hello";
    }

}
