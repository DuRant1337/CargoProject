package domainLogic;

import administration.Customer;
import cargo.Hazard;
import implementation.CustomerImpl;

import java.util.*;

public class Management {

    private Map<String, CustomerImpl> customers;  // Map von Kundenname -> Customer
    private Map<Integer, StorableCargos> cargoStorage;  // Map von storageLocation -> StorableCargos
    private long maxCapacity;
    private long currentCapacity;

    public Management(long maxCapacity) {
        this.customers = new HashMap<>();
        this.cargoStorage = new HashMap<>();
        this.maxCapacity = maxCapacity;
        this.currentCapacity = 0;
    }

    public synchronized boolean addCustomer(String customerName) {
        if (customerName == null || customerName.isEmpty() || customers.containsKey(customerName)) {
            return false;
        }
        customers.put(customerName, new CustomerImpl(customerName));
        return true;
    }

    public synchronized boolean addCargo(StorableCargos cargo) {
        if (cargo == null || cargo.getOwner() == null || !customers.containsKey(cargo.getOwner().getName())) {
            return false;
        }

        if ((maxCapacity - (currentCapacity + cargo.getValue().longValue())) < 0) {
            return false;
        }


        int storageLocation = findAvailableStorageLocation();
        if (storageLocation == -1) {
            return false;
        }


        cargo.setStorageLocation(storageLocation);
        cargo.setInsertDate(new Date());
        cargoStorage.put(storageLocation, cargo);

        currentCapacity += cargo.getValue().longValue();
        customers.get(cargo.getOwner().getName()).addCargo(cargo);

        return true;
    }
    public synchronized boolean doesCustomerExist(String customerName) {
        return customers.containsKey(customerName);
    }

    // Abruf aller Kunden mit der Anzahl ihrer Frachtstücke
    public synchronized List<String> getAllCustomersWithCargoCount() {
        List<String> result = new ArrayList<>();
        for (CustomerImpl customer : customers.values()) {
            result.add(customer.getName() + " - " + customer.getCargoCount() + " Frachtstücke");
        }
        return result;
    }

    // Abruf eingelagerter Frachtstücke nach Typ
    public synchronized List<StorableCargos> getCargosByType(String type) {
        List<StorableCargos> result = new ArrayList<>();
        for (StorableCargos cargo : cargoStorage.values()) {
            if (cargo.getClass().getSimpleName().equals(type)) {
                result.add(cargo);
            }
        }
        return result;
    }

    // Abruf aller vorhandenen Gefahrenstoffe
    public synchronized Set<Hazard> getAllHazards() {
        Set<Hazard> hazards = new HashSet<>();
        for (StorableCargos cargo : cargoStorage.values()) {
            hazards.addAll(cargo.getHazards());
        }
        return hazards;
    }

    // Setzen des Datums der letzten Überprüfung
    public synchronized boolean setLastInspectionDate(int storageLocation, Date inspectionDate) {
        StorableCargos cargo = cargoStorage.get(storageLocation);
        if (cargo != null) {
            cargo.setLastInspectionDate(inspectionDate);
            return true;
        }
        return false;
    }

    // Löschen eines Kunden
    public synchronized boolean deleteCustomer(String customerName) {
        CustomerImpl customer = customers.get(customerName);
        if (customer == null) {
            return false;
        }

        // Alle Frachtstücke des Kunden entfernen
        for (StorableCargos cargo : customer.getCargos()) {
            cargoStorage.remove(cargo.getStorageLocation());
            currentCapacity -= cargo.getValue().longValue();
        }

        customers.remove(customerName);
        return true;
    }

    // Entfernen eines Frachtstücks
    public synchronized boolean removeCargo(int storageLocation) {
        StorableCargos cargo = cargoStorage.get(storageLocation);
        if (cargo == null) {
            return false;
        }

        // Cargo aus Lager und Kunden entfernen
        CustomerImpl customer = customers.get(cargo.getOwner().getName());
        if (customer != null) {
            customer.removeCargo(cargo);
        }
        cargoStorage.remove(storageLocation);
        currentCapacity -= cargo.getValue().longValue();
        return true;
    }

    // Aktuelle Kapazität abrufen
    public synchronized long getCurrentCapacity() {
        return currentCapacity;
    }

    // Maximale Kapazität abrufen
    public synchronized long getMaxCapacity() {
        return maxCapacity;
    }

    public synchronized List<CustomerImpl> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }
    public CustomerImpl getCustomer(String name) {
        for (Map.Entry<String, CustomerImpl> entry : customers.entrySet()) {
            if (entry.getKey().equals(name)) {
                return entry.getValue();
            }
        }
        return null;
    }



    // Hilfsmethode: Sucht einen freien Speicherplatz
    public int findAvailableStorageLocation() {
        for (int i = 1; i <= maxCapacity; i++) {
            if (!cargoStorage.containsKey(i)) {
                return i;
            }
        }
        return -1;
    }
}
