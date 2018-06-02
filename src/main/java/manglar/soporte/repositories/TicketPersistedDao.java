package manglar.soporte.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
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
    String json = ticketToJson(ticket);
    persistenceMap.put(ticket.getId(), json);
    return ticket;
  }

  @Override
  public Ticket getOne(long id) {
    String json = persistenceMap.get(id);
    if (json != null) {
      return jsonToTicket(json);
    }
    throw new NoSuchElementException();
  }

  @Override
  public Optional<Ticket> findOne(long id) {
    String json = persistenceMap.get(id);
    if (json != null) {
      return Optional.of(jsonToTicket(json));
    }
    return Optional.empty();
  }

  @Override
  public List<Ticket> find(Predicate<Ticket> predicate) {
    List<Ticket> tickets = new ArrayList<>();
    persistenceMap.forEach((k, v) -> {
      Ticket ticket = jsonToTicket(v);
      if (predicate.test(ticket)) {
        tickets.add(ticket);
      }
    });
    return tickets;
  }

  @Override
  public List<Ticket> list() {
    return persistenceMap.values()
        .stream()
        .map(this::jsonToTicket)
        .collect(Collectors.toList());
  }

  @Override
  public Ticket update(Ticket ticket) {
    persistenceMap.put(ticket.getId(), ticketToJson(ticket));
    return ticket;
  }

  @Override
  public Ticket delete(long id) {
    Ticket ticket = getOne(id);
    persistenceMap.remove(id);
    return ticket;
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
