package home.safrin.commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.rvesse.airline.HelpOption;
import com.github.rvesse.airline.SingleCommand;
import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;
import home.safrin.models.Yahrzeits;
import home.safrin.services.YahrzeitCalculationService;
import home.safrin.types.DateConverterProvider;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Callable;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Command(name = "calculate-yahrzeits",
    description = "This command calculates Yahrzeits")
public class CalculateYahrzeits implements Callable<Yahrzeits> {
  private static final Logger LOG = LoggerFactory.getLogger(CalculateYahrzeits.class);

//  @Inject
  private final YahrzeitCalculationService yahrzeitCalculationService = new YahrzeitCalculationService();

  @Inject
  private HelpOption<CalculateYahrzeits> help;

  @Option(name = {"-n", "--name"},
      title = "Name",
      description = "Sets the name for the Yahrzeits to be calculated")
  private String name;

  @Option(name = {"-d", "--date"},
      title = "Date",
      description = "Sets the original date the person died which is the basis for calculating Yahrzeits",
      typeConverterProvider = DateConverterProvider.class)
  private LocalDate date;

  @Option(name = {"-t", "--total"},
      title = "Total Yahrzeits to Calculate",
      description = "Sets the total number of Yahrzeits to be calculated")
  private int totalYahrzeits;

  @Arguments(description = "Additional arguments")
  private List<String> arguments;

  /**
   * Calculates Yahrzeits for the provided {@code localDate} parameter value and returns them to the caller.
   *
   * @param name           A String specifying for whom the Yahrzeits are to be calculated
   * @param date           a {@link LocalDate} instance that efficiently encapsulates the year, month, and day of
   *                       the month on the Gregorian calendar for which Yahrzeits are to be calculated
   * @param totalYahrzeits an {@code int} the specifies how many Yahrzeits are to be calculated for the provided
   *                       date
   * @return a {@link Yahrzeits} instance from which the {@link List} of Yahrzeits can be consumed by the caller
   * @throws JsonProcessingException when there's any problem parsing JSON returned by the underlying webservice
   *                                 that calculates the Yahrzeits
   */
  public Yahrzeits getYahrzeits(final String name, final LocalDate date, final int totalYahrzeits)
      throws JsonProcessingException {
    return this.yahrzeitCalculationService.getYahrzeits(name, date, totalYahrzeits);
  }

  @Override
  public Yahrzeits call() throws JsonProcessingException {
    if (!help.showHelpIfRequested()) {
      LOG.debug("Name: {}", this.name);
      LOG.debug("Date: {}", this.date);
      LOG.debug("Total yahrzeits: {}", this.totalYahrzeits);
      LOG.debug("Additional arguments: {}", this.arguments);
    }

    if (this.name == null || this.date == null || this.totalYahrzeits == 0) {
      help.showHelp();
      System.exit(1);
    }

    return this.getYahrzeits(this.name, this.date, this.totalYahrzeits);
  }

  public static void main(String[] args) throws JsonProcessingException {
    final SingleCommand<CalculateYahrzeits> parser = SingleCommand.singleCommand(CalculateYahrzeits.class);
    final CalculateYahrzeits cmd = parser.parse(args);
    final Yahrzeits result = cmd.call();
    result.getYahrzeits().forEach(yz -> LOG.info("{}: {}", yz.getTitle(), yz.getDate()));

  }
}
