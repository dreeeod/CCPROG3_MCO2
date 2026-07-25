package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CharacterMenuView extends VBox {

    private final Button createButton = new Button("Add/Create New Character");
    private final Button viewButton = new Button("View a Character");
    private final Button modifyButton = new Button("Modify a Character");
    private final Button deleteButton = new Button("Delete a Character");
    private final Button backButton = new Button("Back to Menu");

    public CharacterMenuView() {
        Label title = new Label("Character Customization");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        this.getChildren().addAll(title, createButton, viewButton, modifyButton, deleteButton, backButton);
        this.setSpacing(12);
        this.setPadding(new Insets(20));
        this.setAlignment(Pos.CENTER);
    }

    public Button getCreateButton() { return createButton; }
    public Button getViewButton() { return viewButton; }
    public Button getModifyButton() { return modifyButton; }
    public Button getDeleteButton() { return deleteButton; }
    public Button getBackButton() { return backButton; }
}
