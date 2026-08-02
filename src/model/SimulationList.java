package model;

import javafx.application.Platform;
import model.exceptions.DuplicateNameException;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SimulationList {

    // File Names for Data Storage
    private static final Path DIR_PATH = Path.of(System.getProperty("user.dir"), "src", "model");

    private static final String CHAR_FILE = DIR_PATH.resolve("Character.txt").toString();
    private static final String DELETE_FILE = DIR_PATH.resolve("Deleted.txt").toString();
    private static final String GROUP_FILE = DIR_PATH.resolve("Group.txt").toString();
    private static final String FRUIT_FILE = DIR_PATH.resolve("DevilFruit.txt").toString();
    private static final String CAPTURE_FILE = DIR_PATH.resolve("Capture.txt").toString();

    private List<String[]> charDevID = new ArrayList<>(); // per array, 0 is Character ID, 1 is DevilFruit ID (if there is one)
    private List<String[]> memberCrewID = new ArrayList<>(); // per array, 0 is Pirate ID, 1 is PirateCrew ID (if there is one)
    private List<String[]> memberCorpID = new ArrayList<>(); // per array, 0 is Marine ID, 1 is MarineCorps ID (if there is one)

    private final List<Character> characters = new ArrayList<>();
    private final List<Character> deleted = new ArrayList<>();
    private final List<PirateCrew> crews = new ArrayList<>();
    private final List<MarineCorps> corps = new ArrayList<>();
    private final List<DevilFruit> fruits = new ArrayList<>();
    private final List<Capture> captures = new ArrayList<>();

    public void createPirate(String name, String alias, String origin, String status,
                               long wallet, long bounty, String role) throws DuplicateNameException {
        checkDuplicateName(name);
        int id = ThreadLocalRandom.current().nextInt(10_000_000, 100_000_000);
        Pirate pirate = new Pirate(id, name, alias, origin, status, wallet, bounty, role);
        characters.add(pirate);
    }

    public void createMarine(String name, String alias, String origin, String status,
                               long wallet, String rank) throws DuplicateNameException {
        checkDuplicateName(name);
        int id = ThreadLocalRandom.current().nextInt(10_000_000, 100_000_000);
        Marine marine = new Marine(id, name, alias, origin, status, wallet, rank, null);
        characters.add(marine);
    }

    public void createHunter(String name, String alias, String origin, String status,
                                     long wallet, String style, int captures) throws DuplicateNameException {
        checkDuplicateName(name);
        int id = ThreadLocalRandom.current().nextInt(10_000_000, 100_000_000);
        PirateHunter hunter = new PirateHunter(id, name, alias, origin, status, wallet, style, captures);
        characters.add(hunter);
    }

    public void createCivilian(String name, String alias, String origin, String status,
                                   long wallet, String profession, String residence) throws DuplicateNameException {
        checkDuplicateName(name);
        int id = ThreadLocalRandom.current().nextInt(10_000_000, 100_000_000);
        Civilian civilian = new Civilian(id, name, alias, origin, status, wallet, profession, residence);
        characters.add(civilian);
    }

    public void checkDuplicateName(String name) throws DuplicateNameException {
        for (Character c : characters) {
            if (c.getName().equalsIgnoreCase(name)) {
                throw new DuplicateNameException("There is already a character named " + name + " in the simulation!");
            }
        }
    }

    public void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CHAR_FILE))) {
            for (Character c : characters) {
                writer.write(c.fileString());
                writer.newLine();
            }
        }
        catch (IOException e) {
            System.out.println("Something went wrong");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DELETE_FILE))) {
            for (Character c : deleted) {
                writer.write(c.fileString());
                writer.newLine();
            }
        }
        catch (IOException e) {
            System.out.println("Something went wrong");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(GROUP_FILE))) {
            for (PirateCrew p : crews) {
                writer.write(p.fileString());
                writer.newLine();
            }
            for (MarineCorps m : corps) {
                writer.write(m.fileString());
                writer.newLine();
            }
        }
        catch (IOException e) {
            System.out.println("Something went wrong");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FRUIT_FILE))) {
            for (DevilFruit f : fruits) {
                writer.write(f.fileString());
                writer.newLine();
            }
        }
        catch (IOException e) {
            System.out.println("Something went wrong");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAPTURE_FILE))) {
            for (Capture a : captures) {
                writer.write(a.fileString());
                writer.newLine();
            }
        }
        catch (IOException e) {
            System.out.println("Something went wrong");
        }

        Platform.exit();
    }

    public void loadData() {
        // Load Characters
        loadCharacter();
        // Load Dead Characters
        loadDeadCharacter();
        // Load Devil Fruit
        loadFruit();
        // Load Groups
        loadGroup();
        // Load Captures
        loadCaptures();
    }

    public void loadCharacter() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CHAR_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }

                String[] c = line.split("\\|", -1);

                //checks if Character has DevilFruit
                if (!c[6].equalsIgnoreCase("NONE")) {
                    String[] devilKeyPair = {c[1], c[6]};
                    charDevID.add(devilKeyPair);
                }

                //checks if Pirate has PirateCrew
                if (c[0].equalsIgnoreCase("PIRATE") && !c[10].equalsIgnoreCase("NONE")) {
                    String[] crewKeyPair = {c[1], c[10]};
                    memberCrewID.add(crewKeyPair);
                }

                //checks if Marine has MarineCorps
                if (c[0].equalsIgnoreCase("MARINE") && !c[9].equalsIgnoreCase("NONE")) {
                    String[] corpKeyPair = {c[1], c[9]};
                    memberCorpID.add(corpKeyPair);
                }

                switch (c[0]) {
                    case "PIRATE":
                        Pirate pirate = new Pirate(Integer.parseInt(c[1]), c[2], c[3], c[4], c[5], Long.parseLong(c[7]), Long.parseLong(c[8]), c[9]);
                        pirate.setIsCaptain(Boolean.parseBoolean(c[11]));
                        characters.add(pirate);
                        break;
                    case "MARINE":
                        Marine marine = new Marine(Integer.parseInt(c[1]), c[2], c[3], c[4], c[5], Long.parseLong(c[7]), c[8], null);
                        characters.add(marine);
                        break;
                    case "PIRATEHUNTER":
                        PirateHunter hunter = new PirateHunter(Integer.parseInt(c[1]), c[2], c[3], c[4], c[5], Long.parseLong(c[7]), c[8], Integer.parseInt(c[9]));
                        characters.add(hunter);
                        break;
                    case "CIVILIAN":
                        Civilian civilian = new Civilian(Integer.parseInt(c[1]), c[2], c[3], c[4], c[5], Long.parseLong(c[7]), c[8], c[9]);
                        characters.add(civilian);
                        break;
                }
            }
        }
        catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }

    public void loadDeadCharacter() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DELETE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }

                String[] c = line.split("\\|", -1);

                switch (c[0]) {
                    case "PIRATE":
                        Pirate pirate = new Pirate(Integer.parseInt(c[1]), c[2], c[3], c[4], c[5], Long.parseLong(c[7]), Long.parseLong(c[8]), c[9]);
                        pirate.setIsCaptain(Boolean.parseBoolean(c[11]));
                        deleted.add(pirate);
                        break;
                    case "MARINE":
                        Marine marine = new Marine(Integer.parseInt(c[1]), c[2], c[3], c[4], c[5], Long.parseLong(c[7]), c[8], null);
                        deleted.add(marine);
                        break;
                    case "PIRATEHUNTER":
                        PirateHunter hunter = new PirateHunter(Integer.parseInt(c[1]), c[2], c[3], c[4], c[5], Long.parseLong(c[7]), c[8], Integer.parseInt(c[9]));
                        deleted.add(hunter);
                        break;
                    case "CIVILIAN":
                        Civilian civilian = new Civilian(Integer.parseInt(c[1]), c[2], c[3], c[4], c[5], Long.parseLong(c[7]), c[8], c[9]);
                        deleted.add(civilian);
                        break;
                }
            }
        }
        catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }

    public void loadFruit() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FRUIT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }

                String[] f = line.split("\\|", -1);

                DevilFruit fruit = new DevilFruit(Integer.parseInt(f[0]), f[1], f[2], f[3]);
                Character c = null;
                if (!f[4].equalsIgnoreCase("NONE")) { // for when the fruit currently has an owner
                    for (int i = 0; i < charDevID.size(); i++) {
                        if (f[4].equals(charDevID.get(i)[0])) {
                            c = getCharByID(Integer.parseInt(charDevID.get(i)[0]));
                            break;
                        }
                    }
                    fruit.setOwner(c); // makes the character be the current user
                }

                if (f.length > 5) { // if there's an ID value at index 5, that means the length is 6, etc.
                    for (int j = 5; j < f.length; j++) {
                        c = getCharByID(Integer.parseInt(f[j]));
                        fruit.getHistory().add(c);
                    }
                }

                fruits.add(fruit);
            }
        }
        catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }

    public void loadGroup() {
        try (BufferedReader reader = new BufferedReader(new FileReader(GROUP_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }

                String[] g = line.split("\\|", -1);

                switch (g[0]) {
                    case "PIRATECREW" :
                        Pirate captain = null;
                        if (!g[4].equalsIgnoreCase("NONE")) {
                            captain = (Pirate) getCharByID(Integer.parseInt(g[4]));
                        }

                        PirateCrew crew = new PirateCrew(Integer.parseInt(g[1]), g[2], g[3], captain);
                        crew.setTotalBounty(Long.parseLong(g[5]));

                        if (g.length > 6) {
                            for (int j = 6; j < g.length; j++) {
                                Pirate member = (Pirate) getCharByID(Integer.parseInt(g[j]));
                                crew.getCrewMembers().add(member);
                                member.setPirateCrew(crew);
                            }
                        }

                        crews.add(crew);
                        break;
                    case "MARINECORPS" :
                        Marine commander = null;
                        if (!g[3].equalsIgnoreCase("NONE")) {
                            commander = (Marine) getCharByID(Integer.parseInt(g[3]));
                        }

                        MarineCorps corp = new MarineCorps(Integer.parseInt(g[1]), g[2], commander, Long.parseLong(g[4]));

                        if (g.length > 5) {
                            for (int j = 5; j < g.length; j++) {
                                Marine member = (Marine) getCharByID(Integer.parseInt(g[j]));
                                corp.getMembers().add(member);
                                member.setCorps(corp);
                            }
                        }

                        corps.add(corp);
                        break;
                }
            }
        }
        catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }

    public void loadCaptures() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CAPTURE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }

                String[] a = line.split("\\|", -1);
                Pirate captured = (Pirate) getCharByID(Integer.parseInt(a[1]));
                Character captor = getCharByID(Integer.parseInt(a[2]));
                Capture capture = null;

                if (captor instanceof Marine m) {
                    capture = new Capture(Integer.parseInt(a[0]), captured, m, a[3]);
                }
                else if (captor instanceof PirateHunter h) {
                    capture = new Capture(Integer.parseInt(a[0]), captured, h, a[3]);
                }
                else if (captor instanceof Civilian v) {
                    capture = new Capture(Integer.parseInt(a[0]), captured, v, a[3]);
                }

                captures.add(capture);

            }
        }
        catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }

    public Character getCharByID(int ID) {
        for (Character c : characters) {
            if (c.getCharacterID() == ID)
                return c;
        }
        for (Character c : deleted) {
            if (c.getCharacterID() == ID) {
                return c;
            }
        }
        return null;
    }

    public List<Character> getCharacters() { return characters; }
    public List<Character> getDeleted() { return deleted; }
    public List<PirateCrew> getCrews() { return crews; }
    public List<MarineCorps> getCorps() { return corps; }
    public List<DevilFruit> getFruits() { return fruits; }
    public List<Capture> getCaptures() { return captures; }
}
