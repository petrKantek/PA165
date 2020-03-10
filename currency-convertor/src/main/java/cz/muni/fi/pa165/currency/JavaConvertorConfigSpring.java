package cz.muni.fi.pa165.currency;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Class
 *
 * @author Petr Kantek
 */
@Configuration
@ComponentScan("cz.muni.fi.pa165.currency")
@EnableAspectJAutoProxy
public class JavaConvertorConfigSpring {

//    @Bean
//    public CurrencyConvertor currencyConvertor() {
//        return new CurrencyConvertorImpl(exchangeRateTable());
//    }
//
//    @Bean
//    public ExchangeRateTable exchangeRateTable() {
//        return new ExchangeRateTableImpl();
//    }
}
