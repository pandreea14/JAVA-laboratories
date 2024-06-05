package com;

import java.util.Locale;
import java.util.ResourceBundle;

public class DisplayLocales {
    public static void execute(Locale currentLocale) {
        //retrieve the ResourceBundle for the specified locale
        ResourceBundle messages = ResourceBundle.getBundle("res.Messages", currentLocale);//currentLocale decides the language
        Locale[] locales = Locale.getAvailableLocales();
        System.out.println(messages.getString("locales"));
        for (Locale locale : locales) {
            System.out.println(locale.toString() + " " + locale.getDisplayName() + "\n");
        }
    }
}
