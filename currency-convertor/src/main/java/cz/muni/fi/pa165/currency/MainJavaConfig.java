package cz.muni.fi.pa165.currency;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Class
 *
 * @author Petr Kantek
 */
public class MainJavaConfig {

    public static void main(String[] args) {

        Currency eur = Currency.getInstance("EUR");
        Currency czk = Currency.getInstance("CZK");
        BigDecimal amount = new BigDecimal("1");

        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConvertorConfigSpring.class);
        CurrencyConvertor convertor = (CurrencyConvertor) context.getBean("currencyConvertorImpl");

        if (convertor.convert(eur, czk, amount).compareTo(new BigDecimal("27")) == 0) {
            System.out.println("Java config application context works!");
        } else {
            System.out.println("Java config application context does not work!");
        }
    }
}
