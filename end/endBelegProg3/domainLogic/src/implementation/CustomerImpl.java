package implementation;

import administration.Customer;
import domainLogic.StorableCargos;

import java.util.ArrayList;
import java.util.List;

public class CustomerImpl implements Customer {
    private String name;
    private List<StorableCargos> cargos; // Liste für die Frachtstücke

    public CustomerImpl(String name) {
        this.name = name;
        this.cargos = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Methode zum Hinzufügen eines Frachtstücks
    public void addCargo(StorableCargos cargo) {
        if (cargo != null) {
            cargos.add(cargo);  // Frachtstück zur Liste des Kunden hinzufügen
        }
    }

    // Methode zum Entfernen eines Frachtstücks
    public boolean removeCargo(StorableCargos cargo) {
        return cargos.remove(cargo);
    }

    // Methode zum Abrufen der Anzahl der Frachtstücke
    public int getCargoCount() {
        return cargos.size();
    }

    // Methode zum Abrufen aller Frachtstücke
    public List<StorableCargos> getCargos() {
        return new ArrayList<>(cargos); // Rückgabe einer Kopie der Liste
    }
}
