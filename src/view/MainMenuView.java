package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainMenuView extends VBox {
    private final Button characterButton = new Button("Character Customization");
    private final Button groupButton = new Button("Group Customization");
    private final Button devilFruitButton = new Button("Devil Fruit Customization");
    private final Button exitButton = new Button("Exit");

    public MainMenuView() {
        Label title = new Label("One Piece Simulator");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        this.getChildren().addAll(title, characterButton, groupButton, devilFruitButton, exitButton);
        this.setSpacing(12);
        this.setPadding(new Insets(20));
        this.setAlignment(Pos.CENTER);
    }

    public Button getCharacterButton() { return characterButton; }
    public Button getGroupButton() { return groupButton; }
    public Button getDevilFruitButton() { return devilFruitButton; }
    public Button getExitButton() { return exitButton; }

}
