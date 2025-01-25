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

class DryBulkCargoImplTest {

    private DryBulkCargoImpl dryBulkCargo;
    private BigDecimal value;
    private List<Hazard> hazards;
    private Customer owner;
    private Duration storageDuration;
    private Date lastInspectionDate;
    private int storageLocation;
    private int grainSize;

    @BeforeEach
    void setUp() {
        value = BigDecimal.valueOf(100.50);
        hazards = Arrays.asList(Hazard.explosive, Hazard.toxic);
        owner = new CustomerImpl("Pavel A");
        storageDuration = Duration.ofDays(30);
        lastInspectionDate = new Date();
        storageLocation = 5;
        grainSize = 10;

        dryBulkCargo = new DryBulkCargoImpl(value, hazards, owner, storageDuration, lastInspectionDate, storageLocation, grainSize);
    }

    @Test
    void testGetGrainSize() {
        assertEquals(grainSize, dryBulkCargo.getGrainSize());
    }

    @Test
    void testGetValue() {
        assertEquals(value, dryBulkCargo.getValue());
    }

    @Test
    void testGetHazards() {
        assertEquals(hazards, dryBulkCargo.getHazards());
    }

    @Test
    void testGetOwner() {
        assertEquals(owner, dryBulkCargo.getOwner());
    }

    @Test
    void testGetDurationOfStorage() {
        assertEquals(storageDuration, dryBulkCargo.getDurationOfStorage());
    }

    @Test
    void testGetLastInspectionDate() {
        assertEquals(lastInspectionDate, dryBulkCargo.getLastInspectionDate());
    }

    @Test
    void testGetStorageLocation() {
        assertEquals(storageLocation, dryBulkCargo.getStorageLocation());
    }

    @Test
    void testSetStorageLocation() {
        int newStorageLocation = 10;
        dryBulkCargo.setStorageLocation(newStorageLocation);
        assertEquals(newStorageLocation, dryBulkCargo.getStorageLocation());
    }

    @Test
    void testSetInsertDate() {
        Date newInsertDate = new Date();
        dryBulkCargo.setInsertDate(newInsertDate);
        assertEquals(newInsertDate, dryBulkCargo.getInsertDate());
    }

    @Test
    void testSetLastInspectionDate() {
        Date newInspectionDate = new Date();
        dryBulkCargo.setLastInspectionDate(newInspectionDate);
        assertEquals(newInspectionDate, dryBulkCargo.getLastInspectionDate());
    }
}
