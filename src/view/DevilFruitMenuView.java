package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DevilFruitMenuView extends VBox {

    private final Button createButton = new Button("Create a Devil Fruit");
    private final Button viewButton = new Button("View a Devil Fruit");
    private final Button assignButton = new Button("Assign a Devil Fruit");
    private final Button backButton = new Button("Back to Menu");

    public DevilFruitMenuView() {
        Label title = new Label("Devil Fruit Customization");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        this.getChildren().addAll(title, createButton, viewButton, assignButton, backButton);
        this.setSpacing(12);
        this.setPadding(new Insets(20));
        this.setAlignment(Pos.CENTER);
    }

    public Button getCreateButton() { return createButton; }
    public Button getViewButton() { return viewButton; }
    public Button getAssignButton() { return assignButton; }
    public Button getBackButton() { return backButton; }

}
