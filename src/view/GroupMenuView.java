package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;


public class GroupMenuView {

    //Main Group Menu Buttons
    private final Button createButton = new Button("Create Pirate Crew / Marine Corps");
    private final Button viewButton = new Button("View Groups");
    private final Button modifyButton = new Button("Modify Group Attributes");
    private final Button addButton = new Button("Add Members to a Group");
    private final Button removeButton = new Button("Remove Members from a Group");
    private final Button backButton = new Button("Back to Menu");

    //Shared
    private final Label  messageLabel = new Label();

    public Scene mainGroupMenu(){

        //Vertical Box a layout container that stacks all its children virtually from top to bottom
        VBox layout = new VBox();

        //Creates the text that displays the title
        Label title = new Label("Group Customization");

        //Sets the font styles
        //18 pixels tall and makes the text bold
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        //list of child nodes inside the VBox
        layout.getChildren().addAll(title, createButton, viewButton, modifyButton, addButton, removeButton, backButton);
        //12 spaces of vertical gap from each child element
        layout.setSpacing(12);
        layout.setPadding(new Insets(20));
        //Centers all the children/buttons within the VBox
        layout.setAlignment(Pos.CENTER);

        //Returns the Group Customization menu Scene
        return new Scene(layout);

    }

    //Getters
    public Button getCreateButton(){

        return createButton;
    }

    public Button getViewButton(){

        return viewButton;
    }

    public Button getModifyButton(){

        return modifyButton;
    }

    public Button getAddButton(){

        return addButton;
    }

    public Button getRemoveButton(){

        return removeButton;
    }

    public Button getBackButton(){

        return backButton;
    }

    public Label getMessageLabel(){

        return messageLabel;
    }

    //Create Group Input Fields
    private final ComboBox<String> groupTypeBox = new ComboBox<>();
    private final TextField crewNameField = new TextField();
    private final TextField shipNameField = new TextField();
    private final ComboBox<String> captainBox = new ComboBox<>();
    private final TextField baseLocField = new TextField();
    private final ComboBox<String> commanderBox = new ComboBox<>();
    private final TextField fundsField = new TextField();
    private final Button confirmCreate = new Button("Create");


    private final VBox pirateCrewFields  = new VBox(6);
    private final VBox marineCorpsFields = new VBox(6);

    //Create Group Scene
    public Scene createGroupView(){

        //Setting choices for the dropdown
        groupTypeBox.getItems().setAll("Pirate Crew", "Marine Corps");
        groupTypeBox.setPromptText("Select Group Type");
        captainBox.setPromptText("Select Captain (optional)");
        commanderBox.setPromptText("Select Commander (optional)");

        pirateCrewFields.getChildren().addAll(new Label("Crew Name:"), crewNameField, new Label("Ship Name:"), shipNameField, new Label("Captain:"), captainBox);

        //Hides the pirate crew fields visually
        pirateCrewFields.setVisible(false);
        pirateCrewFields.setManaged(false);

        marineCorpsFields.getChildren().addAll(new Label("Base Location:"), baseLocField, new Label("Commander:"), commanderBox, new Label("Operational Funds:"), fundsField);

        //Hides marine corps fields visually
        marineCorpsFields.setVisible(false);
        marineCorpsFields.setManaged(false);

        // Runs every time the user changes the dropdown selection
        groupTypeBox.setOnAction(e ->{
            //True if "Pirate Crew" is selected, false otherwise
            boolean isPirate = "Pirate Crew".equals(groupTypeBox.getValue());
            //shows pirate fields if pirate crew is selected
            pirateCrewFields.setVisible(isPirate);
            pirateCrewFields.setManaged(isPirate);
            //shows marine fields if pirate crew is not selected
            marineCorpsFields.setVisible(!isPirate);
            marineCorpsFields.setManaged(!isPirate);
        });

        VBox layout = new VBox(8, new Label("Create a Group"), new Label("Group Type:"), groupTypeBox, pirateCrewFields, marineCorpsFields, confirmCreate, backButton, messageLabel);

        layout.setPadding(new Insets(20));

        return new Scene(layout);

    }

    //Create group Getters
    public ComboBox<String> getGroupTypeBox(){

        return groupTypeBox;
    }

    public TextField getCrewNameField(){

        return crewNameField;
    }

    public TextField getShipNameField(){

        return shipNameField;
    }

    public ComboBox<String> getCaptainBox(){

        return captainBox;
    }

    public TextField getBaseLocField(){

        return baseLocField;
    }

    public ComboBox<String> getCommanderBox(){

        return commanderBox;
    }

    public TextField getFundsField(){

        return fundsField;
    }

    public Button getConfirmCreate(){

        return confirmCreate;
    }


    //View Group
    private final ComboBox<String> viewGroupTypeBox = new ComboBox<>();
    private final ComboBox<String> viewGroupBox = new ComboBox<>();
    private final Button viewGroupButton = new Button("View");
    private final Label groupProfileLabel = new Label();

    public Scene viewGroupView(){

        viewGroupTypeBox.getItems().setAll("Pirate Crew", "Marine Corps");
        viewGroupTypeBox.setPromptText("Select Group Type");
        viewGroupBox.setPromptText("Select Group");

        VBox layout = new VBox(8, new Label("View Groups"), new Label("Group Type:"), viewGroupTypeBox, new Label("Group:"), viewGroupBox, viewGroupButton, backButton, messageLabel, groupProfileLabel);

        layout.setPadding(new Insets(20));

        return new Scene(layout);

    }

    //View Group Getters
    public ComboBox<String> getViewGroupTypeBox(){

        return viewGroupTypeBox;
    }

    public ComboBox<String> getViewGroupBox(){

        return viewGroupBox;
    }

    public Button getViewGroupButton(){

        return viewGroupButton;
    }

    public Label getGroupProfileLabel(){

        return groupProfileLabel;
    }

    //Edit Group
    private final ComboBox<String> editGroupTypeBox = new ComboBox<>();
    private final ComboBox<String> editGroupBox = new ComboBox<>();
    private final ComboBox<String> editAttributeBox = new ComboBox<>();
    private final TextField editValueField = new TextField();
    private final ComboBox<String> editCommanderBox = new ComboBox<>();
    private final ComboBox<String> editCaptainBox = new ComboBox<>();
    private final Button confirmEdit = new Button("Save Changes");

    //Edit group Scene
    public Scene editGroupView(){

        editGroupTypeBox.getItems().setAll("Pirate Crew", "Marine Corps");
        editGroupTypeBox.setPromptText("Select a Group Type");
        editGroupBox.setPromptText("Select Group");
        editAttributeBox.setPromptText("Select Field to Edit");
        editValueField.setPromptText("New value....");
        editCommanderBox.setPromptText("Select a new Commander");
        editCaptainBox.setPromptText("Select a new Captain");

        editCommanderBox.setVisible(false);
        editCommanderBox.setManaged(false);

        editCaptainBox.setVisible(false);
        editCaptainBox.setManaged(false);

        VBox layout = new VBox(8, new Label("Edit Group Attributes"), new Label("Group Type:"), editGroupTypeBox, new Label("Group:"), editGroupBox, new Label("Attribute:"), editAttributeBox, editValueField, editCommanderBox, editCaptainBox, confirmEdit, backButton, messageLabel);

        layout.setPadding(new Insets(20));

        return new Scene(layout);

    }

    //Edit Group Getters
    public ComboBox<String> getEditGroupTypeBox(){

        return editGroupTypeBox;
    }

    public ComboBox<String> getEditGroupBox(){

        return editGroupBox;
    }

    public ComboBox<String> getEditAttributeBox(){

        return editAttributeBox;
    }

    public TextField getEditValueAttribute(){

        return editValueField;
    }

    public ComboBox<String> getEditCommanderBox(){

        return editCommanderBox;
    }

    public ComboBox<String> getEditCaptainBox(){

        return editCaptainBox;
    }

    public Button getConfirmEdit() {

        return confirmEdit;
    }

    //Add Members
    private final ComboBox<String> addGroupTypeBox = new ComboBox<>();
    private final ComboBox<String> addGroupBox = new ComboBox<>();
    private final ComboBox<String> addMemberBox = new ComboBox<>();
    private final Button confirmAdd = new Button("Add Member");

    //Add Members Scene
    public Scene addMembersView(){

        addGroupTypeBox.getItems().setAll("Pirate Crew", "Marine Corps");
        addGroupTypeBox.setPromptText("Select Group Type");
        addGroupBox.setPromptText("Select Group");
        addMemberBox.setPromptText("Select Member to Add");

        VBox layout = new VBox(8, new Label("Add Members"), new Label("Group Type"), addGroupTypeBox, new Label("Group:"), addGroupBox, new Label("Member:"), addMemberBox, confirmAdd, backButton, messageLabel);

        layout.setPadding(new Insets(20));

        return new Scene(layout);
    }

    //Add Members getters
    public ComboBox<String> getAddGroupTypeBox(){

        return addGroupTypeBox;
    }

    public ComboBox<String> getAddGroupBox(){

        return addGroupBox;
    }

    public ComboBox<String> getAddMemberBox(){

        return addMemberBox;
    }

    public Button getConfirmAdd(){

        return confirmAdd;
    }

    //Remove Members
    private final ComboBox<String> removeGroupTypeBox = new ComboBox<>();
    private final ComboBox<String> removeGroupBox = new ComboBox<>();
    private final ComboBox<String> removeMemberBox = new ComboBox<>();
    private final Button confirmRemove = new Button("Remove Member");

    //Remove Members Scene
    public Scene removeMembersView(){

        removeGroupTypeBox.getItems().setAll("Pirate Crew", "Marine Corps");
        removeGroupTypeBox.setPromptText("Select Group Type");
        removeGroupBox.setPromptText("Select Group");
        removeMemberBox.setPromptText("Select Member to Remove");

        VBox layout = new VBox(8, new Label("Remove Members"), new Label("Group Type:"), removeGroupTypeBox, new Label("Group:"), removeGroupBox, new Label("Member"), removeMemberBox, confirmRemove, backButton, messageLabel);

        layout.setPadding(new Insets(20));

        return new Scene(layout);

    }

    //Remove Member Getters
    public ComboBox<String> getRemoveGroupTypeBox(){

        return removeGroupTypeBox;
    }

    public ComboBox<String> getRemoveGroupBox(){

        return removeGroupBox;
    }

    public ComboBox<String> getRemoveMemberBox(){

        return removeMemberBox;
    }

    public Button getConfirmRemove(){

        return confirmRemove;
    }
}
