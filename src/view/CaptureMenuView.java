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
import javafx.scene.layout.VBox;

public class CaptureMenuView {

    //Main Menu for Capture

    //Button to go to perform capture screen
    private final Button performCaptureButton = new Button("Perform Capture");
    //Button to view capture log
    private final Button viewCaptureLogButton = new Button("View Capture Log");
    //Back button
    private final Button backButton = new Button("Back to Menu");
    //Label that displays the feedback messages to the user
    private final Label messageLabel = new Label();

    //Builds and returns the main capture menu scene
    public Scene mainCaptureMenu(){

        //creates a vertical box to stack elements
        VBox layout = new VBox();

        //Title label for the Capture Simulation Menu
        Label title = new Label("Simulate a Capture");

        //makes title font bigger and bold
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        //Adds all buttons and message label to the layout
        layout.getChildren().addAll(title, performCaptureButton, viewCaptureLogButton, backButton, messageLabel);

        //sets space between elements
        layout.setSpacing(12);

        //sets spacing around the edges of the layout
        layout.setPadding(new Insets(20));

        //centers all elements horizontally
        layout.setAlignment(Pos.CENTER);

        return new Scene(layout);
    }
    //Getter for main menu buttons and labels

    public Button getPerformCaptureButton(){

        return performCaptureButton;
    }

    public Button getViewCaptureLogButton(){

        return viewCaptureLogButton;
    }

    public Button getBackButton(){

        return backButton;
    }

    public Label getMessageLabel(){

        return messageLabel;
    }


    //Perform Capture

    //Dropdown choices for selecting the Pirate to be Captures
    private final ComboBox<String> targetBox = new ComboBox<>();
    //Dropdown choices for group type of the captor
    private final ComboBox<String> captorTypeBox = new ComboBox<>();
    //Dropdown choices for specific captor
    private final ComboBox<String> captorBox = new ComboBox<>();
    //Dropdown box for the state of the captured pirate
    private final ComboBox<String> capturedStateBox = new ComboBox<>();
    //Button to confirm capture
    private final Button confirmCaptureButton = new Button("Confirm Capture");

    //Builds and returns the perform capture Scene
    public Scene performCaptureView(){

        //placeholder text shown before a selection is made
        targetBox.setPromptText("Select Pirate to Capture");
        //
        captorTypeBox.getItems().setAll("Marine", "Pirate Hunter", "Civilian");
        captorTypeBox.setPromptText("Select a Captor Type");

        captorBox.setPromptText("Select Captor");

        //the two possible outcomes of a capture
        capturedStateBox.getItems().setAll("Alive", "Dead");
        capturedStateBox.setPromptText("Select a Capture State");

        //stacks all labels, dropdowns, and buttons vertically
        VBox layout = new VBox(8, new Label("Perform a Capture"), new Label("Pirate to Capture:"), targetBox, new Label("Captor Type:"), captorTypeBox, new Label("Captor:"), captorBox, new Label("Capture State:"), capturedStateBox, confirmCaptureButton, backButton, messageLabel);

        layout.setPadding(new Insets(20));

        return new Scene(layout);
    }
    //Getters for perform Capture elements

    public ComboBox<String> getTargetBox(){

        return targetBox;
    }

    public ComboBox<String> getCaptorTypeBox(){

        return captorTypeBox;
    }

    public ComboBox<String> getCaptorBox(){

        return captorBox;
    }

    public ComboBox<String> getCapturedStateBox(){

        return capturedStateBox;
    }

    public Button getConfirmCaptureButton(){

        return confirmCaptureButton;
    }


    //View Capture Log

    //Dropdown to select which logged capture to view
    private final ComboBox<String> logBox = new ComboBox<>();
    //Button to confirm viewing of the capture log
    private final Button confirmViewLogButton = new Button("View");
    //Label that displays the full details of the selected capture
    private final Label captureDetailLabel = new Label();

    //Builds and returns the view capture log scene
    public Scene viewCaptureLogView(){

        logBox.setPromptText("Select a Logged Capture");

        VBox layout = new VBox(8, new Label("Capture Log"), new Label("Logged Captures:"), logBox, confirmViewLogButton, backButton, messageLabel, captureDetailLabel);

        layout.setPadding(new Insets(20));

        return new Scene(layout);
    }
    //Getters for view capture log elements
    public ComboBox<String> getLogBox(){

        return logBox;
    }

    public Button getConfirmViewLogButton(){

        return confirmViewLogButton;
    }

    public Label getCaptureDetailLabel(){

        return captureDetailLabel;
    }
}
