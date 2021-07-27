package home.safrin.types;

import com.github.rvesse.airline.model.ArgumentsMetadata;
import com.github.rvesse.airline.model.OptionMetadata;
import com.github.rvesse.airline.parser.ParseState;
import com.github.rvesse.airline.types.TypeConverter;
import com.github.rvesse.airline.types.numerics.NumericTypeConverter;
import java.time.LocalDate;

public class DateConverter implements TypeConverter {
  final ArgumentsMetadata arguments;
  final OptionMetadata option;
  final ParseState<?> state;

  public DateConverter(final ArgumentsMetadata arguments, final ParseState<?> state) {
    this.arguments = arguments;
    this.option = null;
    this.state = state;
  }

  public DateConverter(final OptionMetadata option, final ParseState<?> state) {
    this.arguments = null;
    this.option = option;
    this.state = state;
  }

  @Override
  public LocalDate convert(final String name, final Class<?> type, final String value) {
//    if (!String.class.isAssignableFrom(type)) {
//      throw new IllegalArgumentException(
//          String.format("Cannot parse a %s object to a LocalDate instance", type.getName()));
//    }

    /* TODO: By default this parsing uses the yyyy-mm-dd format, but perhaps in the future we
     *       might want to accept other date formats. */
    return LocalDate.parse(value);
  }

  @Override
  public void setNumericConverter(final NumericTypeConverter converter) {
    throw new UnsupportedOperationException("Not support for this type conversion");
  }
}
