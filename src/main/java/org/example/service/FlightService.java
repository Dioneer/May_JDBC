package org.example.service;

import org.example.dao.FlightDao;
import org.example.dto.FlightDto;

import java.util.List;

public class FlightService {
    private static volatile FlightService INSTANCE;
    private final FlightDao flightDao = FlightDao.getInstance();

    private FlightService(){}
    public static FlightService getInstance(){
        if(INSTANCE==null){
            synchronized (FlightService.class){
                if(INSTANCE==null){
                    return INSTANCE = new FlightService();
                }
            }
        }
        return INSTANCE;
    }

    public List<FlightDto> flightList(){
        return flightDao.findAll().stream().map(i->new FlightDto(
                i.getId(), "%s-%s-%S".formatted(i.getArrivalAirportCode(),i.getDepartureAirportCode(),i.getStatus())))
                .toList();
    };
}
