package controller;

import app.Main;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import view.MainMenuView;

public class MainMenuController {

    /**
     * Constructor of MainMenuController that controls that button's actions in MainMenuView
     * @param view MainMenuView object used to access the buttons
     * @param app Main object from Main class used to access the Stage
     */
    public MainMenuController(MainMenuView view, Main app) {
        view.getCharacterButton().setOnAction(e -> app.showCharacterMenu(app.getMainStage()));
        view.getGroupButton().setOnAction(e -> notYetImplemented());
        view.getDevilFruitButton().setOnAction(e -> notYetImplemented());
        view.getCaptureButton().setOnAction(e -> notYetImplemented());
        view.getExitButton().setOnAction(e -> Platform.exit());
    }

    private void notYetImplemented() {
        new Alert(Alert.AlertType.INFORMATION, "This screen isn't built yet!").showAndWait();
    }

}
