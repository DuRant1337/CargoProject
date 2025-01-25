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

class DryBulkAndUnitisedCargoImplTest {
    private DryBulkAndUnitisedCargoImpl dryBulkAndUnitisedCargo;
    private BigDecimal value;
    private List<Hazard> hazards;
    private Customer owner;
    private Duration storageDuration;
    private Date lastInspectionDate;
    private int storageLocation;
    private Integer grainSize;
    private boolean fragile;

    @BeforeEach
    void setUp() {
        value = new BigDecimal("3000.00");
        hazards = Arrays.asList(Hazard.explosive, Hazard.toxic);
        owner = new CustomerImpl("Pavel A");
        storageDuration = Duration.ofDays(45);
        lastInspectionDate = new Date();
        storageLocation = 10;
        grainSize = 15;
        fragile = true;

        dryBulkAndUnitisedCargo = new DryBulkAndUnitisedCargoImpl(value, hazards, owner, storageDuration, lastInspectionDate, storageLocation, grainSize, fragile);
    }

    @Test
    void testGetGrainSize() {
        assertEquals(grainSize, dryBulkAndUnitisedCargo.getGrainSize());
    }

    @Test
    void testIsFragile() {
        assertTrue(dryBulkAndUnitisedCargo.isFragile());
    }

    @Test
    void testGetValue() {
        assertEquals(value, dryBulkAndUnitisedCargo.getValue());
    }

    @Test
    void testGetHazards() {
        assertEquals(hazards, dryBulkAndUnitisedCargo.getHazards());
    }

    @Test
    void testGetOwner() {
        assertEquals(owner, dryBulkAndUnitisedCargo.getOwner());
    }

    @Test
    void testGetDurationOfStorage() {
        assertEquals(storageDuration, dryBulkAndUnitisedCargo.getDurationOfStorage());
    }

    @Test
    void testGetLastInspectionDate() {
        assertEquals(lastInspectionDate, dryBulkAndUnitisedCargo.getLastInspectionDate());
    }

    @Test
    void testGetStorageLocation() {
        assertEquals(storageLocation, dryBulkAndUnitisedCargo.getStorageLocation());
    }
}