package home.safrin.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import home.safrin.models.Yahrzeits;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * [The Yahrzeit API](https://www.hebcal.com/home/1705/yahrzeit-anniversary-api) is documented as
 * <em>experimental</em>, so it probably will work, but might have a few glitches in some way.
 */
public class YahrzeitCalculationService {
  private static final Logger LOG = LoggerFactory.getLogger(YahrzeitCalculationService.class);

  private static final ObjectMapper MAPPER = new ObjectMapper();

  // The freely available REST API web service at hebcal.com

  private static final String REST_ENDPOINT_FOR_YAHRZEITS = "https://www.hebcal.com/yahrzeit";

  private static final String CONFIG_KEY = "cfg";
  private static final String JSON = "json";
  private static final String EVENT_TYPE_KEY = "v";
  private static final String NAME_KEY = "n1";
  private static final String GREGORIAN_YEAR_KEY = "y1";
  private static final String GREGORIAN_MONTH_KEY = "m1";
  private static final String GREGORIAN_DAY_KEY = "d1";
  private static final String YEARS_KEY = "years";
  private static final String AFTER_SUNSET = "s1";
  private static final String ON = "on";

  public Yahrzeits getYahrzeits(final String name, final LocalDate date, final int totalYahrzeits)
      throws JsonProcessingException {
    // FIXME: come back later to fix after sunset based on zoned time
    final Map<String, Object> parameters = this.buildParametersForYahrzeitApi(
        name, date.getYear(), date.getMonthValue(), date.getDayOfMonth(), false, totalYahrzeits);
    final Client client = ClientBuilder.newClient();
    /* TODO: Use the Failsafe framework to make this retryable in case the Internet connection or service endpoint
     *       is unstable when attempting to get a date conversion. */
    final Response response = client
        .target(REST_ENDPOINT_FOR_YAHRZEITS)
        .queryParam(CONFIG_KEY, parameters.get(CONFIG_KEY))
        .queryParam(EVENT_TYPE_KEY, parameters.get(EVENT_TYPE_KEY))
        .queryParam(NAME_KEY, parameters.get(NAME_KEY))
        .queryParam(GREGORIAN_YEAR_KEY, parameters.get(GREGORIAN_YEAR_KEY))
        .queryParam(GREGORIAN_MONTH_KEY, parameters.get(GREGORIAN_MONTH_KEY))
        .queryParam(GREGORIAN_DAY_KEY, parameters.get(GREGORIAN_DAY_KEY))
        .queryParam(YEARS_KEY, parameters.get(YEARS_KEY))
        .request(MediaType.APPLICATION_JSON)
        .get(Response.class);

    return MAPPER.readValue(response.readEntity(String.class), Yahrzeits.class);
  }

  private Map<String, Object> buildParametersForYahrzeitApi(
      final String name,
      final int gregorianYear,
      final int gregorianMonth,
      final int gregorianDay,
      final boolean afterSunset,
      final int years) {
    final Map<String, Object> parameters = new HashMap<>(9);
    parameters.put(CONFIG_KEY, JSON);
    parameters.put(EVENT_TYPE_KEY, "yahrzeit");
    parameters.put(NAME_KEY, name);
    parameters.put(GREGORIAN_YEAR_KEY, gregorianYear);
    parameters.put(GREGORIAN_MONTH_KEY, gregorianMonth);
    parameters.put(GREGORIAN_DAY_KEY, gregorianDay);

    if (afterSunset) {
      parameters.put(AFTER_SUNSET, ON);
    }

    parameters.put(YEARS_KEY, years);

    return Collections.unmodifiableMap(parameters);
  }
}
