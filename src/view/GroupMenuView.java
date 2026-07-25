package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class GroupMenuView extends VBox {

    private final Button createButton = new Button("Create a Group");
    private final Button viewButton = new Button("View a Group");
    private final Button modifyButton = new Button("Edit a Group");
    private final Button addButton = new Button("Add Members");
    private final Button removeButton = new Button("Remove Members");
    private final Button backButton = new Button("Back to Menu");

    public GroupMenuView() {
        Label title = new Label("Group Customization");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        this.getChildren().addAll(title, createButton, viewButton, modifyButton, addButton, removeButton, backButton);
        this.setSpacing(12);
        this.setPadding(new Insets(20));
        this.setAlignment(Pos.CENTER);
    }

    public Button getCreateButton() { return createButton; }
    public Button getViewButton() { return viewButton; }
    public Button getModifyButton() { return modifyButton; }
    public Button getAddButton() { return addButton; }
    public Button getRemoveButton() { return removeButton; }
    public Button getBackButton() { return backButton; }
}
