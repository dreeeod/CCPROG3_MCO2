package controller;

import app.Main;
import com.sun.javafx.geom.PickRay;
import javafx.scene.control.Alert;
import model.*;
import model.Character;
import model.exceptions.DuplicateNameException;
import model.exceptions.LowRankException;
import model.exceptions.NegativeValueException;
import view.CharacterMenuView;

public class CharacterMenuController {

    public CharacterMenuController(CharacterMenuView view, Main app, SimulationList data) {
        view.getCreateButton().setOnAction(e -> implementCreateCharacter(view, app, data));
        view.getViewButton().setOnAction(e -> implementViewCharacter(view, app, data));
        view.getModifyButton().setOnAction(e -> implementModifyCharacter(view, app, data));
        view.getDeleteButton().setOnAction(e -> implementDeleteCharacter(view, app, data));
        view.getBackButton().setOnAction(e -> app.showMainMenu(app.getMainStage()));
    }

    private void notYetImplemented() {
        new Alert(Alert.AlertType.INFORMATION, "This screen isn't built yet!").showAndWait();
    }

    /*
        METHODS FOR CHARACTER CREATION
    */
    private void implementCreateCharacter(CharacterMenuView view, Main app, SimulationList data) {
        app.getMainStage().setScene(view.characterCreateMenu()); // sets the scene of the stage at Main to be that of the Character Create menu

        view.getPirateButton().setOnAction(e -> controlPirateCreate(view, app, data));
        view.getMarineButton().setOnAction(e -> controlMarineCreate(view, app, data));
        view.getHunterButton().setOnAction(e -> controlHunterCreate(view, app, data));
        view.getCivilianButton().setOnAction(e -> controlCivilianCreate(view, app, data));
        view.getBackButton().setOnAction(e -> app.showCharacterMenu(app.getMainStage()));
    }

    private void controlPirateCreate(CharacterMenuView view, Main app, SimulationList data) {
        app.getMainStage().setScene(view.createPirateView()); // sets the scene of the stage at Main to be that of the Pirate Creation GUI

        view.getCreatePirateButton().setOnAction(e -> createPirate(view, data));
        view.getCharBackButton().setOnAction(e -> app.showCharacterMenu(app.getMainStage()));
    }

    private void createPirate(CharacterMenuView view, SimulationList data) {
        String name = view.getNameField().getText();
        String alias = view.getAliasField().getText();
        String origin = view.getOriginField().getText();
        String status = view.getStatusBox().getValue();
        String role = view.getRoleBox().getValue();

        if (name.isBlank() || status == null || role == null) {
            view.getMessageLabel().setText("Please fill in all required fields.");
            return;
        }

        double wallet;
        long bounty;
        try {
            wallet = Double.parseDouble(view.getWalletField().getText());
            bounty = Long.parseLong(view.getBountyField().getText());
        }
        catch (NumberFormatException ex) {
            view.getMessageLabel().setText("Wallet and bounty must be valid numbers.");
            return;
        }

        if (wallet < 0 || bounty < 0) {
            view.getMessageLabel().setText("Wallet and bounty cannot be negative.");
            return;
        }

        try {
            data.createPirate(name, alias, origin, status, wallet, bounty, role);
            view.getMessageLabel().setText("Pirate '" + name + "' created successfully!");
            view.getNameField().clear();
            view.getAliasField().clear();
            view.getOriginField().clear();
            view.getWalletField().clear();
            view.getBountyField().clear();
        }
        catch (DuplicateNameException ex) {
            view.getMessageLabel().setText(ex.getMessage());
        }
    }

    private void controlMarineCreate(CharacterMenuView view, Main app, SimulationList data) {
        app.getMainStage().setScene(view.createMarineView()); // sets the scene of the stage at Main to be that of the Marine Creation GUI

        view.getCreateMarineButton().setOnAction(e -> createMarine(view, data));
        view.getCharBackButton().setOnAction(e -> app.showCharacterMenu(app.getMainStage()));
    }

    private void createMarine(CharacterMenuView view, SimulationList data) {
        String name = view.getNameField().getText();
        String alias = view.getAliasField().getText();
        String origin = view.getOriginField().getText();
        String status = view.getStatusBox().getValue();
        String rank = view.getRankBox().getValue();

        if (name.isBlank() || status == null || rank == null) {
            view.getMessageLabel().setText("Please fill in all required fields.");
            return;
        }

        double wallet;
        try {
            wallet = Double.parseDouble(view.getWalletField().getText());
        }
        catch (NumberFormatException ex) {
            view.getMessageLabel().setText("Wallet must be a valid number.");
            return;
        }

        if (wallet < 0) {
            view.getMessageLabel().setText("Wallet cannot be negative.");
            return;
        }

        try {
            data.createMarine(name, alias, origin, status, wallet, rank);
            view.getMessageLabel().setText("Marine '" + name + "' created successfully!");
            view.getNameField().clear();
            view.getAliasField().clear();
            view.getOriginField().clear();
            view.getWalletField().clear();
        }
        catch (DuplicateNameException ex) {
            view.getMessageLabel().setText(ex.getMessage());
        }
    }

    private void controlHunterCreate(CharacterMenuView view, Main app, SimulationList data) {
        app.getMainStage().setScene(view.createHunterView()); // sets the scene of the stage at Main to be that of the Marine Creation GUI

        view.getCreateHunterButton().setOnAction(e -> createHunter(view, data));
        view.getCharBackButton().setOnAction(e -> app.showCharacterMenu(app.getMainStage()));
    }

    private void createHunter(CharacterMenuView view, SimulationList data) {
        String name = view.getNameField().getText();
        String alias = view.getAliasField().getText();
        String origin = view.getOriginField().getText();
        String status = view.getStatusBox().getValue();
        String style = view.getStyleField().getText();

        if (name.isBlank() || status == null || style.isBlank()) {
            view.getMessageLabel().setText("Please fill in all required fields.");
            return;
        }

        double wallet;
        int captures;
        try {
            wallet = Double.parseDouble(view.getWalletField().getText());
            captures = Integer.parseInt(view.getCapturesField().getText());
        }
        catch (NumberFormatException ex) {
            view.getMessageLabel().setText("Wallet must be a valid number.");
            return;
        }

        if (wallet < 0 || captures < 0) {
            view.getMessageLabel().setText("Wallet cannot be negative.");
            return;
        }

        try {
            data.createHunter(name, alias, origin, status, wallet, style, captures);
            view.getMessageLabel().setText("Pirate Hunter '" + name + "' created successfully!");
            view.getNameField().clear();
            view.getAliasField().clear();
            view.getOriginField().clear();
            view.getWalletField().clear();
            view.getStyleField().clear();
            view.getCapturesField().clear();
        }
        catch (DuplicateNameException ex) {
            view.getMessageLabel().setText(ex.getMessage());
        }
    }

    private void controlCivilianCreate(CharacterMenuView view, Main app, SimulationList data) {
        app.getMainStage().setScene(view.createCivilianView()); // sets the scene of the stage at Main to be that of the Civilian Creation GUI

        view.getCreateCivilianButton().setOnAction(e -> createCivilian(view, data));
        view.getCharBackButton().setOnAction(e -> app.showCharacterMenu(app.getMainStage()));
    }

    private void createCivilian(CharacterMenuView view, SimulationList data) {
        String name = view.getNameField().getText();
        String alias = view.getAliasField().getText();
        String origin = view.getOriginField().getText();
        String status = view.getStatusBox().getValue();
        String profession = view.getProfessionField().getText();
        String residence = view.getResidenceField().getText();

        if (name.isBlank() || status == null || profession.isBlank() || residence.isBlank()) {
            view.getMessageLabel().setText("Please fill in all required fields.");
            return;
        }

        double wallet;
        try {
            wallet = Double.parseDouble(view.getWalletField().getText());
        }
        catch (NumberFormatException ex) {
            view.getMessageLabel().setText("Wallet must be a valid number.");
            return;
        }

        if (wallet < 0) {
            view.getMessageLabel().setText("Wallet cannot be negative.");
            return;
        }

        try {
            data.createCivilian(name, alias, origin, status, wallet, profession, residence);
            view.getMessageLabel().setText("Civilian '" + name + "' created successfully!");
            view.getNameField().clear();
            view.getAliasField().clear();
            view.getOriginField().clear();
            view.getWalletField().clear();
            view.getProfessionField().clear();
            view.getResidenceField().clear();
        }
        catch (DuplicateNameException ex) {
            view.getMessageLabel().setText(ex.getMessage());
        }
    }

    /*
        METHODS FOR CHARACTER VIEWING
    */
    private void implementViewCharacter(CharacterMenuView view, Main app, SimulationList data) {
        app.getMainStage().setScene(view.viewCharacterMenu()); // sets the scene of the stage at Main to be that of the View Character menu

        view.getTypeBox().setOnAction(e -> characterSelection(view, data));
        view.getCharacterBox().setOnAction(e -> viewCharacter(view, data));
        view.getBackButton().setOnAction(e -> app.showCharacterMenu(app.getMainStage()));
    }

    private void characterSelection(CharacterMenuView view, SimulationList data) {
        String type = view.getTypeBox().getValue();
        view.getCharacterBox().getItems().clear();

        for (Character c : data.getCharacters()) {
            if (type.equals("Pirate") && c instanceof Pirate) {
                view.getCharacterBox().getItems().add(c.getName());
            }
            else if (type.equals("Marine") && c instanceof Marine) {
                view.getCharacterBox().getItems().add(c.getName());
            }
            else if (type.equals("Pirate Hunter") && c instanceof PirateHunter) {
                view.getCharacterBox().getItems().add(c.getName());
            }
            else if (type.equals("Civilian") && c instanceof Civilian) {
                view.getCharacterBox().getItems().add(c.getName());
            }
        }

        if (view.getCharacterBox().getItems().isEmpty()) {
            view.getMessageLabel().setText("There are currently no characters of this type!");
        }
        else {
            view.getMessageLabel().setText("");
        }
    }

    private void viewCharacter(CharacterMenuView view, SimulationList data) {
        String selectedName = view.getCharacterBox().getValue();

        if (selectedName == null) {
            view.getMessageLabel().setText("Please select a character to view!");
            return;
        }

        for (Character c : data.getCharacters()) {
            if (c.getName().equalsIgnoreCase(selectedName)) {
                StringBuilder profile = new StringBuilder();

                // Base Attributes
                profile.append("XXX Character Profile XXX\n");
                profile.append("ID          : ").append(c.getCharacterID()).append("\n");
                profile.append("Name        : ").append(c.getName()).append("\n");
                profile.append("Alias       : ").append(c.getAlias()).append("\n");
                profile.append("Origin      : ").append(c.getOrigin()).append("\n");
                profile.append("Status      : ").append(c.getStatus()).append("\n");
                profile.append("Wallet      : ").append(c.getWallet()).append("\n");
                profile.append("Devil Fruit : ").append(
                        c.getDevilFruitPower() != null ? c.getDevilFruitPower().getName() : "None"
                ).append("\n");

                // Specific Attributes
                if (c instanceof Pirate pirate) {
                    profile.append("\nXXX Pirate Info XXX\n");
                    profile.append("Bounty      : ").append(pirate.getBounty()).append(" Berries\n");
                    profile.append("Role        : ").append(pirate.getPirateRole()).append("\n");
                    profile.append("Captain     : ").append(pirate.getIsCaptain()).append("\n");
                    profile.append("Crew        : ").append(
                            pirate.getPirateCrew() != null ? pirate.getPirateCrew().getCrewName() : "None"
                    ).append("\n");
                }
                else if (c instanceof Marine marine) {
                    profile.append("\nXXX Marine Info XXX\n");
                    profile.append("Rank        : ").append(marine.getRank()).append("\n");
                    profile.append("Corps       : ").append(
                            marine.getCorps() != null ? marine.getCorps().getBaseLoc() : "None"
                    ).append("\n");
                }
                else if (c instanceof PirateHunter hunter) {
                    profile.append("\nXXX Pirate Hunter Info XXX\n");
                    profile.append("Style       : ").append(hunter.getStyle()).append("\n");
                    profile.append("Captures    : ").append(hunter.getCaptures()).append("\n");
                }
                else if (c instanceof Civilian civilian) {
                    profile.append("\nXXX Pirate Hunter Info XXX\n");
                    profile.append("Profession  : ").append(civilian.getProfession()).append("\n");
                    profile.append("Residence   : ").append(civilian.getResidence()).append("\n");
                }

                view.getProfileLabel().setText(profile.toString());
                view.getMessageLabel().setText("");
                return;
            }
        }
    }

    /*
        METHODS FOR CHARACTER MODIFICATION
    */
    private void implementModifyCharacter(CharacterMenuView view, Main app, SimulationList data) {
        app.getMainStage().setScene(view.modifyCharacterMenu()); // sets the scene of the stage at Main to be that of the Modify Character menu

        view.getTypeBox().setOnAction(e -> characterModifySelection(view, app, data));
        view.getModifyCharacterButton().setOnAction(e -> controlCharacterModify(view, data));
        view.getBackButton().setOnAction(e -> app.showCharacterMenu(app.getMainStage()));
    }

    private void characterModifySelection(CharacterMenuView view, Main app, SimulationList data) {
        String type = view.getTypeBox().getValue();
        view.getCharacterBox().getItems().clear();

        for (Character c : data.getCharacters()) {
            if (type.equals("Pirate") && c instanceof Pirate) {
                view.getCharacterBox().getItems().add(c.getName());
            }
            else if (type.equals("Marine") && c instanceof Marine) {
                view.getCharacterBox().getItems().add(c.getName());
            }
            else if (type.equals("Pirate Hunter") && c instanceof PirateHunter) {
                view.getCharacterBox().getItems().add(c.getName());
            }
            else if (type.equals("Civilian") && c instanceof Civilian) {
                view.getCharacterBox().getItems().add(c.getName());
            }
        }

        if (view.getCharacterBox().getItems().isEmpty()) {
            view.getMessageLabel().setText("There are currently no characters of this type!");
            return;
        }

        view.getMessageLabel().setText("");
        if (type.equals("Pirate")) {
            view.modifyPirateView(app, data);
        }
        else if (type.equals("Marine")) {
            view.modifyMarineView(app, data);
        }
        else if (type.equals("Pirate Hunter")) {
            view.modifyHunterView(app, data);
        }
        else if (type.equals("Civilian")) {
            view.modifyCivilianView(app, data);
        }

    }

    private void controlCharacterModify(CharacterMenuView view, SimulationList data) {
        String selectedName = view.getCharacterBox().getValue();
        String selectedType = view.getTypeBox().getValue();
        String selectedAction = view.getActionBox().getValue();

        if (selectedName == null) {
            view.getMessageLabel().setText("Please select a pirate to modify!");
            return;
        }
        if (selectedAction == null) {
            view.getMessageLabel().setText("Please select a modification action!");
            return;
        }

        if (selectedType.equals("Pirate")) {
            Pirate pirate = new Pirate();
            for (Character c : data.getCharacters()) {
                if (c.getName().equalsIgnoreCase(selectedName)) {
                    pirate = (Pirate) c;
                    break;
                }
            }

            switch (selectedAction) {
                case "Assign/Modify Bounty" :
                    long bounty;
                    try {
                        bounty = Long.parseLong(view.getModBounty().getText());
                    }
                    catch (NumberFormatException e) {
                        view.getMessageLabel().setText("Bounty must be a valid number.");
                        return;
                    }
                    try {
                        pirate.assignModifyBounty(bounty);
                    }
                    catch (NegativeValueException e) {
                        view.getMessageLabel().setText("Bounty must not be a negative number.");
                        return;
                    }
                    view.getMessageLabel().setText("Successfully modified bounty to " + bounty + "!");
                    break;
                case "Assign/Modify Crew" :
                    String crewName = view.getModCrew().getValue();
                    for (PirateCrew p : data.getCrews()) {
                        if (p.getCrewName().equalsIgnoreCase(crewName)) {
                            pirate.assignToPirateCrew(p);
                            break;
                        }
                    }
                    view.getMessageLabel().setText("Successfully assigned to " + crewName + " Pirate Crew!");
                    break;
            }
        }
        else if (selectedType.equals("Marine")) {
            Marine marine = new Marine();
            for (Character c : data.getCharacters()) {
                if (c.getName().equalsIgnoreCase(selectedName)) {
                    marine = (Marine) c;
                    break;
                }
            }

            switch (selectedAction) {
                case "Promote Rank" :
                    String rank = view.getModRank().getValue();
                    try {
                        marine.promoteRank(rank);
                    }
                    catch (LowRankException e) {
                        view.getMessageLabel().setText("New Rank must be higher than the original rank!");
                        return;
                    }
                    view.getMessageLabel().setText("Successfully promoted rank to " + rank + "!");
                    break;
                case "Assign/Modify Corps" :
                    String corpsName = view.getModCorp().getValue();
                    for (MarineCorps m : data.getCorps()) {
                        if (m.getBaseLoc().equalsIgnoreCase(corpsName)) {
                            marine.assignMarineCorps(m);
                            break;
                        }
                    }
                    view.getMessageLabel().setText("Successfully assigned to " + corpsName + " Marine Corps!");
                    break;
            }
        }
        else if (selectedType.equals("Pirate Hunter")) {
            PirateHunter hunter = new PirateHunter();
            for (Character c : data.getCharacters()) {
                if (c.getName().equalsIgnoreCase(selectedName)) {
                    hunter = (PirateHunter) c;
                    break;
                }
            }

            switch (selectedAction) {
                case "Change Combat Style" :
                    String style = view.getModStyle().getText();
                    if (style == null) {
                        view.getMessageLabel().setText("Combat Style cannot be left as blank!");
                        return;
                    }
                    if (style.equals(hunter.getStyle())) {
                        view.getMessageLabel().setText("Please enter a new Combat Style!");
                        return;
                    }
                    hunter.setStyle(style);
                    view.getMessageLabel().setText("Successfully updated Combat Style to " + style + "!");
                    break;
                case "Change Amount of Captures" :
                    int captures;
                    try {
                        captures = Integer.parseInt(view.getModCaptures().getText());
                    }
                    catch (NumberFormatException e) {
                        view.getMessageLabel().setText("Captures must be a valid number.");
                        return;
                    }
                    try {
                        hunter.setCaptures(captures);
                    }
                    catch (NegativeValueException e) {
                        view.getMessageLabel().setText("Captures must not be a negative number.");
                        return;
                    }
                    view.getMessageLabel().setText("Successfully modified amount of captures to " + captures + "!");
                    break;
            }
        }
        else if (selectedType.equals("Civilian")) {
            Civilian civilian = new Civilian();
            for (Character c : data.getCharacters()) {
                if (c.getName().equalsIgnoreCase(selectedName)) {
                    civilian = (Civilian) c;
                    break;
                }
            }

            switch (selectedAction) {
                case "Change Profession" :
                    String profession = view.getModProfession().getText();
                    if (profession == null) {
                        view.getMessageLabel().setText("Profession cannot be left as blank!");
                        return;
                    }
                    if (profession.equals(civilian.getProfession())) {
                        view.getMessageLabel().setText("Please enter a new Profession!");
                        return;
                    }
                    civilian.setProfession(profession);
                    view.getMessageLabel().setText("Successfully updated Profession to " + profession + "!");
                    break;
                case "Change Residence" :
                    String residence = view.getModResidence().getText();
                    if (residence == null) {
                        view.getMessageLabel().setText("Residence cannot be left as blank!");
                        return;
                    }
                    if (residence.equals(civilian.getResidence())) {
                        view.getMessageLabel().setText("Please enter a new Residence!");
                        return;
                    }
                    civilian.setResidence(residence);
                    view.getMessageLabel().setText("Successfully updated Residence to " + residence + "!");
                    break;
            }
        }
    }

    /*
        METHODS FOR CHARACTER DELETION
    */
    private void implementDeleteCharacter(CharacterMenuView view, Main app, SimulationList data) {
        app.getMainStage().setScene(view.deleteCharacterMenu()); // sets the scene of the stage at Main to be that of the Delete Character menu

        view.getTypeBox().setOnAction(e -> characterSelection(view, data));
        view.getCharacterBox().setOnAction(e -> deleteCharacter(view, data));
        view.getBackButton().setOnAction(e -> app.showCharacterMenu(app.getMainStage()));
    }

    private void deleteCharacter(CharacterMenuView view, SimulationList data) {
        String selectedName = view.getCharacterBox().getValue();

        if (selectedName == null) {
            view.getMessageLabel().setText("Please select a character to view!");
            return;
        }

        for (Character c : data.getCharacters()) {
            if (c.getName().equalsIgnoreCase(selectedName)) {
                if (c instanceof Pirate p) {
                    view.getMessageLabel().setText("Pirate " + c.getName() + " has been removed from the simulation!");
                    if (p.getPirateCrew() != null) {
                        p.getPirateCrew().removeCrewMember(p);
                    }
                }
                else if (c instanceof Marine m) {
                    view.getMessageLabel().setText("Marine " + c.getName() + " has been removed from the simulation!");
                    if (m.getCorps() != null) {
                        m.getCorps().dischargeMarine(m);
                    }
                }
                else if (c instanceof PirateHunter) {
                    view.getMessageLabel().setText("Pirate Hunter " + c.getName() + " has been removed from the simulation!");
                }
                else if (c instanceof Civilian) {
                    view.getMessageLabel().setText("Civilian " + c.getName() + " has been removed from the simulation!");
                }

                c.setStatus("Dead");
                if (c.getDevilFruitPower() != null) {
                    c.getDevilFruitPower().triggerReincarnation();
                }
                data.getCharacters().remove(c);
                data.getDeleted().add(c);
                return;
            }
        }
    }
}
