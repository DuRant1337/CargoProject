package domainLogic;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;

public class Storage implements Serializable {

    private List<StorableCargos> list;

    public Storage() {
        list = new LinkedList<>();
    }

    // Frachtstück hinzufügen
    public boolean insert(StorableCargos storableCargo) {
        if (storableCargo == null) {
            return false;
        }
        return list.add(storableCargo);
    }

    // Frachtstück anhand der StorageLocation löschen
    public StorableCargos delete(int storageLocation) {
        if (list.isEmpty()) {
            return null;
        }

        for (StorableCargos storableCargo : list) {
            if (storableCargo.getStorageLocation() == storageLocation) {
                list.remove(storableCargo);
                return storableCargo;
            }
        }

        return null;
    }

    // Liste der Frachtstücke zurückgeben
    public List<StorableCargos> getList() {
        return new LinkedList<>(list);
    }

    public StorableCargos get(int id) {
        for (StorableCargos storableCargo : list) {
            if (storableCargo.getStorageLocation() == id) {
                return storableCargo;
            }
        }
        return null;
    }


    public boolean updateInspectionDate(int storageLocation) {
        for (StorableCargos storableCargo : list) {
            if (storableCargo.getStorageLocation() == storageLocation) {
                storableCargo.setLastInspectionDate(new Date());
                return true;
            }
        }
        return false;
    }




    public boolean update(int storageLocation, StorableCargos updatedCargo) {
        if (updatedCargo == null || list.isEmpty()) {
            return false;
        }

        for (int i = 0; i < list.size(); i++) {
            StorableCargos storableCargo = list.get(i);
            if (storableCargo.getStorageLocation() == storageLocation) {
                list.set(i, updatedCargo);
                return true;
            }
        }

        return false;
    }

    // Abruf eines Frachtstücks nach StorageLocation
    public StorableCargos getByStorageLocation(int storageLocation) {
        for (StorableCargos storableCargo : list) {
            if (storableCargo.getStorageLocation() == storageLocation) {
                return storableCargo;
            }
        }
        return null;
    }

    // Überprüfen, ob ein Frachtstück vorhanden ist
    public boolean exists(int storageLocation) {
        for (StorableCargos storableCargo : list) {
            if (storableCargo.getStorageLocation() == storageLocation) {
                return true;
            }
        }
        return false;
    }
}
