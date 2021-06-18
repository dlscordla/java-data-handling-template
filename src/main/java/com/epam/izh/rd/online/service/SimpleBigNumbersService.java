package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class SimpleBigNumbersService implements BigNumbersService {

    @Override
    public BigDecimal getPrecisionNumber(int a, int b, int range) {
        return new BigDecimal(a).divide(new BigDecimal(b), new MathContext(range));
    }

    @Override
    public BigInteger getPrimaryNumber(int range) {
        BigInteger number = BigInteger.valueOf(2);
        for (int i = 0; i < range; i++) {
            number = number.nextProbablePrime();
        }
        return number;
    }
}
