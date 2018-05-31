package manglar.soporte.model;

import java.time.LocalDateTime;
import java.util.Optional;

public interface Ticket {

  Long getId();

  String getSubject();

  TicketStatus getStatus();

  Optional<Resolution> getClosedAs();

  Optional<String> getSolution();

  LocalDateTime getCreationDate();

  Optional<LocalDateTime> getClosingDate();
}
