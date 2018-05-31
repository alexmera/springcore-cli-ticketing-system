package manglar.soporte.commands;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.google.common.base.MoreObjects;
import java.time.LocalDateTime;
import java.util.List;
import javax.annotation.Nonnull;
import manglar.soporte.model.Ticket;
import manglar.soporte.model.TicketStatus;
import manglar.soporte.model.value.TicketValue;
import manglar.soporte.services.IdGenerator;
import manglar.soporte.services.TicketsService;
import org.springframework.util.StringUtils;

@Parameters(commandDescription = "Reportar un ticket")
public class ReportCommand implements CliCommand {

  public static final String NAME = "report";

  private TicketsService ticketsService;

  private IdGenerator idGenerator;

  @Parameter(description = "Asunto del ticket")
  private List<String> subject;

  public ReportCommand(
      TicketsService ticketsService,
      IdGenerator idGenerator
  ) {
    this.ticketsService = ticketsService;
    this.idGenerator = idGenerator;
  }

  public List<String> getSubject() {
    return subject;
  }

  public void setSubject(List<String> subject) {
    this.subject = subject;
  }

  public String subjectAsText() {
    return StringUtils.collectionToDelimitedString(getSubject(), " ");
  }

  @Nonnull
  @Override
  public String getCommandName() {
    return NAME;
  }

  @Override
  public String runCommand() {
    System.out.println("Running command report...");
    Ticket ticket = new TicketValue(
        idGenerator.nextId(),
        subjectAsText(),
        TicketStatus.OPEN,
        null,
        null,
        LocalDateTime.now(),
        null
    );
    return "Ticket created: " + ticket.toString();
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("subject", subject)
        .toString();
  }
}