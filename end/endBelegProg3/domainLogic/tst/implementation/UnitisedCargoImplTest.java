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

class UnitisedCargoImplTest {

    private UnitisedCargoImpl unitisedCargo;
    private BigDecimal value;
    private List<Hazard> hazards;
    private Customer owner;
    private Duration storageDuration;
    private Date lastInspectionDate;
    private int storageLocation;
    private boolean fragile;

    @BeforeEach
    void setUp() {
        value = new BigDecimal("2500.00");
        hazards = Arrays.asList(Hazard.flammable, Hazard.toxic);
        owner = new CustomerImpl("Pavel A");
        storageDuration = Duration.ofDays(30);
        lastInspectionDate = new Date();
        storageLocation = 5;
        fragile = true;

        unitisedCargo = new UnitisedCargoImpl(value, hazards, owner, storageDuration, lastInspectionDate, storageLocation, fragile);
    }

    @Test
    void testIsFragile() {
        assertTrue(unitisedCargo.isFragile());
    }

    @Test
    void testGetValue() {
        assertEquals(new BigDecimal("2500.00"), unitisedCargo.getValue());
    }

    @Test
    void testGetHazards() {
        List<Hazard> expectedHazards = Arrays.asList(Hazard.flammable, Hazard.toxic);
        assertEquals(expectedHazards, unitisedCargo.getHazards());
    }

    @Test
    void testGetOwner() {
        assertEquals("Pavel A", unitisedCargo.getOwner().getName());
    }

    @Test
    void testGetDurationOfStorage() {
        assertEquals(Duration.ofDays(30), unitisedCargo.getDurationOfStorage());
    }

    @Test
    void testGetLastInspectionDate() {
        assertNotNull(unitisedCargo.getLastInspectionDate());
    }

    @Test
    void testGetStorageLocation() {
        assertEquals(5, unitisedCargo.getStorageLocation());
    }

    @Test
    void testSetStorageLocation() {
        unitisedCargo.setStorageLocation(10);
        assertEquals(10, unitisedCargo.getStorageLocation());
    }

    @Test
    void testSetInsertDate() {
        Date newInsertDate = new Date();
        unitisedCargo.setInsertDate(newInsertDate);
        assertEquals(newInsertDate, unitisedCargo.getInsertDate());
    }

    @Test
    void testSetLastInspectionDate() {
        Date newLastInspectionDate = new Date();
        unitisedCargo.setLastInspectionDate(newLastInspectionDate);
        assertEquals(newLastInspectionDate, unitisedCargo.getLastInspectionDate());
    }
}