package controller;

import java.io.BufferedReader;
import com.google.gson.JsonObject;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.User;
import utils.ReimbursementCalculator;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		 ReimbursementCalculator calculator = (ReimbursementCalculator) getServletContext().getAttribute("reimbursementCalculator");

	    BufferedReader reader = request.getReader();
	    StringBuilder jsonBody = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        jsonBody.append(line);
	    }

	    System.out.println("Data: " + jsonBody.toString());

	    // Parse JSON data into a User object
	    User user = objectMapper.readValue(jsonBody.toString(), User.class);

	    // Perform necessary calculations based on the user input
	    double calculatedReimbursement = calculator.calculateReimbursement(user);

	    // Create a response JSON object
	    JsonObject responseObject = new JsonObject();
	    responseObject.addProperty("calculatedReimbursement", calculatedReimbursement);

	    // Set content type and send the response
	    response.setContentType("application/json");
	    response.getWriter().write(responseObject.toString());
	}



    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/user.jsp").forward(request, response);
    }
}
