package controller;

import app.Main;
import javafx.scene.control.Alert;
import view.CharacterProfileMenuView;

public class CharacterProfileController {

    public CharacterProfileController(CharacterProfileMenuView view, Main mainApp) {
        view.getPirateButton().setOnAction(e -> mainApp.showCharacterProfileMenu());
        view.getMarineButton().setOnAction(e -> notYetImplemented());
        view.getHunterButton().setOnAction(e -> notYetImplemented());
        view.getCivilianButton().setOnAction(e -> notYetImplemented());
        view.getBackButton().setOnAction(e -> mainApp.showMainMenu());
    }

    private void notYetImplemented() {
        new Alert(Alert.AlertType.INFORMATION, "This screen isn't built yet!").showAndWait();
    }

}
