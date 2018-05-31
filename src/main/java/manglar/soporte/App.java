package manglar.soporte;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.MissingCommandException;
import com.beust.jcommander.ParameterException;
import java.util.Scanner;
import manglar.soporte.commands.CommandExecutor;
import manglar.soporte.config.AppConfig;
import manglar.soporte.config.CommandsConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
        AppConfig.class,
        CommandsConfig.class
    );

    while (true) {
      CommandExecutor executor = applicationContext.getBean(CommandExecutor.class);
      JCommander.Builder builder = JCommander.newBuilder();
      executor.commands()
          .forEach(cliCommand -> builder.addCommand(cliCommand.getCommandName(), cliCommand));
      JCommander commander = builder.build();

      System.out.print("$ > ");
      String input = scanner.nextLine().trim();

      if (input.equals("exit")) {
        System.out.println("Bye!");
        break;
      }

      try {
        commander.parse(input.split(" "));
        executor.execute(commander.getParsedCommand());
      } catch (MissingCommandException e) {
        System.out.println("Invalid command, try again");
        commander.usage();
      } catch (ParameterException pe) {
        System.out.println(pe.getMessage());
      }
    }
  }
}
