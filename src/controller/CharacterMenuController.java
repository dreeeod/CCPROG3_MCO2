package controller;

import app.Main;
import javafx.scene.control.Alert;
import model.SimulationList;
import model.exceptions.DuplicateNameException;
import view.CharacterMenuView;

public class CharacterMenuController {

    public CharacterMenuController(CharacterMenuView view, Main app, SimulationList data) {
        view.getCreateButton().setOnAction(e -> implementCreateCharacter(view, app, data));
        view.getViewButton().setOnAction(e -> notYetImplemented());
        view.getModifyButton().setOnAction(e -> notYetImplemented());
        view.getDeleteButton().setOnAction(e -> notYetImplemented());
        view.getBackButton().setOnAction(e -> app.showMainMenu(app.getMainStage()));
    }

    private void notYetImplemented() {
        new Alert(Alert.AlertType.INFORMATION, "This screen isn't built yet!").showAndWait();
    }

    private void implementCreateCharacter(CharacterMenuView view, Main app, SimulationList data) {
        app.getMainStage().setScene(view.characterCreateMenu()); // sets the scene of the stage at Main to be that of the Character Create menu

        view.getPirateButton().setOnAction(e -> controlPirateCreate(view, app, data));
        view.getMarineButton().setOnAction(e -> controlMarineCreate(view, app, data));
        view.getHunterButton().setOnAction(e -> controlHunterCreate(view, app, data));
        view.getCivilianButton().setOnAction(e -> controlCivilianCreate(view, app, data));
        view.getBackButton().setOnAction(e -> app.showMainMenu(app.getMainStage()));
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
}
