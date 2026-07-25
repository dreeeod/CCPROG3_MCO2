package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PirateCreateView extends VBox {

    private final TextField nameField = new TextField();
    private final TextField aliasField = new TextField();
    private final TextField originField = new TextField();
    private final ComboBox<String> statusBox = new ComboBox<>();
    private final TextField walletField = new TextField();
    private final TextField bountyField = new TextField();
    private final ComboBox<String> roleBox = new ComboBox<>();
    private final Button createButton = new Button("Create Pirate");
    private final Button backButton = new Button("Back");
    private final Label messageLabel = new Label();

    public PirateCreateView() {
        statusBox.getItems().addAll("Alive", "Captured", "Dead");
        roleBox.getItems().addAll("Captain", "First Mate", "Second Mate", "Boatswain", "Cook",
                "Doctor", "Navigator", "Shipwright", "Sniper", "Archaeologist");

        this.getChildren().addAll(
                new Label("Create a Pirate"),
                new Label("Name:"), nameField,
                new Label("Alias:"), aliasField,
                new Label("Origin:"), originField,
                new Label("Status:"), statusBox,
                new Label("Wallet (Berries):"), walletField,
                new Label("Bounty:"), bountyField,
                new Label("Role:"), roleBox,
                createButton, backButton, messageLabel
        );
        this.setSpacing(8);
        this.setPadding(new Insets(20));
    }

    public TextField getNameField() { return nameField; }
    public TextField getAliasField() { return aliasField; }
    public TextField getOriginField() { return originField; }
    public ComboBox<String> getStatusBox() { return statusBox; }
    public TextField getWalletField() { return walletField; }
    public TextField getBountyField() { return bountyField; }
    public ComboBox<String> getRoleBox() { return roleBox; }
    public Button getCreateButton() { return createButton; }
    public Button getBackButton() { return backButton; }
    public Label getMessageLabel() { return messageLabel; }

}
