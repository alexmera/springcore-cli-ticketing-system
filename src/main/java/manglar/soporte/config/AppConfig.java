package manglar.soporte.config;

import manglar.soporte.repositories.TicketDao;
import manglar.soporte.repositories.TicketMemoryDao;
import manglar.soporte.services.TicketsDaoBasedService;
import manglar.soporte.services.TicketsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public TicketDao ticketDao() {
    return new TicketMemoryDao();
  }

  @Bean
  public TicketsService ticketsService() {
    return new TicketsDaoBasedService(ticketDao());
  }
}
