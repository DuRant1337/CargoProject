package domainLogic;

import administration.Customer;
import cargo.Hazard;
import implementation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StorableCargosTest {
    private StorableCargos liquidBulkCargo;
    private StorableCargos unitisedCargo;
    private StorableCargos dryBulkAndUnitisedCargo;

    @BeforeEach
    void setUp() {
        Customer customer = new CustomerImpl("Pavel A");
        List<Hazard> hazards = new ArrayList<>();
        hazards.add(Hazard.explosive);
        hazards.add(Hazard.toxic);

        liquidBulkCargo = new LiquidBulkCargoImpl(
                BigDecimal.valueOf(1000),
                hazards,
                customer,
                Duration.ofDays(30),
                new Date(),
                0,
                true
        );

        unitisedCargo = new UnitisedCargoImpl(
                BigDecimal.valueOf(2000),
                hazards,
                customer,
                Duration.ofDays(60),
                new Date(),
                1,
                true
        );

        dryBulkAndUnitisedCargo = new DryBulkAndUnitisedCargoImpl(
                BigDecimal.valueOf(1500),
                hazards,
                customer,
                Duration.ofDays(45),
                new Date(),
                2,
                10,
                false
        );
    }

    @Test
    void storableCargos_exist() {
        assertNotNull(liquidBulkCargo);
        assertNotNull(unitisedCargo);
        assertNotNull(dryBulkAndUnitisedCargo);
    }

    @Test
    void getValue_test() {
        assertEquals(BigDecimal.valueOf(1000), liquidBulkCargo.getValue());
        assertEquals(BigDecimal.valueOf(2000), unitisedCargo.getValue());
        assertEquals(BigDecimal.valueOf(1500), dryBulkAndUnitisedCargo.getValue());
    }

    @Test
    void getHazards_test() {
        assertNotNull(liquidBulkCargo.getHazards());
        assertEquals(2, liquidBulkCargo.getHazards().size());

        assertNotNull(unitisedCargo.getHazards());
        assertEquals(2, unitisedCargo.getHazards().size());

        assertNotNull(dryBulkAndUnitisedCargo.getHazards());
        assertEquals(2, dryBulkAndUnitisedCargo.getHazards().size());
    }

    @Test
    void getOwner_test() {
        assertNotNull(liquidBulkCargo.getOwner());
        assertEquals("Pavel A", liquidBulkCargo.getOwner().getName());

        assertNotNull(unitisedCargo.getOwner());
        assertEquals("Pavel A", unitisedCargo.getOwner().getName());

        assertNotNull(dryBulkAndUnitisedCargo.getOwner());
        assertEquals("Pavel A", dryBulkAndUnitisedCargo.getOwner().getName());
    }

    @Test
    void getDurationOfStorage_test() {
        assertEquals(Duration.ofDays(30), liquidBulkCargo.getDurationOfStorage());
        assertEquals(Duration.ofDays(60), unitisedCargo.getDurationOfStorage());
        assertEquals(Duration.ofDays(45), dryBulkAndUnitisedCargo.getDurationOfStorage());
    }

    @Test
    void getLastInspectionDate_test() {
        assertNotNull(liquidBulkCargo.getLastInspectionDate());
        assertNotNull(unitisedCargo.getLastInspectionDate());
        assertNotNull(dryBulkAndUnitisedCargo.getLastInspectionDate());
    }

    @Test
    void getStorageLocation_test() {
        assertEquals(0, liquidBulkCargo.getStorageLocation());
        assertEquals(1, unitisedCargo.getStorageLocation());
        assertEquals(2, dryBulkAndUnitisedCargo.getStorageLocation());
    }

    @Test
    void specificProperties_test() {
        assertTrue(((LiquidBulkCargoImpl) liquidBulkCargo).isPressurized());
        assertTrue(((UnitisedCargoImpl) unitisedCargo).isFragile());
        assertEquals(10, ((DryBulkAndUnitisedCargoImpl) dryBulkAndUnitisedCargo).getGrainSize());
        assertFalse(((DryBulkAndUnitisedCargoImpl) dryBulkAndUnitisedCargo).isFragile());
    }
}
