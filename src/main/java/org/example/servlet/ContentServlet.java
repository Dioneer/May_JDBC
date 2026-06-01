package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dto.FlightDto;
import org.example.service.FlightService;

import java.io.IOException;
import java.util.List;

@WebServlet("/content")
public class ContentServlet extends HttpServlet {
    private final FlightService flightService = FlightService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<FlightDto> flightsAll = flightService.flightList();
        req.setAttribute("flightsAll", flightsAll);
        req.getRequestDispatcher("/WEB-INF/jsp/flights.jsp").forward(req,resp);
    }
}
