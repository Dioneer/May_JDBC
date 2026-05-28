package org.example.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Ticket {
    private Integer id;
    private String passportNo;
    private String passengerName;
    private Integer flightId;
    private String seatNo;
    private BigDecimal cost;

    public Ticket(Integer id, String passportNo, String passengerName, Integer flightId, String seatNo,
                  BigDecimal cost) {
        this.id = id;
        this.passportNo = passportNo;
        this.passengerName = passengerName;
        this.flightId = flightId;
        this.seatNo = seatNo;
        this.cost = cost;
    }
    public Ticket(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) && Objects.equals(passportNo, ticket.passportNo)
                && Objects.equals(passengerName, ticket.passengerName) && Objects.equals(flightId, ticket.flightId)
                && Objects.equals(seatNo, ticket.seatNo) && Objects.equals(cost, ticket.cost);
    }

    public Ticket(String passportNo, String passengerName, Integer flightId, String seatNo, BigDecimal cost) {
        this.passportNo = passportNo;
        this.passengerName = passengerName;
        this.flightId = flightId;
        this.seatNo = seatNo;
        this.cost = cost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passportNo, passengerName, flightId, seatNo, cost);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", passwordName='" + passportNo + '\'' +
                ", passengerName='" + passengerName + '\'' +
                ", flightId=" + flightId +
                ", seatNo='" + seatNo + '\'' +
                ", cost=" + cost +
                '}';
    }
}
