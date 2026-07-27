package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CharacterProfileMenuView extends VBox {

    private final Button pirateButton = new Button("View a Pirate");
    private final Button marineButton = new Button("View a Marine");
    private final Button hunterButton = new Button("View a Pirate Hunter");
    private final Button civilianButton = new Button("View a Civilian");
    private final Button backButton = new Button("Back to Menu");

    public CharacterProfileMenuView() {
        Label title = new Label("View a Character!");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        this.getChildren().addAll(title, pirateButton, marineButton, hunterButton, civilianButton, backButton);
        this.setSpacing(12);
        this.setPadding(new Insets(20));
        this.setAlignment(Pos.CENTER);
    }

    public Button getPirateButton() { return pirateButton; }
    public Button getMarineButton() { return marineButton; }
    public Button getHunterButton() { return hunterButton; }
    public Button getCivilianButton() { return civilianButton; }
    public Button getBackButton() { return backButton; }

}
