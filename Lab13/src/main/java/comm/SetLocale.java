package comm;
import java.util.Locale;
import java.util.ResourceBundle;

public class SetLocale {
    public void execute(String languageTag) {
        Locale locale = Locale.forLanguageTag(languageTag);
        Locale.setDefault(locale);
        ResourceBundle messages = ResourceBundle.getBundle("res.Messages");
        System.out.println(messages.getString("locale.set") + " " + locale.getDisplayName());
    }
}
