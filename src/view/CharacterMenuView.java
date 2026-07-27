package view;

import app.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.PirateCrew;
import model.SimulationList;

public class CharacterMenuView {

    // Main Character Menu Attributes/Methods
    private final Button createButton = new Button("Add/Create New Character");
    private final Button viewButton = new Button("View a Character");
    private final Button modifyButton = new Button("Modify a Character");
    private final Button deleteButton = new Button("Delete a Character");
    private final Button backButton = new Button("Back to Menu"); // can be reused for other menus

    public Scene mainCharacterMenu() {
        Scene scene;
        VBox layout = new VBox();
        Label title = new Label("Character Customization");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        layout.getChildren().addAll(title, createButton, viewButton, modifyButton, deleteButton, backButton);
        layout.setSpacing(12);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        scene = new Scene(layout);

        return scene;
    }

    public Button getCreateButton() {
        return createButton;
    }
    public Button getViewButton() {
        return viewButton;
    }
    public Button getModifyButton() {
        return modifyButton;
    }
    public Button getDeleteButton() {
        return deleteButton;
    }
    public Button getBackButton() {
        return backButton;
    }

    /*
        METHODS RELATED TO CHARACTER CREATION
    */
    // Create a Character Menu Attributes/Methods
    private final Button pirateButton = new Button("Create a Pirate");
    private final Button marineButton = new Button("Create a Marine");
    private final Button hunterButton = new Button("Create a Pirate Hunter");
    private final Button civilianButton = new Button("Create a Civilian");

    public Scene characterCreateMenu() {
        Scene scene;
        VBox layout = new VBox();
        Label title = new Label("Create a Character");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        layout.getChildren().addAll(title, pirateButton, marineButton, hunterButton, civilianButton, backButton);
        layout.setSpacing(12);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        scene = new Scene(layout);

        return scene;
    }

    public Button getPirateButton() {
        return pirateButton;
    }
    public Button getMarineButton() {
        return marineButton;
    }
    public Button getHunterButton() {
        return hunterButton;
    }
    public Button getCivilianButton() {
        return civilianButton;
    }

    // Universal Character Attributes for GUI
    private final TextField nameField = new TextField();
    private final TextField aliasField = new TextField();
    private final TextField originField = new TextField();
    private final ComboBox<String> statusBox = new ComboBox<>();
    private final TextField walletField = new TextField();
    private final Label messageLabel = new Label();

    public TextField getNameField() { return nameField; }
    public TextField getAliasField() { return aliasField; }
    public TextField getOriginField() { return originField; }
    public ComboBox<String> getStatusBox() { return statusBox; }
    public TextField getWalletField() { return walletField; }
    public Button getCharBackButton() { return backButton; }
    public Label getMessageLabel() { return messageLabel; }

    // Pirate Creation Specific GUI
    private final TextField bountyField = new TextField();
    private final ComboBox<String> roleBox = new ComboBox<>();
    private final Button createPirateButton = new Button("Create Pirate");

    public Scene createPirateView() {
        Scene scene;
        VBox layout = new VBox();

        statusBox.getItems().addAll("Alive", "Captured", "Dead");
        roleBox.getItems().addAll("Captain", "First Mate", "Second Mate", "Boatswain", "Cook",
                "Doctor", "Navigator", "Shipwright", "Sniper", "Archaeologist");

        layout.getChildren().addAll(
                new Label("Create a Pirate"),
                new Label("Name:"), nameField,
                new Label("Alias:"), aliasField,
                new Label("Origin:"), originField,
                new Label("Status:"), statusBox,
                new Label("Wallet (Berries):"), walletField,
                new Label("Bounty:"), bountyField,
                new Label("Role:"), roleBox,
                createPirateButton, backButton, messageLabel
        );
        layout.setSpacing(8);
        layout.setPadding(new Insets(20));

        scene = new Scene(layout);

        return scene;
    }

    public TextField getBountyField() { return bountyField; }
    public ComboBox<String> getRoleBox() { return roleBox; }
    public Button getCreatePirateButton() { return createPirateButton; }


    // Marine Creation Specific GUI
    private final ComboBox<String> rankBox = new ComboBox<>();
    private final Button createMarineButton = new Button("Create Marine");

    public Scene createMarineView() {
        Scene scene;
        VBox layout = new VBox();

        statusBox.getItems().addAll("Alive", "Captured", "Dead");
        rankBox.getItems().addAll("Fleet Admiral", "Admiral", "Vice-Admiral", "Rear Admiral", "Commodore", "Captain",
                "Commander", "Ensign", "Warrant Officer", "Petty Officer", "Seaman", "Chore Boy");

        layout.getChildren().addAll(
                new Label("Create a Marine"),
                new Label("Name:"), nameField,
                new Label("Alias:"), aliasField,
                new Label("Origin:"), originField,
                new Label("Status:"), statusBox,
                new Label("Wallet (Berries):"), walletField,
                new Label("Rank:"), rankBox,
                createMarineButton, backButton, messageLabel
        );
        layout.setSpacing(8);
        layout.setPadding(new Insets(20));

        scene = new Scene(layout);

        return scene;
    }

    public ComboBox<String> getRankBox() { return rankBox; }
    public Button getCreateMarineButton() { return createMarineButton; }

    // Pirate Hunter Create Specific GUI
    private final TextField styleField = new TextField();
    private final TextField capturesField = new TextField();
    private final Button createHunterButton = new Button("Create Pirate Hunter");

    public Scene createHunterView() {
        Scene scene;
        VBox layout = new VBox();

        statusBox.getItems().addAll("Alive", "Captured", "Dead");

        layout.getChildren().addAll(
                new Label("Create a Pirate Hunter"),
                new Label("Name:"), nameField,
                new Label("Alias:"), aliasField,
                new Label("Origin:"), originField,
                new Label("Status:"), statusBox,
                new Label("Wallet (Berries):"), walletField,
                new Label("Fighting Style:"), styleField,
                new Label("Number of Captures:"), capturesField,
                createHunterButton, backButton, messageLabel
        );
        layout.setSpacing(8);
        layout.setPadding(new Insets(20));

        scene = new Scene(layout);

        return scene;
    }

    public TextField getStyleField() { return styleField; }
    public TextField getCapturesField() { return capturesField; }
    public Button getCreateHunterButton() { return createHunterButton; }

    // Civilian Create Specific GUI
    private final TextField professionField = new TextField();
    private final TextField residenceField = new TextField();
    private final Button createCivilianButton = new Button("Create Civilian");

    public Scene createCivilianView() {
        Scene scene;
        VBox layout = new VBox();

        statusBox.getItems().addAll("Alive", "Captured", "Dead");

        layout.getChildren().addAll(
                new Label("Create a Civlian"),
                new Label("Name:"), nameField,
                new Label("Alias:"), aliasField,
                new Label("Origin:"), originField,
                new Label("Status:"), statusBox,
                new Label("Wallet (Berries):"), walletField,
                new Label("Profession:"), professionField,
                new Label("Residence:"), residenceField,
                createCivilianButton, backButton, messageLabel
        );
        layout.setSpacing(8);
        layout.setPadding(new Insets(20));

        scene = new Scene(layout);

        return scene;
    }

    public TextField getProfessionField() { return professionField; }
    public TextField getResidenceField() { return residenceField; }
    public Button getCreateCivilianButton() { return  createCivilianButton; }

    /*
        METHODS RELATED TO CHARACTER VIEWING
    */
    // View Character Specific GUI
    private final ComboBox<String> typeBox = new ComboBox<>();
    private final ComboBox<String> characterBox = new ComboBox<>();
    private final Label profileLabel = new Label();

    public Scene viewCharacterMenu() {
        Scene scene;
        VBox layout = new VBox();

        typeBox.getItems().addAll("Pirate", "Marine", "Pirate Hunter", "Civilian");
        typeBox.setPromptText("Select Character Type to View");
        characterBox.setPromptText("Select Character");

        layout.getChildren().addAll(
                new Label("View a Character"),
                new Label("Character Type:"), typeBox,
                new Label("Character:"), characterBox,
                backButton, messageLabel,
                profileLabel
        );
        layout.setSpacing(8);
        layout.setPadding(new Insets(20));

        scene = new Scene(layout);

        return scene;
    }

    public ComboBox<String> getTypeBox() { return typeBox; }
    public ComboBox<String> getCharacterBox() { return characterBox; }
    public Label getProfileLabel() { return profileLabel; }

    /*
        METHODS RELATED TO CHARACTER MODIFICATION
    */
    // Modify Character Specific GUI
    private final Button modifyCharacterButton = new Button("Modify Character");

    public Scene modifyCharacterMenu() {
        Scene scene;
        VBox layout = new VBox();

        typeBox.getItems().addAll("Pirate", "Marine", "Pirate Hunter", "Civilian");
        typeBox.setPromptText("Select Character Type to Modify");
        characterBox.setPromptText("Select Character");

        layout.getChildren().addAll(
                new Label("Modify a Character"),
                new Label("Character Type:"), typeBox,
                new Label("Character:"), characterBox,
                modifyCharacterButton, backButton, messageLabel
        );
        layout.setSpacing(8);
        layout.setPadding(new Insets(20));

        scene = new Scene(layout);

        return scene;
    }

    public Button getModifyCharacterButton() { return modifyCharacterButton; }

    //Modify Pirate Specific GUI
    public ComboBox<String> actionBox = new ComboBox<>();
    public Label modLabel = new Label();
    public TextField modBounty = new TextField();
    public ComboBox<String> modCrew = new ComboBox<>();

    public void modifyPirateView(Main app, SimulationList data) {
        VBox layout = (VBox) app.getMainStage().getScene().getRoot();
        actionBox.getItems().addAll("Assign/Modify Bounty", "Assign/Modify Crew");
        actionBox.setPromptText("Choose Action");

        layout.getChildren().addAll(actionBox);

        actionBox.setOnAction(e -> {
            if (actionBox.getValue().equals("Assign/Modify Bounty")) {
                layout.getChildren().remove(modCrew);
                modLabel.setText("Input valid bounty");
                layout.getChildren().addAll(modLabel, modBounty);
            }
            else if (actionBox.getValue().equals("Assign/Modify Crew")) {
                layout.getChildren().remove(modBounty);
                layout.getChildren().remove(modLabel);
                modCrew.setPromptText("Choose Pirate Crew");
                for (PirateCrew c : data.getCrews()) {
                    modCrew.getItems().add(c.getCrewName());
                }
                layout.getChildren().addAll(modCrew);
            }
        });
    }

    /*
        METHODS RELATED TO CHARACTER DELETION
    */

    public Scene deleteCharacterMenu() {
        Scene scene;
        VBox layout = new VBox();

        typeBox.getItems().addAll("Pirate", "Marine", "Pirate Hunter", "Civilian");
        typeBox.setPromptText("Select Character Type to Delete");
        characterBox.setPromptText("Select Character");

        layout.getChildren().addAll(
                new Label("Delete a Character"),
                new Label("Character Type:"), typeBox,
                new Label("Character:"), characterBox,
                backButton, messageLabel
        );
        layout.setSpacing(8);
        layout.setPadding(new Insets(20));

        scene = new Scene(layout);

        return scene;
    }

}
