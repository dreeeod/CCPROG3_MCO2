package app;

import controller.*;
import javafx.application.Application;
import javafx.stage.Stage;
import model.SimulationList;
import view.*;

public class Main extends Application {
    private Stage mainStage;
    private SimulationList sim = new SimulationList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        mainStage = stage;
        showMainMenu(mainStage);
        mainStage.show();
    }

    public void showMainMenu(Stage stage) {
        MainMenuView view = new MainMenuView();
        new MainMenuController(view, this);
        stage.setScene(view.showMainMenu());
        stage.setWidth(1200);
        stage.setHeight(800);
    }

    public void showCharacterMenu(Stage stage) {
        CharacterMenuView view = new CharacterMenuView();
        new CharacterMenuController(view, this, sim);
        stage.setScene(view.mainCharacterMenu());
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public void showGroupMenu(Stage stage){

        GroupMenuView view = new GroupMenuView();

        new GroupMenuController(view, this, sim);

        stage.setScene(view.mainGroupMenu());
    }

}
