package org.pauldenhez.accountme.common.model.transaction.vo;

import lombok.Getter;

@Getter
public class PositiveAmount {

    private final Double value;

    public PositiveAmount(double value) {
        if(value <= 0) {
           throw new IllegalArgumentException("should be positive");
        }
        this.value = value;
    }
}
