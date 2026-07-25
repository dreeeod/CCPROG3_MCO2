package controller;

import app.Main;
import model.SimulationList;
import model.exceptions.DuplicateNameException;
import view.PirateCreateView;

public class PirateCreateController {

    public PirateCreateController(PirateCreateView view, Main mainApp, SimulationList model) {
        view.getCreateButton().setOnAction(e -> create(view, model));
        view.getBackButton().setOnAction(e -> mainApp.showCharacterCreateMenu());
    }

    public void create(PirateCreateView view, SimulationList sim) {
        String name = view.getNameField().getText();
        String alias = view.getAliasField().getText();
        String origin = view.getOriginField().getText();
        String status = view.getStatusBox().getValue();
        String role = view.getRoleBox().getValue();

        if (name.isBlank() || status == null || role == null) {
            view.getMessageLabel().setText("Please fill in all required fields.");
            return;
        }

        double wallet;
        long bounty;
        try {
            wallet = Double.parseDouble(view.getWalletField().getText());
            bounty = Long.parseLong(view.getBountyField().getText());
        }
        catch (NumberFormatException ex) {
            view.getMessageLabel().setText("Wallet and bounty must be valid numbers.");
            return;
        }

        if (wallet < 0 || bounty < 0) {
            view.getMessageLabel().setText("Wallet and bounty cannot be negative.");
            return;
        }

        try {
            sim.createPirate(name, alias, origin, status, wallet, bounty, role);
            view.getMessageLabel().setText("Pirate '" + name + "' created successfully!");
            view.getNameField().clear();
            view.getAliasField().clear();
            view.getOriginField().clear();
            view.getWalletField().clear();
            view.getBountyField().clear();
        }
        catch (DuplicateNameException ex) {
            view.getMessageLabel().setText(ex.getMessage());
        }
    }
}
