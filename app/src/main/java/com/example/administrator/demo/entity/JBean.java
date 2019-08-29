package com.example.administrator.demo.entity;

import java.io.Serializable;

public class JBean implements Serializable {

    /**
     * integralNumber : 1.999999998E9
     */

    private double integralNumber;

    public double getIntegralNumber() {
        return integralNumber;
    }

    public void setIntegralNumber(double integralNumber) {
        this.integralNumber = integralNumber;
    }
}
