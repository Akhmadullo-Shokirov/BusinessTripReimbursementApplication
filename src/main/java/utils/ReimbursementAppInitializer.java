package utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ReimbursementAppInitializer implements ServletContextListener {

	@Override
    public void contextInitialized(ServletContextEvent sce) {
        ReimbursementCalculator calculator = new ReimbursementCalculator();
        // Set the calculator instance as a context attribute
        sce.getServletContext().setAttribute("reimbursementCalculator", calculator);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
