package controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import utils.ReimbursementCalculator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.StringWriter;
import java.io.PrintWriter;

import static org.mockito.Mockito.*;

public class AdminServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private ServletContext servletContext;

    @Mock
    private RequestDispatcher requestDispatcher;

    private AdminServlet adminServlet;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute("reimbursementCalculator")).thenReturn(new ReimbursementCalculator());

        adminServlet = new AdminServlet();
    }

    @Test
    public void testDoPost() throws Exception {
        when(request.getParameter("dailyAllowanceRate")).thenReturn("20");
        when(request.getParameter("carMileageRate")).thenReturn("0.35");
        when(request.getParameter("receiptsList")).thenReturn("taxi,hotel,planeTicket,train");
        when(request.getParameter("reimbursementLimit")).thenReturn("limitType");
        when(request.getParameter("limitAmount")).thenReturn("100");

        when(servletContext.getRequestDispatcher("/WEB-INF/views/admin.jsp")).thenReturn(requestDispatcher);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        adminServlet.doPost(request, response);

        verify(request, times(1)).getParameter("dailyAllowanceRate");
        verify(request, times(1)).getParameter("carMileageRate");
        verify(request, times(1)).getParameter("receiptsList");
        verify(request, times(1)).getParameter("reimbursementLimit");
        verify(request, times(1)).getParameter("limitAmount");

        writer.flush();
        verify(response, times(1)).getWriter();
    }
}
