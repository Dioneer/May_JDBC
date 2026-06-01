<%@ page import="org.example.service.TicketService" %>
<%@ page import="org.example.dto.TicketDto" %>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page isELIgnored="false" %>
<html>
<head><title>Title</title></head>
<body>
    <h1>Title</h1>
    <h1>Flights list: </h1>
    <ul>
        <%
            TicketService ticketService = TicketService.getInstance();
            Integer flightId = Integer.valueOf(request.getParameter("flightId"));
            for(TicketDto ticketDto: ticketService.flightAllByFlightId(flightId)){
                out.write(String.format("<li>%s</li>", ticketDto.seatNo()));
        }
        %>
    </ul>
</body>
</html>