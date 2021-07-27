package safrin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import home.safrin.Converter;
import home.safrin.models.Yahrzeits;
import home.safrin.services.YahrzeitCalculationService;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = Converter.class)
class YahrzeitCalculationServiceTest {
  private static final Logger LOG = LoggerFactory.getLogger(YahrzeitCalculationServiceTest.class);
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("EEE., MMM. d, yyyy");

  @Autowired
  private YahrzeitCalculationService service;

  @Test
  @DisplayName("Validate Yahrzeits calculation service")
  void getYahrzeits() throws JsonProcessingException {
    /* My dad passed away on Fri., Mar. 15, 1996 which converts to 24th of Adar, 5756 on the Hebrew calendar.
     * Command line test with curl to ensure the hebcal webservice is working:
     *
     *    curl -G https://www.hebcal.com/converter?cfg=json\&gy=1996\&gm=3\&gd=15\&g2h=1
     *
     * Once this service is working, Mar. 15, 1996 will be a good date with which to test. */
    final String name = "Dad";
    final LocalDate localDate = LocalDate.of(1996, 3, 15);
    final int totalYears = 5;

    final Yahrzeits result = this.service.getYahrzeits(name, localDate, totalYears);

    assertEquals("Yahrzeit: Dad", result.getTitle());
    final LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneOffset.UTC);
    assertEquals(today, LocalDate.ofInstant(result.getInstant(), ZoneOffset.UTC));
    assertEquals(totalYears + 1, result.getYahrzeits().size());
    result.getYahrzeits().forEach( yz -> LOG.info("{}: {}", yz.getTitle(), DATE_FORMATTER.format(yz.getDate())) );
  }
}
