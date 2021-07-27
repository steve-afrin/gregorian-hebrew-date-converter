package home.safrin;

import home.safrin.services.HebrewDateConversionService;
import home.safrin.services.YahrzeitCalculationService;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class ServicesConfig {
  @Bean
  public HebrewDateConversionService dateConversionService() {
    return new HebrewDateConversionService();
  }

  @Bean
  public YahrzeitCalculationService yahrzeitCalculationService() {
    return new YahrzeitCalculationService();
  }
}
