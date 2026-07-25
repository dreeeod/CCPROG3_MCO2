package model;

public class Character {

    private int characterID;
    private String name, alias, origin, status;
    DevilFruit devilFruitPower;
    private double wallet;

    //Constructor

    /**
     * First constructor method for Character class with input parameters
     * @param characterID Is the value to be assigned to characterID attribute
     * @param name Is the value to be assigned to name attribute
     * @param alias Is the value to be assigned to alias attribute
     * @param origin Is the value to be assigned to origin attribure
     * @param status Is the value to be assigned to status attribute
     * @param wallet Is the value to be assigned to wallet attribute
     */
    public Character(int characterID, String name, String alias, String origin, String status, double wallet){

        this.characterID = characterID;
        this.name = name;
        this.alias = alias;
        this.origin = origin;
        this.status = status;
        this.devilFruitPower = null; // initially set as blank upon character creation
        this.wallet = wallet;

    }

    /**
     * Second constructor method for Character class without input parameters
     */
    public Character() {
        this.characterID = 0;
        this.name = "Unknown";
        this.alias = "Unknown";
        this.origin = "Unknown";
        this.status = "Unknown";
        this.devilFruitPower = null;
        this.wallet = 0;
    }

    //Getters

    /**
     * Getter method for current characterID of Character object
     * @return int
     */
    public int getCharacterID(){

        return characterID;

    }

    /**
     * Getter method for the current name of Character object
     * @return String
     */
    public String getName(){

        return name;
    }

    /**
     * Getter method for current alias of Character object
     * @return String
     */
    public String getAlias(){

        return alias;
    }

    /**
     * Getter method for current origin of Character object
     * @return String
     */
    public String getOrigin(){

        return origin;
    }

    /**
     * Getter method for current status of Character object
     * @return String
     */
    public String getStatus(){

        return status;
    }

    /**
     * Getter method for current Devil Fruit power of Character object
     * @return DevilFruit
     */
    public DevilFruit getDevilFruitPower(){

        return devilFruitPower;
    }

    /**
     * Getter method for current wallet of Character object
     * @return double
     */
    public double getWallet(){

        return wallet;
    }

    //Setters

    /**
     * Setter method for characterID attribute of Character
     * @param characterID Is the new int value to be assigned to the characterID attribute
     */
    public void setCharacterID(int characterID){
        this.characterID = characterID;
    }

    /**
     * Setter method for name attribute of Character
     * @param name It is the new String value to be assigned to the name attribute
     */
    public void setName(String name){

        this.name = name;
    }

    /**
     * Setter method for alias attribute of Character
     * @param alias alias Is the new String value to be assigned to the alias attribute
     */
    public void setAlias(String alias){

        this.alias = alias;
    }

    /**
     * Setter method for origin attribute of Character
     * @param origin origin Is the new String value to be assigned to the origin attribute
     */
    public void setOrigin(String origin){

        this.origin = origin;
    }

    /**
     * Setter method for the status attribute of Character
     * @param status It is the new String value to be assigned to status attribute
     */
    public void setStatus(String status){

        if(status.equals("Free") || status.equals("Captured") || status.equals("Dead")){

            this.status = status;
        }
        else{

            System.out.println("Invalid status, status must be: Free, Captured, or Dead");
        }
    }

    /**
     * Setter method for the devilFruitPower attribute of Character
     * @param devilFruitPower It is the new DevilFruit value to be assigned to devilFruitPower attribute
     */
    public void setDevilFruitPower(DevilFruit devilFruitPower){

        this.devilFruitPower = devilFruitPower;
    }

    /**
     * Setter method for the walled attribute of character
     * @param wallet it is the new double value to be assigned to wallet attribute
     */
    public void setWallet(double wallet){

        this.wallet = wallet;
    }

}
