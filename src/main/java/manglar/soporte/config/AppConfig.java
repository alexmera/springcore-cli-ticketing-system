package manglar.soporte.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.ConcurrentMap;
import manglar.soporte.repositories.TicketDao;
import manglar.soporte.repositories.TicketPersistedDao;
import manglar.soporte.services.IdGenerator;
import manglar.soporte.services.LongIdGenerator;
import manglar.soporte.services.TicketsDaoBasedService;
import manglar.soporte.services.TicketsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public IdGenerator idGenerator() {
    return new LongIdGenerator();
  }

  @Bean
  public TicketDao ticketDao(ConcurrentMap<Long, String> tickets, ObjectMapper objectMapper) {
    return new TicketPersistedDao(tickets, objectMapper);
  }

  @Bean
  public TicketsService ticketsService(TicketDao ticketDao) {
    return new TicketsDaoBasedService(ticketDao);
  }
}
