package org.example.dao;

import org.example.entity.Ticket;
import org.example.exception.DaoException;
import org.example.utils.ConnectionManager;

import java.sql.*;

public class TicketDao {
    private static volatile TicketDao INSTANCE;
    private TicketDao(){}
    public TicketDao getInstance(){
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
            return statement.execute();
        }catch (SQLException e){
            throw new DaoException("Ticket DAO delete method",e);
        }
    }
}
