package model;

public class Civilian extends Character{

    String profession;
    String residence;

    //Constructor

    /**
     * First constructor method for the Civilian class with input parameters
     * @param characterID Is the value to be assigned to characterID attribute in the super class
     * @param name Is the value to be assigned to name attribute in the super class
     * @param alias Is the value to be assigned to the alias attribute in the super class
     * @param origin Is the value to be assigned to origin attribute in the super class
     * @param status Is the value to be assigned to status attribute in the super class
     * @param wallet Is the value to be assigned to wallet attribute in the super class
     * @param profession Is the value to be assigned to profession attribute
     * @param residence Is the value to be assigned to residence attribute
     */
    public Civilian(int characterID, String name, String alias, String origin, String status, double wallet, String profession, String residence){

        super(characterID, name, alias, origin, status, wallet);

        this.profession = profession;
        this.residence = residence;

    }

    /**
     * Second Constructor for Civilian class without input parameters
     */
    public Civilian() {
        super();
        this.profession = "Unknown";
        this.residence = "Unknown";
    }
    //Getters

    /**
     * getter method for the string profession attribute of the Civilian object
     * @return String profession
     */
    public String getProfession(){

        return profession;
    }

    /**
     * getter method for the string residence attribute of the Civilian object
     * @return String Residence
     */
    public String getResidence() {

        return residence;
    }

    //Setters
    /**
     * Setter method for profession attribute of Civilian
     * @param profession Is the new String value to be assigned to the profession attribute
     */
    public void setProfession(String profession){

        this.profession = profession;
    }

    /**
     * Setter method for residence attribute of Civilian
     * @param residence Is the new String value to be assigned to the residence attribute
     */
    public void setResidence(String residence){

        this.residence = residence;
    }

    @Override
    public String fileString() {
        return "Hello";
    }

}
