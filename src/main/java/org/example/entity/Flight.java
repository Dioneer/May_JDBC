package org.example.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Flight {
    private Integer id;
    private Integer flightNo;
    private LocalDateTime departureDate;
    private Integer departureAirportCode;
    private LocalDateTime arrivalDate;
    private Integer arrivalAirportCode;
    private Integer aircraftId;
    private String status;
    public Flight(){};

    public Flight(Integer id, Integer flightNo, LocalDateTime departureDate, Integer departureAirportCode,
                  LocalDateTime arrivalDate, Integer arrivalAirportCode, Integer aircraftId, String status) {
        this.id = id;
        this.flightNo= flightNo;
        this.departureDate= departureDate;
        this.departureAirportCode = departureAirportCode;
        this.arrivalDate = arrivalDate;
        this.arrivalAirportCode = arrivalAirportCode;
        this.aircraftId = aircraftId;
        this.status = status;
    }
    public Flight(Integer flightNo, LocalDateTime departureDate, Integer departureAirportCode,
                  LocalDateTime arrivalDate, Integer arrivalAirportCode, Integer aircraftId, String status) {
        this.flightNo = flightNo;
        this.departureDate = departureDate;
        this.departureAirportCode = departureAirportCode;
        this.arrivalDate = arrivalDate;
        this.arrivalAirportCode = arrivalAirportCode;
        this.aircraftId= aircraftId;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(Integer flightNo) {
        this.flightNo = flightNo;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public Integer getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(Integer departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Integer getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public void setArrivalAirportCode(Integer arrivalAirportCode) {
        this.arrivalAirportCode =arrivalAirportCode;
    }

    public Integer getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(Integer aircraftId) {
        this.aircraftId = aircraftId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(id, flight.id) && Objects.equals(flightNo, flight.flightNo)
                && Objects.equals(departureDate, flight.departureDate) &&
                Objects.equals(departureAirportCode, flight.departureAirportCode) &&
                Objects.equals(arrivalDate, flight.arrivalDate) &&
                Objects.equals(arrivalAirportCode, flight.arrivalAirportCode) &&
                Objects.equals(aircraftId, flight.aircraftId) && Objects.equals(status, flight.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flightNo, departureDate, departureAirportCode,
                arrivalDate, arrivalAirportCode, aircraftId, status);
    }
}
