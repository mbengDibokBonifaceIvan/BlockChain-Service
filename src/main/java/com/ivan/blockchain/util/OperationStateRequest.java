package com.ivan.blockchain.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationStateRequest {

    private BigInteger identification;
    private String type;
    private String status;
    private String seller;
    private String buyer;
    private BigInteger timestamp;

}
