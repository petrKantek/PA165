package cz.muni.fi.pa165.currency;

import org.junit.Rule;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CurrencyConvertorImplTest {

    private static Currency czk = Currency.getInstance("CZK");
    private static Currency usd = Currency.getInstance("USD");
    private static Currency eur = Currency.getInstance("EUR");
    private static Currency gbp = Currency.getInstance("GBP");

    private static BigDecimal amount = new BigDecimal("100");

    private static ExchangeRateTable table = mock(ExchangeRateTable.class);
    private static CurrencyConvertor conv = new CurrencyConvertorImpl(table);

    @Test
    public void testConvert() throws ExternalServiceFailureException {

        when(table.getExchangeRate(Currency.getInstance("USD"), Currency.getInstance("CZK"))).thenReturn(new BigDecimal("25"));
        when(table.getExchangeRate(Currency.getInstance("USD"), Currency.getInstance("EUR"))).thenReturn(new BigDecimal("0.92"));
        //doThrow(new ExternalServiceFailureException("Cant't find this currency")).when(table).getExchangeRate(Currency.getInstance("GBP"), any(Currency.class));

        assertEquals(new BigDecimal("2500.00"), conv.convert(usd, czk, new BigDecimal("100")));
        assertEquals(new BigDecimal("18.45"), conv.convert(usd, eur, new BigDecimal("20.050")));
        assertEquals(new BigDecimal("18.43"), conv.convert(usd, eur, new BigDecimal("20.038")));
        assertEquals(new BigDecimal("19.31"), conv.convert(usd, eur, new BigDecimal("20.99")));
        assertEquals(new BigDecimal("0.00"), conv.convert(usd, eur, new BigDecimal("0")));
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testConvertWithNullSourceCurrency() {
        expectedException.expect(IllegalArgumentException.class);
        conv.convert(null, czk, amount);
    }

    @Test
    public void testConvertWithNullTargetCurrency() {
        expectedException.expect(IllegalArgumentException.class);
        conv.convert(usd, null, amount);
    }

    @Test
    public void testConvertWithNullSourceAmount() {
        expectedException.expect(IllegalArgumentException.class);
        conv.convert(usd, czk, null);
    }

    @Test
    public void testConvertWithUnknownCurrency() throws ExternalServiceFailureException {
        when(table.getExchangeRate(czk, eur)).thenReturn(null);
        expectedException.expect(UnknownExchangeRateException.class);
        conv.convert(Currency.getInstance("CZK"), eur, amount);
    }

    @Test
    public void testConvertWithExternalServiceFailure() throws ExternalServiceFailureException {
        when(table.getExchangeRate(gbp, czk)).thenThrow(UnknownExchangeRateException.class);
        expectedException.expect(UnknownExchangeRateException.class);
        conv.convert(gbp, czk, amount);
    }
}
