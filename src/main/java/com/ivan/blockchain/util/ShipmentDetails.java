package com.ivan.blockchain.util;

public class ShipmentDetails {

    private String sender;
    private String receiver;
    private long creationDate;
    private long departureDate;

    private int status;
    private int packageWeight;
    private String depot;
    private byte[] receiverHash;

    public ShipmentDetails(String sender, String receiver, long creationDate, long departureDate, int status, int packageWeight, String depot, byte[] receiverHash) {
        this.sender = sender;
        this.receiver = receiver;
        this.creationDate = creationDate;
        this.departureDate = departureDate;
        this.status= status;
        this.packageWeight = packageWeight;
        this.depot = depot;
        this.receiverHash = receiverHash;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(long departureDate) {
        this.departureDate = departureDate;
    }

    public int getPackageWeight() {
        return packageWeight;
    }

    public void setPackageWeight(int packageWeight) {
        this.packageWeight = packageWeight;
    }

    public String getDepot() {
        return depot;
    }

    public void setDepot(String depot) {
        this.depot = depot;
    }

    public byte[] getReceiverHash() {
        return receiverHash;
    }

    public void setReceiverHash(byte[] receiverHash) {
        this.receiverHash = receiverHash;
    }
// Getters and setters...
}
