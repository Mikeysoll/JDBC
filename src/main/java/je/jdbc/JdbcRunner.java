package je.jdbc;

import je.jdbc.utils.ConnetionManager;

import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcRunner {

    public static void main(String[] args) throws SQLException {
        try (var connection = ConnetionManager.open()) {
            System.out.println(connection.getTransactionIsolation());
        }
    }
}
