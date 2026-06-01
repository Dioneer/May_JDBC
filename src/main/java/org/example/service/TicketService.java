package org.example.service;

import org.example.dao.TicketDao;
import org.example.dto.TicketDto;
import org.example.entity.Ticket;

import java.util.List;

public class TicketService {
    private static volatile TicketService INSTANCE;
    private final TicketDao flightDao = TicketDao.getInstance();

    private TicketService(){}
    public static TicketService getInstance(){
        if(INSTANCE==null){
            synchronized (TicketService.class){
                if(INSTANCE==null){
                    return INSTANCE = new TicketService();
                }
            }
        }
        return INSTANCE;
    }

    public List<TicketDto> flightAllByFlightId(Integer id){
        return flightDao.findAllByFlightId(id).stream().map(i->new TicketDto(
                        i.getId(), i.getFlight().getFlightNo(), i.getSeatNo()))
                .toList();
    };
}
