package je.jdbc;

import je.jdbc.dao.TicketDao;
import je.jdbc.entity.Ticket;

import java.math.BigDecimal;
import java.sql.SQLException;

public class JdbcRunner {
    public static void main(String[] args) throws SQLException {
        TicketDao ticketDao = TicketDao.getInstance();
        Ticket ticket = new Ticket();
        ticket.setPassportNo("12312");
        ticket.setPassengerName("Misha");
        ticket.setFlightId(6L);
        ticket.setSeatNo("54B");
        ticket.setCost(BigDecimal.TEN);

        System.out.println(ticketDao.save(ticket));
    }
}