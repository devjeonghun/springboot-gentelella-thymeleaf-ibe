package com.lj.ibe.vo;

import javax.persistence.GeneratedValue;

public class Fare {
    String fareType;
    int seatAvailablity;
    int appliedFareAmount;
    boolean bestFare;

    public String getFareType() {
        return fareType;
    }

    public void setFareType(String fareType) {
        this.fareType = fareType;
    }

    public int getSeatAvailablity() {
        return seatAvailablity;
    }

    public void setSeatAvailablity(int seatAvailablity) {
        this.seatAvailablity = seatAvailablity;
    }

    public int getAppliedFareAmount() {
        return appliedFareAmount;
    }

    public void setAppliedFareAmount(int appliedFareAmount) {
        this.appliedFareAmount = appliedFareAmount;
    }

    public boolean isBestFare() {
        return bestFare;
    }

    public void setBestFare(boolean bestFare) {
        this.bestFare = bestFare;
    }
}
