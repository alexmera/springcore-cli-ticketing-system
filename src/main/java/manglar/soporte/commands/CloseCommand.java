package manglar.soporte.commands;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.google.common.base.MoreObjects;
import javax.annotation.Nonnull;
import manglar.soporte.model.Resolution;
import manglar.soporte.model.Ticket;
import manglar.soporte.services.TicketsService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(CloseCommand.NAME)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Parameters(commandDescription = "Cerrar un ticket")
public class CloseCommand implements CliCommand {

  public static final String NAME = "close";

  private TicketsService ticketsService;

  @Parameter(names = "--id", required = true)
  private Long id;

  @Parameter(names = "--resolution", required = true)
  private Resolution resolution;

  @Parameter(names = "--solution")
  private String solution;

  public CloseCommand(
      TicketsService ticketsService
  ) {
    this.ticketsService = ticketsService;
  }

  public Resolution getResolution() {
    return resolution;
  }

  public void setResolution(Resolution resolution) {
    this.resolution = resolution;
  }

  public String getSolution() {
    return solution;
  }

  public void setSolution(String solution) {
    this.solution = solution;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Nonnull
  @Override
  public String getCommandName() {
    return NAME;
  }

  @Override
  public String runCommand() {
    System.out.println(">> Running command: " + this.toString());
    Ticket ticket = solution != null
        ? ticketsService.close(id, resolution, solution)
        : ticketsService.close(id, resolution);

    return ticket.toString();
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("resolution", resolution)
        .add("solution", solution)
        .toString();
  }
}
