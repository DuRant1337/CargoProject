package implementation;

import administration.Customer;
import cargo.Hazard;
import cargo.LiquidBulkCargo;
import domainLogic.StorableCargos;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class LiquidBulkCargoImpl extends StorableCargos implements LiquidBulkCargo {

    private Date insertDate;

    public LiquidBulkCargoImpl(BigDecimal value, List<Hazard> hazards, Customer owner,
                               Duration storageDuration, Date lastInspectionDate,
                               int storageLocation, boolean pressurized) {
        super(value, hazards, owner, storageDuration, lastInspectionDate, storageLocation, new Date(), pressurized, false, null);
    }

    @Override
    public boolean isPressurized() {
        return super.isPressurized();
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

    public void setLastInspectionDate(Date lastInspectionDate) {
        super.setLastInspectionDate(lastInspectionDate);
    }

    public void setStorageLocation(int storageLocation) {
        super.setStorageLocation(storageLocation);
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Date getInsertDate() {
        return this.insertDate;
    }
}
