package manglar.soporte;

import java.time.LocalDateTime;
import manglar.soporte.config.AppConfig;
import manglar.soporte.model.TicketStatus;
import manglar.soporte.model.value.TicketValue;
import manglar.soporte.services.TicketsService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

  public static void main(String[] args) {
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
        AppConfig.class
    );

    TicketsService service = applicationContext.getBean(TicketsService.class);

    service.report(new TicketValue(
        1L,
        "Error en Java",
        TicketStatus.OPEN,
        null,
        null,
        LocalDateTime.now(),
        null
    ));

    service.report(new TicketValue(
        2L,
        "Error en Java 2",
        TicketStatus.OPEN,
        null,
        null,
        LocalDateTime.now(),
        null
    ));

    service.all().forEach(System.out::println);
  }
}
