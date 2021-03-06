package data;

import data.interfaces.IDB;

import javax.ws.rs.ServerErrorException;
import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresDB implements IDB {


    @Override
    public Connection getConnection() {
        String connectionUrl = "jdbc:postgresql://localhost:5432/FinalProject";
        try {
            // Establish the connection
            Connection con = DriverManager.getConnection(connectionUrl, "postgres", "adilet2002");

            return con;
        } catch (Exception e) {
            System.out.println(e);
            throw new ServerErrorException("Cannot connect to DB", 500);
        }
    }
}