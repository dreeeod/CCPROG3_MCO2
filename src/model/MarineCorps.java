package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MarineCorps {

    private int corpsID;
    String baseLoc;
    Marine commander;
    List<Marine> members;
    private double funds;

    /**
     * Constructor method for MarineCorps class with input parameters
     * @param id Is the value to be assigned to corpsID attribute
     * @param baseLoc Is the value to be assigned to baseLoc attribute
     * @param commander Is the value to be assigned to commander attribute
     * @param funds Is the value to be assigned to funds attribute
     */
    public MarineCorps(int id, String baseLoc, Marine commander, double funds) {
        corpsID = id;
        this.baseLoc = baseLoc;
        this.commander = commander;
        this.funds = funds;
        members = new ArrayList<>();
    }

    // GETTERS
    /**
     * Getter method for ID value of the MarineCorps object
     * @return int
     */
    public int getCorpsID(){return this.corpsID;}

    /**
     * Getter method for String base location value of the MarineCorps object
     * @return String
     */
    public String getBaseLoc() {
        return this.baseLoc;
    }

    /**
     * Getter method for assigned Marine object as the MarineCorps object's commander
     * @return Marine
     */
    public Marine getCommander() {
        return this.commander;
    }

    /**
     * Getter method for list of Marine object members of MarineCorps object
     * @return List<Marine>
     */
    public List<Marine> getMembers () {
        return this.members;
    }

    /**
     * Getter method for funds value of the MarineCorps object
     * @return double
     */
    public double getFunds() {
        return this.funds;
    }

    //SETTERS
    /**
     * Setter method for baseLoc value of MarineCorps object
     * @param baseLoc String object to be assgined to baseLoc
     */
    public void setBaseLoc(String baseLoc){
        this.baseLoc = baseLoc;
    }

    /**
     * Setter method for commander value of MarineCorps object
     * @param commander Marine object to be assigned to commander
     */
    public void setCommander(Marine commander){
        this.commander = commander;
    }

    /**
     * Setter method for funds value of MarineCorps object
     * @param funds double value to be assigned to funds
     */
    public void setFunds(double funds){
        this.funds = funds;
    }

    /**
     * Adds a passed Marine object to members if it is not part of the List yet
     * @param member Marine object to be added to List<Marine> members
     */
    public void recruitMarine(Marine member) {
        String[] commanderRanks = {"Fleet Admiral", "Admiral", "Vice-Admiral", "Rear Admiral", "Commodore", "Captain",
                "Commander"}; // index 0 means highest rank, higher index means lower rank

        // checks if passed Marine object has a rank that is considered as commander level
        if (Arrays.asList(commanderRanks).contains(member.getRank())) {
            if (this.commander == null) { // automatically assigns that member as a commander if the spot is vacant (null)
                this.setCommander(member);
                members.add(member);
                System.out.println("\nMarine " + member.getName() + " has been assigned as the top commander of this corps!\n");
                member.setCorps(this);
                return;
            }
            // if there is a commander, checks if that current commander's rank is higher than that of the passed Marine object
            int memInd = Arrays.asList(commanderRanks).indexOf(member.getRank());
            int commInd = Arrays.asList(commanderRanks).indexOf(getCommander().getRank());
            if (memInd < commInd) {
                System.out.println("\nMarine " + commander.getName() + " stepped down from being the top commander!\n");
                this.setCommander(member);
                members.add(member);
                System.out.println("\nMarine " + member.getName() + " has been assigned as the new top commander of this corps!\n");
                member.setCorps(this);
                return;
            }
        }

        // checks if member is already part of the member list (will still execute if member's commander rank is lower)
        if (this.members.contains(member)) {
            System.out.println("Marine " + member.getName() + " is already part of " + this.baseLoc);
            return;
        }
        else {
            members.add(member);
            member.setCorps(this);// double check this
            System.out.println("Marine " + member.getName() + " is now assigned to " + this.getBaseLoc() + "!\n");
        }

    }

    /**
     * Removes a passed Marine to members if it is a part of the List
     * @param member Marine object to be removed from List<Marine> members
     */
    public void dischargeMarine(Marine member) {
        String[] commanderRanks = {"Fleet Admiral", "Admiral", "Vice-Admiral", "Rear Admiral", "Commodore", "Captain",
                "Commander"};
        if (member == this.getCommander()) {
            this.setCommander(null);
            System.out.println("Marine " + member.getName() + " has stepped down from being the commander! The position is now open!\n");
        }

        if (!this.members.contains(member)) {
            System.out.println("Marine " + member.getName() + " is currently not part part of " + this.baseLoc + "\n");
        }
        else {
            this.members.remove(member);
            member.setCorps(null); // removes corps assignment in member
            System.out.println("Marine " + member.getName() + " has been discharged from " + this.baseLoc + "\n");
        }
    }

    /**
     * Searches for the highest ranking Marine in members list and returns the index based on the String Array
     * @return int
     */
    public int getHighestRank() {
        String[] commanderRanks = {"Fleet Admiral", "Admiral", "Vice-Admiral", "Rear Admiral", "Commodore", "Captain",
                "Commander"};
        int high = -1, ind;
        for (Marine m : members) {
            if (Arrays.asList(commanderRanks).contains(m.getRank())) {
                ind = Arrays.asList(commanderRanks).indexOf(m.getRank()); // determines ind value in array above of member's rank
                if (high == -1) { // means that high has not been previously assigned an assumed highest commander level
                    high = ind; // is the baseline/first occurrence of a commander level
                }
                else if (ind < high) {
                    high = ind;
                }
            }
        }

        return high;
    }

}
