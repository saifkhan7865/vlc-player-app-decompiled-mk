package j$.time.format;

import j$.time.DateTimeException;
import j$.time.Period;
import j$.time.ZoneId;
import j$.time.chrono.Chronology;
import j$.time.chrono.IsoChronology;
import j$.time.format.DateTimeFormatterBuilder;
import j$.time.temporal.ChronoField;
import j$.time.temporal.IsoFields;
import j$.time.temporal.TemporalAccessor;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalQuery;
import j$.util.Objects;
import java.io.IOException;
import java.text.ParsePosition;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.videolan.resources.Constants;

public final class DateTimeFormatter {
    public static final DateTimeFormatter BASIC_ISO_DATE;
    public static final DateTimeFormatter ISO_DATE;
    public static final DateTimeFormatter ISO_DATE_TIME;
    public static final DateTimeFormatter ISO_INSTANT;
    public static final DateTimeFormatter ISO_LOCAL_DATE;
    public static final DateTimeFormatter ISO_LOCAL_DATE_TIME;
    public static final DateTimeFormatter ISO_LOCAL_TIME;
    public static final DateTimeFormatter ISO_OFFSET_DATE;
    public static final DateTimeFormatter ISO_OFFSET_DATE_TIME;
    public static final DateTimeFormatter ISO_OFFSET_TIME;
    public static final DateTimeFormatter ISO_ORDINAL_DATE;
    public static final DateTimeFormatter ISO_TIME;
    public static final DateTimeFormatter ISO_WEEK_DATE;
    public static final DateTimeFormatter ISO_ZONED_DATE_TIME;
    private static final TemporalQuery PARSED_EXCESS_DAYS = new DateTimeFormatter$$ExternalSyntheticLambda0();
    private static final TemporalQuery PARSED_LEAP_SECOND = new DateTimeFormatter$$ExternalSyntheticLambda1();
    public static final DateTimeFormatter RFC_1123_DATE_TIME;
    private final Chronology chrono;
    private final DecimalStyle decimalStyle;
    private final Locale locale;
    private final DateTimeFormatterBuilder.CompositePrinterParser printerParser;
    private final Set resolverFields;
    private final ResolverStyle resolverStyle;
    private final ZoneId zone;

    static {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder();
        ChronoField chronoField = ChronoField.YEAR;
        SignStyle signStyle = SignStyle.EXCEEDS_PAD;
        DateTimeFormatterBuilder appendLiteral = dateTimeFormatterBuilder.appendValue(chronoField, 4, 10, signStyle).appendLiteral('-');
        ChronoField chronoField2 = ChronoField.MONTH_OF_YEAR;
        DateTimeFormatterBuilder appendLiteral2 = appendLiteral.appendValue(chronoField2, 2).appendLiteral('-');
        ChronoField chronoField3 = ChronoField.DAY_OF_MONTH;
        DateTimeFormatterBuilder appendValue = appendLiteral2.appendValue(chronoField3, 2);
        ResolverStyle resolverStyle2 = ResolverStyle.STRICT;
        IsoChronology isoChronology = IsoChronology.INSTANCE;
        DateTimeFormatter formatter = appendValue.toFormatter(resolverStyle2, isoChronology);
        ISO_LOCAL_DATE = formatter;
        ISO_OFFSET_DATE = new DateTimeFormatterBuilder().parseCaseInsensitive().append(formatter).appendOffsetId().toFormatter(resolverStyle2, isoChronology);
        ISO_DATE = new DateTimeFormatterBuilder().parseCaseInsensitive().append(formatter).optionalStart().appendOffsetId().toFormatter(resolverStyle2, isoChronology);
        DateTimeFormatterBuilder dateTimeFormatterBuilder2 = new DateTimeFormatterBuilder();
        ChronoField chronoField4 = ChronoField.HOUR_OF_DAY;
        DateTimeFormatterBuilder appendLiteral3 = dateTimeFormatterBuilder2.appendValue(chronoField4, 2).appendLiteral((char) AbstractJsonLexerKt.COLON);
        ChronoField chronoField5 = ChronoField.MINUTE_OF_HOUR;
        DateTimeFormatterBuilder appendLiteral4 = appendLiteral3.appendValue(chronoField5, 2).optionalStart().appendLiteral((char) AbstractJsonLexerKt.COLON);
        ChronoField chronoField6 = ChronoField.SECOND_OF_MINUTE;
        DateTimeFormatter formatter2 = appendLiteral4.appendValue(chronoField6, 2).optionalStart().appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true).toFormatter(resolverStyle2, (Chronology) null);
        ISO_LOCAL_TIME = formatter2;
        ISO_OFFSET_TIME = new DateTimeFormatterBuilder().parseCaseInsensitive().append(formatter2).appendOffsetId().toFormatter(resolverStyle2, (Chronology) null);
        ISO_TIME = new DateTimeFormatterBuilder().parseCaseInsensitive().append(formatter2).optionalStart().appendOffsetId().toFormatter(resolverStyle2, (Chronology) null);
        DateTimeFormatter formatter3 = new DateTimeFormatterBuilder().parseCaseInsensitive().append(formatter).appendLiteral('T').append(formatter2).toFormatter(resolverStyle2, isoChronology);
        ISO_LOCAL_DATE_TIME = formatter3;
        DateTimeFormatter formatter4 = new DateTimeFormatterBuilder().parseCaseInsensitive().append(formatter3).parseLenient().appendOffsetId().parseStrict().toFormatter(resolverStyle2, isoChronology);
        ISO_OFFSET_DATE_TIME = formatter4;
        ISO_ZONED_DATE_TIME = new DateTimeFormatterBuilder().append(formatter4).optionalStart().appendLiteral((char) AbstractJsonLexerKt.BEGIN_LIST).parseCaseSensitive().appendZoneRegionId().appendLiteral((char) AbstractJsonLexerKt.END_LIST).toFormatter(resolverStyle2, isoChronology);
        ISO_DATE_TIME = new DateTimeFormatterBuilder().append(formatter3).optionalStart().appendOffsetId().optionalStart().appendLiteral((char) AbstractJsonLexerKt.BEGIN_LIST).parseCaseSensitive().appendZoneRegionId().appendLiteral((char) AbstractJsonLexerKt.END_LIST).toFormatter(resolverStyle2, isoChronology);
        ISO_ORDINAL_DATE = new DateTimeFormatterBuilder().parseCaseInsensitive().appendValue(chronoField, 4, 10, signStyle).appendLiteral('-').appendValue(ChronoField.DAY_OF_YEAR, 3).optionalStart().appendOffsetId().toFormatter(resolverStyle2, isoChronology);
        DateTimeFormatterBuilder appendLiteral5 = new DateTimeFormatterBuilder().parseCaseInsensitive().appendValue(IsoFields.WEEK_BASED_YEAR, 4, 10, signStyle).appendLiteral("-W").appendValue(IsoFields.WEEK_OF_WEEK_BASED_YEAR, 2).appendLiteral('-');
        ChronoField chronoField7 = ChronoField.DAY_OF_WEEK;
        ISO_WEEK_DATE = appendLiteral5.appendValue(chronoField7, 1).optionalStart().appendOffsetId().toFormatter(resolverStyle2, isoChronology);
        ISO_INSTANT = new DateTimeFormatterBuilder().parseCaseInsensitive().appendInstant().toFormatter(resolverStyle2, (Chronology) null);
        BASIC_ISO_DATE = new DateTimeFormatterBuilder().parseCaseInsensitive().appendValue(chronoField, 4).appendValue(chronoField2, 2).appendValue(chronoField3, 2).optionalStart().parseLenient().appendOffset("+HHMMss", "Z").parseStrict().toFormatter(resolverStyle2, isoChronology);
        HashMap hashMap = new HashMap();
        hashMap.put(1L, "Mon");
        hashMap.put(2L, "Tue");
        hashMap.put(3L, "Wed");
        hashMap.put(4L, "Thu");
        hashMap.put(5L, "Fri");
        hashMap.put(6L, "Sat");
        IsoChronology isoChronology2 = isoChronology;
        hashMap.put(7L, "Sun");
        HashMap hashMap2 = new HashMap();
        hashMap2.put(1L, "Jan");
        hashMap2.put(2L, "Feb");
        hashMap2.put(3L, "Mar");
        hashMap2.put(4L, "Apr");
        hashMap2.put(5L, "May");
        hashMap2.put(6L, "Jun");
        hashMap2.put(7L, "Jul");
        hashMap2.put(8L, "Aug");
        hashMap2.put(9L, "Sep");
        hashMap2.put(10L, "Oct");
        hashMap2.put(11L, "Nov");
        hashMap2.put(12L, "Dec");
        RFC_1123_DATE_TIME = new DateTimeFormatterBuilder().parseCaseInsensitive().parseLenient().optionalStart().appendText((TemporalField) chronoField7, (Map) hashMap).appendLiteral(", ").optionalEnd().appendValue(chronoField3, 1, 2, SignStyle.NOT_NEGATIVE).appendLiteral(' ').appendText((TemporalField) chronoField2, (Map) hashMap2).appendLiteral(' ').appendValue(chronoField, 4).appendLiteral(' ').appendValue(chronoField4, 2).appendLiteral((char) AbstractJsonLexerKt.COLON).appendValue(chronoField5, 2).optionalStart().appendLiteral((char) AbstractJsonLexerKt.COLON).appendValue(chronoField6, 2).optionalEnd().appendLiteral(' ').appendOffset("+HHMM", "GMT").toFormatter(ResolverStyle.SMART, isoChronology2);
    }

    DateTimeFormatter(DateTimeFormatterBuilder.CompositePrinterParser compositePrinterParser, Locale locale2, DecimalStyle decimalStyle2, ResolverStyle resolverStyle2, Set set, Chronology chronology, ZoneId zoneId) {
        this.printerParser = (DateTimeFormatterBuilder.CompositePrinterParser) Objects.requireNonNull(compositePrinterParser, "printerParser");
        this.resolverFields = set;
        this.locale = (Locale) Objects.requireNonNull(locale2, "locale");
        this.decimalStyle = (DecimalStyle) Objects.requireNonNull(decimalStyle2, "decimalStyle");
        this.resolverStyle = (ResolverStyle) Objects.requireNonNull(resolverStyle2, "resolverStyle");
        this.chrono = chronology;
        this.zone = zoneId;
    }

    private DateTimeParseException createError(CharSequence charSequence, RuntimeException runtimeException) {
        String str;
        if (charSequence.length() > 64) {
            str = charSequence.subSequence(0, 64).toString() + "...";
        } else {
            str = charSequence.toString();
        }
        return new DateTimeParseException("Text '" + str + "' could not be parsed: " + runtimeException.getMessage(), charSequence, 0, runtimeException);
    }

    static /* synthetic */ Period lambda$static$0(TemporalAccessor temporalAccessor) {
        return temporalAccessor instanceof Parsed ? ((Parsed) temporalAccessor).excessDays : Period.ZERO;
    }

    static /* synthetic */ Boolean lambda$static$1(TemporalAccessor temporalAccessor) {
        return temporalAccessor instanceof Parsed ? Boolean.valueOf(((Parsed) temporalAccessor).leapSecond) : Boolean.FALSE;
    }

    public static DateTimeFormatter ofPattern(String str) {
        return new DateTimeFormatterBuilder().appendPattern(str).toFormatter();
    }

    private TemporalAccessor parseResolved0(CharSequence charSequence, ParsePosition parsePosition) {
        String str;
        ParsePosition parsePosition2 = parsePosition != null ? parsePosition : new ParsePosition(0);
        DateTimeParseContext parseUnresolved0 = parseUnresolved0(charSequence, parsePosition2);
        if (parseUnresolved0 != null && parsePosition2.getErrorIndex() < 0 && (parsePosition != null || parsePosition2.getIndex() >= charSequence.length())) {
            return parseUnresolved0.toResolved(this.resolverStyle, this.resolverFields);
        }
        if (charSequence.length() > 64) {
            str = charSequence.subSequence(0, 64).toString() + "...";
        } else {
            str = charSequence.toString();
        }
        if (parsePosition2.getErrorIndex() >= 0) {
            throw new DateTimeParseException("Text '" + str + "' could not be parsed at index " + parsePosition2.getErrorIndex(), charSequence, parsePosition2.getErrorIndex());
        }
        throw new DateTimeParseException("Text '" + str + "' could not be parsed, unparsed text found at index " + parsePosition2.getIndex(), charSequence, parsePosition2.getIndex());
    }

    private DateTimeParseContext parseUnresolved0(CharSequence charSequence, ParsePosition parsePosition) {
        Objects.requireNonNull(charSequence, "text");
        Objects.requireNonNull(parsePosition, Constants.PLAY_EXTRA_START_TIME);
        DateTimeParseContext dateTimeParseContext = new DateTimeParseContext(this);
        int parse = this.printerParser.parse(dateTimeParseContext, charSequence, parsePosition.getIndex());
        if (parse < 0) {
            parsePosition.setErrorIndex(parse ^ -1);
            return null;
        }
        parsePosition.setIndex(parse);
        return dateTimeParseContext;
    }

    public String format(TemporalAccessor temporalAccessor) {
        StringBuilder sb = new StringBuilder(32);
        formatTo(temporalAccessor, sb);
        return sb.toString();
    }

    public void formatTo(TemporalAccessor temporalAccessor, Appendable appendable) {
        Objects.requireNonNull(temporalAccessor, "temporal");
        Objects.requireNonNull(appendable, "appendable");
        try {
            DateTimePrintContext dateTimePrintContext = new DateTimePrintContext(temporalAccessor, this);
            if (appendable instanceof StringBuilder) {
                this.printerParser.format(dateTimePrintContext, (StringBuilder) appendable);
                return;
            }
            StringBuilder sb = new StringBuilder(32);
            this.printerParser.format(dateTimePrintContext, sb);
            appendable.append(sb);
        } catch (IOException e) {
            throw new DateTimeException(e.getMessage(), e);
        }
    }

    public Chronology getChronology() {
        return this.chrono;
    }

    public DecimalStyle getDecimalStyle() {
        return this.decimalStyle;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public ZoneId getZone() {
        return this.zone;
    }

    public Object parse(CharSequence charSequence, TemporalQuery temporalQuery) {
        Objects.requireNonNull(charSequence, "text");
        Objects.requireNonNull(temporalQuery, "query");
        try {
            return parseResolved0(charSequence, (ParsePosition) null).query(temporalQuery);
        } catch (DateTimeParseException e) {
            throw e;
        } catch (RuntimeException e2) {
            throw createError(charSequence, e2);
        }
    }

    /* access modifiers changed from: package-private */
    public DateTimeFormatterBuilder.CompositePrinterParser toPrinterParser(boolean z) {
        return this.printerParser.withOptional(z);
    }

    public String toString() {
        String compositePrinterParser = this.printerParser.toString();
        return compositePrinterParser.startsWith("[") ? compositePrinterParser : compositePrinterParser.substring(1, compositePrinterParser.length() - 1);
    }

    public DateTimeFormatter withLocale(Locale locale2) {
        if (this.locale.equals(locale2)) {
            return this;
        }
        return new DateTimeFormatter(this.printerParser, locale2, this.decimalStyle, this.resolverStyle, this.resolverFields, this.chrono, this.zone);
    }

    public DateTimeFormatter withZone(ZoneId zoneId) {
        return Objects.equals(this.zone, zoneId) ? this : new DateTimeFormatter(this.printerParser, this.locale, this.decimalStyle, this.resolverStyle, this.resolverFields, this.chrono, zoneId);
    }
}