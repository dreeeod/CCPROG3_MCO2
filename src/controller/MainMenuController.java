package controller;

import app.Main;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import view.MainMenuView;

public class MainMenuController {

    public MainMenuController(MainMenuView view, Main app) {
        view.getCharacterButton().setOnAction(e -> app.showCharacterMenu(app.getMainStage()));
        view.getGroupButton().setOnAction(e -> app.showGroupMenu(app.getMainStage()));
        view.getDevilFruitButton().setOnAction(e -> notYetImplemented());
        view.getExitButton().setOnAction(e -> Platform.exit());
    }

    private void notYetImplemented() {
        new Alert(Alert.AlertType.INFORMATION, "This screen isn't built yet!").showAndWait();
    }

}
