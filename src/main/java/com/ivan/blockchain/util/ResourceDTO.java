package com.ivan.blockchain.util;

import java.math.BigInteger;
import lombok.Data;
@Data
public class ResourceDTO {
    private BigInteger resourceId;
    private BigInteger creationDate;
    private String creator;
    private String originLocation;
    private String currentLocation;
    private String currentOwner;
    private BigInteger lastUpdateDate;

    public ResourceDTO() {
    }


    public ResourceDTO(BigInteger resourceId, BigInteger creationDate, String creator, String originLocation, String currentLocation, String currentOwner, BigInteger lastUpdateDate) {
        this.resourceId = resourceId;
        this.creationDate = creationDate;
        this.creator = creator;
        this.originLocation = originLocation;
        this.currentLocation = currentLocation;
        this.currentOwner = currentOwner;
        this.lastUpdateDate = lastUpdateDate;
    }

    public BigInteger getResourceId() {
        return resourceId;
    }

    public void setResourceId(BigInteger resourceId) {
        this.resourceId = resourceId;
    }

    public BigInteger getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(BigInteger creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getOriginLocation() {
        return originLocation;
    }

    public void setOriginLocation(String originLocation) {
        this.originLocation = originLocation;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getCurrentOwner() {
        return currentOwner;
    }

    public void setCurrentOwner(String currentOwner) {
        this.currentOwner = currentOwner;
    }

    public BigInteger getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(BigInteger lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
