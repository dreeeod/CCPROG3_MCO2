package controller;

//imports Main to allow scene switching
import app.Main;
//imports all model classes
import model.*;
import model.Character;
//imports model exceptions thrown while processing a capture
import model.exceptions.IllegalCaptorException;
//imports capture view class that this controller manages
import view.CaptureMenuView;

import java.util.StringJoiner;
import java.util.concurrent.ThreadLocalRandom;

public class CaptureMenuController {

    public CaptureMenuController(CaptureMenuView view, Main app, SimulationList data){

        //perform capture button goes to the perform capture screen
        view.getPerformCaptureButton().setOnAction(e ->  implementPerfromCapture(view, app, data));
        //view log button goes to the view capture log screen
        view.getViewCaptureLogButton().setOnAction(e -> implementViewingLog(view, app, data));
        //back button returns to main menu
        view.getBackButton().setOnAction(e -> app.showMainMenu(app.getMainStage()));

    }

    //Perform Capture Methods

    //Sets up the perform capture screen and populates the target and captor dropdowns

    public void implementPerfromCapture(CaptureMenuView view, Main app, SimulationList data){

        //Sets scene to perform capture
        app.getMainStage().setScene(view.performCaptureView());

        //clears any previously loaded items in the target dropdown
        view.getTargetBox().getItems().clear();

        //Adds character to dropdown if character is a pirate and is status free
        for(Character c : data.getCharacters()){

            if(c instanceof Pirate && c.getStatus().equals("Free")){

                view.getTargetBox().getItems().add(c.getName());
            }
        }

        //shows a message if there are no eligible pirates to capture
        if(view.getTargetBox().getItems().isEmpty()){

            view.getMessageLabel().setText("No Free pirates available for capture");

        }
        else{

            view.getMessageLabel().setText("");
        }

        //clears any leftover selections from a previous visit to this screen
        view.getCaptorBox().getItems().clear();
        view.getCaptorTypeBox().setValue(null);
        view.getCapturedStateBox().setValue(null);

        //when the user selects a captor type, populate the captor dropdown
        view.getCaptorTypeBox().setOnAction(e -> {

            String type = view.getCaptorTypeBox().getValue();

            if(type == null){

                return;
            }

            view.getCaptorBox().getItems().clear();


            for(Character c : data.getCharacters()){

                if(c.getStatus().equals("Dead")){

                    continue;
                }

                //checks if the captor is a Marine
                if(type.equals("Marine") && c instanceof Marine){

                    view.getCaptorBox().getItems().add(c.getName());
                }

                //checks if the captor is a Pirate Hunter
                else if(type.equals("Pirate Hunter") && c instanceof PirateHunter){

                    view.getCaptorBox().getItems().add(c.getName());
                }

                //checks if the captor is a Civilian
                else if(type.equals("Civilian") && c instanceof Civilian){

                    view.getCaptorBox().getItems().add(c.getName());
                }
            }

            //if the captor dropdown is empty
            if(view.getCaptorBox().getItems().isEmpty()){

                view.getMessageLabel().setText("No eligible " + type + " available to be a captor");
            }
            else{

                view.getMessageLabel().setText("");
            }
        });

        //confirm button calls performCapture
        view.getConfirmCaptureButton().setOnAction(e -> performCapture(view, data));

        //back button returns to the capture menu
        view.getBackButton().setOnAction(e -> app.showCaptureMenu(app.getMainStage()));

    }

    //Handles the logic for processing a new Capture
    private void performCapture(CaptureMenuView view, SimulationList data){

        //gets the name of the selected target pirate
        String targetName = view.getTargetBox().getValue();
        //gets the selected captor type
        String captorType = view.getCaptorTypeBox().getValue();
        //gets the name of the selected captor
        String captorName = view.getCaptorBox().getValue();
        //gets the chosen capture state
        String state = view.getCapturedStateBox().getValue();

        //checks that all required fields were answered
        if(targetName == null || captorType == null || captorName == null || state == null){

            view.getMessageLabel().setText("Please fill in all required fields.");

            return;
        }

        //will hold the matching Pirate object
        Pirate target = null;

        //loops through all characters to find the matching pirate
        for(Character c : data.getCharacters()){

            if(c instanceof Pirate && c.getName().equals(targetName)){

                target = (Pirate) c;

                break;
            }
        }

        //will hold the matching captor Character object
        Character captor = null;

        for(Character c : data.getCharacters()){

            if(c.getName().equals(captorName)){

                captor = c;

                break;
            }
        }

        //checks if the target or captor wasn't found
        if(target == null || captor == null){

            view.getMessageLabel().setText("Could not find selected pirate or captor.");

            return;
        }

        //Double checks if the target is still free
        if(!target.getStatus().equals("Free")){

            view.getMessageLabel().setText(targetName + " is no longer Free and cannot be captured.");

            return;
        }

        //generates a random unique ID for this Capture
        int randID = ThreadLocalRandom.current().nextInt(10_000_000, 100_000_000);

        //will hold the newly created Capture object
        Capture capture = null;

        //builds the Capture using the corresponding capture class constructor
        if(captor instanceof Marine marine){

            capture = new Capture(randID, target, marine, state);

        }
        else if(captor instanceof PirateHunter pirateHunter){

            capture = new Capture(randID, target, pirateHunter, state);

        }
        else if(captor instanceof Civilian civilian){

            capture = new Capture(randID, target, civilian, state);

        }

        try{
            //validates that the captor is not itself a Pirate
            capture.validateCapture(captor);
        }
        catch(IllegalCaptorException ex){

            view.getMessageLabel().setText("A Pirate cannot be the captor of another Pirate!");

            return;
        }

        //processes the target's new status
        capture.processTargetStatus();

        //logs the completed Capture to the simulation's capture history
        capture.logTransaction(data.getCaptures());

        //Shows success message
        view.getMessageLabel().setText(captorName + " captured " + targetName + " (" + state + ")!");

        //removes the now-captured pirate from the target dropdown
        view.getTargetBox().getItems().remove(targetName);

        //resets the target dropdown selection
        view.getTargetBox().setValue(null);

        //resets the captor dropdowns
        view.getCaptorBox().getItems().clear();
        view.getCaptorTypeBox().setValue(null);

        //resets the capture state dropdown
        view.getCapturedStateBox().setValue(null);
    }


    //View Capture Log Method

    //Sets up the view capture log screen and populates the log dropdown
    private void implementViewingLog(CaptureMenuView view, Main app, SimulationList data){

        //switches scene to the view capture log scene
        app.getMainStage().setScene(view.viewCaptureLogView());

        //Clears any previously loaded items in the dropdown
        view.getLogBox().getItems().clear();

        //clears any leftover detail text from a previous visit to this screen
        view.getCaptureDetailLabel().setText("");

        //Loops through all logged captures
        for(Capture c : data.getCaptures()){

            //builds a short summary line for the dropdown entry
            String summary = "Capture #" + c.getCaptID() + ": " + c.getCaptor().getName() + " -> " + c.getCaptured().getName() + " (" + c.getCaptureState() + ")";

            view.getLogBox().getItems().add(summary);
        }

        //shows a message if no captures have happened yet
        if(view.getLogBox().getItems().isEmpty()){

            view.getMessageLabel().setText("No Captures have been logged yet!");
        }
        else{

            view.getMessageLabel().setText("");
        }

        //view button calls viewCaptureDetail
        view.getViewCaptureLogButton().setOnAction(e -> viewCaptureDetails(view, data));

        //Back button returns to the capture menu
        view.getBackButton().setOnAction(e -> app.showCaptureMenu(app.getMainStage()));

    }

    private void viewCaptureDetails(CaptureMenuView view, SimulationList data){

        //gets the summary line of the selected capture
        String selected = view.getLogBox().getValue();

        //checks that a capture was actually selected
        if(selected == null){

            view.getMessageLabel().setText("Please select a Capture to view");

            return;
        }

        //loops through all logged captures to find the matching one
        for(Capture capture : data.getCaptures()){

            String summary = "Capture #" + capture.getCaptID() + ": " + capture.getCaptor().getName() + " -> " + capture.getCaptured().getName() + " (" + capture.getCaptureState() + ")";

            //checks if this capture matches the selected summary
            if(summary.equals(selected)){

                StringBuilder sb = new StringBuilder();

                sb.append("XXX Capture Details XXX\n");
                sb.append("Capture ID   :").append(capture.getCaptID()).append("\n");
                sb.append("Captured     :").append(capture.getCaptured().getName()).append("\n");
                sb.append("Captor       : ").append(capture.getCaptor().getName()).append("\n");
                sb.append("Capture State:").append(capture.getCaptureState()).append("\n");
                sb.append("Target Status: ").append(capture.getCaptured().getStatus()).append("\n");

                //displays the built detail in the label
                view.getCaptureDetailLabel().setText(sb.toString());
                view.getMessageLabel().setText("");

                return;
            }
        }
    }
}
