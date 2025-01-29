package com.ivan.blockchain.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceStateRequest {

    private BigInteger identification;
    private BigInteger creationTimestamp;
    private String creator;
    private String birthPlace;
    private String location;
    private String owner;
    private BigInteger timestamp;

}
