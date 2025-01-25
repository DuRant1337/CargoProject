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

class LiquidAndUnitisedCargoImplTest {
    private LiquidBulkAndUnitisedCargoImpl liquidBulkAndUnitisedCargo;
    private BigDecimal value;
    private List<Hazard> hazards;
    private Customer owner;
    private Duration storageDuration;
    private Date lastInspectionDate;
    private int storageLocation;
    private boolean fragile;
    private boolean pressurized;

    @BeforeEach
    void setUp() {
        value = new BigDecimal("8000.00");
        hazards = Arrays.asList(Hazard.toxic, Hazard.radioactive);
        owner = new CustomerImpl("Maria Klein");
        storageDuration = Duration.ofDays(45);
        lastInspectionDate = new Date();
        storageLocation = 25;
        fragile = false;
        pressurized = true;

        liquidBulkAndUnitisedCargo = new LiquidBulkAndUnitisedCargoImpl(value, hazards, owner, storageDuration, lastInspectionDate, storageLocation, fragile, pressurized);
    }

    @Test
    void testIsFragile() {
        assertTrue(liquidBulkAndUnitisedCargo.isFragile());
    }

    @Test
    void testIsPressurized() {
        assertFalse(liquidBulkAndUnitisedCargo.isPressurized());
    }

    @Test
    void testGetValue() {
        assertEquals(value, liquidBulkAndUnitisedCargo.getValue());
    }

    @Test
    void testGetHazards() {
        assertEquals(hazards, liquidBulkAndUnitisedCargo.getHazards());
    }

    @Test
    void testGetOwner() {
        assertEquals(owner, liquidBulkAndUnitisedCargo.getOwner());
    }

    @Test
    void testGetDurationOfStorage() {
        assertEquals(storageDuration, liquidBulkAndUnitisedCargo.getDurationOfStorage());
    }

    @Test
    void testGetLastInspectionDate() {
        assertEquals(lastInspectionDate, liquidBulkAndUnitisedCargo.getLastInspectionDate());
    }

    @Test
    void testGetStorageLocation() {
        assertEquals(storageLocation, liquidBulkAndUnitisedCargo.getStorageLocation());
    }
}
