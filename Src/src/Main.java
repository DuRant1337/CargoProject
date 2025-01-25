import domainLogic.Management;
import domainLogic.Storage;
import cli.CargoController;
import cli.CargoView;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage();
        long maxCapacity = 1000; // Beispielwert
        Management management = new Management(maxCapacity);// Speicher für die Frachtstücke
        CargoView view = new CargoView(); // Ansicht, um dem Benutzer Nachrichten anzuzeigen
        Scanner scanner = new Scanner(System.in);  // Scanner für die Benutzereingabe

        // Controller initialisieren und starten
        CargoController controller = new CargoController(storage,management, view, scanner);
        controller.start();
    }
}