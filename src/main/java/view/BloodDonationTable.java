package view;

import entity.BloodDonation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.BloodDonationLogic;
import logic.Logic;
import logic.LogicFactory;

/*
 * @author jayle
 */
@WebServlet(name = "BloodDonationTable", urlPatterns = {"/BloodDonationTable"})

public class BloodDonationTable extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>BloodDonationView</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<table style=\"margin-left: auto; margin-right: auto;\" border=\"1\">");
            out.println("<caption>Blood Donation</caption>");
            /*
            From line 27 to 35 is setting up the html format so the table can be
            shown so giving the table a title and setting everything yp to be dispalyed 
            */
            
            Logic<BloodDonation> logic = LogicFactory.getFor("BloodDonation");
            //Creates logic object which holds all the information inside BloodDonation
            //This is a class its calling This holds all the methods which call columns 
            out.println("<tr>");

            logic.getColumnNames().forEach(c -> out.printf("<th>%s</th>", c));
            //Create a inhanced loop for each of the column names that are initialized 
            out.println("</tr>");

            logic.getAll().forEach(e -> out.printf("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>",
                    logic.extractDataAsList(e).toArray()));
            //Create rows so the text can displayed inside the tables there are 6
            //columns so six rows need to be filled will information
            out.println("<tr>");

            logic.getColumnNames().forEach(c -> out.printf("<th>%s</th>", c));
            //prints the column names inside the table but at the bottom of the table
            out.println("</tr>");
            out.println("</table>");
            out.printf("<div style=\"text-align: center;\"><pre>%s</pre></div>",
                    toStringMap(request.getParameterMap()));
            out.println("</body>");
            out.println("</html>");
            //End of html therefore closing it up 
        }
    }

    private String toStringMap(Map<String, String[]> m) {
        StringBuilder builder = new StringBuilder();
        for (String k : m.keySet()) {
            builder.append("Key=").append(k)
                    .append(", ")
                    .append("Value/s=").append(Arrays.toString(m.get(k)))
                    .append(System.lineSeparator());
        }
        return builder.toString();
    }
    //This method will give each of the values insdie the table a bit of style 
    //By removing any commas and hard coded toString charaters 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log("GET");
        processRequest(request, response);
    }
    /*
    This is called by the server and allows the servlet to handle any get requests
    The servlet contains must write the headers before committing a response.
    
    */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log("POST");
        BloodDonationLogic logic = LogicFactory.getFor("BloodDonation");
        BloodDonation donor = logic.updateEntity(request.getParameterMap());
        logic.update(donor);
        processRequest(request, response);
        /*
        this handles any sort of logic inside the object for BloodDonation
        There is a donnor oject that hold the values of each person after the 
        request will be processed and sent out
        
        */
    }

    @Override
    public String getServletInfo() {
        return "Sample of Blood Donor View Normal";
    }
    private static final boolean DEBUG = true;

    public void log(String msg) {
        if (DEBUG) {
            String message = String.format("[%s] %s", getClass().getSimpleName(), msg);
            getServletContext().log(message);
        }
    }

    public void log(String msg, Throwable t) {
        String message = String.format("[%s] %s", getClass().getSimpleName(), msg);
        getServletContext().log(message, t);
    }
}
