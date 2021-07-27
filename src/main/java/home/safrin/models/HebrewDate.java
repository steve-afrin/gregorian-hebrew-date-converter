package home.safrin.models;

import com.google.common.base.Preconditions;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

/**
 * <p>Immutable implementation of {@link HebrewDate}.</p>
 * <p>Use the builder to create immutable instances:
 * {@code new HebrewDate.Builder()}.</p>
 */
@AutoProperty
public final class HebrewDate {
  private final int hebrewYear, hebrewDay;
  private final String hebrewMonth;

  private HebrewDate(
      final int hebrewYear,
      final String hebrewMonth,
      final int hebrewDay) {
    this.hebrewYear = hebrewYear;
    this.hebrewMonth = hebrewMonth;
    Preconditions.checkArgument(hebrewDay >= 1 && hebrewDay <= 28,
        String.format("%d is in invalid day value for a Hebrew date", hebrewDay));
    this.hebrewDay = hebrewDay;
  }

  public int getHebrewYear() {
    return this.hebrewYear;
  }

  public String getHebrewMonth() {
    return this.hebrewMonth;
  }

  public int getHebrewDay() {
    return this.hebrewDay;
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

  public static final class Builder {
    private int hebrewYear, hebrewDay;
    private String hebrewMonth;

    public HebrewDate.Builder hebrewYear(final int year) {
      this.hebrewYear = year;
      return this;
    }

    public HebrewDate.Builder hebrewMonth(final String month) {
      this.hebrewMonth = month;
      return this;
    }

    public HebrewDate.Builder hebrewDay(final int day) {
      this.hebrewDay = day;
      return this;
    }

    public HebrewDate build() {
      return new HebrewDate(this.hebrewYear, this.hebrewMonth, this.hebrewDay);
    }
  }
}
