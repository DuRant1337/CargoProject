package implementation;

import administration.Customer;
import cargo.Hazard;
import cargo.LiquidAndDryBulkCargo;
import domainLogic.StorableCargos;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;
import java.util.List;


    public class LiquidAndDryBulkCargoImpl extends CombinedCargoImpl {

        public LiquidAndDryBulkCargoImpl(BigDecimal value, List<Hazard> hazards, Customer owner,
                                         Duration storageDuration, Date lastInspectionDate,
                                         int storageLocation, int grainSize, boolean pressurized) {
            // Aufruf des Konstruktors der Basisklasse
            super(value, hazards, owner, storageDuration, lastInspectionDate, storageLocation, false, pressurized, grainSize);
        }
    }
