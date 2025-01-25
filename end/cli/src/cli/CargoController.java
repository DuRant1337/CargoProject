package cli;

import domainLogic.StorableCargos;
import domainLogic.Storage;
import domainLogic.Management;
import cargo.Hazard;
import implementation.*;

import java.time.Duration;
import java.util.*;
import java.math.BigDecimal;

public class CargoController {
    private static final String COMMAND_INSERT_MODE = ":c";
    private static final String COMMAND_DELETE_MODE = ":d";
    private static final String COMMAND_DISPLAY_MODE = ":r";
    private static final String COMMAND_UPDATE_MODE = ":u";
    private static final String COMMAND_QUIT = ":q";

    private final Storage storage;
    private final CargoView view;
    private final Scanner scanner;
    private final Management management;

    public CargoController(Storage storage, Management management, CargoView view, Scanner scanner) {
        this.storage = storage;
        this.management = management;
        this.view = view;
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            view.displayCommandSet();
            view.promptCommand();
            String mode = scanner.nextLine().toLowerCase();

            if (COMMAND_QUIT.equals(mode)) {
                view.exitMessage();
                scanner.close();
                break;
            }

            switch (mode) {
                case COMMAND_INSERT_MODE -> insertMode();
                case COMMAND_DELETE_MODE -> deleteMode();
                case COMMAND_DISPLAY_MODE -> displayMode();
                case COMMAND_UPDATE_MODE -> updateMode();
                default -> view.invalidInput();
            }
        }
    }

    // Insert Mode
    private void insertMode() {
        boolean inInsertionMode = true;
        view.displayInsertModeInstructions();

        String customerName = null;
        while (customerName == null) {
            view.promptCustomerName();
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("back")) {
                view.returnToMainMenu();
                return;
            }

            if (input.isEmpty()) {
                view.invalidCustomerInput();
                continue;
            }

            if (management.doesCustomerExist(input)) {
                view.customerAlreadyExists(input);
            } else {
                if (management.addCustomer(input)) {
                    view.customerCreatedSuccess();
                } else {
                    view.invalidCustomerInput();
                    continue;
                }
            }

            customerName = input;
        }

        // Frachtstück-Eingabe
        while (inInsertionMode) {
            view.promptInsertCargo(); // Methode in der View, um die Eingabe eines Frachtstücks zu starten
            String command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("back")) {
                inInsertionMode = false;
                view.returnToMainMenu();
                continue;
            }

            String[] parts = command.split(" ");
            if (parts.length < 2) {
                view.invalidInput();
                continue;
            }

            // Frachtstück erstellen
            parts[1] = customerName; // Setze den Kundennamen automatisch
            createCargo(parts);
        }
    }


    private void createCargo(String[] parts) {
        try {
            // Überprüfen, ob die Eingabe gültig ist
            if (parts.length < 3) {
                view.invalidInput();
                return;
            }

            String cargoType = parts[0].toLowerCase();
            String customerName = parts[1];

            // Überprüfen, ob der Kunde existiert
            CustomerImpl customer = null;
            for (CustomerImpl c : management.getAllCustomers()) {
                if (c.getName().equals(customerName)) {
                    customer = c;
                    break;
                }
            }

            if (customer == null) {
                view.customerDoesNotExistError(customerName);
                return;
            }

            BigDecimal value = new BigDecimal(parts[2]);
            List<Hazard> hazards = parseHazards(parts[3]);

            Boolean fragile = null;
            Boolean pressurized = null;
            Integer grainSize = null;

            // Cargo-spezifische Eingaben je nach Cargo-Typ
            switch (cargoType) {
                case "liquidanddrybulkcargo":
                    pressurized = getBooleanInput("Pressurized? (true/false): ");
                    if (pressurized == null) {
                        view.missingPressurized();
                        return;
                    }
                    grainSize = getIntegerInput("Grain size: ");
                    if (grainSize == null) {
                        view.missingGrainSize();
                        return;
                    }
                    break;

                case "unitisedcargo":
                    fragile = getBooleanInput("Fragile? (true/false): ");
                    if (fragile == null) {
                        view.missingFragile();
                        return;
                    }
                    break;

                case "liquidbulkandunitisedcargo":
                    fragile = getBooleanInput("Fragile? (true/false): ");
                    if (fragile == null) {
                        view.missingFragile();
                        return;
                    }
                    pressurized = getBooleanInput("Pressurized? (true/false): ");
                    if (pressurized == null) {
                        view.missingPressurized();
                        return;
                    }
                    break;

                case "drybulkandunitisedcargo":
                    grainSize = getIntegerInput("Grain size: ");
                    if (grainSize == null) {
                        view.missingGrainSize();
                        return;
                    }
                    fragile = getBooleanInput("Fragile? (true/false): ");
                    if (fragile == null) {
                        view.missingFragile();
                        return;
                    }
                    break;

                case "drybulkcargo":
                    grainSize = getIntegerInput("Grain size: ");
                    if (grainSize == null) {
                        view.missingGrainSize();
                        return;
                    }
                    break;

                case "liquidbulkcargo":
                    pressurized = getBooleanInput("Pressurized? (true/false): ");
                    if (pressurized == null) {
                        view.missingPressurized();
                        return;
                    }
                    break;

                default:
                    view.invalidCargoType();
                    return;
            }

            // Überprüfen, ob es freien Speicherplatz gibt
            int storageLocation = management.findAvailableStorageLocation();
            if (storageLocation == -1) {
                view.noAvailableStorageSpace();
                return;
            }

            StorableCargos cargo = createCargoInstance(cargoType, value, hazards, fragile, pressurized, grainSize);
            if (cargo != null) {
                customer.addCargo(cargo);
                storage.insert(cargo);
                view.cargoCreatedSuccess();
            } else {
                view.invalidCargoType();
            }
        } catch (Exception e) {
            view.invalidInput();
        }
    }

    private Boolean getBooleanInput(String prompt) {
        view.displayMessage(prompt);
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("true") ? Boolean.TRUE : input.equals("false") ? Boolean.FALSE : null;
    }

    private Integer getIntegerInput(String prompt) {
        view.displayMessage(prompt);
        String input = scanner.nextLine().trim();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            view.invalidInput();
            return null;
        }
    }
    private StorableCargos createCargoInstance(String cargoType, BigDecimal value, List<Hazard> hazards, Boolean fragile, Boolean pressurized, Integer grainSize) {
        switch (cargoType) {
            case "liquidanddrybulkcargo":
                return new LiquidAndDryBulkCargoImpl(value, hazards, null, Duration.ofDays(30), new Date(), -1, grainSize, pressurized);
            case "unitisedcargo":
                return new UnitisedCargoImpl(value, hazards, null, Duration.ofDays(30), new Date(), -1, fragile);
            case "liquidbulkandunitisedcargo":
                return new LiquidBulkAndUnitisedCargoImpl(value, hazards, null, Duration.ofDays(30), new Date(), -1, fragile, pressurized);
            case "drybulkandunitisedcargo":
                return new DryBulkAndUnitisedCargoImpl(value, hazards, null, Duration.ofDays(30), new Date(), -1, grainSize, fragile);
            case "drybulkcargo":
                return new DryBulkCargoImpl(value, hazards, null, Duration.ofDays(30), new Date(), -1, grainSize);
            case "liquidbulkcargo":
                return new LiquidBulkCargoImpl(value, hazards, null, Duration.ofDays(30), new Date(), -1, pressurized);
            default:
                return null;
        }
    }

    private List<Hazard> parseHazards(String input) {
        if (input.trim().equals(",")) {
            return Collections.emptyList();
        }

        String[] hazardStrings = input.split(",");
        List<Hazard> hazards = new ArrayList<>();
        for (String hazardString : hazardStrings) {
            try {
                Hazard hazard = Hazard.valueOf(hazardString.trim().toUpperCase());
                hazards.add(hazard);
            } catch (IllegalArgumentException e) {
                view.invalidHazard(hazardString.trim());
            }
        }
        return hazards;
    }


    private void deleteMode() {
        view.displayDeleteModeInstructions();

        while (true) {
            view.promptDeleteCommand();
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("back")) {
                view.returnToMainMenu();
                break;
            }

            if (!input.isEmpty() && !input.contains(" ")) {
                boolean success = management.deleteCustomer(input);

                if (success) {
                    view.customerDeletedSuccess(input);
                } else {
                    view.customerDoesNotExistError(input);
                }
            }

            else {
                try {
                    int location = Integer.parseInt(input);
                    StorableCargos deletedCargo = storage.delete(location);
                    if (deletedCargo != null) {
                        view.deleteSuccess(deletedCargo);
                    } else {
                        view.noCargoAtLocation();
                    }

                } catch (NumberFormatException e) {
                    view.invalidDeleteInput();
                }
            }
        }
    }




    // Display Mode
    private void displayMode() {
        view.displayDisplayModeInstructions();

        while (true) {
            view.promptDisplayCommand();
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("back")) {
                view.returnToMainMenu();
                break;
            }

            switch (input) {
                case "customers" -> displayCustomers();
                case "cargos" -> displayCargos();
                case "hazards" -> displayHazards();
                default -> {
                    try {
                        int cargoId = Integer.parseInt(input);
                        StorableCargos cargo = storage.get(cargoId);
                        if (cargo != null) {
                            view.display(List.of(cargo));
                        } else {
                            view.noCargosFound();
                        }
                    } catch (NumberFormatException e) {
                        view.invalidInput();
                    }
                }
            }
        }
    }

    private void displayCustomers() {
        List<CustomerImpl> customers = management.getAllCustomers();

        if (customers.isEmpty()) {
            view.noCustomersFound();
            return;
        }

        for (CustomerImpl customer : customers) {
            String customerName = customer.getName();
            int cargoCount = customer.getCargoCount();
            view.displayCustomerDetails(customerName, cargoCount);

            // Optional: Frachtstücke des Kunden anzeigen
            if (cargoCount > 0) {
                view.displayCustomerCargos(customerName, customer.getCargos());
            }
        }
    }


    private void displayCargos() {
        view.promptCargoTypeFilter();
        String typeFilter = scanner.nextLine().toLowerCase();

        var cargos = storage.getList();
        if (!typeFilter.isEmpty()) {
            cargos = cargos.stream()
                    .filter(cargo -> cargo.getClass().getSimpleName().equalsIgnoreCase(typeFilter))
                    .toList();
        }

        if (cargos.isEmpty()) {
            view.noCargosFound();
            return;
        }

        for (var cargo : cargos) {
            long storageDurationInSeconds = cargo.getDurationOfStorage().toSeconds();
            view.displayCargoDetails(
                    cargo.getStorageLocation(),
                    cargo.getLastInspectionDate(),
                    storageDurationInSeconds
            );
        }
    }

    private void displayHazards() {
        view.promptHazardFilter();
        String filter = scanner.nextLine().toLowerCase();

        boolean includeHazards = filter.equals("i");

        Set<Hazard> allHazards = EnumSet.allOf(Hazard.class);
        Set<Hazard> relevantHazards = includeHazards ?
                EnumSet.copyOf(allHazards) :
                EnumSet.complementOf(EnumSet.copyOf(allHazards));

        if (relevantHazards.isEmpty()) {
            view.noHazardsFound();
            return;
        }

        List<Hazard> hazardsList = new ArrayList<>(relevantHazards);
        view.displayHazards(hazardsList);
    }

    // Update Mode
    private void updateMode() {
        view.displayUpdateModeInstructions();

        while (true) {
            view.promptUpdateCommand();
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("back")) {
                view.returnToMainMenu();
                break;
            }

            try {
                view.promptUpdateLocation();
                int location = Integer.parseInt(scanner.nextLine());

                boolean success = storage.updateInspectionDate(location);
                view.update(success);
            } catch (NumberFormatException e) {
                view.invalidUpdateInput();
            }
        }
    }
}
