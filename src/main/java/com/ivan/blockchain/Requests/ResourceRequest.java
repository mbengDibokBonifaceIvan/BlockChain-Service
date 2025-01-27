package com.ivan.blockchain.Requests;

import lombok.Data;

@Data
public class ResourceRequest {
    private String originLocation;

    public ResourceRequest() {
    }
    public ResourceRequest(String originLocation) {
        this.originLocation = originLocation;
    }

    public String getOriginLocation() {
        return originLocation;
    }

    public void setOriginLocation(String originLocation) {
        this.originLocation = originLocation;
    }
}
