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
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component(ReportCommand.NAME)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
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
    System.out.println(">> Running command: " + this.toString());
    Ticket ticket = new TicketValue(
        idGenerator.nextId(),
        subjectAsText(),
        TicketStatus.OPEN,
        null,
        null,
        LocalDateTime.now(),
        null
    );
    ticketsService.report(ticket);
    return "Ticket created: " + ticket.toString();
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("subject", subject)
        .toString();
  }
}
