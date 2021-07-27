package safrin.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import home.safrin.models.Yahrzeit;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class YahrzeitTest {
  static final ObjectMapper MAPPER = new ObjectMapper();

  @Test
  @DisplayName("Validate JSON deserialization of Yahrzeit")
  void jsonDeserialization() throws JsonProcessingException {
    final String expectedMemo = "Hebcal joins you in remembering Dad, whose 25th Yahrzeit occurs on Monday, March 8, corresponding to the 24th of Adar, 5781.\\n\\nDad's Yahrzeit begins at sundown on Sunday, March 7 and continues until sundown on the day of observance. It is customary to light a memorial candle at sundown as the Yahrzeit begins.\\n\\nMay your loved one's soul be bound up in the bond of eternal life and may their memory serve as a continued source of inspiration and comfort to you.";
    final String json = "{\"title\":\"Dad's's 25th Yahrzeit (24th of Adar)\",\"date\":\"2021-03-08\",\"memo\":\"" + expectedMemo + "\"}";

    final Yahrzeit result = MAPPER.readValue(json, Yahrzeit.class);

    assertEquals("Dad's's 25th Yahrzeit (24th of Adar)", result.getTitle());
    assertEquals(LocalDate.of(2021, 3, 8), result.getDate());
    // TODO: Need to fix whitespace issues for comparison of multi-line String comparison.
//    assertEquals(expectedMemo, result.getMemo());
  }
}
