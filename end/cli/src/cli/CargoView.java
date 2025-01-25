package cli;

import administration.Storable;
import cargo.Hazard;
import domainLogic.StorableCargos;


import java.util.Date;
import java.math.BigDecimal;
import java.util.List;

public class CargoView {

    // Befehlsbeschreibungen
    private static final String COMMAND_INSERT_MODE_DESCRIPTION = ":c - Switch to insert mode";
    private static final String COMMAND_DELETE_MODE_DESCRIPTION = ":d - Switch to delete mode";
    private static final String COMMAND_DISPLAY_MODE_DESCRIPTION = ":r - Switch to display mode";
    private static final String COMMAND_UPDATE_MODE_DESCRIPTION = ":u - Switch to update mode";
    private static final String COMMAND_PERSISTENCE_MODE_DESCRIPTION = ":p - Switch to persistence mode";
    private static final String COMMAND_QUIT_DESCRIPTION = ":q - Quit the program";

    // Erfolg- und Fehlermeldungen
    private static final String MSG_CARGO_ADDED_SUCCESS = "Cargo item added successfully!";
    private static final String MSG_CARGO_ADDED_FAILURE = "Failed to add cargo item.";
    private static final String MSG_CARGO_DELETED_SUCCESS = "Cargo item deleted successfully!";
    private static final String MSG_CARGO_DELETED_FAILURE = "Failed to delete cargo item.";
    private static final String MSG_INSPECTION_UPDATED_SUCCESS = "Inspection date updated successfully!";
    private static final String MSG_INSPECTION_UPDATED_FAILURE = "Failed to update inspection date.";
    private static final String MSG_NO_CARGO_FOUND = "No cargo items found.";
    private static final String MSG_SAVE_SUCCESS = "Storage saved successfully!";
    private static final String MSG_SAVE_FAILURE = "Failed to save storage.";
    private static final String MSG_INVALID_COMMAND = "Invalid command. Please try again.";

    private static final String MSG_INVALID_CARGO="Cargo could not be added";
    private static final String MSG_INVALID_CARGO_TYPE = "Invalid cargo type. Please select a valid type.";
    private static final String MSG_CUSTOMER_CREATED_SUCCESS = "Customer account created successfully!";
    private static final String MSG_INVALID_CUSTOMER_INPUT = "Invalid input. Customer name and email are required.";

    public void displayCommandSet() {
        System.out.println("Available commands:");
        System.out.println(COMMAND_INSERT_MODE_DESCRIPTION);
        System.out.println(COMMAND_DELETE_MODE_DESCRIPTION);
        System.out.println(COMMAND_DISPLAY_MODE_DESCRIPTION);
        System.out.println(COMMAND_UPDATE_MODE_DESCRIPTION);
        System.out.println(COMMAND_PERSISTENCE_MODE_DESCRIPTION);
        System.out.println(COMMAND_QUIT_DESCRIPTION);
    }


    public void delete(boolean result) {
        resultMessage(result, MSG_CARGO_DELETED_SUCCESS, MSG_CARGO_DELETED_FAILURE);
    }


    public void update(boolean result) {
        resultMessage(result, MSG_INSPECTION_UPDATED_SUCCESS, MSG_INSPECTION_UPDATED_FAILURE);
    }


    public void display(List<Storable> cargoList) {
        if (cargoList.isEmpty()) {
            System.out.println(MSG_NO_CARGO_FOUND);
        } else {
            for (Storable storable : cargoList) {
                if (storable instanceof StorableCargos cargo) { // Pattern Matching for instanceof (Java 16+)
                    System.out.println();
                    System.out.println("Cargo Details:");
                    System.out.println("- Type: " + cargo.getClass().getSimpleName());
                    System.out.println("- Value: " + cargo.getValue());
                    System.out.println("- Owner: " + (cargo.getOwner() != null ? cargo.getOwner().getName() : "N/A"));
                    System.out.println("- Storage Location: " + cargo.getStorageLocation());
                    System.out.println("- Duration of Storage: " + cargo.getDurationOfStorage());
                    System.out.println("- Last Inspection Date: " + cargo.getLastInspectionDate());
                    System.out.println("- Insert Date: " + cargo.getInsertDate());
                    System.out.println("- Hazards: " + cargo.getHazards());
                    System.out.println("- Pressurized: " + (cargo.isPressurized() ? "Yes" : "No"));
                    System.out.println("- Fragile: " + (cargo.isFragile() ? "Yes" : "No"));
                    System.out.println("- Grain Size: " + (cargo.getGrainSize() != null ? cargo.getGrainSize() : "N/A"));
                    System.out.println();
                } else {
                    System.out.println("Invalid cargo type found.");
                }
            }
        }
    }


    public BigDecimal promptValidatedValue() {
        while (true) {
            try {
                promptValue();
                return new BigDecimal(System.console().readLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please enter a valid numeric value.");
            }
        }
    }
    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void promptCommand() {
        System.out.print("Enter command: ");
    }

    public void promptInsertCommand() {
        System.out.print("Enter 'add' to create a new cargo or 'back' to return: ");
    }
    public void promptDeleteCommand() {
        System.out.print("Enter the ID of the cargo to delete or 'back' to return: ");
    }
    public void promptDisplayCommand() {
        System.out.print("Enter the ID of the cargo to display or 'back' to return: ");
    }
    public void promptUpdateCommand() {
        System.out.print("Enter the ID of the cargo to update or 'back' to return: ");
    }
    public void invalidUpdateInput() {
        System.out.println("Ungültige Eingabe. Bitte versuche es erneut.");
    }


    public void promptCargoType() {
        System.out.println("Enter the type of cargo to create:");
        System.out.println("- drybulk");
        System.out.println("- liquid");
        System.out.println("- unitised");
        System.out.println("- liquidanddrybulk");
        System.out.println("- liquidbulkandunitised");
        System.out.println("- drybulkandunitised");
    }

    public void promptValue() {
        System.out.print("Enter the value of the cargo: ");
    }

    public void promptGrainSize() {
        System.out.print("Enter the grain size (only for dry bulk cargo): ");
    }

    public void promptPressurized() {
        System.out.print("Is the cargo pressurized? (yes/no): ");
    }

    public void promptFragile() {
        System.out.print("Is the cargo fragile? (yes/no): ");
    }

    public void promptDeleteLocation() {
        System.out.print("Enter the storage location of the cargo to delete: ");
    }

    public void promptUpdateLocation() {
        System.out.print("Enter the storage location of the cargo to update: ");
    }

    public void promptPersistenceCommand() {
        System.out.print("Enter 'save' to save, 'load' to load, or 'back' to return: ");
    }

    // Nachrichten für den Wechsel zwischen Modi
    public void displayInsertModeInstructions() {
        System.out.println("You are now in INSERT MODE:");
        System.out.println("[K-Name] fügt eine Kund*in ein");
        System.out.println("[Fracht-Typ] [K-Name] [Wert] [Gefahrenstoffe] [[fragile]] [[pressurized]] [[grainSize]]");
        System.out.println("Beispiele:");
        System.out.println("liquidanddrybulk Alice 4004.50 flammable,toxic true 10");
        System.out.println("Geben Sie 'back' ein, um zum Hauptmenü zurückzukehren.");
    }
    public void displayDeleteModeInstructions() {
        System.out.println("You are now in DELETE MODE.");
        System.out.println("Provide the storage location of the cargo to delete.");
        System.out.println("Type 'back' to return to the main menu.");
    }

    public void displayDisplayModeInstructions() {
        System.out.println("You are now in DISPLAY MODE.");
        System.out.println("All stored cargo items will be displayed.");
        System.out.println("Type 'back' to return to the main menu.");
    }

    public void displayUpdateModeInstructions() {
        System.out.println("You are now in UPDATE MODE.");
        System.out.println("Provide the storage location of the cargo to update.");
        System.out.println("Type 'back' to return to the main menu.");
    }
    public void displayCustomerWithCargoCount(String customerName, int cargoCount) {
        System.out.printf("Kunde/Kundin: %s, Anzahl der Frachtstücke: %d%n", customerName, cargoCount);
    }
    public void displayCustomerDetails(String customerName, int cargoCount) {
        System.out.printf("Kund*in: %s, Anzahl der Frachtstücke: %d%n", customerName, cargoCount);
    }

    public void noCustomersFound() {
        System.out.println("Keine Kund*innen gefunden.");
    }

    public void promptCargoTypeFilter() {
        System.out.println("Optional: Geben Sie einen Frachttyp an, um zu filtern, oder lassen Sie das Feld leer:");
    }

    public void displayCargoDetails(int storageLocation, Date inspectionDate, long durationInSeconds) {
        System.out.printf("Platz: %d, Inspektionsdatum: %s, Einlagerungsdauer: %d Sekunden%n",
                storageLocation, inspectionDate, durationInSeconds);
    }

    public void noCargosFound() {
        System.out.println("Keine Frachtstücke gefunden.");
    }

    // Gefahrenstoffe anzeigen
    public void promptHazardFilter() {
        System.out.println("Filteroptionen: 'i' für enthalten, 'e' für nicht enthalten");
    }

    public void displayHazards(List<Hazard> hazards) {
        System.out.println("Gefundene Gefahrenstoffe:");
        for (var hazard : hazards) {
            System.out.println("- " + hazard);
        }
    }

    public void noHazardsFound() {
        System.out.println("Keine passenden Gefahrenstoffe gefunden.");
    }
    public void displayPersistenceModeInstructions() {
        System.out.println("You are now in PERSISTENCE MODE.");
        System.out.println("Type 'save' to save the storage or 'load' to load the storage data.");
        System.out.println("Type 'back' to return to the main menu.");
    }

    public void returnToMainMenu() {
        System.out.println("Returning to main menu...");
    }

    public void exitMessage() {
        System.out.println("Exiting application. Goodbye!");
    }
    public void promptCustomerCreation() {
        System.out.println("You are now creating a new customer account.");
    }

    public void promptCustomerName() {
        System.out.print("Enter customer name: ");
    }

    public void promptCustomerEmail() {
        System.out.print("Enter customer email: ");
    }

    public void customerCreatedSuccess() {
        System.out.println(MSG_CUSTOMER_CREATED_SUCCESS);
    }
    public void customerDoesNotExistError(String customerName) {
        System.out.println("Fehler: Der Kunde \"" + customerName + "\" existiert nicht. Bitte legen Sie den Kunden zuerst an.");
    }
    public void customerAlreadyExists(String customerName) {
        System.out.println("Willkommen zurück, " + customerName + "! Sie können jetzt Frachtstücke hinzufügen.");
    }
    public void promptInsertCargo() {
        System.out.println("Bitte geben Sie Ihr Frachtstück ein (oder 'back', um zum Hauptmenü zurückzukehren):");
    }

    public void invalidHazard(String hazard) {
        System.out.printf("Ungültiger Gefahrenstoff: %s%n", hazard);
    }

    public void invalidCustomerInput() {
        System.out.println(MSG_INVALID_CUSTOMER_INPUT);
    }

    // Hilfsmethode zur Anzeige von Erfolg oder Misserfolg
    private void resultMessage(boolean result, String successMessage, String failureMessage) {
        System.out.println(result ? successMessage : failureMessage);
    }

    public void invalidInput() {
        System.out.println(MSG_INVALID_COMMAND);
    }

    public void invalidCargoType() {
        System.out.println(MSG_INVALID_CARGO_TYPE);
        promptCargoType();
    }

    public void cargoCreationFailed(){
        System.out.println(MSG_INVALID_CARGO);
    }

    public void cargoCreatedSuccess() {
        System.out.println("Die Fracht wurde erfolgreich erstellt.");
    }

    public void missingPressurized() {
        System.out.println("Cargo has no Pressurizrd label");
    }

    public void missingGrainSize() {
        System.out.println("Cargo has no GrainSize.");
    }

    public void missingFragile() {
        System.out.println("is fragil?");
    }

    public void noAvailableStorageSpace() {
        System.out.println("no space left");
    }

    public void displayCustomerCargos(String customerName, List<StorableCargos> cargos) {

    }

    public void customerDeletedSuccess(String customerName) {
    }
    public void deleteSuccess(StorableCargos deletedCargo) {
        System.out.println("Das Cargo mit der StorageLocation " + deletedCargo.getStorageLocation() + " wurde erfolgreich gelöscht.");
    }
    public void noCargoAtLocation() {
        System.out.println("Kein Frachtstück an dieser StorageLocation gefunden. Bitte überprüfen Sie die Eingabe.");
    }
    public void invalidDeleteInput() {
        System.out.println("Ungültige Eingabe. Bitte geben Sie eine gültige StorageLocation als Zahl ein.");
    }

}
