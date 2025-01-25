package implementation;

import administration.Customer;
import cargo.Hazard;
import cargo.DryBulkCargo;
import domainLogic.StorableCargos;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class DryBulkCargoImpl extends StorableCargos implements DryBulkCargo {

    public DryBulkCargoImpl(BigDecimal value, List<Hazard> hazards, Customer owner,
                            Duration storageDuration, Date lastInspectionDate,
                            int storageLocation, int grainSize) {
        super(value, hazards, owner, storageDuration, lastInspectionDate, storageLocation, new Date(), false, false, grainSize);
    }

    @Override
    public Integer getGrainSize() {
        return super.getGrainSize();
    }

    @Override
    public BigDecimal getValue() {
        return super.getValue();
    }

    @Override
    public Collection<Hazard> getHazards() {
        return super.getHazards();
    }

    @Override
    public Customer getOwner() {
        return super.getOwner();
    }

    @Override
    public Duration getDurationOfStorage() {
        return super.getDurationOfStorage();
    }

    @Override
    public Date getLastInspectionDate() {
        return super.getLastInspectionDate();
    }

    @Override
    public int getStorageLocation() {
        return super.getStorageLocation();
    }

    public void setStorageLocation(int storageLocation) {
        super.setStorageLocation(storageLocation);
    }

    public void setInsertDate(Date insertDate) {
        super.setInsertDate(insertDate);
    }

    public Date getInsertDate() {
        return super.getInsertDate();
    }

    public void setLastInspectionDate(Date lastInspectionDate) {
        super.setLastInspectionDate(lastInspectionDate);
    }
}
