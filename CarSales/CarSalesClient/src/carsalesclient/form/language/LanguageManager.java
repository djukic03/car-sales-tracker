/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carsalesclient.form.language;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author user
 */
public class LanguageManager {
    private static ResourceBundle bundle;

    public static void setLocale(Locale locale) {
        bundle = ResourceBundle.getBundle("resources.language", locale);
    }

    public static String getValue(String key) {
        return bundle.getString(key);
    }
}
