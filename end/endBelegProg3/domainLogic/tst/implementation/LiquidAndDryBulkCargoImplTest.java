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

class LiquidAndDryBulkCargoImplTest {
    private LiquidAndDryBulkCargoImpl liquidAndDryBulkCargo;
    private BigDecimal value;
    private List<Hazard> hazards;
    private Customer owner;
    private Duration storageDuration;
    private Date lastInspectionDate;
    private int storageLocation;
    private Integer grainSize;
    private boolean pressurized;

    @BeforeEach
    void setUp() {
        value = new BigDecimal("5000.00");
        hazards = Arrays.asList(Hazard.explosive, Hazard.flammable);
        owner = new CustomerImpl("Pavel A");
        storageDuration = Duration.ofDays(60);
        lastInspectionDate = new Date();
        storageLocation = 15;
        grainSize = 20;
        pressurized = true;

        liquidAndDryBulkCargo = new LiquidAndDryBulkCargoImpl(value, hazards, owner, storageDuration, lastInspectionDate, storageLocation, grainSize, pressurized);
    }

    @Test
    void testGetGrainSize() {
        assertEquals(grainSize, liquidAndDryBulkCargo.getGrainSize());
    }

    @Test
    void testIsPressurized() {
        assertTrue(liquidAndDryBulkCargo.isPressurized());
    }

    @Test
    void testGetValue() {
        assertEquals(value, liquidAndDryBulkCargo.getValue());
    }

    @Test
    void testGetHazards() {
        assertEquals(hazards, liquidAndDryBulkCargo.getHazards());
    }

    @Test
    void testGetOwner() {
        assertEquals(owner, liquidAndDryBulkCargo.getOwner());
    }

    @Test
    void testGetDurationOfStorage() {
        assertEquals(storageDuration, liquidAndDryBulkCargo.getDurationOfStorage());
    }

    @Test
    void testGetLastInspectionDate() {
        assertEquals(lastInspectionDate, liquidAndDryBulkCargo.getLastInspectionDate());
    }

    @Test
    void testGetStorageLocation() {
        assertEquals(storageLocation, liquidAndDryBulkCargo.getStorageLocation());
    }
}