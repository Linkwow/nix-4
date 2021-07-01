package ua.nix.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;


@WebServlet(name = "UserAgentDataSender", urlPatterns = "/infoget")
public class InfoSender extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(InfoSender.class);

    @Override
    public void init() {
        logger.debug("InfoSender was initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter responseBody = resp.getWriter();
        resp.setContentType("text/html");
        ConcurrentHashMap<String, String> visitorsData = DataStore.getInstance().getVisitorsData();
        String value;
        for (String key : visitorsData.keySet()){
            value = visitorsData.get(key);
            if (key.equals(req.getRemoteHost())){
                responseBody.println("<p><b>" + key + " :: " + value + "</p></b>");
            } else {
                responseBody.println("<p>" + key + " :: " + value + "</p>");
            }
        }
    }

    @Override
    public void destroy() {
        logger.debug("InfoSender was destroyed");
    }
}
