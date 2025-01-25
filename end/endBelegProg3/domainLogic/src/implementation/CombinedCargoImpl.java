package implementation;

import administration.Customer;
import cargo.Hazard;
import domainLogic.StorableCargos;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public abstract class CombinedCargoImpl extends StorableCargos {

        // Konstruktor der Basisklasse, der alle notwendigen Parameter übernimmt
        public CombinedCargoImpl(BigDecimal value, List<Hazard> hazards, Customer owner,
                                 Duration storageDuration, Date lastInspectionDate,
                                 int storageLocation, boolean fragile, boolean pressurized, Integer grainSize) {
            super(value, hazards, owner, storageDuration, lastInspectionDate, storageLocation, new Date(), fragile, pressurized, grainSize);
        }
    }

