package model;

import java.util.ArrayList;

public class PirateCrew {

    private int crewID;
    private String crewName, shipName;
    private Pirate captain;
    private ArrayList<Pirate> crewMembers;
    long totalBounty = 0; // default value

    /**
     * Constructor method for PirateCrew class with input parameters
     * @param crewID Is the value to be assigned to crewID attribute
     * @param crewName Is the value to be assigned to crewName attribute
     * @param shipName Is the value to be assigned to shipName attribute
     * @param captain Is the value to be assigned to captain attribute
     */
    public PirateCrew(int crewID, String crewName, String shipName, Pirate captain){

        this.crewID = crewID;
        this.crewName = crewName;
        this.shipName = shipName;
        this.captain = captain;
        this.crewMembers = new ArrayList<>();

    }

    //Getters
    /**
     * Getter method for crewID of PirateCrew object
     * @return int
     */
    public int getCrewID(){

        return crewID;

    }

    /**
     * Getter method for crewName of PirateCrew object
     * @return String
     */
    public String getCrewName(){

        return crewName;
    }

    /**
     * Getter method for shipName of PirateCrew object
     * @return String
     */
    public String getShipName(){

        return shipName;
    }

    /**
     * Getter method for captain of PirateCrew object
     * @return
     */
    public Pirate getCaptain(){

        return captain;
    }

    /**
     * Getter method for list of Pirate object members of PirateCrew object
     * @return ArrayList<Pirate>
     */
    public ArrayList<Pirate> getCrewMembers(){

        return crewMembers;
    }

    //Setters

    /**
     * Setter method for crewName attribute of PirateCrew
     * @param crewName Is the new String value to be assigned to the crewName attribute
     */
    public void setCrewName(String crewName){

        this.crewName = crewName;
    }

    /**
     * Setter method for shipName attribute of PirateCrew
     * @param shipName Is the new String value to be assigned to the shipName attribute
     */
    public void setShipName(String shipName){

        this.shipName = shipName;
    }

    /**
     * Setter method for captain attribute of PirateCrew
     * @param captain Is the new Pirate reference to be assigned to the captain attribute
     */
    public void setCaptain(Pirate captain){

        this.captain = captain;
    }

    //Methods
    /**
     * Adds a Pirate object to the Pirate Crew's list of members
     * @param pirate the pirate object to be added to the crew
     */
    public void addCrewMember(Pirate pirate){

        if(pirate.getPirateCrew() != null){

            System.out.println(pirate.getName() + " is already part of a pirate crew!\n");
            return;
        }
        else{

            crewMembers.add(pirate);
            totalBounty += pirate.getBounty(); // adds Pirate's bounty to Pirate Crew's total bounty
            pirate.setPirateCrew(this);
            System.out.println(pirate.getName() + " has joined the " + crewName + "!\n");
        }
    }

    /**
     *  Removes the Pirate object from the Pirate Crew's list of members
     * @param pirate The Pirate object to be removed from the crew
     */
    public void removeCrewMember(Pirate pirate){

        if(!crewMembers.contains(pirate)){

            System.out.println(pirate.getName() + " is not part of this crew!\n");
            return;
        }
        else{

            totalBounty -= pirate.getBounty(); // removes Pirate's bounty to Pirate Crew's total bounty
            crewMembers.remove(pirate);
            pirate.setPirateCrew(null);
            System.out.println(pirate.getName() + " has been removed from the " + crewName + "!\n");
        }
    }

    /**
     * Assigns a Pirate object to be the captain of the Pirate Crew
     * @param newCaptain The Pirate object to be assigned as the new captain
     */
    public void assignCaptain(Pirate newCaptain){

        if(!crewMembers.contains(newCaptain)) {
            System.out.println(newCaptain.getName() + " is not part of this crew!\n");
            return;
        }
        else{

            //Demote current captain if he exists
            if(this.captain != null){
                this.captain.setPirateRole("Crew Member"); // default role
                this.captain.setIsCaptain(false);
            }

            //Promote new Captain
            newCaptain.setIsCaptain(true);
            newCaptain.setPirateRole("Captain");
            //store as Pirate object
            this.captain = newCaptain;
            System.out.println(newCaptain.getName() + " is now the new captain of " + crewName + "!\n");
        }
    }

    /**
     * Calculates and returns the total bounty of all active crew members in the Pirate Crew
     * @return long
     */
    public long getTotalCrewBounty(){
        long currentTotal = 0;
        for (Pirate p : crewMembers) {
            currentTotal += p.getBounty();
        }
        this.totalBounty = currentTotal;
        return totalBounty;
    }

}
