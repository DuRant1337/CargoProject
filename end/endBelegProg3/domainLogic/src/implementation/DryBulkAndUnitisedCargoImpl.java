package implementation;

import administration.Customer;
import cargo.Hazard;
import cargo.DryBulkAndUnitisedCargo;
import domainLogic.StorableCargos;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class DryBulkAndUnitisedCargoImpl extends CombinedCargoImpl {

    public DryBulkAndUnitisedCargoImpl(BigDecimal value, List<Hazard> hazards, Customer owner,
                                       Duration storageDuration, Date lastInspectionDate,
                                       int storageLocation, Integer grainSize, boolean fragile) {
        // Aufruf des Konstruktors der Basisklasse
        super(value, hazards, owner, storageDuration, lastInspectionDate, storageLocation, fragile, false, grainSize);
    }
}
