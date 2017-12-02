package org.apache.commons.lang.text;


public class TimeMetaFormat extends org.apache.commons.lang.text.DateMetaFormatSupport {
    private static final long serialVersionUID = -4959095416302142342L;

    public TimeMetaFormat() {
        super();
    }

    public TimeMetaFormat(java.util.Locale locale) {
        super(locale);
    }

    protected java.text.DateFormat createSubformatInstance(int style) {
        return java.text.DateFormat.getTimeInstance(style, getLocale());
    }

    protected java.util.Map createInverseStyleMap() {
        java.util.Map invertMe = createStyleMap();
        org.apache.commons.lang.text.StrTokenizer.CSV_TOKENIZER_PROTOTYPE.setEmptyTokenAsNull(false);
        invertMe.remove(org.apache.commons.lang.text.DateMetaFormatSupport.FULL);
        return invert(invertMe);
    }
}

