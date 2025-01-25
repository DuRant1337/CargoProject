package implementation;

import administration.Customer;
import cargo.Hazard;
import cargo.LiquidBulkAndUnitisedCargo;
import domainLogic.StorableCargos;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class LiquidBulkAndUnitisedCargoImpl extends CombinedCargoImpl {

    public LiquidBulkAndUnitisedCargoImpl(BigDecimal value, List<Hazard> hazards, Customer owner,
                                          Duration storageDuration, Date lastInspectionDate,
                                          int storageLocation, boolean fragile, boolean pressurized) {
        // Aufruf des Konstruktors der Basisklasse
        super(value, hazards, owner, storageDuration, lastInspectionDate, storageLocation, fragile, pressurized, 0);
    }
}
