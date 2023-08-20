package utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Receipt;
import model.User;

public class ReimbursementCalculator {
	
	private double dailyAllowanceRate = 15;
    private double carMileageRate = 0.3;
    private List<String> receiptsList = Arrays.asList("taxi", "hotel", "planeTicket", "train");
    private Map<String, Double> reimbursementLimits = new HashMap<>();

    public double calculateReimbursement(User user) {
    	double totalReimbursement = 0;
    	// Calculate reimbursement based on receipt amounts
        List<Receipt> receipts = user.getReceipts();
        if (receipts != null) {
            for (Receipt receipt : receipts) {
            	if(receiptsList.contains(receipt.getType())) {
            		totalReimbursement += receipt.getAmount();
            	}
            }
        }

        // Add daily allowance and car mileage
        totalReimbursement += user.getDailyAllowance() * dailyAllowanceRate;
        totalReimbursement += user.getCarUsage() * carMileageRate;

        return totalReimbursement;
    }

    public void updateReimbursementRates(double dailyAllowanceRate, double carMileageRate) {
        this.dailyAllowanceRate = dailyAllowanceRate;
        this.carMileageRate = carMileageRate;
    }

    public void updateReceiptsList(String receiptsList) {
        this.receiptsList = Arrays.asList(receiptsList.split(","));
    }

    public void updateReimbursementLimit(String limitType, double limitAmount) {
        reimbursementLimits.put(limitType, limitAmount);
    }

}
