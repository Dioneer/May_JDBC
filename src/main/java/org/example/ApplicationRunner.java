package org.example;

import org.example.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;

public class ApplicationRunner {
    public static void main(String[] args) throws SQLException {
        try(Connection connection = ConnectionManager.open()){
            System.out.println(connection.getTransactionIsolation());

        }

    }

}
