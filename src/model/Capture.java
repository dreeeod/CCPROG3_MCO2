package model;

import model.exceptions.IllegalCaptorException;
import model.exceptions.NegativeValueException;

import java.util.List;

public class Capture {

    int captID;
    Pirate captured;
    Character captor;
    String captureState;

    /**
     * Constructor for when the captor is of class Marine
     * @param id Pre-generated random ID for this specific capture
     * @param captured Pirate object that is being captured
     * @param captor Marine object that the captor of the Pirate
     * @param captureState Chosen state of the Pirate's capture, either Dead or Alive
     */
    public Capture(int id, Pirate captured, Marine captor, String captureState) {
        captID = id;
        this.captured = captured;
        this.captor = captor;
        this.captureState = captureState;
    }
    /**
     * Constructor for when the captor is of class PirateHunter
     * @param id Pre-generated random ID for this specific capture
     * @param captured Pirate object that is being captured
     * @param captor PirateHunter object that the captor of the Pirate
     * @param captureState Chosen state of the Pirate's capture, either Dead or Alive
     */
    public Capture(int id, Pirate captured, PirateHunter captor, String captureState) {
        captID = id;
        this.captured = captured;
        this.captor = captor;
        this.captureState = captureState;
    }
    /**
     * Constructor for when the captor is of class Civilian
     * @param id Pre-generated random ID for this specific capture
     * @param captured Pirate object that is being captured
     * @param captor Civilian object that the captor of the Pirate
     * @param captureState Chosen state of the Pirate's capture, either Dead or Alive
     */
    public Capture(int id, Pirate captured, Civilian captor, String captureState) {
        captID = id;
        this.captured = captured;
        this.captor = captor;
        this.captureState = captureState;
    }

    public int getCaptID() {
        return captID;
    }

    public Pirate getCaptured() {
        return captured;
    }

    public Character getCaptor() {
        return captor;
    }

    public String getCaptureState() {
        return captureState;
    }

    /**
     * Method for validating if the captor is not a Pirate. Throws an exception otherwise
     * @param captor Character object to be checked if it is also a Pirate object
     * @throws IllegalCaptorException User-defined exception thrown, specific for this method
     */
    public void validateCapture(Character captor) throws IllegalCaptorException {
        if (captor instanceof Pirate) {
            throw new IllegalCaptorException("");
        }
    }
    /**
     * Method for processing the actions of setting the Pirate's status, calling the routeFinancialRewards method,
     * and adjusting a Devil Fruit's history if needed
     */
    public void processTargetStatus() {
        // sets the user's status appropriately if they were captured as Dead or Alive
        if (captureState.equals("Alive")) {
            this.captured.setStatus("Captured");
        }
        else if (captureState.equals("Dead")) {
            this.captured.setStatus("Dead");
        }

        // handles the distribution of funds to the wallets/fund (for MarineCorps)
        routeFinancialRewards();
        if (this.captured.getPirateCrew() != null && this.captureState.equals("Dead")) {
            this.captured.getPirateCrew().removeCrewMember(this.captured);
        }

        if (this.captor instanceof PirateHunter h) {
            int captures = h.getCaptures();
            captures += 1;
            try {
                h.setCaptures(captures);
            }
            catch (NegativeValueException e) {
                return;
            }
        }

        // puts the captured entity into a Devil Fruit's list of owners if they are captured as Dead
        if (this.captor.getStatus().equals("Dead") && this.captor.getDevilFruitPower() != null) {
            this.captor.getDevilFruitPower().triggerReincarnation();
        }
    }
    /**
     * Method responsible for adding the Pirate's bounty to a PirateHunter or Civilian's wallets, or to a Marine's wallet
     * or its MarineCorps' funds if it is part of one
     */
    public void routeFinancialRewards() {
        double addBounty;
        if (this.captor instanceof Marine marine) {
            if (marine.getCorps() == null) {
                addBounty = this.captured.getBounty();
                addBounty += this.captor.getWallet();
                this.captor.setWallet(addBounty);
            }
            else {
                addBounty = this.captured.getBounty();
                addBounty += marine.getCorps().getFunds();
                marine.getCorps().setFunds(addBounty);
            }
        }
        else if (this.captor instanceof PirateHunter || this.captor instanceof Civilian) {
            addBounty = this.captured.getBounty();
            addBounty += this.captor.getWallet();
            this.captor.setWallet(addBounty);
        }

        if (this.captured.getPirateCrew() != null && this.captured.getStatus().equals("Captured")) {
            addBounty = this.getCaptured().getPirateCrew().getTotalCrewBounty();
            addBounty -= this.getCaptured().getBounty();
            long bounty = (long) addBounty;
            this.getCaptured().getPirateCrew().setTotalBounty(bounty);
        }
    }
    /**
     * Method responsible for logging the Capture object to the global list of created Captures
     * @param captures Global list of all created Captures, saved and modified during the simulation's start and exit
     */
    public void logTransaction(List<Capture> captures) {
        captures.add(this);
    }

    public String fileString() {
        return this.captID + "|" + this.captured.getCharacterID() + "|" + this.captor.getCharacterID() + "|" + this.captureState;
    }

}
