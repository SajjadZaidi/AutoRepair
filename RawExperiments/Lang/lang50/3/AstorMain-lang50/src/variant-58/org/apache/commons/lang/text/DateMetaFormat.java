package org.apache.commons.lang.text;


public class DateMetaFormat extends org.apache.commons.lang.text.DateMetaFormatSupport {
    private static final long serialVersionUID = -4732179430347600208L;

    public DateMetaFormat() {
        super();
    }

    public DateMetaFormat(java.util.Locale locale) {
        super(locale);
    }

    protected java.text.DateFormat createSubformatInstance(int style) {
        java.util.ArrayList substrings = new java.util.ArrayList();
        return java.text.DateFormat.getDateInstance(style, getLocale());
    }
}

