package manglar.soporte.model.value;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.Builder;
import lombok.Value;
import manglar.soporte.model.Resolution;
import manglar.soporte.model.Ticket;
import manglar.soporte.model.TicketStatus;

@Value
@Builder(toBuilder = true)
public final class TicketValue implements Ticket {

  private Long id;

  private String subject;

  private TicketStatus status;

  private Resolution closedAs;

  private String solution;

  private LocalDateTime creationDate;

  private LocalDateTime closingDate;

  @JsonCreator
  public TicketValue(
      @JsonProperty("id")
          Long id,
      @JsonProperty("subject")
          String subject,
      @JsonProperty("status")
          TicketStatus status,
      @JsonProperty("closedAs")
          Resolution closedAs,
      @JsonProperty("solution")
          String solution,
      @JsonProperty("creationDate")
          LocalDateTime creationDate,
      @JsonProperty("closingDate")
          LocalDateTime closingDate
  ) {
    this.id = id;
    this.subject = subject;
    this.status = status;
    this.closedAs = closedAs;
    this.solution = solution;
    this.creationDate = creationDate;
    this.closingDate = closingDate;
  }

  public static TicketValue from(Ticket other) {
    return builder()
        .id(other.getId())
        .subject(other.getSubject())
        .status(other.getStatus())
        .closedAs(other.getClosedAs().isPresent() ? other.getClosedAs().get() : null)
        .solution(other.getSolution().isPresent() ? other.getSolution().get() : null)
        .creationDate(other.getCreationDate())
        .closingDate(other.getClosingDate().isPresent() ? other.getClosingDate().get() : null)
        .build();
  }

  @Override
  public Optional<String> getSolution() {
    return Optional.ofNullable(solution);
  }

  @Override
  public Optional<LocalDateTime> getClosingDate() {
    return Optional.ofNullable(closingDate);
  }

  @Override
  public Optional<Resolution> getClosedAs() {
    return Optional.ofNullable(closedAs);
  }

}
