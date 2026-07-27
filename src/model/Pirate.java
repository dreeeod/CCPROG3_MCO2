package model;

public class Pirate extends Character{
    private long bounty;
    private String pirateRole;
    private PirateCrew crew = null; // default value
    private boolean isCaptain = false; // default value

    /**
     * First constructor method for Pirate class with input parameters
     * @param characterID Is the value to be assigned to characterID attribute in the super class
     * @param name Is the value to be assigned to name attribute in the super class
     * @param alias Is the value to be assigned to alias attribute in the super class
     * @param origin Is the value to be assigned to origin attribute in the super class
     * @param status Is the value to be assigned to status attribute in the super class
     * @param wallet Is the value to be assigned to wallet attribute in the super class
     * @param bounty Is the value to be assigned to bounty attribute
     * @param pirateRole Is the value to be assigned to pirateRole attribute
     */
    public Pirate(int characterID, String name, String alias, String origin, String status, double wallet, long bounty, String pirateRole){

        super(characterID, name, alias, origin, status, wallet);

        this.bounty = bounty;
        this.pirateRole = pirateRole;
    }

    /**
     * Second constructor method for Pirate class with no input parameters
     */
    public Pirate() {
        super();
        this.bounty = 0;
        this.pirateRole = "Unknown";
    }

    //Getters
    /**
     * Getter method for current bounty of Pirate object
     * @return long
     */
    public long getBounty(){

        return bounty;
    }

    /**
     * Getter method for current pirateRole of Pirate object
     * @return String
     */
    public String getPirateRole(){

        return pirateRole;
    }

    /**
     * Getter method for current crew of Pirate object
     * @return PirateCrew
     */
    public PirateCrew getPirateCrew(){

        return crew;

    }

    /**
     * Getter method for captaincy status of Pirate object
     * @return boolean
     */
    public boolean getIsCaptain(){

        return isCaptain;
    }

    //Setters

    /**
     * Setter method for bounty attribute of Marine
     * @param bounty Is the new long value to be assigned to the bounty attribute
     */
    public void setBounty(long bounty){

        if(bounty < 0){

            System.out.println("Bounty cannot be negative!");
        }
        else{

            this.bounty = bounty;
        }
    }

    /**
     * Setter method for pirateRole attribute of Marine
     * @param pirateRole Is the new String value to be assigned to the pirateRole attribute
     */
    public void setPirateRole(String pirateRole){

        this.pirateRole = pirateRole;
    }

    /**
     * Setter method for crew attribute of Marine
     * @param pirateCrew Is the new PirateCrew reference to be assigned to the crew attribute
     */
    public void setPirateCrew(PirateCrew pirateCrew){
        this.crew = pirateCrew;
    }

    /**
     * Setter method for isCaptain attribute of Marine
     * @param isCaptain Is the boolean value to be assigned to the isCaptain attribute
     */
    public void setIsCaptain(boolean isCaptain){

        this.isCaptain = isCaptain;
    }

    //methods
    /**
     * Modifies the bounty of a pirate
     * @param bounty the new positive bounty value to be assigned to the Pirate
     */
    public void assignModifyBounty(long bounty){

        if(bounty < 0){

            System.out.println("Bounty cannot be negative!\n");
        }
        else{

            setBounty(bounty);
            System.out.println(getName() + " Bounty updated to: " + bounty + " Berries!\n");

        }
    }

    /**
     * Assigns the pirate to an existing pirate Crew
     * @param pirateCrew The PirateCrew object that the Pirate will be assigned to
     */
    public void assignToPirateCrew(PirateCrew pirateCrew){

        pirateCrew.addCrewMember(this);
    }

    @Override
    public String fileString() {
        int fruitID;
        if (getDevilFruitPower() == null) {

        }

        return "PIRATE|" + getCharacterID() + "|" + getName() + "|" + getAlias() + "|" + getOrigin() +
                "|" + getStatus() + "|" + getDevilFruitPower().getFruitID() + "|";
    }
}
