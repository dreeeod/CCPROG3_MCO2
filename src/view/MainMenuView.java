package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainMenuView {
    private final Button characterButton = new Button("Character Customization");
    private final Button groupButton = new Button("Group Customization");
    private final Button devilFruitButton = new Button("Devil Fruit Customization");
    private final Button captureButton = new Button("Simulate a Capture");
    private final Button exitButton = new Button("Exit");

    /**
     * Method that returns the appropriate Scene object that shows the Main Menu
     * @return Scene
     */
    public Scene showMainMenu() {
        Scene scene;
        VBox layout = new VBox();
        Label title = new Label("One Piece Simulator");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        layout.getChildren().addAll(title, characterButton, groupButton, devilFruitButton, captureButton, exitButton);
        layout.setSpacing(12);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        scene = new Scene(layout);

        return scene;
    }

    /**
     * Getter for Character Button
     * @return Button
     */
    public Button getCharacterButton() {
        return characterButton;
    }
    /**
     * Getter for Group Button
     * @return Button
     */
    public Button getGroupButton() {
        return groupButton;
    }
    /**
     * Getter for Devil Fruit Button
     * @return Button
     */
    public Button getDevilFruitButton() {
        return devilFruitButton;
    }
    /**
     * Getter for Capture Button
     * @return Button
     */
    public Button getCaptureButton() {
        return captureButton;
    }
    /**
     * Getter for Exit Button
     * @return Button
     */
    public Button getExitButton() {
        return exitButton;
    }
}
