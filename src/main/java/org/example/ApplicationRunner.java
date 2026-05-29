package org.example;

import org.example.dao.TicketDao;
import org.example.dto.TicketFilter;
import org.example.entity.Ticket;
import org.example.utils.ConnectionManager;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApplicationRunner {
    public static void main(String[] args) throws SQLException {
        String sql = """
                create schema game;
                """;
        String sql1 = """
                insert into aircraft (model) values ('qwerty'),('ytrewq')
                """;
        String sql2 = """
                select * from ticket;
                """;

//        try(Connection connection = ConnectionManager.get();
//            Statement statement = connection.createStatement()){
//            System.out.println(statement.execute(sql));
//        }
//        try(Connection connection = ConnectionManager.get();
//            Statement statement = connection.createStatement()){
//            System.out.println(statement.executeUpdate(sql1));
//        }
//        try(Connection connection = ConnectionManager.get();
//            Statement statement = connection.createStatement()){
//            ResultSet set = statement.executeQuery(sql2);
//            while (set.next()){
//                System.out.println(set.getLong("id"));
//            }
//        }
//        getTicketsByFlightId(2);
//        getPrepareTicketsByFlightId(2);
//        getFlightsBetween(LocalDate.of(2021,1,3),
//                LocalDate.of(2021, 1,5));
        TicketDao ticketDao = TicketDao.getInstance();
//        Ticket t = ticketDao.save(new Ticket("A5126545D","Elena",1,"8BF",
//                BigDecimal.valueOf(2.35)));
//        System.out.println(t);
//        System.out.println(ticketDao.delete(t.getId()));
//        System.out.println(ticketDao.findAll());
//        System.out.println(ticketDao.findById(1).orElseThrow());
//        System.out.println(ticketDao.update(new Ticket(1,"A85614G","Elena",1,"9BF",
//                BigDecimal.valueOf(2.35))));
//        System.out.println(ticketDao.findById(1).orElseThrow());
        System.out.println(ticketDao.findAll(new TicketFilter("Elena", null, 12, 0)));
    }
//    public static void getTicketsByFlightId(int flightId){
//        List<Integer> tickets = new ArrayList<>();
//        String sql3 = """
//                select * from ticket
//                where flight_id = %s
//                """.formatted(flightId);
//        try(Connection connection = ConnectionManager.get();
//            Statement statement = connection.createStatement()){
//            ResultSet set = statement.executeQuery(sql3);
//            while (set.next()){
//                tickets.add(set.getInt("id"));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(tickets);
//    }
//    public static void getPrepareTicketsByFlightId(int flightId){
//        List<Integer> tickets = new ArrayList<>();
//        String sql3 = """
//                select * from ticket
//                where flight_id = ?
//                """;
//        try(Connection connection = ConnectionManager.get();
//            PreparedStatement statement = connection.prepareStatement(sql3)){
//            statement.setInt(1,flightId);
//            ResultSet set = statement.executeQuery();
//            while (set.next()){
//                tickets.add(set.getInt("id"));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(tickets);
//    }
//    public static void getFlightsBetween(LocalDate start, LocalDate end){
//        List<Integer> flights = new ArrayList<>();
//        String sql3 = """
//                select * from flight
//                where departure_date between ? and ?;
//                """;
//        try(Connection connection = ConnectionManager.get();
//            PreparedStatement statement = connection.prepareStatement(sql3)){
//            statement.setTimestamp(1,Timestamp.valueOf(start.atStartOfDay()));
//            statement.setTimestamp(2,Timestamp.valueOf(end.atStartOfDay()));
//            ResultSet set = statement.executeQuery();
//            while (set.next()){
//                flights.add(set.getInt("id"));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(flights);
//    }

}
