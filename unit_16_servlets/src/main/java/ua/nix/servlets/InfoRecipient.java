package ua.nix.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;

@WebServlet(name = "UserAgentDataRecipient", urlPatterns = "/infoset")
public class InfoRecipient extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(InfoRecipient.class);

    @Override
    public void init() {
       logger.debug("InfoRecipient was initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter responseBody = resp.getWriter();
        resp.setContentType("text/html");
        responseBody.println("<h1 align=\"center\"> Current server time </h1>" + ZonedDateTime.now());
        String userAgentInfo = req.getHeader("user-agent");
        String ipAddress = req.getRemoteHost();
        DataStore.getInstance().setVisitorsData(ipAddress, userAgentInfo);
    }

    @Override
    public void destroy() {
        logger.debug("InfoRecipient was destroyed");
    }
}
