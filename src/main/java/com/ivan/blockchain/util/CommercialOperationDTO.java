package com.ivan.blockchain.util;

import java.math.BigInteger;

public class CommercialOperationDTO {
    private BigInteger id;
    private String operationType;
    private String status;
    private String seller;

    public CommercialOperationDTO() {
    }

    public CommercialOperationDTO(BigInteger id, String operationType, String status, String seller, String buyer, BigInteger blockNumber, BigInteger blockTimestamp) {
        this.id = id;
        this.operationType = operationType;
        this.status = status;
        this.seller = seller;
        this.buyer = buyer;
        this.blockNumber = blockNumber;
        this.blockTimestamp = blockTimestamp;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
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

    private String buyer;
    private BigInteger blockNumber;
    private BigInteger blockTimestamp;
}