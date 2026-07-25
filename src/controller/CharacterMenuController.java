package controller;

import app.Main;
import javafx.scene.control.Alert;
import view.CharacterMenuView;

public class CharacterMenuController {

    public CharacterMenuController(CharacterMenuView view, Main mainApp) {
        view.getCreateButton().setOnAction(e -> notYetImplemented());
        view.getViewButton().setOnAction(e -> notYetImplemented());
        view.getModifyButton().setOnAction(e -> notYetImplemented());
        view.getDeleteButton().setOnAction(e -> notYetImplemented());
        view.getBackButton().setOnAction(e -> mainApp.showMainMenu());
    }

    private void notYetImplemented() {
        new Alert(Alert.AlertType.INFORMATION, "This screen isn't built yet!").showAndWait();
    }
}
