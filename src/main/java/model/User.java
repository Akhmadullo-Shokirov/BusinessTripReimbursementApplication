package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class User {

    private String tripDate;
    private List<Receipt> receipts;
    private int dailyAllowance;
    private double carUsage;

    // Default constructor
    public User() {
    }

    @JsonCreator
    public User(@JsonProperty("tripDate") String tripDate,
                @JsonProperty("receipts") List<Receipt> receipts,
                @JsonProperty("dailyAllowance") int dailyAllowance,
                @JsonProperty("carUsage") double carUsage) {
        this.tripDate = tripDate;
        this.receipts = receipts;
        this.dailyAllowance = dailyAllowance;
        this.carUsage = carUsage;
    }

    public String getTripDate() {
        return tripDate;
    }

    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }

    public int getDailyAllowance() {
        return dailyAllowance;
    }

    public void setDailyAllowance(int dailyAllowance) {
        this.dailyAllowance = dailyAllowance;
    }

    public double getCarUsage() {
        return carUsage;
    }

    public void setCarUsage(double carUsage) {
        this.carUsage = carUsage;
    }
}
