package domainLogic;

import implementation.DryBulkCargoImpl;
import implementation.LiquidBulkAndUnitisedCargoImpl;
import administration.Customer;
import cargo.Hazard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {
    private DryBulkCargoImpl cargo1;
    private LiquidBulkAndUnitisedCargoImpl cargo2;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        cargo1 = new DryBulkCargoImpl(
                BigDecimal.valueOf(100),
                new ArrayList<>(),
                null,
                Duration.ofDays(5),
                new Date(),
                1,
                10
        );

        cargo2 = new LiquidBulkAndUnitisedCargoImpl(
                BigDecimal.valueOf(200),
                new ArrayList<>(),
                null,
                Duration.ofDays(5),
                new Date(),
                2,
                true,  // fragile
                false  // pressurized
        );

        storage = new Storage();
    }

    @Test
    public void insert_success() {
        boolean res1 = storage.insert(cargo1);
        boolean res2 = storage.insert(cargo2);

        assertTrue(res1);
        assertTrue(res2);
    }

    @Test
    public void insert_null() {
        boolean res = storage.insert(null);
        assertFalse(res);
    }

    @Test
    public void delete_success() {
        storage.insert(cargo1);
        storage.insert(cargo2);

        StorableCargos deletedCargo1 = storage.delete(1);
        StorableCargos deletedCargo2 = storage.delete(2);

        assertEquals(cargo1, deletedCargo1);
        assertEquals(cargo2, deletedCargo2);
    }

    @Test
    public void delete_nonExistingLocation() {
        StorableCargos deletedCargo = storage.delete(999);
        assertNull(deletedCargo);
    }

    @Test
    public void delete_null() {
        StorableCargos deletedCargo = storage.delete(0);
        assertNull(deletedCargo);
    }

    @Test
    public void getList_exist() {
        List<StorableCargos> cargoList = storage.getList();
        assertEquals(0, cargoList.size());
    }

    @Test
    public void getList_insert() {
        storage.insert(cargo1);
        storage.insert(cargo2);

        List<StorableCargos> cargoList = storage.getList();
        assertEquals(2, cargoList.size());
    }

    @Test
    public void update_success() {
        storage.insert(cargo1);
        storage.insert(cargo2);

        DryBulkCargoImpl updatedCargo1 = new DryBulkCargoImpl(
                BigDecimal.valueOf(150),
                new ArrayList<>(),
                null,
                Duration.ofDays(5),
                new Date(),
                1,
                20
        );

        LiquidBulkAndUnitisedCargoImpl updatedCargo2 = new LiquidBulkAndUnitisedCargoImpl(
                BigDecimal.valueOf(250),
                new ArrayList<>(),
                null,
                Duration.ofDays(5),
                new Date(),
                2,
                false,
                true
        );

        boolean res1 = storage.update(1, updatedCargo1);
        boolean res2 = storage.update(2, updatedCargo2);

        assertTrue(res1);
        assertTrue(res2);
    }

    @Test
    public void update_nonExistingLocation() {
        LiquidBulkAndUnitisedCargoImpl updatedCargo = new LiquidBulkAndUnitisedCargoImpl(
                BigDecimal.valueOf(150),
                new ArrayList<>(),
                null,
                Duration.ofDays(5),
                new Date(),
                999,
                true,
                true
        );
        boolean res = storage.update(999, updatedCargo);
        assertFalse(res);
    }

    @Test
    public void update_null() {
        boolean res = storage.update(0, null);
        assertFalse(res);
    }
}
