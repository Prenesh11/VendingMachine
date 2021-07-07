package com.vendingmachine.entity;

import java.util.Arrays;
import java.util.List;

public enum DenominationEnum {

    FIVE_RAND(5),
    TEN_RAND(10),
    TWENTY_RAND(20);

    private int randValue;

    DenominationEnum( int value)
    {
        this.randValue = value;
    }

    public int getRandValue()
    {
        return  this.randValue;
    }


public static final List<DenominationEnum> VALID_DENOMINATIONS = Arrays.asList(
        DenominationEnum.FIVE_RAND,
        DenominationEnum.TEN_RAND,
        DenominationEnum.TWENTY_RAND);
}

