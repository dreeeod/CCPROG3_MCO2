package model;

import model.exceptions.DuplicateNameException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SimulationList {

    // File Names for Data Storage
    private static final String CHAR_FILE = "Character.txt";
    private static final String GROUP_FILE = "Group.txt";
    private static final String FRUIT_FILE = "DevilFruit.txt";

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

    public Marine createMarine(String name, String alias, String origin, String status,
                               double wallet, String rank) throws DuplicateNameException {
        checkDuplicateName(name);
        int id = ThreadLocalRandom.current().nextInt(10_000_000, 100_000_000);
        Marine marine = new Marine(id, name, alias, origin, status, wallet, rank, null);
        characters.add(marine);
        return marine;
    }

    public PirateHunter createHunter(String name, String alias, String origin, String status,
                                     double wallet, String style, int captures) throws DuplicateNameException {
        checkDuplicateName(name);
        int id = ThreadLocalRandom.current().nextInt(10_000_000, 100_000_000);
        PirateHunter hunter = new PirateHunter(id, name, alias, origin, status, wallet, style, captures);
        characters.add(hunter);
        return hunter;
    }

    public Civilian createCivilian(String name, String alias, String origin, String status,
                                   double wallet, String profession, String residence) throws DuplicateNameException {
        checkDuplicateName(name);
        int id = ThreadLocalRandom.current().nextInt(10_000_000, 100_000_000);
        Civilian civilian = new Civilian(id, name, alias, origin, status, wallet, profession, residence);
        characters.add(civilian);
        return civilian;
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
