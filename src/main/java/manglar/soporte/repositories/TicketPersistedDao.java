package manglar.soporte.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Predicate;
import manglar.soporte.model.Ticket;
import manglar.soporte.model.value.TicketValue;

public class TicketPersistedDao implements TicketDao {

  private ConcurrentMap<Long, String> persistenceMap;

  private ObjectMapper objectMapper;

  public TicketPersistedDao(ConcurrentMap<Long, String> persistenceMap, ObjectMapper objectMapper) {
    this.persistenceMap = persistenceMap;
    this.objectMapper = objectMapper;
  }

  @Override
  public Ticket create(Ticket ticket) {
    return null;
  }

  @Override
  public Ticket getOne(long id) {
    return null;
  }

  @Override
  public Optional<Ticket> findOne(long id) {
    return Optional.empty();
  }

  @Override
  public List<Ticket> find(Predicate<Ticket> predicate) {
    return null;
  }

  @Override
  public List<Ticket> list() {
    return null;
  }

  @Override
  public Ticket update(Ticket ticket) {
    return null;
  }

  @Override
  public Ticket delete(long id) {
    return null;
  }

  private String ticketToJson(Ticket ticket) {
    try {
      return objectMapper.writeValueAsString(ticket);
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  private Ticket jsonToTicket(String json) {
    try {
      return objectMapper.readValue(json, TicketValue.class);
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }
}
