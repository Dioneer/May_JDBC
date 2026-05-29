package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.FlightService;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/flights")
public class FlightServlet extends HttpServlet {
    private final FlightService flightService = FlightService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try(PrintWriter writer = resp.getWriter()){
            writer.write("<h1>Flights list: <h1>");
            writer.write("<ul>");
            flightService.flightList().stream().
                    forEach(list->writer.write("""
                            <li><a href='/tickets?flightId=%d'>
                            %s</a></li>""".formatted(list.id(),list.description())));
            writer.write("</ul>");
        }
    }
}
