package com.example.hades.tdd.junit;

public class PrimeNumberChecker {
    /**
     * 验证是否为质数
     */
    public Boolean validate(final Integer primeNumber) {
        for (int i = 2; i < (primeNumber / 2); i++) {
            if (primeNumber % i == 0) {
                return false;
            }
        }
        return true;
    }
}