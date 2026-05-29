package org.example.dao;

import org.example.dto.TicketFilter;
import org.example.entity.Flight;
import org.example.entity.Ticket;
import org.example.exception.DaoException;
import org.example.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TicketDao implements Dao<Ticket, Integer> {
    private static volatile TicketDao INSTANCE;
    private TicketDao(){}
    public static TicketDao getInstance(){
        if(INSTANCE==null){
            synchronized (TicketDao.class){
                if(INSTANCE==null){
                    INSTANCE = new TicketDao();
                    return INSTANCE;
                }
            }
        }
        return INSTANCE;
    }
    @Override
    public Ticket save(Ticket ticket){
        final String save = """
        insert into ticket (passport_no, passenger_name, flight_id, seat_no, cost) values (?,?,?,?,?);
        """;
        try(Connection connection = ConnectionManager.get();
            PreparedStatement statement = connection.prepareStatement(save, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, ticket.getPassportNo());
            statement.setString(2, ticket.getPassengerName());
            statement.setInt(3, ticket.getFlight().getId());
            statement.setString(4,ticket.getSeatNo());
            statement.setBigDecimal(5, ticket.getCost());
            statement.executeUpdate();
            ResultSet set = statement.getGeneratedKeys();
            if(set.next()){
                ticket.setId(set.getInt("id"));
            }
            return ticket;
        } catch (SQLException e) {
            throw new DaoException("Ticket DAO save method",e);
        }
    }
    @Override
    public boolean delete(Integer id) {
        final String delete = """
                delete from ticket where id = ?;
                """;
        try(Connection connection = ConnectionManager.get();
            PreparedStatement statement = connection.prepareStatement(delete)){
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }catch (SQLException e){
            throw new DaoException("Ticket DAO delete method",e);
        }
    }
    @Override
    public List<Ticket> findAll() {
        List<Ticket> arr = new ArrayList<>();
        final String findAll = """
                select * from ticket
                left join flight f on f.id = ticket.flight_id;
                """;
        try(Connection connection = ConnectionManager.get();
            PreparedStatement statement = connection.prepareStatement(findAll)){
            ResultSet set = statement.executeQuery();
            while(set.next()){
                arr.add(createItem(set));
            }
            return arr;
        }catch (SQLException e){
            throw new DaoException("Ticket DAO findAll method",e);
        }

    }
    public List<Ticket> findAll(TicketFilter filter) {
        List<Object> param = new ArrayList<>();
        List<String>whereSQL = new ArrayList<>();
        if(filter.passengerName()!=null){
            param.add("%"+filter.passengerName()+"%");
            whereSQL.add("passenger_name like ? ");
        }
        if(filter.seatNo()!=null){
            param.add("%"+filter.seatNo()+"%");
            whereSQL.add("seat_no like ? ");
        }
        param.add(filter.limit());
        param.add(filter.offSet());
        String str =  whereSQL.stream().
                collect(Collectors.joining(" and ", "where ", "limit ? offset ?"));
        String findAll = "select * from ticket left join flight f on f.id = ticket.flight_id " + str;

        try(Connection connection = ConnectionManager.get();
            PreparedStatement statement = connection.prepareStatement(findAll)){
            List<Ticket> arr = new ArrayList<>();
            for (int i = 0; i < param.size(); i++) {
                statement.setObject(i+1, param.get(i));
            }
            System.out.println(statement);
            ResultSet set = statement.executeQuery();
            while(set.next()){
                arr.add(createItem(set));
            }
            return arr;
        }catch (SQLException e){
            throw new DaoException("Ticket DAO findAll with filter method",e);
        }

    }
    @Override
    public Optional<Ticket> findById(Integer id) {
        final String findById = """
                select * from ticket t left join flight f on f.id = t.flight_id where t.id = ?;
                """;
        try(Connection connection = ConnectionManager.get();
            PreparedStatement statement = connection.prepareStatement(findById )){
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            Ticket ticket = null;
            while (set.next()){
                ticket = createItem(set);
            }
            return Optional.ofNullable(ticket);
        }catch (SQLException e){
            throw new DaoException("Ticket DAO findById  method",e);
        }

    }
    @Override
    public boolean update(Ticket ticket){
        final String update = """
        update ticket 
        set passport_no = ?, passenger_name = ?, flight_id = ?, seat_no = ?, cost = ?
        where id = ?;
        """;
        try(Connection connection = ConnectionManager.get();
            PreparedStatement statement = connection.prepareStatement(update)){
            statement.setString(1, ticket.getPassportNo());
            statement.setString(2, ticket.getPassengerName());
            statement.setInt(3, ticket.getFlight().getId());
            statement.setString(4,ticket.getSeatNo());
            statement.setBigDecimal(5, ticket.getCost());
            statement.setInt(6, ticket.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Ticket DAO save method",e);
        }
    }
    @Override
    public Ticket createItem(ResultSet set){
        Flight flight = new Flight();
        try {
            flight.setId(set.getInt("id"));
            flight.setFlightNo(set.getInt("flight_no"));
            flight.setDepartureDate(set.getTimestamp("departure_date").toLocalDateTime());
            flight.setDepartureAirportCode(set.getInt("departure_airport_code"));
            flight.setArrivalDate(set.getTimestamp("arrival_date").toLocalDateTime());
            flight.setArrivalAirportCode(set.getInt("arrival_airport_code"));
            flight.setAircraftId(set.getInt("aircraft_id"));
            flight.setStatus(set.getString("status"));
        } catch (SQLException e) {
            throw new DaoException("Flight DAO create flight method",e);
        }
        try {
            return new Ticket(set.getInt("id"),
            set.getString("passport_no"),
            set.getString("passenger_name"),
            flight,
            set.getString("seat_no"),
            set.getBigDecimal("cost"));
        } catch (SQLException e) {
            throw new DaoException("Ticket DAO create ticket method",e);
        }
    }

}
