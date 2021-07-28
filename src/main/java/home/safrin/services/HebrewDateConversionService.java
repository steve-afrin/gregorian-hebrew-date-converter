package home.safrin.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import home.safrin.models.ConvertedHebrewDate;
import home.safrin.models.HebrewDate;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Service class for invoking RESTful webservice endpoints on hebcal.com to convert dates between the
 * Gregorian and Hebrew calendars.</p>
 * <p>Here are a couple {@code curl} commands to test the actual REST endpoints from the command line:</p>
 * <ul>
 *   <li>Converting from a Gregorian date to a Hebrew date:
 *   {@code curl -G https://www.hebcal.com/converter?cfg=json\&gy=1996\&gm=3\&gd=15\&g2h=1}</li>
 *   <li>Converting from a Hebrew date to a Gregorian date:
 *   {@code curl -G https://www.hebcal.com/converter?cfg=json\&hy=5781\&hm=Av\&hd=13\&h2g=1}</li>
 * </ul>
 */
public class HebrewDateConversionService {
  private static final Logger LOG = LoggerFactory.getLogger(HebrewDateConversionService.class);

  private static final ObjectMapper MAPPER = new ObjectMapper();

  // The freely available REST API web service at hebcal.com
  private static final String REST_ENDPOINT_FOR_DATE_CONVERTER = "https://www.hebcal.com/converter";

  private static final String CONFIG_KEY = "cfg";
  private static final String JSON = "json";
  private static final String GREGORIAN_YEAR_KEY = "gy";
  private static final String GREGORIAN_MONTH_KEY = "gm";
  private static final String GREGORIAN_DAY_KEY = "gd";
  private static final String HEBREW_YEAR_KEY = "hy";
  private static final String HEBREW_MONTH_KEY = "hm";
  private static final String HEBREW_DAY_KEY = "hd";
  private static final String AFTER_SUNSET = "gs";
  private static final String ON = "on";
  private static final String CONVERT_TO_GREGORIAN_DATE = "h2g";
  private static final String CONVERT_TO_HEBREW_DATE = "g2h";

  /**
   * Computes the Hebrew date given a Gregorian date.
   *
   * @param dateTime a {@link ZonedDateTime} instance that represents the Gregorian date to be converted to a Hebrew
   *                 date
   * @return the {@link ConvertedHebrewDate} instance deserialized from the REST webservice that does the actual
   * computation
   */
  public ConvertedHebrewDate getHebrewDate(final ZonedDateTime dateTime) throws JsonProcessingException {
    // FIXME: come back later to fix after sunset based on zoned time
    final Map<String, Object> parameters = this.buildParametersForHebcalRestApi(
        dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth(), false);
    final Client client = ClientBuilder.newClient();
    /* TODO: Use the Failsafe framework to make this retryable in case the Internet connection or service endpoint
     *       is unstable when attempting to get a date conversion. */
    final Response response = client
        .target(REST_ENDPOINT_FOR_DATE_CONVERTER)
        .queryParam(CONFIG_KEY, parameters.get(CONFIG_KEY))
        .queryParam(GREGORIAN_YEAR_KEY, parameters.get(GREGORIAN_YEAR_KEY))
        .queryParam(GREGORIAN_MONTH_KEY, parameters.get(GREGORIAN_MONTH_KEY))
        .queryParam(GREGORIAN_DAY_KEY, parameters.get(GREGORIAN_DAY_KEY))
        .queryParam(CONVERT_TO_HEBREW_DATE, parameters.get(CONVERT_TO_HEBREW_DATE))
        .request(MediaType.APPLICATION_JSON)
        .get(Response.class);

    return MAPPER.readValue(response.readEntity(String.class), ConvertedHebrewDate.class);
  }

  public ConvertedHebrewDate getGregorianDate(final HebrewDate hebrewDate) throws JsonProcessingException {
    final Map<String, Object> parameters = this.buildParametersForHebrewDateConversion(
        hebrewDate.getHebrewYear(), hebrewDate.getHebrewMonth(), hebrewDate.getHebrewDay());
    final Client client = ClientBuilder.newClient();
    /* TODO: Use the Failsafe framework to make this retryable in case the Internet connection or service endpoint
     *       is unstable when attempting to get a date conversion. */
    final Response response = client
        .target(REST_ENDPOINT_FOR_DATE_CONVERTER)
        .queryParam(CONFIG_KEY, parameters.get(CONFIG_KEY))
        .queryParam(HEBREW_YEAR_KEY, parameters.get(HEBREW_YEAR_KEY))
        .queryParam(HEBREW_MONTH_KEY, parameters.get(HEBREW_MONTH_KEY))
        .queryParam(HEBREW_DAY_KEY, parameters.get(HEBREW_DAY_KEY))
        .queryParam(CONVERT_TO_GREGORIAN_DATE, parameters.get(CONVERT_TO_GREGORIAN_DATE))
        .request(MediaType.APPLICATION_JSON)
        .get(Response.class);

    return MAPPER.readValue(response.readEntity(String.class), ConvertedHebrewDate.class);
  }

  private Map<String, Object> buildParametersForHebcalRestApi(
      final int gregorianYear,
      final int gregorianMonth,
      final int gregorianDay,
      final boolean afterSunset) {
    final Map<String, Object> parameters = new HashMap<>(6);
    parameters.put(CONFIG_KEY, JSON);
    parameters.put(GREGORIAN_YEAR_KEY, gregorianYear);
    parameters.put(GREGORIAN_MONTH_KEY, gregorianMonth);
    parameters.put(GREGORIAN_DAY_KEY, gregorianDay);
    parameters.put(CONVERT_TO_HEBREW_DATE, 1);

    if (afterSunset) {
      parameters.put(AFTER_SUNSET, ON);
    }

    return Collections.unmodifiableMap(parameters);
  }

  private Map<String, Object> buildParametersForHebrewDateConversion(
      final int hebrewYear,
      final String hebrewMonth,
      final int hebrewDay) {
    return Map.of(CONFIG_KEY, JSON,
        HEBREW_YEAR_KEY, hebrewYear,
        HEBREW_MONTH_KEY, hebrewMonth,
        HEBREW_DAY_KEY, hebrewDay,
        CONVERT_TO_GREGORIAN_DATE, 1);
  }
}
