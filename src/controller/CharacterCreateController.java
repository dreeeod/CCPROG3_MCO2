package controller;

import app.Main;
import javafx.scene.control.Alert;
import view.CharacterCreateMenuView;

public class CharacterCreateController {

    public CharacterCreateController(CharacterCreateMenuView view, Main mainApp) {
        view.getPirateButton().setOnAction(e -> notYetImplemented());
        view.getMarineButton().setOnAction(e -> notYetImplemented());
        view.getHunterButton().setOnAction(e -> notYetImplemented());
        view.getCivilianButton().setOnAction(e -> notYetImplemented());
        view.getBackButton().setOnAction(e -> mainApp.showMainMenu());
    }

    private void notYetImplemented() {
        new Alert(Alert.AlertType.INFORMATION, "This screen isn't built yet!").showAndWait();
    }

}
