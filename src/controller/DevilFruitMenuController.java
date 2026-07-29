package controller;

//imports Main to allow scene switching
import app.Main;
//imports all model classes
import model.*;
import model.Character;
//imports devil fruit view class that this controller manages
import view.DevilFruitMenuView;

import java.util.concurrent.ThreadLocalRandom;

public class DevilFruitMenuController {

    //Constructor - wires all main menu buttons to their respective methods
    public DevilFruitMenuController(DevilFruitMenuView view, Main app, SimulationList data){

        //create button goes to create screen
        view.getCreateButton().setOnAction(e -> implementCreate(view, app, data));
        //view button goes to view screen
        view.getViewButton().setOnAction(e -> implementView(view, app, data));
        //assign button goes to assign screen
        view.getAssignButton().setOnAction(e -> implementAssign(view, app, data));
        //back button returns to main menu
        view.getBackButton().setOnAction(e-> app.showMainMenu(app.getMainStage()));
    }


    //Create Devil Fruit Methods

    // Sets up the create devil fruit screen and wires its buttons
    private void implementCreate(DevilFruitMenuView view, Main app, SimulationList data){

        //Switches to create Devil Fruit scene
        app.getMainStage().setScene(view.createDevilFruitView());

        // confirm button calls createDevilFruit
        view.getConfirmCreateButton().setOnAction(e -> createDevilFruit(view, data));
        // back button returns to devil fruit menu
        view.getBackButton().setOnAction(e -> app.showDevilFruitMenu(app.getMainStage()));
    }

    //Handles logic for creating a new Devil Fruit
    private void createDevilFruit(DevilFruitMenuView view, SimulationList data){

        //Gets the fruit name inputted in the text field
        String name = view.getFruitNameField().getText();
        //Gets the selected fruit category
        String category = view.getCategoryBox().getValue();
        //Gets the fruit ability description inputted in the text field
        String ability = view.getAbilityField().getText();

        //checks all if all requred fields were answered
        if(name.isBlank() || category == null || ability.isBlank()){

            //shows error message
            view.getMessageLabel().setText("Please fill in all required fields.");

            return;
        }
        // Checks for duplicate names
        for(DevilFruit fruit : data.getFruits()){

            if(fruit.getName().equalsIgnoreCase(name)){

                view.getMessageLabel().setText("A Devil Fruit named " + name + " already exists!");

                return;
            }
        }
        //generates a random unique ID
        int randID = ThreadLocalRandom.current().nextInt(10_000_000, 100_000_000);

        //creates the new DevilFruit object
        DevilFruit newFruit = new DevilFruit(randID, name, category, ability);

        // adds the new fruit to the simulation's fruit list
        data.getFruits().add(newFruit);

        //Show success message
        view.getMessageLabel().setText("Devil Fruit " + name + " created successfully!");
        // clears the name field after creation
        view.getFruitNameField().clear();
        // clears the ability field after creation
        view.getAbilityField().clear();
        // resets the category dropdown after creation
        view.getCategoryBox().setValue(null);
    }


    //View Devil Fruit Methods

    //Sets up the view Devil Fruit screen and populates devil fruit dropdown
    private void implementView(DevilFruitMenuView view, Main app, SimulationList data){

        //Switches scene to view Devil Fruit Scene
        app.getMainStage().setScene(view.viewDevilFruitView());

        // clears any previously loaded items in the dropdown
        view.getViewFruitBox().getItems().clear();

        //loops through all the Devil fruits created
        for(DevilFruit fruit : data.getFruits()){

            // adds each fruit name to the dropdown
            view.getViewFruitBox().getItems().add(fruit.getName());
        }

        //Shows a message id no fruits have been created
        if(view.getViewFruitBox().getItems().isEmpty()){

            view.getMessageLabel().setText("No Devil Fruits created yet!");
        }
        else{

            view.getMessageLabel().setText("");
        }

        // view button calls viewDevilFruit
        view.getConfirmViewButton().setOnAction(e-> viewDevilFruit(view, data));
        // back button returns to devil fruit menu
        view.getBackButton().setOnAction(e -> app.showDevilFruitMenu(app.getMainStage()));
    }

    //Handles displaying full profile of Devil Fruit
    private void viewDevilFruit(DevilFruitMenuView view, SimulationList data){

        // gets the name of the selected fruit
        String selected = view.getViewFruitBox().getValue();

        //checks that a fruit was actually selected
        if(selected == null){

            view.getMessageLabel().setText("Please select a Devil Fruit to view");

            return;
        }

        // loops through all fruits to find the matching one
        for(DevilFruit fruit : data.getFruits()){

            // checks if this fruit matches the selected name
            if(fruit.getName().equalsIgnoreCase(selected)){

                // builds the profile string line by line
                StringBuilder sb = new StringBuilder();

                sb.append("XXX Devil Fruit Details XXX\n");
                sb.append("Fruit ID     : ").append(fruit.getFruitID()).append("\n");
                sb.append("Name         : ").append(fruit.getName()).append("\n");
                sb.append("Category     : ").append(fruit.getCategory()).append("\n");
                sb.append("Ability      : ").append(fruit.getAbilityDescription()).append("\n");
                sb.append("Current Owner: ").append(fruit.getOwner() != null ? fruit.getOwner().getName() : "None").append("\n");
                sb.append("Past Owners  : ");
                if(fruit.getHistory().isEmpty()){

                    sb.append("None\n");
                }
                else{
                    sb.append("\n");

                    // loops through all past owners
                    for(Character c : data.getCharacters()){

                        sb.append("  - ").append(c.getName()).append("\n");
                    }
                }

                // displays the built profile in the label
                view.getFruitProfileLabel().setText(sb.toString());
                view.getMessageLabel().setText("");

                return;
            }
        }
    }


    //Assign Devil Fruit Method

    //Sets up the assign Devil Fruit screen and populates the Devil Fruit dropdown
    private void implementAssign(DevilFruitMenuView view, Main app, SimulationList data){

        // switches to the assign devil fruit scene
        app.getMainStage().setScene(view.assignDevilFruitView());

        // clears any previously loaded items in the fruit dropdown
        view.getAssignDevilFruitBox().getItems().clear();

        // loops through all created fruits
        for(DevilFruit fruit : data.getFruits()){

            //Checks if fruit is already owned
            if(fruit.getOwner() == null){

                // adds unowned fruit to dropdown
                view.getAssignDevilFruitBox().getItems().add(fruit.getName());

            }
        }

        //Shows a message if all fruits are already owned
        if(view.getAssignDevilFruitBox().getItems().isEmpty()){

            view.getMessageLabel().setText("No unassigned Devil Fruits Available");
        }
        else{

            view.getMessageLabel().setText("");
        }

        // when the user selects a character type, populate the character dropdown
        view.getAssignCharTypeBox().setOnAction(e -> {

            //gets the selected character type
            String type = view.getAssignCharTypeBox().getValue();

            if(type == null){

                return;
            }

            //clears previous character list
            view.getAssignCharBox().getItems().clear();

            for(Character c : data.getCharacters()){

                //only shows characters with no devil fruit and who are not dead
                if(c.getDevilFruitPower() == null && !c.getStatus().equals("Dead")){

                    //checks if character is a Pirate
                    if(type.equals("Pirate") && c instanceof Pirate){

                        view.getAssignCharBox().getItems().add(c.getName());

                    }
                    //checks if character is a Marine
                    else if(type.equals("Marine") && c instanceof Marine){

                        view.getAssignCharBox().getItems().add(c.getName());

                    }
                    //checks if character is a Pirate Hunter
                    else if(type.equals("Pirate Hunter") && c instanceof PirateHunter){

                        view.getAssignCharBox().getItems().add(c.getName());

                    }
                    //checks if character is a Civilian
                    else if(type.equals("Civilian") && c instanceof Civilian){

                        view.getAssignCharBox().getItems().add(c.getName());

                    }
                }
            }

            //shows a message if no eligible characters of this type exist
            if(view.getAssignCharBox().getItems().isEmpty()){

                view.getMessageLabel().setText("No eligible character to wield a Devil Fruit");

            }
            else{

                view.getMessageLabel().setText("");
            }
        });

        //confirm button calls assignDevilFruit
        view.getConfirmAssignButton().setOnAction(e -> assignDevilFruit(view, data));
        //back button returns to devil fruit menu
        view.getBackButton().setOnAction(e -> app.showDevilFruitMenu(app.getMainStage()));
    }

    //Handles Assign Devil Fruit logic
    private void assignDevilFruit(DevilFruitMenuView view, SimulationList data){

        //gets the selected fruit name
        String fruitName = view.getAssignDevilFruitBox().getValue();
        //gets the selected character
        String charName = view.getAssignCharBox().getValue();

        //checks if both fruit and character were selected
        if(fruitName == null || charName == null){

            view.getMessageLabel().setText("Please select a Devil Fruit and a character to assign it to");

            return;
        }

        //will hold the matching DevilFruit object
        DevilFruit fruit = null;
        //loops through all fruits to find the matching one
        for(DevilFruit f : data.getFruits()){

            if(f.getName().equals(fruitName)){

                // assigns the found fruit
                fruit = f;

                break;
            }
        }

        //will hold the matching Character object
        Character character = null;
        //loops through all characters to find the matching one
        for(Character c : data.getCharacters()){

            if(c.getName().equals(charName)){

                character = c;

                break;
            }
        }

        //Check if Devil Fruit or Character wasn't found
        if(fruit == null || character == null){

            view.getMessageLabel().setText("Could not find selected fruit or character.");

            return;
        }
        if(fruit.getOwner() != null){

            view.getMessageLabel().setText(fruitName + " is already owned by " + fruit.getOwner().getName() + "!");

            return;
        }

        if(character.getStatus().equals("Dead")){

            view.getMessageLabel().setText(charName + " is already dead and cannot wield a Devil Fruit power!");

            return;
        }

        if (character.getDevilFruitPower() != null) {

            view.getMessageLabel().setText(charName + " already has a Devil Fruit power!");

            return;
        }

        //calls assignNewUser()
        fruit.assignNewUser(character);
        view.getMessageLabel().setText(fruitName + " successfully assigned to " + charName + "!");

        //removes the owned fruit from the dropdown
        view.getAssignDevilFruitBox().getItems().remove(fruitName);
        //resets the fruit dropdown selection
        view.getAssignDevilFruitBox().setValue(null);

        //clears the character dropdown
        view.getAssignCharBox().getItems().clear();
        //resets the character type dropdown
        view.getAssignCharTypeBox().setValue(null);
    }
}
