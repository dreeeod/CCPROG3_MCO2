package app;

import controller.*;
import javafx.application.Application;
import javafx.stage.Stage;
import model.SimulationList;
import view.*;

public class Main extends Application {
    private Stage mainStage;
    private static SimulationList sim = new SimulationList();

    public static void main(String[] args) {
        sim.loadData();
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
        new MainMenuController(view, this, sim);
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

    public void showDevilFruitMenu(Stage stage){

        DevilFruitMenuView view = new DevilFruitMenuView();
        new DevilFruitMenuController(view, this, sim);
        stage.setScene(view.mainDevilFruitMenu());
    }

    public void showCaptureMenu(Stage stage){

        CaptureMenuView view = new CaptureMenuView();
        new CaptureMenuController(view, this, sim);
        stage.setScene(view.mainCaptureMenu());
    }

}
