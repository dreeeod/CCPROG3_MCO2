package model;

import model.exceptions.DuplicateNameException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SimulationList {

    private final List<Character> characters = new ArrayList<>();
    private final List<PirateCrew> crews = new ArrayList<>();
    private final List<MarineCorps> corps = new ArrayList<>();
    private final List<DevilFruit> fruits = new ArrayList<>();

    public Pirate createPirate(String name, String alias, String origin, String status,
                               double wallet, long bounty, String role) throws DuplicateNameException {
        checkDuplicateName(name);
        int id = ThreadLocalRandom.current().nextInt(10_000_000, 100_000_000);
        Pirate pirate = new Pirate(id, name, alias, origin, status, wallet, bounty, role);
        characters.add(pirate);
        return pirate;
    }

    public void checkDuplicateName(String name) throws DuplicateNameException {
        for (Character c : characters) {
            if (c.getName().equalsIgnoreCase(name)) {
                throw new DuplicateNameException("There is already a character named " + name + " in the simulation!");
            }
        }
    }

    public List<Character> getCharacters() { return characters; }
    public List<PirateCrew> getCrews() { return crews; }
    public List<MarineCorps> getCorps() { return corps; }
    public List<DevilFruit> getFruits() { return fruits; }
}
