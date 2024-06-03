package comm;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class Info {
    public void execute(String languageTag) {
        Locale locale = Locale.forLanguageTag(languageTag);
        ResourceBundle messages = ResourceBundle.getBundle("res.Messages", locale);
        DateFormatSymbols symbols = new DateFormatSymbols(locale);
        Currency currency = Currency.getInstance(locale);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy", locale);
        Date today = new Date();

        System.out.println(messages.getString("info") + " " + locale.getDisplayName());
        System.out.println("Country: " + locale.getDisplayCountry(locale));
        System.out.println("Language: " + locale.getDisplayLanguage(locale));
        System.out.println("Currency: " + currency.getDisplayName());
        System.out.println("Week Days: " + String.join(", ", symbols.getWeekdays()));
        System.out.println("Months: " + String.join(", ", symbols.getMonths()));
        System.out.println("Today: " + dateFormat.format(today));
    }
}
