package com;

import java.util.Locale;
import java.util.ResourceBundle;

public class SetLocale {
    private Locale currentLocale;

    public void setLocale(String languageTag) {
        currentLocale = Locale.forLanguageTag(languageTag);
        ResourceBundle messages = ResourceBundle.getBundle("res.Messages", currentLocale);
        System.out.println(String.format(messages.getString("locale.set"), currentLocale.toString()));
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }
}
