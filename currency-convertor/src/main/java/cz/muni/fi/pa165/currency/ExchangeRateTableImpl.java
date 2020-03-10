package cz.muni.fi.pa165.currency;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.inject.Named;
import java.math.BigDecimal;
import java.util.Currency;

/**
 * Class
 *
 * @author Petr Kantek
 */
@Service
public class ExchangeRateTableImpl implements ExchangeRateTable{

    @Override
    public BigDecimal getExchangeRate(Currency sourceCurrency, Currency targetCurrency) throws ExternalServiceFailureException {
        if (sourceCurrency == null) throw new IllegalArgumentException("Source currency is null.");
        if (targetCurrency == null) throw new IllegalArgumentException("Target currency is null.");
        if (sourceCurrency.getCurrencyCode().equals("EUR") && targetCurrency.getCurrencyCode().equals("CZK")) return new BigDecimal("27");
        return null;
    }
}
