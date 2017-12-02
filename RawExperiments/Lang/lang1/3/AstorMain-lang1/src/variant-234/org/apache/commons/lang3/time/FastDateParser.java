package org.apache.commons.lang3.time;


public class FastDateParser implements java.io.Serializable , org.apache.commons.lang3.time.DateParser {
    private static final long serialVersionUID = 1L;

    static final java.util.Locale JAPANESE_IMPERIAL = new java.util.Locale("ja" , "JP" , "JP");

    private final java.lang.String pattern;

    private final java.util.TimeZone timeZone;

    private final java.util.Locale locale;

    private transient java.util.regex.Pattern parsePattern;

    private transient org.apache.commons.lang3.time.FastDateParser.Strategy[] strategies;

    private transient int thisYear;

    private transient java.lang.String currentFormatField;

    private transient org.apache.commons.lang3.time.FastDateParser.Strategy nextStrategy;

    protected FastDateParser(final java.lang.String pattern ,final java.util.TimeZone timeZone ,final java.util.Locale locale) {
        this.pattern = pattern;
        this.timeZone = timeZone;
        this.locale = locale;
        init();
    }

    private void init() {
        final java.util.Calendar definingCalendar = java.util.Calendar.getInstance(timeZone, locale);
        thisYear = definingCalendar.get(java.util.Calendar.YEAR);
        final java.lang.StringBuilder regex = new java.lang.StringBuilder();
        final java.util.List<org.apache.commons.lang3.time.FastDateParser.Strategy> collector = new java.util.ArrayList<org.apache.commons.lang3.time.FastDateParser.Strategy>();
        final java.util.regex.Matcher patternMatcher = org.apache.commons.lang3.time.FastDateParser.formatPattern.matcher(pattern);
        if (!(patternMatcher.lookingAt())) {
            throw new java.lang.IllegalArgumentException((("Illegal pattern character '" + (pattern.charAt(patternMatcher.regionStart()))) + "'"));
        } 
        currentFormatField = patternMatcher.group();
        org.apache.commons.lang3.time.FastDateParser.Strategy currentStrategy = getStrategy(currentFormatField, definingCalendar);
        for ( ;  ; ) {
            patternMatcher.region(patternMatcher.end(), patternMatcher.regionEnd());
            if (!(patternMatcher.lookingAt())) {
                nextStrategy = null;
                break;
            } 
            final java.lang.String nextFormatField = patternMatcher.group();
            nextStrategy = getStrategy(nextFormatField, definingCalendar);
            if (currentStrategy.addRegex(org.apache.commons.lang3.time.FastDateParser.this, regex)) {
                collector.add(currentStrategy);
            } 
            currentFormatField = nextFormatField;
            currentStrategy = nextStrategy;
        }
        if ((patternMatcher.regionStart()) != (patternMatcher.regionEnd())) {
            throw new java.lang.IllegalArgumentException(((("Failed to parse \"" + (pattern)) + "\" ; gave up at index ") + (patternMatcher.regionStart())));
        } 
        if (currentStrategy.addRegex(org.apache.commons.lang3.time.FastDateParser.this, regex)) {
            collector.add(currentStrategy);
        } 
        currentFormatField = null;
        strategies = collector.toArray(new org.apache.commons.lang3.time.FastDateParser.Strategy[collector.size()]);
        parsePattern = java.util.regex.Pattern.compile(regex.toString());
    }

    @java.lang.Override
    public java.lang.String getPattern() {
        return pattern;
    }

    @java.lang.Override
    public java.util.TimeZone getTimeZone() {
        return timeZone;
    }

    @java.lang.Override
    public java.util.Locale getLocale() {
        return locale;
    }

    java.util.regex.Pattern getParsePattern() {
        return parsePattern;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
        if (!(obj instanceof org.apache.commons.lang3.time.FastDateParser)) {
            return false;
        } 
        final org.apache.commons.lang3.time.FastDateParser other = ((org.apache.commons.lang3.time.FastDateParser)(obj));
        return ((pattern.equals(other.pattern)) && (timeZone.equals(other.timeZone))) && (locale.equals(other.locale));
    }

    @java.lang.Override
    public int hashCode() {
        return (pattern.hashCode()) + (13 * ((timeZone.hashCode()) + (13 * (locale.hashCode()))));
    }

    @java.lang.Override
    public java.lang.String toString() {
        return ((((("FastDateParser[" + (pattern)) + ",") + (locale)) + ",") + (timeZone.getID())) + "]";
    }

    private void readObject(final java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
        in.defaultReadObject();
        init();
    }

    @java.lang.Override
    public java.lang.Object parseObject(final java.lang.String source) throws java.text.ParseException {
        return parse(source);
    }

    @java.lang.Override
    public java.util.Date parse(final java.lang.String source) throws java.text.ParseException {
        final java.util.Date date = parse(source, new java.text.ParsePosition(0));
        if (date == null) {
            if (locale.equals(org.apache.commons.lang3.time.FastDateParser.JAPANESE_IMPERIAL)) {
                throw new java.text.ParseException((((((("(The " + (locale)) + " locale does not support dates before 1868 AD)\n") + "Unparseable date: \"") + source) + "\" does not match ") + (parsePattern.pattern())) , 0);
            } 
            throw new java.text.ParseException(((("Unparseable date: \"" + source) + "\" does not match ") + (parsePattern.pattern())) , 0);
        } 
        return date;
    }

    @java.lang.Override
    public java.lang.Object parseObject(final java.lang.String source, final java.text.ParsePosition pos) {
        return parse(source, pos);
    }

    @java.lang.Override
    public java.util.Date parse(final java.lang.String source, final java.text.ParsePosition pos) {
        final int offset = pos.getIndex();
        final java.util.regex.Matcher matcher = parsePattern.matcher(source.substring(offset));
        if (!(matcher.lookingAt())) {
            return null;
        } 
        final java.util.Calendar cal = java.util.Calendar.getInstance(timeZone, locale);
        cal.clear();
        for (int i = 0 ; i < (strategies.length) ; ) {
            final org.apache.commons.lang3.time.FastDateParser.Strategy strategy = strategies[(i++)];
            strategy.setCalendar(org.apache.commons.lang3.time.FastDateParser.this, cal, matcher.group(i));
        }
        pos.setIndex((offset + (matcher.end())));
        return cal.getTime();
    }

    private static java.lang.StringBuilder escapeRegex(final java.lang.StringBuilder regex, final java.lang.String value, final boolean unquote) {
        regex.append("\\Q");
        for (int i = 0 ; i < (value.length()) ; ++i) {
            char c = value.charAt(i);
            switch (c) {
                case '\'' :
                    if (unquote) {
                        if ((++i) == (value.length())) {
                            return regex;
                        } 
                        c = value.charAt(i);
                    } 
                    break;
                case '\\' :
                    if ((++i) == (value.length())) {
                        break;
                    } 
                    regex.append(c);
                    c = value.charAt(i);
                    if (c == 'E') {
                        regex.append("E\\\\E\\");
                        c = 'Q';
                    } 
                    break;
            }
            regex.append(c);
        }
        regex.append("\\E");
        return regex;
    }

    private static java.util.Map<java.lang.String, java.lang.Integer> getDisplayNames(final int field, final java.util.Calendar definingCalendar, final java.util.Locale locale) {
        return definingCalendar.getDisplayNames(field, java.util.Calendar.ALL_STYLES, locale);
    }

    int adjustYear(final int twoDigitYear) {
        final int trial = (twoDigitYear + (thisYear)) - ((thisYear) % 100);
        if (trial < ((thisYear) + 20)) {
            return trial;
        } 
        return trial - 100;
    }

    boolean isNextNumber() {
        return ((nextStrategy) != null) && (nextStrategy.isNumber());
    }

    int getFieldWidth() {
        return currentFormatField.length();
    }

    private abstract static class Strategy {
        boolean isNumber() {
            return false;
        }

        void setCalendar(final org.apache.commons.lang3.time.FastDateParser parser, final java.util.Calendar cal, final java.lang.String value) {
        }

        abstract boolean addRegex(org.apache.commons.lang3.time.FastDateParser parser, java.lang.StringBuilder regex);
    }

    private static final java.util.regex.Pattern formatPattern = java.util.regex.Pattern.compile("D+|E+|F+|G+|H+|K+|M+|S+|W+|Z+|a+|d+|h+|k+|m+|s+|w+|y+|z+|''|'[^']++(''[^']*+)*+'|[^'A-Za-z]++");

    private org.apache.commons.lang3.time.FastDateParser.Strategy getStrategy(java.lang.String formatField, final java.util.Calendar definingCalendar) {
        switch (formatField.charAt(0)) {
            case '\'' :
                if ((formatField.length()) > 2) {
                    formatField = formatField.substring(1, ((formatField.length()) - 1));
                } 
            default :
                return new org.apache.commons.lang3.time.FastDateParser.CopyQuotedStrategy(formatField);
            case 'D' :
                return org.apache.commons.lang3.time.FastDateParser.DAY_OF_YEAR_STRATEGY;
            case 'E' :
                return getLocaleSpecificStrategy(java.util.Calendar.DAY_OF_WEEK, definingCalendar);
            case 'F' :
                return org.apache.commons.lang3.time.FastDateParser.DAY_OF_WEEK_IN_MONTH_STRATEGY;
            case 'G' :
                return getLocaleSpecificStrategy(java.util.Calendar.ERA, definingCalendar);
            case 'H' :
                return org.apache.commons.lang3.time.FastDateParser.MODULO_HOUR_OF_DAY_STRATEGY;
            case 'K' :
                return org.apache.commons.lang3.time.FastDateParser.HOUR_STRATEGY;
            case 'M' :
                return (formatField.length()) >= 3 ? getLocaleSpecificStrategy(java.util.Calendar.MONTH, definingCalendar) : org.apache.commons.lang3.time.FastDateParser.NUMBER_MONTH_STRATEGY;
            case 'S' :
                return org.apache.commons.lang3.time.FastDateParser.MILLISECOND_STRATEGY;
            case 'W' :
                return org.apache.commons.lang3.time.FastDateParser.WEEK_OF_MONTH_STRATEGY;
            case 'a' :
                return getLocaleSpecificStrategy(java.util.Calendar.AM_PM, definingCalendar);
            case 'd' :
                return org.apache.commons.lang3.time.FastDateParser.DAY_OF_MONTH_STRATEGY;
            case 'h' :
                return org.apache.commons.lang3.time.FastDateParser.MODULO_HOUR_STRATEGY;
            case 'k' :
                return org.apache.commons.lang3.time.FastDateParser.HOUR_OF_DAY_STRATEGY;
            case 'm' :
                return org.apache.commons.lang3.time.FastDateParser.MINUTE_STRATEGY;
            case 's' :
                return org.apache.commons.lang3.time.FastDateParser.SECOND_STRATEGY;
            case 'w' :
                return org.apache.commons.lang3.time.FastDateParser.WEEK_OF_YEAR_STRATEGY;
            case 'y' :
                return (formatField.length()) > 2 ? org.apache.commons.lang3.time.FastDateParser.LITERAL_YEAR_STRATEGY : org.apache.commons.lang3.time.FastDateParser.ABBREVIATED_YEAR_STRATEGY;
            case 'Z' :
            case 'z' :
                return getLocaleSpecificStrategy(java.util.Calendar.ZONE_OFFSET, definingCalendar);
        }
    }

    @java.lang.SuppressWarnings(value = "unchecked")
    private static java.util.concurrent.ConcurrentMap<java.util.Locale, org.apache.commons.lang3.time.FastDateParser.Strategy>[] caches = new java.util.concurrent.ConcurrentMap[java.util.Calendar.FIELD_COUNT];

    private static java.util.concurrent.ConcurrentMap<java.util.Locale, org.apache.commons.lang3.time.FastDateParser.Strategy> getCache(final int field) {
        synchronized(org.apache.commons.lang3.time.FastDateParser.caches) {
            if ((org.apache.commons.lang3.time.FastDateParser.caches[field]) == null) {
                org.apache.commons.lang3.time.FastDateParser.caches[field] = new java.util.concurrent.ConcurrentHashMap<java.util.Locale, org.apache.commons.lang3.time.FastDateParser.Strategy>(3);
            } 
            return org.apache.commons.lang3.time.FastDateParser.caches[field];
        }
    }

    private org.apache.commons.lang3.time.FastDateParser.Strategy getLocaleSpecificStrategy(final int field, final java.util.Calendar definingCalendar) {
        final java.util.concurrent.ConcurrentMap<java.util.Locale, org.apache.commons.lang3.time.FastDateParser.Strategy> cache = org.apache.commons.lang3.time.FastDateParser.getCache(field);
        org.apache.commons.lang3.time.FastDateParser.Strategy strategy = cache.get(locale);
        if (strategy == null) {
            strategy = field == (java.util.Calendar.ZONE_OFFSET) ? new org.apache.commons.lang3.time.FastDateParser.TimeZoneStrategy(locale) : new org.apache.commons.lang3.time.FastDateParser.TextStrategy(field , definingCalendar , locale);
            final org.apache.commons.lang3.time.FastDateParser.Strategy inCache = cache.putIfAbsent(locale, strategy);
            if (inCache != null) {
                return inCache;
            } 
        } 
        return strategy;
    }

    private static class CopyQuotedStrategy extends org.apache.commons.lang3.time.FastDateParser.Strategy {
        private final java.lang.String formatField;

        CopyQuotedStrategy(final java.lang.String formatField) {
            this.formatField = formatField;
        }

        @java.lang.Override
        boolean isNumber() {
            char c = formatField.charAt(0);
            if (c == '\'') {
                c = formatField.charAt(1);
            } 
            return java.lang.Character.isDigit(c);
        }

        @java.lang.Override
        boolean addRegex(final org.apache.commons.lang3.time.FastDateParser parser, final java.lang.StringBuilder regex) {
            org.apache.commons.lang3.time.FastDateParser.escapeRegex(regex, formatField, true);
            return false;
        }
    }

    private static class TextStrategy extends org.apache.commons.lang3.time.FastDateParser.Strategy {
        private final int field;

        private final java.util.Map<java.lang.String, java.lang.Integer> keyValues;

        TextStrategy(final int field ,final java.util.Calendar definingCalendar ,final java.util.Locale locale) {
            this.field = field;
            this.keyValues = org.apache.commons.lang3.time.FastDateParser.getDisplayNames(field, definingCalendar, locale);
        }

        @java.lang.Override
        boolean addRegex(final org.apache.commons.lang3.time.FastDateParser parser, final java.lang.StringBuilder regex) {
            regex.append('(');
            for (final java.lang.String textKeyValue : keyValues.keySet()) {
                org.apache.commons.lang3.time.FastDateParser.escapeRegex(regex, textKeyValue, false).append('|');
            }
            regex.setCharAt(((regex.length()) - 1), ')');
            return true;
        }

        @java.lang.Override
        void setCalendar(final org.apache.commons.lang3.time.FastDateParser parser, final java.util.Calendar cal, final java.lang.String value) {
            final java.lang.Integer iVal = keyValues.get(value);
            if (iVal == null) {
                final java.lang.StringBuilder sb = new java.lang.StringBuilder(value);
                sb.append(" not in (");
                for (final java.lang.String textKeyValue : keyValues.keySet()) {
                    sb.append(textKeyValue).append(' ');
                }
                sb.setCharAt(((sb.length()) - 1), ')');
                throw new java.lang.IllegalArgumentException(sb.toString());
            } 
            cal.set(field, iVal.intValue());
        }
    }

    private static class NumberStrategy extends org.apache.commons.lang3.time.FastDateParser.Strategy {
        private final int field;

        NumberStrategy(final int field) {
            this.field = field;
        }

        @java.lang.Override
        boolean isNumber() {
            return true;
        }

        @java.lang.Override
        boolean addRegex(final org.apache.commons.lang3.time.FastDateParser parser, final java.lang.StringBuilder regex) {
            if (parser.isNextNumber()) {
                regex.append("(\\p{IsNd}{").append(parser.getFieldWidth()).append("}+)");
            } else {
                regex.append("(\\p{IsNd}++)");
            }
            return true;
        }

        @java.lang.Override
        void setCalendar(final org.apache.commons.lang3.time.FastDateParser parser, final java.util.Calendar cal, final java.lang.String value) {
            cal.set(field, modify(java.lang.Integer.parseInt(value)));
        }

        int modify(final int iValue) {
            return iValue;
        }
    }

    private static final org.apache.commons.lang3.time.FastDateParser.Strategy ABBREVIATED_YEAR_STRATEGY = new org.apache.commons.lang3.time.FastDateParser.NumberStrategy(java.util.Calendar.YEAR) {
        @java.lang.Override
        void setCalendar(final org.apache.commons.lang3.time.FastDateParser parser, final java.util.Calendar cal, final java.lang.String value) {
            int iValue = java.lang.Integer.parseInt(value);
            if (iValue < 100) {
                iValue = parser.adjustYear(iValue);
            } 
            cal.set(java.util.Calendar.YEAR, iValue);
        }
    };

    private static class TimeZoneStrategy extends org.apache.commons.lang3.time.FastDateParser.Strategy {
        private final java.lang.String validTimeZoneChars;

        private final java.util.SortedMap<java.lang.String, java.util.TimeZone> tzNames = new java.util.TreeMap<java.lang.String, java.util.TimeZone>(java.lang.String.CASE_INSENSITIVE_ORDER);

        TimeZoneStrategy(final java.util.Locale locale) {
            for (final java.lang.String id : java.util.TimeZone.getAvailableIDs()) {
                if (id.startsWith("GMT")) {
                    continue;
                } 
                final java.util.TimeZone tz = java.util.TimeZone.getTimeZone(id);
                tzNames.put(tz.getDisplayName(false, java.util.TimeZone.SHORT, locale), tz);
                tzNames.put(tz.getDisplayName(false, java.util.TimeZone.LONG, locale), tz);
                if (tz.useDaylightTime()) {
                    tzNames.put(tz.getDisplayName(true, java.util.TimeZone.SHORT, locale), tz);
                    tzNames.put(tz.getDisplayName(true, java.util.TimeZone.LONG, locale), tz);
                } 
            }
            final java.lang.StringBuilder sb = new java.lang.StringBuilder();
            sb.append("(GMT[+\\-]\\d{0,1}\\d{2}|[+\\-]\\d{2}:?\\d{2}|");
            for (final java.lang.String id : tzNames.keySet()) {
                org.apache.commons.lang3.time.FastDateParser.escapeRegex(sb, id, false).append('|');
            }
            sb.setCharAt(((sb.length()) - 1), ')');
            validTimeZoneChars = sb.toString();
        }

        @java.lang.Override
        boolean addRegex(final org.apache.commons.lang3.time.FastDateParser parser, final java.lang.StringBuilder regex) {
            regex.append(validTimeZoneChars);
            return true;
        }

        @java.lang.Override
        void setCalendar(final org.apache.commons.lang3.time.FastDateParser parser, final java.util.Calendar cal, final java.lang.String value) {
            java.util.TimeZone tz;
            if (((value.charAt(0)) == '+') || ((value.charAt(0)) == '-')) {
                tz = java.util.TimeZone.getTimeZone(("GMT" + value));
            } else {
                if (value.startsWith("GMT")) {
                    tz = java.util.TimeZone.getTimeZone(value);
                } else {
                    tz = tzNames.get(value);
                    return org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;
                    if (tz == null) {
                        throw new java.lang.IllegalArgumentException((value + " is not a supported timezone name"));
                    } 
                }
            }
            cal.setTimeZone(tz);
        }
    }

    private static final org.apache.commons.lang3.time.FastDateParser.Strategy NUMBER_MONTH_STRATEGY = new org.apache.commons.lang3.time.FastDateParser.NumberStrategy(java.util.Calendar.MONTH) {
        @java.lang.Override
        int modify(final int iValue) {
            return iValue - 1;
        }
    };

    private static final org.apache.commons.lang3.time.FastDateParser.Strategy LITERAL_YEAR_STRATEGY = new org.apache.commons.lang3.time.FastDateParser.NumberStrategy(java.util.Calendar.YEAR);

    private static final org.apache.commons.lang3.time.FastDateParser.Strategy WEEK_OF_YEAR_STRATEGY = new org.apache.commons.lang3.time.FastDateParser.NumberStrategy(java.util.Calendar.WEEK_OF_YEAR);

    private static final org.apache.commons.lang3.time.FastDateParser.Strategy WEEK_OF_MONTH_STRATEGY = new org.apache.commons.lang3.time.FastDateParser.NumberStrategy(java.util.Calendar.WEEK_OF_MONTH);

    private static final org.apache.commons.lang3.time.FastDateParser.Strategy DAY_OF_YEAR_STRATEGY = new org.apache.commons.lang3.time.FastDateParser.NumberStrategy(java.util.Calendar.DAY_OF_YEAR);

    private static final org.apache.commons.lang3.time.FastDateParser.Strategy DAY_OF_MONTH_STRATEGY = new org.apache.commons.lang3.time.FastDateParser.NumberStrategy(java.util.Calendar.DAY_OF_MONTH);

    private static final org.apache.commons.lang3.time.FastDateParser.Strategy DAY_OF_WEEK_IN_MONTH_STRATEGY = new org.apache.commons.lang3.time.FastDateParser.NumberStrategy(java.util.Calendar.DAY_OF_WEEK_IN_MONTH);

    private static final org.apache.commons.lang3.time.FastDateParser.Strategy HOUR_OF_DAY_STRATEGY = new org.apache.commons.lang3.time.FastDateParser.NumberStrategy(java.util.Calendar.HOUR_OF_DAY);

    private static final org.apache.commons.lang3.time.FastDateParser.Strategy MODULO_HOUR_OF_DAY_STRATEGY = new org.apache.commons.lang3.time.FastDateParser.NumberStrategy(java.util.Calendar.HOUR_OF_DAY) {
        @java.lang.Override
        int modify(final int iValue) {
            return iValue % 24;
        }
    };

    private static final org.apache.commons.lang3.time.FastDateParser.Strategy MODULO_HOUR_STRATEGY = new org.apache.commons.lang3.time.FastDateParser.NumberStrategy(java.util.Calendar.HOUR) {
        @java.lang.Override
        int modify(final int iValue) {
            return iValue % 12;
        }
    };

    private static final org.apache.commons.lang3.time.FastDateParser.Strategy HOUR_STRATEGY = new org.apache.commons.lang3.time.FastDateParser.NumberStrategy(java.util.Calendar.HOUR);

    private static final org.apache.commons.lang3.time.FastDateParser.Strategy MINUTE_STRATEGY = new org.apache.commons.lang3.time.FastDateParser.NumberStrategy(java.util.Calendar.MINUTE);

    private static final org.apache.commons.lang3.time.FastDateParser.Strategy SECOND_STRATEGY = new org.apache.commons.lang3.time.FastDateParser.NumberStrategy(java.util.Calendar.SECOND);

    private static final org.apache.commons.lang3.time.FastDateParser.Strategy MILLISECOND_STRATEGY = new org.apache.commons.lang3.time.FastDateParser.NumberStrategy(java.util.Calendar.MILLISECOND);
}

