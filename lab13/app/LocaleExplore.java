package app;

import com.DisplayLocales;
import com.SetLocale;
import com.Info;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocaleExplore {
    public staticinfo void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SetLocale setLocale = new SetLocale();
        Locale currentLocale = Locale.getDefault();
        setLocale.setLocale(currentLocale.toLanguageTag());

        while (true) {
            ResourceBundle messages = ResourceBundle.getBundle("res.Messages", setLocale.getCurrentLocale());
            System.out.print(messages.getString("prompt"));
            String command = scanner.nextLine();

            switch (command) {
                case "display locales":
                    DisplayLocales.execute(setLocale.getCurrentLocale());
                    break;
                case "set locale":
                    System.out.print("Enter locale (e.g., en-US, ro-RO): ");
                    String localeStr = scanner.nextLine();
                    setLocale.setLocale(localeStr);
                    break;
                case "info":
                    Info.execute(setLocale.getCurrentLocale());
                    break;
                case "exit":
                    System.exit(0);
                    break;
                default:
                    System.out.println(messages.getString("invalid"));
            }
        }
    }
}
//info
//display locales
//set locale -> ro-RO
//info
//set locale -> en-RO
//info
//exit