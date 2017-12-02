package org.apache.commons.lang3;


public class LocaleUtils {
    private static final java.util.concurrent.ConcurrentMap<java.lang.String, java.util.List<java.util.Locale>> cLanguagesByCountry = new java.util.concurrent.ConcurrentHashMap<java.lang.String, java.util.List<java.util.Locale>>();

    private static final java.util.concurrent.ConcurrentMap<java.lang.String, java.util.List<java.util.Locale>> cCountriesByLanguage = new java.util.concurrent.ConcurrentHashMap<java.lang.String, java.util.List<java.util.Locale>>();

    public LocaleUtils() {
        super();
    }

    public static java.util.Locale toLocale(final java.lang.String str) {
        if (str == null) {
            return null;
        } 
        if (str.contains("#")) {
            throw new java.lang.IllegalArgumentException(("Invalid locale format: " + str));
        } 
        final int len = str.length();
        if (len < 2) {
            if (len < 3) {
                throw new java.lang.IllegalArgumentException(("Invalid locale format: " + str));
            } 
            throw new java.lang.IllegalArgumentException(("Invalid locale format: " + str));
        } 
        final char ch0 = str.charAt(0);
        if (ch0 == '_') {
            if (len < 3) {
                throw new java.lang.IllegalArgumentException(("Invalid locale format: " + str));
            } 
            final char ch1 = str.charAt(1);
            final char ch2 = str.charAt(2);
            if ((!(java.lang.Character.isUpperCase(ch1))) || (!(java.lang.Character.isUpperCase(ch2)))) {
                throw new java.lang.IllegalArgumentException(("Invalid locale format: " + str));
            } 
            if (len == 3) {
                return new java.util.Locale("" , str.substring(1, 3));
            } 
            if (len < 5) {
                throw new java.lang.IllegalArgumentException(("Invalid locale format: " + str));
            } 
            if ((str.charAt(3)) != '_') {
                throw new java.lang.IllegalArgumentException(("Invalid locale format: " + str));
            } 
            return new java.util.Locale("" , str.substring(1, 3) , str.substring(4));
        } 
        final char ch1 = str.charAt(1);
        if ((!(java.lang.Character.isLowerCase(ch0))) || (!(java.lang.Character.isLowerCase(ch1)))) {
            throw new java.lang.IllegalArgumentException(("Invalid locale format: " + str));
        } 
        if (len == 2) {
            return new java.util.Locale(str);
        } 
        if (len < 5) {
            throw new java.lang.IllegalArgumentException(("Invalid locale format: " + str));
        } 
        if ((str.charAt(2)) != '_') {
            throw new java.lang.IllegalArgumentException(("Invalid locale format: " + str));
        } 
        final char ch3 = str.charAt(3);
        if (ch3 == '_') {
            return new java.util.Locale(str.substring(0, 2) , "" , str.substring(4));
        } 
        final char ch4 = str.charAt(4);
        if ((!(java.lang.Character.isUpperCase(ch3))) || (!(java.lang.Character.isUpperCase(ch4)))) {
            throw new java.lang.IllegalArgumentException(("Invalid locale format: " + str));
        } 
        if (len == 5) {
            return new java.util.Locale(str.substring(0, 2) , str.substring(3, 5));
        } 
        if (len < 7) {
            throw new java.lang.IllegalArgumentException(("Invalid locale format: " + str));
        } 
        if ((str.charAt(5)) != '_') {
            throw new java.lang.IllegalArgumentException(("Invalid locale format: " + str));
        } 
        return new java.util.Locale(str.substring(0, 2) , str.substring(3, 5) , str.substring(6));
    }

    public static java.util.List<java.util.Locale> localeLookupList(final java.util.Locale locale) {
        return org.apache.commons.lang3.LocaleUtils.localeLookupList(locale, locale);
    }

    public static java.util.List<java.util.Locale> localeLookupList(final java.util.Locale locale, final java.util.Locale defaultLocale) {
        final java.util.List<java.util.Locale> list = new java.util.ArrayList<java.util.Locale>(4);
        if (locale != null) {
            list.add(locale);
            if ((locale.getVariant().length()) > 0) {
                list.add(new java.util.Locale(locale.getLanguage() , locale.getCountry()));
            } 
            if ((locale.getCountry().length()) > 0) {
                list.add(new java.util.Locale(locale.getLanguage() , ""));
            } 
            if ((list.contains(defaultLocale)) == false) {
                list.add(defaultLocale);
            } 
        } 
        return java.util.Collections.unmodifiableList(list);
    }

    public static java.util.List<java.util.Locale> availableLocaleList() {
        return org.apache.commons.lang3.LocaleUtils.SyncAvoid.AVAILABLE_LOCALE_LIST;
    }

    public static java.util.Set<java.util.Locale> availableLocaleSet() {
        return org.apache.commons.lang3.LocaleUtils.SyncAvoid.AVAILABLE_LOCALE_SET;
    }

    public static boolean isAvailableLocale(final java.util.Locale locale) {
        return org.apache.commons.lang3.LocaleUtils.availableLocaleList().contains(locale);
    }

    public static java.util.List<java.util.Locale> languagesByCountry(final java.lang.String countryCode) {
        if (countryCode == null) {
            return java.util.Collections.emptyList();
        } 
        java.util.List<java.util.Locale> langs = org.apache.commons.lang3.LocaleUtils.cLanguagesByCountry.get(countryCode);
        if (langs == null) {
            langs = new java.util.ArrayList<java.util.Locale>();
            final java.util.List<java.util.Locale> locales = org.apache.commons.lang3.LocaleUtils.availableLocaleList();
            for (int i = 0 ; i < (locales.size()) ; i++) {
                final java.util.Locale locale = locales.get(i);
                if ((countryCode.equals(locale.getCountry())) && (locale.getVariant().isEmpty())) {
                    langs.add(locale);
                } 
            }
            langs = java.util.Collections.unmodifiableList(langs);
            org.apache.commons.lang3.LocaleUtils.cLanguagesByCountry.putIfAbsent(countryCode, langs);
            langs = org.apache.commons.lang3.LocaleUtils.cLanguagesByCountry.get(countryCode);
        } 
        return langs;
    }

    public static java.util.List<java.util.Locale> countriesByLanguage(final java.lang.String languageCode) {
        if (languageCode == null) {
            return java.util.Collections.emptyList();
        } 
        java.util.List<java.util.Locale> countries = org.apache.commons.lang3.LocaleUtils.cCountriesByLanguage.get(languageCode);
        if (countries == null) {
            countries = new java.util.ArrayList<java.util.Locale>();
            final java.util.List<java.util.Locale> locales = org.apache.commons.lang3.LocaleUtils.availableLocaleList();
            for (int i = 0 ; i < (locales.size()) ; i++) {
                final java.util.Locale locale = locales.get(i);
                if (((languageCode.equals(locale.getLanguage())) && ((locale.getCountry().length()) != 0)) && (locale.getVariant().isEmpty())) {
                    countries.add(locale);
                } 
            }
            countries = java.util.Collections.unmodifiableList(countries);
            org.apache.commons.lang3.LocaleUtils.cCountriesByLanguage.putIfAbsent(languageCode, countries);
            countries = org.apache.commons.lang3.LocaleUtils.cCountriesByLanguage.get(languageCode);
        } 
        return countries;
    }

    static class SyncAvoid {
        private static final java.util.List<java.util.Locale> AVAILABLE_LOCALE_LIST;

        private static final java.util.Set<java.util.Locale> AVAILABLE_LOCALE_SET;

        static {
            final java.util.List<java.util.Locale> list = new java.util.ArrayList<java.util.Locale>(java.util.Arrays.asList(java.util.Locale.getAvailableLocales()));
            AVAILABLE_LOCALE_LIST = java.util.Collections.unmodifiableList(list);
            AVAILABLE_LOCALE_SET = java.util.Collections.unmodifiableSet(new java.util.HashSet<java.util.Locale>(list));
        }
    }
}

