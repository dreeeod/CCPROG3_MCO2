package controller;

//Access the main application/stage to switch scenes
import app.Main;
//Brings in all public classes from the model package
import model.*;
//Imports the GroupMenuView class from the view package
import model.Character;
import view.GroupMenuView;

//Random number generator
import java.util.concurrent.ThreadLocalRandom;

public class GroupMenuController {

    //GroupMenuController Constructor
    public GroupMenuController(GroupMenuView view, Main app, SimulationList data){

        //Create a new group
        view.getCreateButton().setOnAction(e -> implementCreate(view, app, data));
        //Display Existing Group
        view.getViewButton().setOnAction(e -> implementView(view, app, data));
        //Modify an existing group
        view.getModifyButton().setOnAction(e -> implementModify(view, app, data));
        //Add a member to an existing group
        view.getAddButton().setOnAction(e -> implementAdd(view, app, data));
        //Remove a member from a group
        view.getRemoveButton().setOnAction(e -> implementRemove(view, app, data));
        //Navigate back to Main Menu
        view.getBackButton().setOnAction(e -> app.showMainMenu(app.getMainStage()));
    }

    //Method for Create Groups
    private void implementCreate(GroupMenuView view, Main app, SimulationList data){

        //Set Scene for Group Creation
        app.getMainStage().setScene(view.createGroupView());

        //Populating Dropdowns
        view.getCaptainBox().getItems().clear();
        view.getCommanderBox().getItems().clear();

        //Loops through all existing characters
        for(Character c : data.getCharacters()) {

            //Checks if character is a pirate and is not part of any group
            if (c instanceof Pirate p && p.getPirateCrew() == null) {

                view.getCaptainBox().getItems().add(c.getName());

            }

            //Checks if character is a Marine
            if (c instanceof Marine) {
                view.getCommanderBox().getItems().add(c.getName());
            }
        }

        //When the user Clicks create
        view.getConfirmCreate().setOnAction(e -> createGroup(view, data));
        view.getBackButton().setOnAction(e -> app.showGroupMenu(app.getMainStage()));
    }
    private void createGroup(GroupMenuView view, SimulationList data){

        //Gets group type input
        String type = view.getGroupTypeBox().getValue();

        if(type == null){

            view.getMessageLabel().setText("Please select a Group Type.");

            return;
        }

        int randID = ThreadLocalRandom.current().nextInt(10_000_000, 100_000_000);

        if(type.equals("Pirate Crew")){

            String crewName = view.getCrewNameField().getText();
            String shipName = view.getShipNameField().getText();

            if(crewName.isBlank() || shipName.isBlank()){

                view.getMessageLabel().setText("Please fill in all required fields.");

                return;
            }

            String captainName = view.getCaptainBox().getValue();
            Pirate captain = null;

            if(captainName != null){

                for(Character c : data.getCharacters()){

                    if(c.getName().equals(captainName) && c instanceof Pirate){

                        captain = (Pirate) c;
                        break;
                    }
                }
            }

            PirateCrew crew = new PirateCrew(randID, crewName, shipName, captain);

            if(captain != null){

                crew.addCrewMember(captain);
                crew.assignCaptain(captain);
            }

            data.getCrews().add(crew);

            view.getMessageLabel().setText("Pirate Crew " + crewName + " created successfully!");
            view.getCrewNameField().clear();
            view.getShipNameField().clear();
        }
        else{

            String baseLoc = view.getBaseLocField().getText();
            String fundsText = view.getFundsField().getText();

            if(baseLoc.isBlank() || fundsText.isBlank()){

                view.getMessageLabel().setText("Please fill in all required fields.");

                return;
            }

            double funds;

            try{

                funds = Double.parseDouble(fundsText);
            }
            catch(NumberFormatException ex){

                view.getMessageLabel().setText("Funds must be a valid positive number.");

                return;
            }

            if(funds < 0){

                view.getMessageLabel().setText("Funds cannot be negative!");

                return;
            }

            String commanderName = view.getCommanderBox().getValue();

            Marine commander = null;

            if(commanderName != null){

                for(Character c : data.getCharacters()){

                    if(c.getName().equals(commanderName) && c instanceof Marine){

                        commander = (Marine) c;

                        break;
                    }
                }
            }

            MarineCorps corps = new MarineCorps(randID, baseLoc, commander, funds);

            if(commander != null){

                corps.recruitMarine(commander);
            }

            data.getCorps().add(corps);

            view.getMessageLabel().setText("Marine Corps " + baseLoc + " created successfully!");
            view.getBaseLocField().clear();
            view.getFundsField().clear();
        }
    }

    //View Group Methods
    private void implementView(GroupMenuView view, Main app, SimulationList data){

        //Set Scene for Group Viewing
        app.getMainStage().setScene(view.viewGroupView());

        view.getViewGroupTypeBox().setOnAction(e -> {

            String type = view.getViewGroupTypeBox().getValue();
            view.getViewGroupBox().getItems().clear();

            if("Pirate Crew".equals(type)){

                for(PirateCrew crew : data.getCrews()){

                    view.getViewGroupBox().getItems().add(crew.getCrewName());
                }
            }
            else{

                for(MarineCorps corps : data.getCorps()){

                    view.getViewGroupBox().getItems().add(corps.getBaseLoc());
                }
            }

            if(view.getViewGroupBox().getItems().isEmpty()){

                view.getMessageLabel().setText("No groups of this type found!");
            }
            else{

                view.getMessageLabel().setText("");
            }
        });

        view.getViewGroupButton().setOnAction(e -> viewGroup(view, data));
        view.getBackButton().setOnAction(e -> app.showGroupMenu(app.getMainStage()));
    }
    private void viewGroup(GroupMenuView view, SimulationList data){

        String selected = view.getViewGroupBox().getValue();
        String type = view.getViewGroupTypeBox().getValue();

        if(selected == null || type == null){

            view.getMessageLabel().setText("Please select the type of the group and the specific group you want to view");

            return;
        }

        StringBuilder sb = new StringBuilder();

        if(type.equals("Pirate Crew")){

            for(PirateCrew crew : data.getCrews()){

                if(crew.getCrewName().equals(selected)){

                    sb.append("XXX Pirate Crew Details XXX\n");
                    sb.append("Crew ID      : ").append(crew.getCrewID()).append("\n");
                    sb.append("Crew Name    : ").append(crew.getCrewName()).append("\n");
                    sb.append("Ship Name    : ").append(crew.getShipName()).append("\n");
                    sb.append("Captain      : ").append(crew.getCaptain() != null ? crew.getCaptain().getName() : "None").append("\n");
                    sb.append("Total Bounty : ").append(crew.getTotalCrewBounty()).append(" Berries\n");
                    sb.append("Members      : ").append(crew.getCrewMembers().size()).append("\n");

                    for(Pirate p : crew.getCrewMembers()){

                        sb.append("  - ").append(p.getName()).append(" (").append(p.getPirateRole()).append(")\n");
                    }

                    break;
                }
            }
        }
        else{

            for(MarineCorps corps : data.getCorps()){

                if(corps.getBaseLoc().equals(selected)){

                    sb.append("XXX Marine Corps Details XXX\n");
                    sb.append("Corps ID     : ").append(corps.getCorpsID()).append("\n");
                    sb.append("Base Location: ").append(corps.getBaseLoc()).append("\n");
                    sb.append("Commander    : ").append(corps.getCommander() != null ? corps.getCommander().getName() : "None").append("\n");
                    sb.append("Funds        : ").append(corps.getFunds()).append(" Berries\n");
                    sb.append("Members      : ").append(corps.getMembers().size()).append("\n");

                    for(Marine marine : corps.getMembers()){

                        sb.append("  - ").append(marine.getName()).append(" (").append(marine.getRank()).append(")\n");
                    }

                    break;
                }
            }
        }

        view.getGroupProfileLabel().setText(sb.toString());
        view.getMessageLabel().setText("");
    }

    //Modify Group Methods
    private void implementModify(GroupMenuView view, Main app, SimulationList data){

        app.getMainStage().setScene(view.editGroupView());

        view.getEditGroupTypeBox().setOnAction(e -> {

            String type = view.getEditGroupTypeBox().getValue();
            view.getEditGroupBox().getItems().clear();
            view.getEditAttributeBox().getItems().clear();

            if("Pirate Crew".equals(type)){

                for(PirateCrew crew : data.getCrews()) {

                    view.getEditGroupBox().getItems().add(crew.getCrewName());

                }

                view.getEditAttributeBox().getItems().setAll("Crew Name", "Ship Name", "Captain");
            }
            else{

                for(MarineCorps corps : data.getCorps()){

                    view.getEditGroupBox().getItems().add(corps.getBaseLoc());

                }
                view.getEditAttributeBox().getItems().setAll("Base Location", "Commander", "Operational Funds");
            }
        });

        view.getEditAttributeBox().setOnAction(e -> {

            String attribute = view.getEditAttributeBox().getValue();
            String type = view.getEditGroupTypeBox().getValue();

            boolean isCommander = "Commander".equals(attribute);
            boolean isCaptain = "Captain".equals(attribute);

            view.getEditValueAttribute().setVisible(!isCommander && !isCaptain);
            view.getEditValueAttribute().setManaged(!isCommander && !isCaptain);

            view.getEditCommanderBox().setVisible(isCommander);
            view.getEditCommanderBox().setManaged(isCommander);

            view.getEditCaptainBox().setVisible(isCaptain);
            view.getEditCaptainBox().setManaged(isCaptain);

            if(isCommander){

                view.getEditCommanderBox().getItems().clear();
                for (Character c : data.getCharacters()) {

                    if (c instanceof Marine){

                        view.getEditCommanderBox().getItems().add(c.getName());
                    }
                }
            }

            if(isCaptain){

                String groupName = view.getEditGroupBox().getValue();

                view.getEditCaptainBox().getItems().clear();

                for(PirateCrew crew : data.getCrews()){

                    if(crew.getCrewName().equals(groupName)){

                        for (Pirate p : crew.getCrewMembers()) {

                            view.getEditCaptainBox().getItems().add(p.getName());
                        }
                    }
                }
            }
        });

        view.getConfirmEdit().setOnAction(e -> editGroup(view, data));
        view.getBackButton().setOnAction(e -> app.showGroupMenu(app.getMainStage()));
    }

    private void editGroup(GroupMenuView view, SimulationList data){

        String type = view.getEditGroupTypeBox().getValue();
        String selected = view.getEditGroupBox().getValue();
        String attribute = view.getEditAttributeBox().getValue();

        if(type == null || selected == null || attribute == null){

            view.getMessageLabel().setText("Please select a group and attribute to edit!");

            return;
        }

        if(type.equals("Pirate Crew")){

            for(PirateCrew crew : data.getCrews()){

                if(crew.getCrewName().equals(selected)){

                    switch(attribute){

                        case "Crew Name" -> {

                            String val = view.getEditValueAttribute().getText();
                            if (val.isBlank()){

                                view.getMessageLabel().setText("Please enter a new crew name.");
                                return;
                            }

                            crew.setCrewName(val);
                            view.getMessageLabel().setText("Crew name updated to: " + val);
                        }

                        case "Ship Name" -> {

                            String val = view.getEditValueAttribute().getText();

                            if (val.isBlank()){

                                view.getMessageLabel().setText("Please enter a new ship name.");
                                return;
                            }

                            crew.setShipName(val);
                            view.getMessageLabel().setText("Ship name updated to: " + val);
                        }
                        case "Captain" -> {

                            String captainName = view.getEditCaptainBox().getValue();

                            if (captainName == null){

                                view.getMessageLabel().setText("Please select a captain.");
                                return;
                            }

                            for (Pirate p : crew.getCrewMembers()){

                                if (p.getName().equals(captainName)){

                                    crew.assignCaptain(p);
                                    view.getMessageLabel().setText(captainName + " is now the Captain!");

                                    break;
                                }
                            }
                        }
                    }

                    break;
                }
            }
        }
        else{

            for(MarineCorps corps : data.getCorps()){

                if (corps.getBaseLoc().equals(selected)){

                    switch(attribute){

                        case "Base Location" -> {

                            String val = view.getEditValueAttribute().getText();

                            if (val.isBlank()){

                                view.getMessageLabel().setText("Please enter a new base location.");

                                return;
                            }

                            corps.setBaseLoc(val);
                            view.getMessageLabel().setText("Base location updated to: " + val);
                        }

                        case "Commander" -> {

                             String commanderName = view.getEditCommanderBox().getValue();

                            if (commanderName == null){

                                view.getMessageLabel().setText("Please select a commander.");

                                return;
                            }

                            for (Character c : data.getCharacters()){

                                if (c.getName().equals(commanderName) && c instanceof Marine m){

                                    corps.setCommander(m);
                                    view.getMessageLabel().setText(commanderName + " is now the Commander!");

                                    break;
                                }
                            }
                        }

                        case "Operational Funds" -> {

                            try{

                                double funds = Double.parseDouble(view.getEditValueAttribute().getText());
                                if (funds < 0){

                                    view.getMessageLabel().setText("Funds cannot be negative.");
                                    return;
                                }

                                corps.setFunds(funds);
                                view.getMessageLabel().setText("Funds updated to: " + funds);
                            }
                            catch(NumberFormatException ex){

                                view.getMessageLabel().setText("Please enter a valid number for funds.");
                            }
                        }
                    }

                    break;
                }
            }
        }
    }

    //Add Members Methods
    private void implementAdd(GroupMenuView view, Main app, SimulationList data){

        //Set add Members Scene
        app.getMainStage().setScene(view.addMembersView());

        //When type is selected populate the group dropdown
        view.getAddGroupTypeBox().setOnAction(e -> {

            String type = view.getAddGroupTypeBox().getValue();

            view.getAddGroupBox().getItems().clear();
            view.getAddMemberBox().getItems().clear();

            if("Pirate Crew".equals(type)){

                for(PirateCrew crew : data.getCrews()){

                    view.getAddGroupBox().getItems().add(crew.getCrewName());
                }
            }
            else{

                for(MarineCorps corps: data.getCorps()){

                    view.getAddGroupBox().getItems().add(corps.getBaseLoc());
                }
            }
        });

        //When group is selected populate the members dropdown
        view.getAddGroupBox().setOnAction(e -> {

            String type = view.getAddGroupTypeBox().getValue();
            view.getAddMemberBox().getItems().clear();

            if("Pirate Crew".equals(type)){

                for(Character c : data.getCharacters()){

                    if(c instanceof Pirate p && p.getPirateCrew() == null){

                        view.getAddMemberBox().getItems().add(c.getName());
                    }
                }
            }
            else{

                for(Character c : data.getCharacters()){

                    if(c instanceof Marine m && m.getCorps() == null){

                        view.getAddMemberBox().getItems().add(c.getName());
                    }
                }
            }
        });

        view.getConfirmAdd().setOnAction(e -> addMember(view, data));
        view.getBackButton().setOnAction(e -> app.showGroupMenu(app.getMainStage()));
    }

    private void addMember(GroupMenuView view, SimulationList data){

        String type = view.getAddGroupTypeBox().getValue();
        String groupName = view.getAddGroupBox().getValue();
        String member = view.getAddMemberBox().getValue();

        if(type == null || groupName == null || member == null){

            view.getMessageLabel().setText("Please select a group and member");

            return;
        }

        if(type.equals("Pirate Crew")){

            for(PirateCrew crew : data.getCrews()){

                if(crew.getCrewName().equals(groupName)){

                    for(Character c : data.getCharacters()){

                        if(c.getName().equals(member) && c instanceof Pirate p){

                            crew.addCrewMember(p);

                            view.getMessageLabel().setText(member + " added to " + groupName + "!");
                            return;
                        }
                    }
                }
            }
        }
        else{

            for(MarineCorps corps : data.getCorps()){

                if(corps.getBaseLoc().equals(groupName)){

                    for(Character c : data.getCharacters()){

                        if(c.getName().equals(member) && c instanceof Marine m){

                            corps.recruitMarine(m);

                            view.getMessageLabel().setText(member + " added to " + groupName + "!");

                            return;
                        }
                    }
                }
            }
        }
    }

    //Remove Members Methods
    private void implementRemove(GroupMenuView view, Main app, SimulationList data){

        app.getMainStage().setScene(view.removeMembersView());

        view.getRemoveGroupTypeBox().setOnAction(e -> {

            String type = view.getRemoveGroupTypeBox().getValue();

            view.getRemoveGroupBox().getItems().clear();
            view.getRemoveMemberBox().getItems().clear();

            if("Pirate Crew".equals(type)){

                for(PirateCrew crew : data.getCrews()){

                    view.getRemoveGroupBox().getItems().add(crew.getCrewName());
                }
            }
            else{

                for(MarineCorps corps : data.getCorps()){

                    view.getRemoveGroupBox().getItems().add(corps.getBaseLoc());
                }
            }
        });

        view.getRemoveGroupBox().setOnAction(e -> {

            String type = view.getRemoveGroupTypeBox().getValue();
            String group = view.getRemoveGroupBox().getValue();
            view.getRemoveMemberBox().getItems().clear();

            if("Pirate Crew".equals(type)){

                for(PirateCrew crew : data.getCrews()){

                    if(crew.getCrewName().equals(group)){

                        for(Pirate p : crew.getCrewMembers()){

                            view.getRemoveMemberBox().getItems().add(p.getName());
                        }
                    }
                }
            }
            else{

                for(MarineCorps corps : data.getCorps()){

                    if(corps.getBaseLoc().equals(group)){

                        for(Marine m : corps.getMembers()){

                            view.getRemoveMemberBox().getItems().add(m.getName());
                        }
                    }
                }
            }
        });

        view.getConfirmRemove().setOnAction(e -> removeMember(view, data));
        view.getBackButton().setOnAction(e -> app.showGroupMenu(app.getMainStage()));
    }

    private void removeMember(GroupMenuView view, SimulationList data){

        String type = view.getRemoveGroupTypeBox().getValue();
        String groupName = view.getRemoveGroupBox().getValue();
        String member = view.getRemoveMemberBox().getValue();

        if(type == null || groupName == null || member == null){

            view.getMessageLabel().setText("Please select a group and member");

            return;
        }

        if(type.equals("Pirate Crew")){

            for(PirateCrew crew : data.getCrews()){

                if(crew.getCrewName().equals(groupName)){

                    for(Pirate p : crew.getCrewMembers()){

                        if(p.getName().equals(member)){

                            crew.removeCrewMember(p);

                            view.getMessageLabel().setText(member + " removed from " + groupName + "!");
                            view.getRemoveMemberBox().getItems().remove(member);

                            return;
                        }
                    }
                }
            }
        }
        else{

            for(MarineCorps corps : data.getCorps()){

                if(corps.getBaseLoc().equals(groupName)){

                    for(Marine m : corps.getMembers()){

                        if(m.getName().equals(member)){

                            corps.dischargeMarine(m);

                            view.getMessageLabel().setText(member + " removed from " + groupName + "!");
                            view.getRemoveMemberBox().getItems().remove(member);

                            return;
                        }
                    }
                }
            }
        }
    }
}
