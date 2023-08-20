package controller;

import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.User;
import utils.ReimbursementCalculator;

@RunWith(MockitoJUnitRunner.class)
public class UserServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private ServletContext servletContext;

    @Mock
    private ReimbursementCalculator calculator;

    @InjectMocks
    private UserServlet userServlet;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute("reimbursementCalculator")).thenReturn(calculator);
    }

    @Test
    public void testDoPost() throws ServletException, IOException {
        // Set up a sample JSON input
        String jsonInput = "{\"tripDate\":\"2023-08-17\",\"dailyAllowance\":3,\"carUsage\":50,\"receipts\":[{\"type\":\"taxi\",\"amount\":20},{\"type\":\"hotel\",\"amount\":100}]}";
        BufferedReader reader = new BufferedReader(new StringReader(jsonInput));
        when(request.getReader()).thenReturn(reader);

        // Set up a sample User object
        User user = new User();
        when(objectMapper.readValue(jsonInput, User.class)).thenReturn(user);
        when(calculator.calculateReimbursement(user)).thenReturn(250.0);

        // Create a StringWriter to capture the response
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Perform the doPost method call
        userServlet.doPost(request, response);

        // Verify the behavior
        verify(response).setContentType("application/json");
        verify(response.getWriter()).write("{\"calculatedReimbursement\":250.0}");
        verify(writer).flush();
    }


}





