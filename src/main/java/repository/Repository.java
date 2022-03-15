package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Repository {

    protected Connection connection;

    public void createConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "sa");
    }
}
