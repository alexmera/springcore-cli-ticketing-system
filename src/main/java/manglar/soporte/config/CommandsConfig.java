package manglar.soporte.config;

import manglar.soporte.commands.CliCommand;
import manglar.soporte.commands.CloseCommand;
import manglar.soporte.commands.CommandExecutor;
import manglar.soporte.commands.ListAllCommand;
import manglar.soporte.commands.ReportCommand;
import manglar.soporte.services.IdGenerator;
import manglar.soporte.services.TicketsService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class CommandsConfig {

  @Bean
  @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  public CommandExecutor commandExecutor(ApplicationContext applicationContext) {
    return new CommandExecutor(applicationContext);
  }

  @Bean(name = ReportCommand.NAME)
  @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  public CliCommand reportCommand(TicketsService ticketsService, IdGenerator idGenerator) {
    return new ReportCommand(ticketsService, idGenerator);
  }

  @Bean(name = ListAllCommand.NAME)
  @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  public CliCommand allCommand(TicketsService ticketsService) {
    return new ListAllCommand(ticketsService);
  }

  @Bean(name = CloseCommand.NAME)
  @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  public CliCommand closeCommand(TicketsService ticketsService) {
    return new CloseCommand(ticketsService);
  }
}
