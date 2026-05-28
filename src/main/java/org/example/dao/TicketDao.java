package org.example.dao;

import org.example.entity.Ticket;
import org.example.exception.DaoException;
import org.example.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDao {
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

    public Ticket save(Ticket ticket){
        final String save = """
        insert into ticket (passport_no, passenger_name, flight_id, seat_no, cost) values (?,?,?,?,?);
        """;
        try(Connection connection = ConnectionManager.get();
            PreparedStatement statement = connection.prepareStatement(save, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, ticket.getPassportNo());
            statement.setString(2, ticket.getPassengerName());
            statement.setInt(3, ticket.getFlightId());
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
    public List<Ticket> findAll() {
        List<Ticket> arr = new ArrayList<>();
        final String findAll = """
                select * from ticket;
                """;
        try(Connection connection = ConnectionManager.get();
            PreparedStatement statement = connection.prepareStatement(findAll)){
            ResultSet set = statement.executeQuery();
            while(set.next()){
                arr.add(createTicket(set));
            }
            return arr;
        }catch (SQLException e){
            throw new DaoException("Ticket DAO findAll method",e);
        }

    }
    public Optional<Ticket> findById(Integer id) {
        final String findById = """
                select * from ticket where id = ?;
                """;
        try(Connection connection = ConnectionManager.get();
            PreparedStatement statement = connection.prepareStatement(findById )){
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            Ticket ticket = null;
            while (set.next()){
                ticket = createTicket(set);
            }
            return Optional.ofNullable(ticket);
        }catch (SQLException e){
            throw new DaoException("Ticket DAO findById  method",e);
        }

    }
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
            statement.setInt(3, ticket.getFlightId());
            statement.setString(4,ticket.getSeatNo());
            statement.setBigDecimal(5, ticket.getCost());
            statement.setInt(6, ticket.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Ticket DAO save method",e);
        }
    }
    private Ticket createTicket(ResultSet set){
        Ticket ticket = new Ticket();
        try {
            ticket.setId(set.getInt("id"));
            ticket.setPassportNo(set.getString("passport_no"));
            ticket.setPassengerName(set.getString("passenger_name"));
            ticket.setFlightId(set.getInt("flight_id"));
            ticket.setSeatNo(set.getString("seat_no"));
            ticket.setCost(set.getBigDecimal("cost"));
            return ticket;
        } catch (SQLException e) {
            throw new DaoException("Ticket DAO create ticket method",e);
        }
    }

}
