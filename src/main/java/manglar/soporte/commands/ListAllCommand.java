package manglar.soporte.commands;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.google.common.base.MoreObjects;
import java.util.List;
import javax.annotation.Nonnull;
import manglar.soporte.model.Ticket;
import manglar.soporte.model.TicketStatus;
import manglar.soporte.services.TicketsService;

@Parameters(commandDescription = "Listar todos los tickets")
public class ListAllCommand implements CliCommand {

  public static final String NAME = "all";

  private TicketsService ticketsService;

  @Parameter(names = "--status")
  private TicketStatus status;

  public ListAllCommand(
      TicketsService ticketsService
  ) {
    this.ticketsService = ticketsService;
  }

  public TicketStatus getStatus() {
    return status;
  }

  public void setStatus(TicketStatus status) {
    this.status = status;
  }

  @Nonnull
  @Override
  public String getCommandName() {
    return NAME;
  }

  @Override
  public String runCommand() {
    System.out.println(">> Running command: " + this.toString());

    List<Ticket> tickets = status != null
        ? ticketsService.find(ticket -> ticket.getStatus().equals(status))
        : ticketsService.all();

    StringBuilder out = new StringBuilder();
    tickets.forEach(t -> out.append(t.toString()).append("\n"));
    return out.toString();
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("status", status)
        .toString();
  }
}
