package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.TicketService;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {
    private final TicketService ticketService = TicketService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        Integer flightId = Integer.valueOf(req.getParameter("flightId"));
        try(PrintWriter writer = resp.getWriter()){
            writer.write("<html><body>");
            writer.write("<h1>Flights list: </h1>");
            writer.write("<ul>");
            ticketService.flightAllByFlightId(flightId ).stream().
                    forEach(list->writer.write("""
                            <li>%s</li>""".formatted(list.seatNo())));
            writer.write("</ul>");
            writer.write("</body></html>");
        }
    }
}
