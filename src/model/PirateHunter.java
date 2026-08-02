package model;

import model.exceptions.NegativeValueException;

public class PirateHunter extends Character{

    private String style;
    private int captures;

    /**
     * First Constructor method of Pirate Hunter class with input parameters
     * @param id Is the value to be assigned to characterID attribute in the super class
     * @param name Is the value to be assigned to name attribute in the super class
     * @param alias Is the value to be assigned to alias attribute in the super class
     * @param origin Is the value to be assigned to the origin attribute in the super class
     * @param status Is the value to be assigned to status attribute in the super class
     * @param wallet Is the value to be assigned to wallet attribute in the super class
     * @param style Is the value to be assigned to style attribute
     * @param captures Is the value to be assigned to captures attribute
     */
    public PirateHunter(int id, String name, String alias, String origin, String status, double wallet, String style, int captures) {
        super(id, name, alias, origin, status, wallet);
        this.style = style;
        this.captures = captures;
    }

    /**
     * Second Constructor method of Pirate Hunter class with no input parameters
     */
    public PirateHunter() {
        super();
        this.style = "Unknown";
        this.captures = 0;
    }

    // GETTERS

    /**
     * Getter method for style of the PirateHunter object
     * @return String
     */
    public String getStyle() {
        return this.style;
    }

    /**
     * Getter method for captures of the PirateHunter object
     * @return int
     */
    public int getCaptures() {
        return this.captures;
    }

    /**
     * Setter method for the style attribute of PirateHunter object
     * @param style Is the new String value to be assigned to the style attribute
     */
    public void setStyle(String style) {
        this.style = style;
    }

    /**
     * Setter method for the capture attribute of PirateHunter object
     * @param captures Is the new int value to be assigned to the style attribute
     */
    public void setCaptures(int captures) throws NegativeValueException {
        if (captures < 0) {
            throw new NegativeValueException("");
        }
        this.captures = captures;
    }

    @Override
    public String fileString() {
        String fruitID = "NONE";
        if (getDevilFruitPower() != null) { // gets Devil Fruit's ID
            fruitID = String.valueOf(getDevilFruitPower().getFruitID());
        }

        return "PIRATEHUNTER|" + getCharacterID() + "|" + getName() + "|" + getAlias() + "|" + getOrigin() +
                "|" + getStatus() + "|" + fruitID + "|" + getWallet() + "|" + getStyle() + "|" + getCaptures();
    }

}
