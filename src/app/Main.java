package app;

import controller.*;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.SimulationList;
import view.*;

public class Main extends Application {
    private Stage stage;
    private SimulationList sim = new SimulationList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("One Piece Simulator");
        showMainMenu();
        stage.show();
    }

    public void showMainMenu() {
        MainMenuView view = new MainMenuView();
        new MainMenuController(view, this);
        setRoot(view);
    }

    // CHARACTER MENUS
    public void showCharacterMenu() {
        CharacterMenuView view = new CharacterMenuView();
        new CharacterMenuController(view, this);
        setRoot(view);
    }
    public void showCharacterCreateMenu() {
        CharacterCreateMenuView view = new CharacterCreateMenuView();
        new CharacterCreateController(view, this);
        setRoot(view);
    }
    public void showPirateCreateMenu() {
        PirateCreateView view = new PirateCreateView();
        PirateCreateController controller = new PirateCreateController(view, this, sim);
        controller.create(view, sim);
        setRoot(view);
    }


    // GROUP MENUS
    public void showGroupMenu() {
        GroupMenuView view = new GroupMenuView();
        new GroupMenuController(view, this);
        setRoot(view);
    }

    // DEVIL FRUIT MENUS
    public void showDevilFruitMenu() {
        DevilFruitMenuView view = new DevilFruitMenuView();
        new DevilFruitMenuController(view, this);
        setRoot(view);
    }

    private void setRoot(Parent root) {
        if (stage.getScene() == null) {
            stage.setScene(new Scene(root, 500, 400));
        } else {
            stage.getScene().setRoot(root);
        }
    }

}
