package home.safrin.types;

import com.github.rvesse.airline.model.ArgumentsMetadata;
import com.github.rvesse.airline.model.OptionMetadata;
import com.github.rvesse.airline.parser.ParseState;
import com.github.rvesse.airline.types.TypeConverter;
import com.github.rvesse.airline.types.TypeConverterProvider;

public class DateConverterProvider implements TypeConverterProvider {
  @Override
  public <T> TypeConverter getTypeConverter(final OptionMetadata option, final ParseState<T> state) {
    return new DateConverter(option, state);
  }

  @Override
  public <T> TypeConverter getTypeConverter(final ArgumentsMetadata arguments, final ParseState<T> state) {
    return new DateConverter(arguments, state);
  }
}
