package org.fasttrackit.Web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.service.TopWinnerService;
import org.fasttrackit.transfer.TopWinnerListResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TopWinnerServlet extends HttpServlet {
    private TopWinnerService topWinnerService = new TopWinnerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            TopWinnerListResponse topWinnerListResponse=topWinnerService.getTopWinners();
            ObjectMapper objectMapper=new ObjectMapper();
            String responseJSON=objectMapper.writeValueAsString(objectMapper);

            //mime type (content type)
            resp.setContentType ("application/json");
            resp.getWriter ().print (responseJSON);
            resp.getWriter ().flush ();

        } catch (Exception e) {
            resp.sendError(500, "There was a error processing your request. " + e.getMessage());
        }
    }
}

