package org.example.dao;

import org.example.dto.FlightFilter;
import org.example.entity.Flight;
import org.example.entity.Ticket;
import org.example.exception.DaoException;
import org.example.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlightDao implements Dao<Flight, FlightFilter>{
    @Override
    public Flight save(Flight flight) {
        final String save = """
        insert into flight (flight_no, departure_date, departure_airport_code, arrival_date, arrival_airport_code,
                                               aircraft_id, status) values (?,?,?,?,?,?,?);
        """;
        try(Connection connection = ConnectionManager.get();
            PreparedStatement statement = connection.prepareStatement(save, Statement.RETURN_GENERATED_KEYS)){
            statement.setInt(1, flight.getFlightNo());
            statement.setDate(2, (Date) flight.getDepartureDate());
            statement.setInt(3, flight.getDepartureAirportCode());
            statement.setDate(4, (Date) flight.getArrivalDate());
            statement.setInt(5, flight.getArrivalAirportCode());
            statement.setInt(6, flight.getAircraftId());
            statement.setString(7, flight.getStatus());
            statement.executeUpdate();
            ResultSet set = statement.getGeneratedKeys();
            if(set.next()){
                flight.setId(set.getInt("id"));
            }
            return flight;
        } catch (SQLException e) {
            throw new DaoException("Flight DAO save method",e);
        }
    }
    @Override
    public boolean delete(Integer id) {
        final String delete = """
                delete from flight where id = ?;
                """;
        try(Connection connection = ConnectionManager.get();
            PreparedStatement statement = connection.prepareStatement(delete)){
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }catch (SQLException e){
            throw new DaoException("Flight DAO delete method",e);
        }
    }
    @Override
    public List<Flight> findAll() {
        List<Flight> arr = new ArrayList<>();
        final String findAll = """
                select * from flight;
                """;
        try(Connection connection = ConnectionManager.get();
            PreparedStatement statement = connection.prepareStatement(findAll)){
            ResultSet set = statement.executeQuery();
            while(set.next()){
                arr.add(createItem(set));
            }
            return arr;
        }catch (SQLException e){
            throw new DaoException("Flight DAO findAll method",e);
        }
    }
    @Override
    public List<Flight> findAll(FlightFilter filter) {
        List<Object> param = new ArrayList<>();
        List<String>whereSQL = new ArrayList<>();
        if(filter.flightNo()!=null){
            param.add(filter.flightNo());
            whereSQL.add("flight_no=? ");
        }
        if(filter.arrivalAirportCode()!=null){
            param.add(filter.arrivalAirportCode());
            whereSQL.add("departure_airport_code = ? ");
        }
        param.add(filter.limit());
        param.add(filter.offSet());
        String str =  whereSQL.stream().
                collect(Collectors.joining(" and ", "where ", "limit ? offset ?"));
        String findAll = "select * from flight " + str;

        try(Connection connection = ConnectionManager.get();
            PreparedStatement statement = connection.prepareStatement(findAll)){
            List<Flight> arr = new ArrayList<>();
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
            throw new DaoException("Flight DAO findAll with filter method",e);
        }
    }
    @Override
    public Optional<Flight> findById(Integer id) {
        final String findById = """
                select * from flight where id = ?;
                """;
        try(Connection connection = ConnectionManager.get();
            PreparedStatement statement = connection.prepareStatement(findById )){
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            Flight flight = null;
            while (set.next()){
                flight = createItem(set);
            }
            return Optional.ofNullable(flight);
        }catch (SQLException e){
            throw new DaoException("Flight DAO findById  method",e);
        }
    }
    @Override
    public boolean update(Flight flight) {
        final String update = """
        update flight
        set flight_no = ?, departure_date= ?, departure_airport_code= ?, arrival_date= ?, arrival_airport_code= ?,
                                               aircraft_id= ?, status= ?
        where id = ?;
        """;
        try(Connection connection = ConnectionManager.get();
            PreparedStatement statement = connection.prepareStatement(update)){
            statement.setInt(1, flight.getFlightNo());
            statement.setDate(2, (Date) flight.getDepartureDate());
            statement.setInt(3, flight.getDepartureAirportCode());
            statement.setDate(4, (Date) flight.getArrivalDate());
            statement.setInt(5, flight.getArrivalAirportCode());
            statement.setInt(6, flight.getAircraftId());
            statement.setString(7, flight.getStatus());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Flight DAO save method",e);
        }
    }
    public Flight createItem(ResultSet set){
        Flight flight = new Flight();
        try {
            flight.setId(set.getInt("id"));
            flight.setFlightNo(set.getInt("flight_no"));
            flight.setDepartureDate(set.getDate("departure_date"));
            flight.setDepartureAirportCode(set.getInt("departure_airport_code"));
            flight.setArrivalDate(set.getDate("arrival_date"));
            flight.setArrivalAirportCode(set.getInt("arrival_airport_code"));
            flight.setAircraftId(set.getInt("aircraft_id"));
            flight.setStatus(set.getString("status"));
            return flight;
        } catch (SQLException e) {
            throw new DaoException("Flight DAO create flight method",e);
        }
    }
}
