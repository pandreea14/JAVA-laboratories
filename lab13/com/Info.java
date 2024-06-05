package com;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class Info {
    public static void execute(Locale locale) {
        ResourceBundle messages = ResourceBundle.getBundle("res.Messages", locale);
        System.out.println(String.format(messages.getString("info"), locale.getDisplayName()));

        Currency currency = Currency.getInstance(locale);
        DateFormatSymbols dfs = new DateFormatSymbols(locale);
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, locale);

        System.out.println("Country: " + locale.getDisplayCountry());
        System.out.println("Language: " + locale.getDisplayLanguage());
        System.out.println("Currency: " + currency.getCurrencyCode() + " (" + currency.getDisplayName() + ")");
        System.out.println("Week Days: " + String.join(", ", dfs.getWeekdays()));
        System.out.println("Months: " + String.join(", ", dfs.getMonths()));
        System.out.println("Today: " + df.format(new Date()));
    }
}
