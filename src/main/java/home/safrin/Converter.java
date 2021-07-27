package home.safrin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.rvesse.airline.SingleCommand;
import com.github.rvesse.airline.annotations.Cli;
import com.github.rvesse.airline.help.Help;
import home.safrin.commands.CalculateYahrzeits;
import home.safrin.models.ConvertedHebrewDate;
import home.safrin.models.Yahrzeits;
import home.safrin.services.HebrewDateConversionService;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@Cli(name = "converter",
    description = "Provides a basic example CLI",
    defaultCommand = Help.class,
    commands = { CalculateYahrzeits.class })
@SpringBootApplication
public class Converter {
  private static final Logger LOG = LoggerFactory.getLogger(Converter.class);
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("EEE., MMM. d, yyyy");

  private final HebrewDateConversionService dateConversionService;

  Converter(final HebrewDateConversionService dateConversionService) {
    this.dateConversionService = Objects.requireNonNull(dateConversionService);
  }

  public ConvertedHebrewDate getHebrewDate(final ZonedDateTime dateTime) throws JsonProcessingException {
    return this.dateConversionService.getHebrewDate(dateTime);
  }

  /**
   * Main entry point for the application.
   *
   * @param args Three parsable integers to represent (in order) year, month, and day
   */
  public static void main(final String[] args) throws JsonProcessingException {
    SingleCommand<CalculateYahrzeits> parser = SingleCommand.singleCommand(CalculateYahrzeits.class);
    CalculateYahrzeits cmd = parser.parse(args);
    final Yahrzeits result = cmd.call();
    if (result.getYahrzeits().isEmpty()) {
      LOG.info("No Yahrzeits were calculated");
    } else {
      result.getYahrzeits().forEach(yz -> LOG.info("{}: {}", yz.getTitle(), DATE_FORMATTER.format(yz.getDate())));
    }
  }

  @Bean
  public CommandLineRunner commandLineRunner(final ApplicationContext ctx) {
    return args -> {
      LOG.info("Let's inspect the beans provided by Spring Boot: {}",
          Arrays.stream(ctx.getBeanDefinitionNames())
              .sorted()
              .collect(Collectors.joining(", ", "[", "]")));
    };
  }
}
