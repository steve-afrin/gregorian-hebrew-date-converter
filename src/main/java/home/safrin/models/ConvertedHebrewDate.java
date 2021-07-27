package home.safrin.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Objects;
import javax.annotation.Nullable;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

/**
 * <p>Immutable implementation of {@link ConvertedHebrewDate}.</p>
 * <p>Use the builder to create immutable instances:
 * {@code new ConvertedHebrewDate.Builder()}.</p>
 */
@JsonSerialize
@JsonDeserialize(builder = ConvertedHebrewDate.Builder.class)
@AutoProperty
public final class ConvertedHebrewDate {
  private final int gregorianYear, gregorianMonth, gregorianDay;
  private final boolean isAfterSunset;
  private final int hebrewYear, hebrewDay;
  private final String hebrewMonth;
  private final String hebrewDate;
  private final String[] events;

  private ConvertedHebrewDate(
      final int gregorianYear,
      final int gregorianMonth,
      final int gregorianDay,
      final boolean afterSunset,
      final int hebrewYear,
      final String hebrewMonth,
      final int hebrewDay,
      final String hebrewDate,
      final String[] events) {
    this.gregorianYear = gregorianYear;
    this.gregorianMonth = gregorianMonth;
    this.gregorianDay = gregorianDay;
    this.isAfterSunset = afterSunset;
    this.hebrewYear = hebrewYear;
    this.hebrewMonth = hebrewMonth;
    this.hebrewDay = hebrewDay;
    this.hebrewDate = hebrewDate;
    this.events = events;
  }

  /**
   * @return The value of the {@code gregorianYear} attribute
   */
  @JsonProperty("gy")
  public int getGregorianYear() {
    return this.gregorianYear;
  }

  /**
   * @return The value of the {@code gregorianMonth} attribute
   */
  @JsonProperty("gm")
  public int getGregorianMonth() {
    return this.gregorianMonth;
  }

  /**
   * @return The value of the {@code gregorianDay} attribute
   */
  @JsonProperty("gd")
  public int getGregorianDay() {
    return this.gregorianDay;
  }

  @JsonProperty("afterSunset")
  public boolean isAfterSunset() {
    return this.isAfterSunset;
  }

  /**
   * @return The value of the {@code hebrewYear} attribute
   */
  @JsonProperty("hy")
  public int getHebrewYear() {
    return this.hebrewYear;
  }

  /**
   * @return The value of the {@code hebrewMonth} attribute
   */
  @JsonProperty("hm")
  public String getHebrewMonth() {
    return this.hebrewMonth;
  }

  /**
   * @return The value of the {@code hebrewDay} attribute
   */
  @JsonProperty("hd")
  public int getHebrewDay() {
    return this.hebrewDay;
  }

  /**
   * @return The value of the {@code hebrewDate} attribute
   */
  @JsonProperty("hebrew")
  public String getHebrewDate() {
    return this.hebrewDate;
  }

  /**
   * @return A cloned {@code events} array
   */
  @JsonProperty("events")
  public String[] getEvents() {
    return this.events.clone();
  }

  /**
   * Copy the current immutable object by setting a value for the {@link ConvertedHebrewDate#getGregorianYear() gregorianYear} attribute.
   * A value equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for gregorianYear
   * @return A modified copy of the {@code this} object
   */
  public final ConvertedHebrewDate withGregorianYear(final int value) {
    if (this.gregorianYear == value) {
      return this;
    }

    return new ConvertedHebrewDate(
        value,
        this.gregorianMonth,
        this.gregorianDay,
        this.isAfterSunset,
        this.hebrewYear,
        this.hebrewMonth,
        this.hebrewDay,
        this.hebrewDate,
        this.events);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link ConvertedHebrewDate#getGregorianMonth() gregorianMonth} attribute.
   * A value equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for gregorianMonth
   * @return A modified copy of the {@code this} object
   */
  public final ConvertedHebrewDate withGregorianMonth(final int value) {
    if (this.gregorianMonth == value) {
      return this;
    }

    return new ConvertedHebrewDate(
        this.gregorianYear,
        value,
        this.gregorianDay,
        this.isAfterSunset,
        this.hebrewYear,
        this.hebrewMonth,
        this.hebrewDay,
        this.hebrewDate,
        this.events);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link ConvertedHebrewDate#getGregorianDay() gregorianDay} attribute.
   * A value equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for gregorianDay
   * @return A modified copy of the {@code this} object
   */
  public final ConvertedHebrewDate withGregorianDay(final int value) {
    if (this.gregorianDay == value) {
      return this;
    }

    return new ConvertedHebrewDate(
        this.gregorianYear,
        this.gregorianMonth,
        value,
        this.isAfterSunset,
        this.hebrewYear,
        this.hebrewMonth,
        this.hebrewDay,
        this.hebrewDate,
        this.events);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link ConvertedHebrewDate#getHebrewYear() hebrewYear} attribute.
   * A value equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for hebrewYear
   * @return A modified copy of the {@code this} object
   */
  public final ConvertedHebrewDate withHebrewYear(final int value) {
    if (this.hebrewYear == value) {
      return this;
    }

    return new ConvertedHebrewDate(
        this.gregorianYear,
        this.gregorianMonth,
        this.gregorianDay,
        this.isAfterSunset,
        value,
        this.hebrewMonth,
        this.hebrewDay,
        this.hebrewDate,
        this.events);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link ConvertedHebrewDate#getHebrewMonth() hebrewMonth} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for hebrewMonth
   * @return A modified copy of the {@code this} object
   */
  public final ConvertedHebrewDate withHebrewMonth(final String value) {
    final String newValue = Objects.requireNonNull(value, "hebrewMonth");

    if (this.hebrewMonth.equals(newValue)) {
      return this;
    }

    return new ConvertedHebrewDate(
        this.gregorianYear,
        this.gregorianMonth,
        this.gregorianDay,
        this.isAfterSunset,
        this.hebrewYear,
        newValue,
        this.hebrewDay,
        this.hebrewDate,
        this.events);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link ConvertedHebrewDate#getHebrewDay() hebrewDay} attribute.
   * A value equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for hebrewDay
   * @return A modified copy of the {@code this} object
   */
  public final ConvertedHebrewDate withHebrewDay(final int value) {
    if (this.hebrewDay == value) {
      return this;
    }

    return new ConvertedHebrewDate(
        this.gregorianYear,
        this.gregorianMonth,
        this.gregorianDay,
        this.isAfterSunset,
        this.hebrewYear,
        this.hebrewMonth,
        value,
        this.hebrewDate,
        this.events);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link ConvertedHebrewDate#getHebrewDate() hebrewDate} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for hebrewDate
   * @return A modified copy of the {@code this} object
   */
  public final ConvertedHebrewDate withHebrewDate(final String value) {
    final String newValue = Objects.requireNonNull(value, "hebrewDate");

    if (this.hebrewDate.equals(newValue)) {
      return this;
    }

    return new ConvertedHebrewDate(
        this.gregorianYear,
        this.gregorianMonth,
        this.gregorianDay,
        this.isAfterSunset,
        this.hebrewYear,
        this.hebrewMonth,
        this.hebrewDay,
        newValue,
        this.events);
  }

  /**
   * Copy the current immutable object with elements that replace the content of {@link ConvertedHebrewDate#getEvents() events}.
   * The array is cloned before being saved as attribute values.
   * @param elements The non-null elements for events
   * @return A modified copy of {@code this} object
   */
  public final ConvertedHebrewDate withEvents(final String[] elements) {
    final String[] newValue = elements.clone();

    return new ConvertedHebrewDate(
        this.gregorianYear,
        this.gregorianMonth,
        this.gregorianDay,
        this.isAfterSunset,
        this.hebrewYear,
        this.hebrewMonth,
        this.hebrewDay,
        this.hebrewDate,
        newValue);
  }

  /**
   * This instance is equal to all instances of {@code ConvertedHebrewDate} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(@Nullable Object another) {
    return Pojomatic.equals(this, another);
  }

  /**
   * Computes a hash code from attributes: {@code gregorianYear}, {@code gregorianMonth}, {@code gregorianDay},
   * {@code hebrewYear}, {@code hebrewMonth}, {@code hebrewDay}, {@code hebrewDate}, {@code events}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    return Pojomatic.hashCode(this);
  }

  /**
   * Prints the immutable value {@code ConvertedHebrewDate} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return Pojomatic.toString(this);
  }

  /**
   * Creates an immutable copy of a {@link ConvertedHebrewDate} value.
   * Uses accessors to get values to initialize the new immutable instance.
   * If an instance is already immutable, it is returned as is.
   * @param instance The instance to copy
   * @return A copied immutable ConvertedHebrewDate instance
   */
  public static ConvertedHebrewDate copyOf(final ConvertedHebrewDate instance) {
    if (instance instanceof ConvertedHebrewDate) {
      return instance;
    }

    return new ConvertedHebrewDate.Builder()
        .from(instance)
        .build();
  }

  /**
   * Builds instances of type {@link ConvertedHebrewDate ConvertedHebrewDate}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @JsonPOJOBuilder(withPrefix = "set")
  public static class Builder {
    private int gregorianYear;
    private int gregorianMonth;
    private int gregorianDay;
    private boolean isAfterSunset;
    private int hebrewYear;
    private @Nullable String hebrewMonth;
    private int hebrewDay;
    private @Nullable String hebrewDate;
    private @Nullable String[] events;

    /**
     * Creates a builder for {@link ConvertedHebrewDate ConvertedHebrewDate} instances.
     * <pre>
     * new ConvertedHebrewDate.Builder()
     *    .gregorianYear(int) // required {@link ConvertedHebrewDate#getGregorianYear() gregorianYear}
     *    .gregorianMonth(int) // required {@link ConvertedHebrewDate#getGregorianMonth() gregorianMonth}
     *    .gregorianDay(int) // required {@link ConvertedHebrewDate#getGregorianDay() gregorianDay}
     *    .hebrewYear(int) // required {@link ConvertedHebrewDate#getHebrewYear() hebrewYear}
     *    .hebrewMonth(String) // required {@link ConvertedHebrewDate#getHebrewMonth() hebrewMonth}
     *    .hebrewDay(int) // required {@link ConvertedHebrewDate#getHebrewDay() hebrewDay}
     *    .hebrewDate(String) // required {@link ConvertedHebrewDate#getHebrewDate() hebrewDate}
     *    .events(String) // required {@link ConvertedHebrewDate#getEvents() events}
     *    .build();
     * </pre>
     */
    public Builder() {
      if (!(this instanceof ConvertedHebrewDate.Builder)) {
        throw new UnsupportedOperationException("Use: new ConvertedHebrewDate.Builder()");
      }
    }

    /**
     * Fill a builder with attribute values from the provided {@code ConvertedHebrewDate} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue
    public final ConvertedHebrewDate.Builder from(final ConvertedHebrewDate instance) {
      Objects.requireNonNull(instance, "instance");
      this.gregorianYear(instance.getGregorianYear());
      this.gregorianMonth(instance.getGregorianMonth());
      this.gregorianDay(instance.getGregorianDay());
      this.hebrewYear(instance.getHebrewYear());
      this.hebrewMonth(instance.getHebrewMonth());
      this.hebrewDay(instance.getHebrewDay());
      this.hebrewDate(instance.getHebrewDate());
      this.events(instance.getEvents());
      return this;
    }

    /**
     * Initializes the value for the {@link ConvertedHebrewDate#getGregorianYear() gregorianYear} attribute.
     * @param gregorianYear The value for gregorianYear
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue
    @JsonProperty("gy")
    public final ConvertedHebrewDate.Builder gregorianYear(final int gregorianYear) {
      this.gregorianYear = gregorianYear;
      return this;
    }

    /**
     * Initializes the value for the {@link ConvertedHebrewDate#getGregorianMonth() gregorianMonth} attribute.
     * @param gregorianMonth The value for gregorianMonth
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue
    @JsonProperty("gm")
    public final ConvertedHebrewDate.Builder gregorianMonth(final int gregorianMonth) {
      this.gregorianMonth = gregorianMonth;
      return this;
    }

    /**
     * Initializes the value for the {@link ConvertedHebrewDate#getGregorianDay() gregorianDay} attribute.
     * @param gregorianDay The value for gregorianDay
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue
    @JsonProperty("gd")
    public final ConvertedHebrewDate.Builder gregorianDay(final int gregorianDay) {
      this.gregorianDay = gregorianDay;
      return this;
    }

    /**
     * Initializes the value for the {@link ConvertedHebrewDate#isAfterSunset() afterSunset} attribute.
     * @param afterSunset The value for afterSunset
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue
    @JsonProperty("afterSunset")
    public final ConvertedHebrewDate.Builder afterSunset(final boolean afterSunset) {
      this.isAfterSunset = afterSunset;
      return this;
    }

    /**
     * Initializes the value for the {@link ConvertedHebrewDate#getHebrewYear() hebrewYear} attribute.
     * @param hebrewYear The value for hebrewYear
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue
    @JsonProperty("hy")
    public final ConvertedHebrewDate.Builder hebrewYear(final int hebrewYear) {
      this.hebrewYear = hebrewYear;
      return this;
    }

    /**
     * Initializes the value for the {@link ConvertedHebrewDate#getHebrewMonth() hebrewMonth} attribute.
     * @param hebrewMonth The value for hebrewMonth
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue
    @JsonProperty("hm")
    public final ConvertedHebrewDate.Builder hebrewMonth(final String hebrewMonth) {
      this.hebrewMonth = Objects.requireNonNull(hebrewMonth, "hebrewMonth");
      return this;
    }

    /**
     * Initializes the value for the {@link ConvertedHebrewDate#getHebrewDay() hebrewDay} attribute.
     * @param hebrewDay The value for hebrewDay
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue
    @JsonProperty("hd")
    public final ConvertedHebrewDate.Builder hebrewDay(final int hebrewDay) {
      this.hebrewDay = hebrewDay;
      return this;
    }

    /**
     * Initializes the value for the {@link ConvertedHebrewDate#getHebrewDate() hebrewDate} attribute.
     * @param hebrewDate The value for hebrewDate
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue
    @JsonProperty("hebrew")
    public final ConvertedHebrewDate.Builder hebrewDate(final String hebrewDate) {
      this.hebrewDate = Objects.requireNonNull(hebrewDate, "hebrewDate");
      return this;
    }

    /**
     * Initializes the value for the {@link ConvertedHebrewDate#getEvents() events} attribute.
     * @param events The elements for events
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue
    @JsonProperty("events")
    public final ConvertedHebrewDate.Builder events(final String[] events) {
      this.events = events == null ? null : events.clone();
      return this;
    }

    /**
     * Builds a new {@link ConvertedHebrewDate ConvertedHebrewDate}.
     * @return An immutable instance of ConvertedHebrewDate
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    @JsonCreator
    public ConvertedHebrewDate build() {
      return new ConvertedHebrewDate(
          this.gregorianYear,
          this.gregorianMonth,
          this.gregorianDay,
          this.isAfterSunset,
          this.hebrewYear,
          this.hebrewMonth,
          this.hebrewDay,
          this.hebrewDate,
          this.events == null ? null : this.events.clone());
    }
  }
}
