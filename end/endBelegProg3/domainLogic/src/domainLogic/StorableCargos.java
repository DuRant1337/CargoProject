package domainLogic;

import cargo.Cargo;
import cargo.Hazard;
import administration.Storable;
import administration.Customer;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public abstract class StorableCargos implements Storable, Cargo, Serializable {
    private BigDecimal value;
    private List<Hazard> hazards;
    private Customer owner;
    private Duration storageDuration;
    private Date lastInspectionDate;
    private int storageLocation;
    private Date insertDate;
    private boolean pressurized;
    private boolean fragile;
    private Integer grainSize;


    @Serial
    private static final long serialVersionUID = 1L;

    public StorableCargos(BigDecimal value, List<Hazard> hazards, Customer owner,
                         Duration storageDuration, Date lastInspectionDate,
                         int storageLocation, Date insertDate, boolean pressurized, boolean fragile, Integer grainSize) {
        this.value = value;
        this.hazards = hazards;
        this.owner = owner;
        this.storageDuration = storageDuration;
        this.lastInspectionDate = lastInspectionDate;
        this.storageLocation = storageLocation;
        this.insertDate = insertDate;
        this.pressurized = pressurized;
        this.fragile = fragile;
        this.grainSize = grainSize;
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }

    @Override
    public Collection<Hazard> getHazards() {
        return hazards;
    }

    public boolean hasHazard() {
        return hazards != null && !hazards.isEmpty();
    }
    @Override
    public Customer getOwner() {
        return owner;
    }

    @Override
    public Duration getDurationOfStorage() {
        return storageDuration;
    }

    @Override
    public Date getLastInspectionDate() {
        return lastInspectionDate;
    }

    public void setLastInspectionDate(Date lastInspectionDate) {
        this.lastInspectionDate = lastInspectionDate;
    }

    @Override
    public int getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(int storageLocation) {
        this.storageLocation = storageLocation;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }
    public boolean isPressurized() {
        return pressurized;
    }

    public void setPressurized(boolean pressurized) {
        this.pressurized = pressurized;
    }

    public boolean isFragile() {
        return fragile;
    }

    public void setFragile(boolean fragile) {
        this.fragile = fragile;
    }
    public Integer getGrainSize() {
        return grainSize;
    }

    public void setGrainSize(Integer grainSize) {
        this.grainSize = grainSize;
    }
}


