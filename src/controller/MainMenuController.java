package controller;

import app.Main;
import javafx.application.Platform;
import view.MainMenuView;

public class MainMenuController {

    public MainMenuController(MainMenuView view, Main mainApp) {
        view.getCharacterButton().setOnAction(e -> mainApp.showCharacterMenu());
        view.getGroupButton().setOnAction(e -> mainApp.showGroupMenu());
        view.getDevilFruitButton().setOnAction(e -> mainApp.showDevilFruitMenu());
        view.getExitButton().setOnAction(e -> Platform.exit());
    }

}
