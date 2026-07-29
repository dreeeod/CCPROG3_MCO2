package view;

//for padding around the layout
import javafx.geometry.Insets;
//for alignment of elements
import javafx.geometry.Pos;
//imports Scene
import javafx.scene.Scene;
//imports all Javafx controls
import javafx.scene.control.*;
//Vertical box layout container
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class DevilFruitMenuView {

    //Main Menu for DevilFruit

    //Button to create Devil Fruit screen
    private final Button createButton = new Button("Create Devil Fruit");
    //Button to view Devil Fruit screen
    private final Button viewButton = new Button("View Devil Fruit");
    //Button to assign Devil Fruit screen
    private final Button assignButton = new Button("Assign Devil Fruit to a New User");
    //Button to got back to Main Menu
    private final Button backButton = new Button("Back to Menu");
    //Label that displays feedback messages to the user
    private final Label messageLabel = new Label();

    //Builds and return Main Devil Fruit menu scene
    public Scene mainDevilFruitMenu(){

        //creates a vertical box to stack elements
        VBox layout = new VBox();

        //Title label for the Devil Fruit Customization Menu
        Label title = new Label("Devil Fruit Customization");
        // makes title font bigger and bold
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        //Adds all buttons and message label to the layout
        layout.getChildren().addAll(title, createButton, viewButton, assignButton, backButton, messageLabel);
        //sets space between elements
        layout.setSpacing(12);
        //Sets spacing around the edges of the layout
        layout.setPadding(new Insets(20));
        //Centers all elements horizontally
        layout.setAlignment(Pos.CENTER);

        //Returns the main menu devil fruit scene
        return new Scene(layout);
    }
    //Getters for main menu and label
    public Button getCreateButton(){

        return createButton;
    }

    public Button getViewButton(){

        return viewButton;
    }

    public Button getAssignButton(){

        return assignButton;
    }

    public Button getBackButton(){

        return backButton;
    }

    public Label getMessageLabel(){

        return messageLabel;
    }


    //Create Devil Fruit

    //Text field for the user to type the name of the Devil Fruit
    private final TextField fruitNameField = new TextField();
    //Dropdown for selecting the devil fruit category
    private final ComboBox<String> categoryBox = new ComboBox<>();
    //Text field for the user to type the ability description
    private final TextField abilityField = new TextField();
    //Button to confirm and create the Devil Fruit
    private final Button confirmCreateButton = new Button("Create Devil Fruit");

    //Builds and returns the crete Devil Fruit scene
    public Scene createDevilFruitView(){

        // adds the 3 devil fruit categories to the dropdown
        categoryBox.getItems().setAll("Paramecia", "Zoan", "Logia");
        // placeholder text shown before a selection is made
        categoryBox.setPromptText("Select Category");

        //Stacks all labels, fields, and buttons certically
        VBox layout = new VBox(8, new Label("Create a Devil Fruit"), new Label("Fruit Name:"), fruitNameField, new Label("Category:"), categoryBox, new Label("Ability Description:"), abilityField, confirmCreateButton, backButton, messageLabel);

        //adds padding around layout
        layout.setPadding(new Insets(20));

        //returns the scene for Create Devil Fruit
        return new Scene(layout);
    }
    //Getters for create Devil Fruit fields and buttons
    public TextField getFruitNameField(){

        return fruitNameField;
    }

    public ComboBox<String> getCategoryBox(){

        return categoryBox;
    }

    public TextField getAbilityField(){

        return abilityField;
    }

    public Button getConfirmCreateButton(){

        return confirmCreateButton;
    }


    //View Devil Fruit

    //Dropdown to select which devil fruit to view
    private final ComboBox<String> viewFruitBox = new ComboBox<>();
    //Button to confirm viewing the selected devil fruit
    private final Button confirmViewButton = new Button("View");
    //Label that displays the full profile of the selected Devil Fruit
    private final Label fruitProfileLabel = new Label();

    //Builds and returns the view Devil Fruit scene
    public Scene viewDevilFruitView(){

        // placeholder text before selection
        viewFruitBox.setPromptText("Select Devil Fruit");

        //stacks all elements vertically
        VBox layout = new VBox(8, new Label("View Devil Fruit"), new Label("Devil Fruit:"), viewFruitBox, confirmViewButton, backButton, messageLabel, fruitProfileLabel);

        // adds padding around the layout
        layout.setPadding(new Insets(20));

        //returns View Devil Fruit Scene
        return new Scene(layout);
    }
    //Getters for view Devil Fruit elements
    public ComboBox<String> getViewFruitBox(){

        return viewFruitBox;
    }

    public Button getConfirmViewButton(){

        return confirmViewButton;
    }

    public Label getFruitProfileLabel(){

        return fruitProfileLabel;
    }


    //Assign Devil Fruit

    //Dropdown to select which devil fruit to assign
    private final ComboBox<String> assignDevilFruitBox = new ComboBox<>();
    //Dropdown to select which character type to assign it to
    private final ComboBox<String> assignCharTypeBox = new ComboBox<>();
    //Dropdown to select which specific character to assign it to
    private final ComboBox<String> assignCharBox = new ComboBox<>();
    //Button to confirm the assignment
    private final Button confirmAssignButton = new Button("Assign");

    //Builds and return the assign Devil Fruit scene
    public Scene assignDevilFruitView(){
        // placeholder before fruit is selected
        assignDevilFruitBox.setPromptText("Select Devil Fruit");
        //All character types
        assignCharTypeBox.getItems().setAll("Pirate", "Marine", "Pirate Hunter", "Civilian");
        assignCharTypeBox.setPromptText("Select Character Type");
        assignCharBox.setPromptText("Select Character");

        //Stacks all elements vertically
        VBox layout = new VBox(8, new Label("Assign Devil Fruit to a New User"), new Label("Devil Fruit:"), assignDevilFruitBox, new Label("Character Type:"), assignCharTypeBox, new Label("Character:"), assignCharBox, confirmAssignButton, backButton, messageLabel);

        // adds padding around the layout
        layout.setPadding(new Insets(20));

        //returns assign Devil Fruit scene
        return new Scene(layout);
    }
    //Getters for assign Devil Fruit elements
    public ComboBox<String> getAssignDevilFruitBox(){

        return assignDevilFruitBox;
    }

    public ComboBox<String> getAssignCharTypeBox(){

        return assignCharTypeBox;
    }

    public ComboBox<String> getAssignCharBox(){

        return assignCharBox;
    }

    public Button getConfirmAssignButton(){

        return confirmAssignButton;
    }
}
