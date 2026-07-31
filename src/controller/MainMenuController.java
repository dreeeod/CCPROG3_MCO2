package controller;

import app.Main;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import model.SimulationList;
import view.MainMenuView;

public class MainMenuController {

    /**
     * Constructor of MainMenuController that controls that button's actions in MainMenuView
     * @param view MainMenuView object used to access the buttons
     * @param app Main object from Main class used to access the Stage
     */
    public MainMenuController(MainMenuView view, Main app, SimulationList data) {
        view.getCharacterButton().setOnAction(e -> app.showCharacterMenu(app.getMainStage()));
        view.getGroupButton().setOnAction(e -> app.showGroupMenu(app.getMainStage()));
        view.getDevilFruitButton().setOnAction(e -> app.showDevilFruitMenu(app.getMainStage()));
        view.getCaptureButton().setOnAction(e -> app.showCaptureMenu(app.getMainStage()));
        view.getExitButton().setOnAction(e -> data.saveData());
    }

    private void notYetImplemented() {
        new Alert(Alert.AlertType.INFORMATION, "This screen isn't built yet!").showAndWait();
    }

}
