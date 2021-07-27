package safrin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import home.safrin.Converter;
import home.safrin.models.ConvertedHebrewDate;
import home.safrin.models.HebrewDate;
import home.safrin.services.HebrewDateConversionService;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.TreeSet;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = Converter.class)
class HebrewDateConversionServiceTest {
  private static final Logger LOG = LoggerFactory.getLogger(HebrewDateConversionServiceTest.class);
  private static final String EST = "US/Eastern";

  @Autowired
  private HebrewDateConversionService service;

  @Test
  @DisplayName("Display all available timezones in the JVM")
  @Disabled
  void timeZones() {
    final Set<String> zoneIDs = new TreeSet<>(ZoneId.getAvailableZoneIds());

    LOG.info("Zone IDs: {}", zoneIDs);
  }

  @Test
  @DisplayName("Validate conversion of Gregorian date to Hebrew date")
  void getHebrewDate() throws JsonProcessingException {
    /* My dad passed away on Fri., Mar. 15, 1996 which converts to 24th of Adar, 5756 on the Hebrew calendar.
     * Command line test with curl to ensure the hebcal webservice is working:
     *
     *    curl -G https://www.hebcal.com/yahrzeit?cfg=json\&v=yahrzeit\&n1=Dad\&y1=1996\&m1=3\&d1=15\&years=5
     *
     * Once this service is working, Mar. 15, 1996 will be a good date with which to test.
     * Today's date is Thu., Jul. 22, 2021 which converts to 13th of Av, 5781 on the Hebrew calendar. */

    final ZonedDateTime zonedDateTime = ZonedDateTime.of(1996, 3, 15, 17, 12, 38, 0, ZoneId.of(EST));

    final ConvertedHebrewDate result = this.service.getHebrewDate(zonedDateTime);

    assertEquals(5756, result.getHebrewYear());
    assertEquals(24, result.getHebrewDay());
    assertEquals("Adar", result.getHebrewMonth());
  }

  @Test
  @DisplayName("Validate conversion of Hebrew date to Gregorian date")
  void getGregorianDate() throws JsonProcessingException {
    /* My dad passed away on Fri., Mar. 15, 1996 which converts to 24th of Adar, 5756 on the Hebrew calendar.
     * Command line test with curl to ensure the hebcal webservice is working:
     *
     *    curl -G https://www.hebcal.com/converter?cfg=json\&gy=1996\&gm=3\&gd=15\&g2h=1
     *
     * Once this service is working, Mar. 15, 1996 will be a good date with which to test.
     * Today's date is Thu., Jul. 22, 2021 which converts to 13th of Av, 5781 on the Hebrew calendar. */

    final HebrewDate hebrewDate = new HebrewDate.Builder().hebrewMonth("Av").hebrewDay(13).hebrewYear(5781).build();

    final ConvertedHebrewDate result = this.service.getGregorianDate(hebrewDate);

    assertEquals(2021, result.getGregorianYear());
    assertEquals(22, result.getGregorianDay());
    assertEquals(7, result.getGregorianMonth());
    assertFalse(result.isAfterSunset());
  }
}
