package home.safrin.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

@JsonDeserialize(builder = Yahrzeits.Builder.class)
@AutoProperty
public class Yahrzeits {
  private final String title;
  private final Instant instant;
  private final List<Yahrzeit> yahrzeits;

  private Yahrzeits(
      final String title,
      final Instant instant,
      final List<Yahrzeit> yahrzeits) {
    this.title = title;
    this.instant = instant;
    this.yahrzeits = List.copyOf(yahrzeits);
  }

  public String getTitle() {
    return this.title;
  }

  public Instant getInstant() {
    return this.instant;
  }

  public List<Yahrzeit> getYahrzeits() {
    return this.yahrzeits;
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
    private String title;
    private Instant instant;
    private List<Yahrzeit> yahrzeits;

    @JsonProperty("title")
    public Yahrzeits.Builder title(final String title) {
      this.title = title;
      return this;
    }

    public Yahrzeits.Builder instant(final Instant instant) {
      this.instant = instant;
      return this;
    }

    @JsonProperty("date")
    public Yahrzeits.Builder instant(final String instantString) {
      this.instant = Instant.parse(instantString);
      return this;
    }

    @JsonProperty("items")
    public Yahrzeits.Builder yahrzeits(final Collection<Yahrzeit>yahrzeits) {
      this.yahrzeits = List.copyOf(yahrzeits);
      return this;
    }

    public Yahrzeits build() {
      return new Yahrzeits(this.title, this.instant, this.yahrzeits);
    }
  }
}
