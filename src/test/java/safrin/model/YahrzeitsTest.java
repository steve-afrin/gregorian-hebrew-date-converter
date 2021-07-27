package safrin.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import home.safrin.models.Yahrzeits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;

class YahrzeitsTest {
  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Test
  @DisplayName("Validate JSON deserialization for Yahrzeits")
  void jsonDeserialization() throws JsonProcessingException {
    final String json = "{\"title\":\"Yahrzeit: Dad's\",\"date\":\"2021-07-23T22:13:56.919Z\",\"items\":[{\"title\":\"Dad's's 25th Yahrzeit (24th of Adar)\",\"date\":\"2021-03-08\",\"memo\":\"Hebcal joins you in remembering Dad's, whose 25th Yahrzeit occurs on Monday, March 8, corresponding to the 24th of Adar, 5781.\\n\\nDad's's Yahrzeit begins at sundown on Sunday, March 7 and continues until sundown on the day of observance. It is customary to light a memorial candle at sundown as the Yahrzeit begins.\\n\\nMay your loved one's soul be bound up in the bond of eternal life and may their memory serve as a continued source of inspiration and comfort to you.\"},{\"title\":\"Dad's's 26th Yahrzeit (24th of Adar I)\",\"date\":\"2022-02-25\",\"memo\":\"Hebcal joins you in remembering Dad's, whose 26th Yahrzeit occurs on Friday, February 25, corresponding to the 24th of Adar I, 5782.\\n\\nDad's's Yahrzeit begins at sundown on Thursday, February 24 and continues until sundown on the day of observance. It is customary to light a memorial candle at sundown as the Yahrzeit begins.\\n\\nMay your loved one's soul be bound up in the bond of eternal life and may their memory serve as a continued source of inspiration and comfort to you.\"},{\"title\":\"Dad's's 27th Yahrzeit (24th of Adar)\",\"date\":\"2023-03-17\",\"memo\":\"Hebcal joins you in remembering Dad's, whose 27th Yahrzeit occurs on Friday, March 17, corresponding to the 24th of Adar, 5783.\\n\\nDad's's Yahrzeit begins at sundown on Thursday, March 16 and continues until sundown on the day of observance. It is customary to light a memorial candle at sundown as the Yahrzeit begins.\\n\\nMay your loved one's soul be bound up in the bond of eternal life and may their memory serve as a continued source of inspiration and comfort to you.\"},{\"title\":\"Dad's's 28th Yahrzeit (24th of Adar I)\",\"date\":\"2024-03-04\",\"memo\":\"Hebcal joins you in remembering Dad's, whose 28th Yahrzeit occurs on Monday, March 4, corresponding to the 24th of Adar I, 5784.\\n\\nDad's's Yahrzeit begins at sundown on Sunday, March 3 and continues until sundown on the day of observance. It is customary to light a memorial candle at sundown as the Yahrzeit begins.\\n\\nMay your loved one's soul be bound up in the bond of eternal life and may their memory serve as a continued source of inspiration and comfort to you.\"},{\"title\":\"Dad's's 29th Yahrzeit (24th of Adar)\",\"date\":\"2025-03-24\",\"memo\":\"Hebcal joins you in remembering Dad's, whose 29th Yahrzeit occurs on Monday, March 24, corresponding to the 24th of Adar, 5785.\\n\\nDad's's Yahrzeit begins at sundown on Sunday, March 23 and continues until sundown on the day of observance. It is customary to light a memorial candle at sundown as the Yahrzeit begins.\\n\\nMay your loved one's soul be bound up in the bond of eternal life and may their memory serve as a continued source of inspiration and comfort to you.\"},{\"title\":\"Dad's's 30th Yahrzeit (24th of Adar)\",\"date\":\"2026-03-13\",\"memo\":\"Hebcal joins you in remembering Dad's, whose 30th Yahrzeit occurs on Friday, March 13, corresponding to the 24th of Adar, 5786.\\n\\nDad's's Yahrzeit begins at sundown on Thursday, March 12 and continues until sundown on the day of observance. It is customary to light a memorial candle at sundown as the Yahrzeit begins.\\n\\nMay your loved one's soul be bound up in the bond of eternal life and may their memory serve as a continued source of inspiration and comfort to you.\"}]}";

    final Yahrzeits result = MAPPER.readValue(json, Yahrzeits.class);

    assertEquals("Yahrzeit: Dad's", result.getTitle());
    final LocalDateTime dateTime = LocalDateTime.ofInstant(result.getInstant(), ZoneOffset.UTC);
    assertEquals(2021, dateTime.getYear());
    assertEquals(Month.JULY, dateTime.getMonth());
    assertEquals(23, dateTime.getDayOfMonth());
    assertEquals(6, result.getYahrzeits().size());
  }
}
