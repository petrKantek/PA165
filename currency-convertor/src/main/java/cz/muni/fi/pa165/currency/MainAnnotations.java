package cz.muni.fi.pa165.currency;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Class
 *
 * @author Petr Kantek
 */
public class MainAnnotations {

    public static void main(String[] args) {
        Currency eur = Currency.getInstance("EUR");
        Currency czk = Currency.getInstance("CZK");
        BigDecimal amount = new BigDecimal("1");

        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        CurrencyConvertor convertor = (CurrencyConvertor) context.getBean("convertor");

        if (convertor.convert(eur, czk, amount).compareTo(new BigDecimal("27")) == 0) {
            System.out.println("Annotation application context works!");
        } else {
            System.out.println("Annotation application context does not work!");
        }
    }


}
