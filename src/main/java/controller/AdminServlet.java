package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ReimbursementCalculator;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {


	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		 ReimbursementCalculator calculator = (ReimbursementCalculator) getServletContext().getAttribute("reimbursementCalculator");
		 
        double dailyAllowanceRate = Double.parseDouble(request.getParameter("dailyAllowanceRate"));
        System.out.println("daily allowance rate: " + dailyAllowanceRate);
        double carMileageRate = Double.parseDouble(request.getParameter("carMileageRate"));
        System.out.println("daily car mileage rate: " + carMileageRate);
        String receiptsList = request.getParameter("receiptsList");
        System.out.println("receiptsList: " + receiptsList);
        String reimbursementLimitType = request.getParameter("reimbursementLimit");
        System.out.println("reimbursement type: " + reimbursementLimitType);
        double reimbursementLimitAmount = Double.parseDouble(request.getParameter("limitAmount"));
        System.out.println("reimbursement amount: " + reimbursementLimitAmount);

        calculator.updateReimbursementRates(dailyAllowanceRate, carMileageRate);
        calculator.updateReceiptsList(receiptsList);
        calculator.updateReimbursementLimit(reimbursementLimitType, reimbursementLimitAmount);

    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/admin.jsp").forward(request, response);
    }
}
