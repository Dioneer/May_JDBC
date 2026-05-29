package org.example.dao;

import org.example.dto.TicketFilter;
import org.example.entity.Ticket;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public interface Dao<T,M> {
    public T save(T ticket);
    public boolean delete(M id);
    public List<T> findAll();
    public Optional<T> findById(M id);
    public boolean update(T item);
    public T createItem(ResultSet set);
}
