package domainLogic;

import implementation.CustomerImpl;
import implementation.DryBulkCargoImpl;
import implementation.LiquidBulkCargoImpl;
import implementation.DryBulkAndUnitisedCargoImpl;
import cargo.Hazard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ManagementTest {
    private Management management;

    @BeforeEach
    void setUp() {
        management = new Management(1000);
    }

    @Test
    void testAddCustomer() {
        assertTrue(management.addCustomer("Customer1"));
        assertFalse(management.addCustomer("Customer1")); // Kunde bereits vorhanden
        assertFalse(management.addCustomer("")); // Leerer Name
        assertFalse(management.addCustomer(null)); // Null als Name
    }

    @Test
    void testAddCargo() {
        CustomerImpl customer = new CustomerImpl("Customer1");
        management.addCustomer(customer.getName());

        LiquidBulkCargoImpl liquidCargo = new LiquidBulkCargoImpl(
                BigDecimal.valueOf(200),
                Collections.emptyList(),
                customer,
                Duration.ofDays(30),
                null,
                0,
                true
        );

        assertTrue(management.addCargo(liquidCargo)); // Cargo erfolgreich hinzugefügt
        assertEquals(200, management.getCurrentCapacity());

        DryBulkCargoImpl largeCargo = new DryBulkCargoImpl(
                BigDecimal.valueOf(900),
                Collections.emptyList(),
                customer,
                Duration.ofDays(30),
                null,
                0,
                50
        );

        assertFalse(management.addCargo(largeCargo)); // Überschreitet die maximale Kapazität
    }

    @Test
    void testGetAllCustomersWithCargoCount() {
        management.addCustomer("Customer1");
        management.addCustomer("Customer2");

        assertEquals(2, management.getAllCustomersWithCargoCount().size());
        assertTrue(management.getAllCustomersWithCargoCount().contains("Customer1 - 0 Frachtstücke"));
    }

    @Test
    void testGetCargosByType() {
        CustomerImpl customer = new CustomerImpl("Customer1");
        management.addCustomer(customer.getName());

        DryBulkAndUnitisedCargoImpl dryCargo = new DryBulkAndUnitisedCargoImpl(
                BigDecimal.valueOf(100),
                Collections.emptyList(),
                customer,
                Duration.ofDays(30),
                null,
                0,
                50,
                true
        );

        management.addCargo(dryCargo);

        List<StorableCargos> result = management.getCargosByType("DryBulkAndUnitisedCargoImpl");
        assertEquals(1, result.size());
        assertEquals(dryCargo, result.get(0));
    }

    @Test
    void testGetAllHazards() {
        CustomerImpl customer = new CustomerImpl("Customer1");
        management.addCustomer(customer.getName());

        // Verwende direkt die Enum-Werte
        List<Hazard> hazards = List.of(Hazard.flammable);

        LiquidBulkCargoImpl cargo = new LiquidBulkCargoImpl(
                BigDecimal.valueOf(100),
                hazards,
                customer,
                Duration.ofDays(30),
                null,
                0,
                true
        );

        management.addCargo(cargo);

        // Überprüfen, ob die Hazards korrekt abgerufen wurden
        Set<Hazard> result = management.getAllHazards();
        assertEquals(1, result.size());
        assertTrue(result.contains(Hazard.flammable));
    }


    @Test
    void testDeleteCustomer() {
        management.addCustomer("Customer1");
        CustomerImpl customer = new CustomerImpl("Customer1");

        DryBulkCargoImpl cargo = new DryBulkCargoImpl(
                BigDecimal.valueOf(100),
                Collections.emptyList(),
                customer,
                Duration.ofDays(30),
                null,
                0,
                50
        );

        management.addCargo(cargo);
        assertEquals(100, management.getCurrentCapacity());
        assertTrue(management.deleteCustomer("Customer1"));
        assertEquals(0, management.getCurrentCapacity());
        assertFalse(management.deleteCustomer("Customer1")); // Kunde existiert nicht mehr
    }

    @Test
    void testRemoveCargo() {
        CustomerImpl customer = new CustomerImpl("Customer1");
        management.addCustomer(customer.getName());

        DryBulkCargoImpl cargo = new DryBulkCargoImpl(
                BigDecimal.valueOf(100),
                Collections.emptyList(),
                customer,
                Duration.ofDays(30),
                null,
                0,
                50
        );

        management.addCargo(cargo);
        assertEquals(100, management.getCurrentCapacity());
        assertTrue(management.removeCargo(cargo.getStorageLocation()));
        assertEquals(0, management.getCurrentCapacity());
        assertFalse(management.removeCargo(cargo.getStorageLocation())); // Cargo existiert nicht mehr
    }
}
