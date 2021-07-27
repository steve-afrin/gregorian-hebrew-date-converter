package home.safrin.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

@JsonDeserialize(builder = Yahrzeit.Builder.class)
@AutoProperty
public final class Yahrzeit {
  private final String title, memo;
  private final LocalDate date;

  private Yahrzeit(
      final String title,
      final LocalDate date,
      final String memo) {
    this.title = title;
    this.date = date;
    this.memo = memo;
  }

  public String getTitle() {
    return this.title;
  }

  public LocalDate getDate() {
    return this.date;
  }

  public String getMemo() {
    return this.memo;
  }

  @Override
  public int hashCode() {
    return Pojomatic.hashCode(this);
  }

  @Override
  public boolean equals(final Object anotherObject) {
    return Pojomatic.equals(this, anotherObject);
  }

  @Override
  public String toString() {
    return Pojomatic.toString(this);
  }

  @JsonPOJOBuilder
  public static final class Builder {
    private String title, memo;
    private LocalDate date;

    /**
     * Sets the title of this Yahrzeit.
     * @param title a String value for what this yahrzeit represents
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("title")
    public Yahrzeit.Builder title(final String title) {
      this.title = title;
      return this;
    }

    /**
     * Sets the date of this Yahrzeit.
     * @param date a {@link LocalDate} value
     * @return {@code this} builder for use in a chained invocation
     */
    public Yahrzeit.Builder date(final LocalDate date) {
      this.date = date;
      return this;
    }

    /**
     * Sets the date of this Yahrzeit from a String value.
     * @param dateString a String value in the ISO_LOCAL_DATE format of {@code yyyy-mm-dd}
     * @return {@code this} builder for use in a chained invocation
     * @throws DateTimeParseException when the {@link DateTimeFormatter} instance has any problem parsing the
     * {@code dateString} parameter value
     */
    @JsonProperty("date")
    public Yahrzeit.Builder date(final String dateString) {
      /* LocalDate#parse(CharSequence) uses the DateTimeFormatter.ISO_LOCAL_DATE formatter - the ISO date formatter
       * that formats or parses a date without an offset, such as '2011-12-03'. */
      this.date = LocalDate.parse(dateString);
      return this;
    }

    /**
     * Sets the memo for this Yahrzeit.
     * @param memo a String value for what this yahrzeit represents
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("memo")
    public Yahrzeit.Builder memo(final String memo) {
      this.memo = memo;
      return this;
    }

    public Yahrzeit build() {
      return new Yahrzeit(title, date, memo);
    }
  }
}
