package je.jdbc.dao;

import je.jdbc.entity.Ticket;
import je.jdbc.exception.DaoException;
import je.jdbc.utils.ConnectionManager;

import java.sql.SQLException;
import java.sql.Statement;

public class TicketDao {
    private static final TicketDao INSTANCE = new TicketDao();

    private static final String SAVE_SQL =
                    """
                    INSERT INTO ticket
                    (passport_no, passenger_name, flight_id, seat_no, cost)  
                    VALUES (?, ?, ?, ?, ?)
                    """;
    private static final String DELETE_SQL =
                    """
                    DELETE INTO ticket
                    WHERE id = ?            
                    """;

    public static TicketDao getInstance() {
        return INSTANCE;
    }

    public Ticket save(Ticket ticket) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, ticket.getPassportNo());
            statement.setString(2, ticket.getPassengerName());
            statement.setString(3, ticket.getSeatNo());
            statement.setLong(4, ticket.getFlightId());
            statement.setBigDecimal(5, ticket.getCost());

            statement.executeUpdate();
            var keys = statement.getGeneratedKeys();
            if (keys.next()) ticket.setId(keys.getLong("id"));


            return ticket;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public boolean delete(Long id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setLong(1, id);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    public static TicketDao getINSTANCE() {
        return INSTANCE;
    }

    private TicketDao() {
    }
};
