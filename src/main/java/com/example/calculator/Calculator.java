package com.example.calculator;

public class Calculator {
    public Integer sum(Integer val1, Integer val2){
        return val1 + val2;
    }

    public Integer subtraction(Integer val1, Integer val2){
        return val1 - val2;
    }

    public Integer multiply(Integer val1, Integer val2){
        return val1 * val2;
    }

    public Integer division(Integer val1, Integer val2){
        return val1 / val2;
    }

    public Integer squareNumber(Integer val1){
        return val1 * val1;
    }

    public double squareRoot(int val1){
        return Math.sqrt(val1);
    }
}

