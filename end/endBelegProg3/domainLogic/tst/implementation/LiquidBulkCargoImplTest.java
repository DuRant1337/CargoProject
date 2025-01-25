package implementation;

import administration.Customer;
import cargo.Hazard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LiquidBulkCargoImplTest {

    private LiquidBulkCargoImpl liquidBulkCargo;
    private BigDecimal value;
    private List<Hazard> hazards;
    private Customer owner;
    private Duration storageDuration;
    private Date lastInspectionDate;
    private int storageLocation;
    private boolean pressurized;

    @BeforeEach
    void setUp() {
        value = BigDecimal.valueOf(200.75);
        hazards = Arrays.asList(Hazard.flammable, Hazard.radioactive);
        owner = new CustomerImpl("Pavel A");
        storageDuration = Duration.ofDays(45);
        lastInspectionDate = new Date();
        storageLocation = 7;
        pressurized = true;

        liquidBulkCargo = new LiquidBulkCargoImpl(value, hazards, owner, storageDuration, lastInspectionDate, storageLocation, pressurized);
    }

    @Test
    void testIsPressurized() {
        assertTrue(liquidBulkCargo.isPressurized());
    }

    @Test
    void testGetValue() {
        assertEquals(value, liquidBulkCargo.getValue());
    }

    @Test
    void testGetHazards() {
        assertEquals(hazards, liquidBulkCargo.getHazards());
    }

    @Test
    void testGetOwner() {
        assertEquals(owner, liquidBulkCargo.getOwner());
    }

    @Test
    void testGetDurationOfStorage() {
        assertEquals(storageDuration, liquidBulkCargo.getDurationOfStorage());
    }

    @Test
    void testGetLastInspectionDate() {
        assertEquals(lastInspectionDate, liquidBulkCargo.getLastInspectionDate());
    }

    @Test
    void testGetStorageLocation() {
        assertEquals(storageLocation, liquidBulkCargo.getStorageLocation());
    }

    @Test
    void testSetStorageLocation() {
        int newStorageLocation = 15;
        liquidBulkCargo.setStorageLocation(newStorageLocation);
        assertEquals(newStorageLocation, liquidBulkCargo.getStorageLocation());
    }

    @Test
    void testSetInsertDate() {
        Date newInsertDate = new Date();
        liquidBulkCargo.setInsertDate(newInsertDate);
        assertEquals(newInsertDate, liquidBulkCargo.getInsertDate());
    }

    @Test
    void testSetLastInspectionDate() {
        Date newInspectionDate = new Date();
        liquidBulkCargo.setLastInspectionDate(newInspectionDate);
        assertEquals(newInspectionDate, liquidBulkCargo.getLastInspectionDate());
    }
}
