package org.example.dao;

import org.example.dto.TicketFilter;
import org.example.entity.Ticket;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public interface Dao<T,M> {
    public T save(T ticket);
    public boolean delete(Integer id);
    public List<T> findAll();
    public List<T> findAll(M filter);
    public Optional<T> findById(Integer id);
    public boolean update(T item);
    public T createItem(ResultSet set);
}
