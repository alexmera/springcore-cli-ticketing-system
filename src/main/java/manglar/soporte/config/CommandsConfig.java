package manglar.soporte.config;

import manglar.soporte.commands.CommandExecutor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {CommandExecutor.class})
public class CommandsConfig {

}
