package com.ivan.blockchain.util;

import java.math.BigInteger;

import com.ivan.blockchain.SmartContracts.Resource;

public class ResourceResponseDTO {
    private BigInteger resourceId;
    private BigInteger creationDate;
    private String creator;
    private String originLocation;
    private String currentLocation;
    private String currentOwner;
    private BigInteger lastUpdateDate;
    private BigInteger blockNumber;
    private BigInteger blockTimestamp;

    // Constructeur pour faciliter la conversion depuis ResourceData
    public ResourceResponseDTO(Resource.ResourceData data) {
        this.resourceId = data.resourceId;
        this.creationDate = data.creationDate;
        this.creator = data.creator;
        this.originLocation = data.originLocation;
        this.currentLocation = data.currentLocation;
        this.currentOwner = data.currentOwner;
        this.lastUpdateDate = data.lastUpdateDate;
        this.blockNumber = data.blockNumber;
        this.blockTimestamp = data.blockTimestamp;
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

    public BigInteger getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(BigInteger blockNumber) {
        this.blockNumber = blockNumber;
    }

    public BigInteger getBlockTimestamp() {
        return blockTimestamp;
    }

    public void setBlockTimestamp(BigInteger blockTimestamp) {
        this.blockTimestamp = blockTimestamp;
    }
}
