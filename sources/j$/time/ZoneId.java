package j$.time;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.common.net.HttpHeaders;
import j$.time.format.DateTimeFormatterBuilder;
import j$.time.format.TextStyle;
import j$.time.temporal.TemporalAccessor;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalQueries;
import j$.time.temporal.TemporalQuery;
import j$.time.temporal.UnsupportedTemporalTypeException;
import j$.time.temporal.ValueRange;
import j$.time.zone.ZoneRules;
import j$.time.zone.ZoneRulesException;
import j$.util.DesugarTimeZone;
import j$.util.Objects;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.slf4j.Marker;

public abstract class ZoneId implements Serializable {
    public static final Map SHORT_IDS = ZoneId$$ExternalSyntheticBackport1.m(new Map.Entry[]{ZoneId$$ExternalSyntheticBackport0.m("ACT", "Australia/Darwin"), ZoneId$$ExternalSyntheticBackport0.m("AET", "Australia/Sydney"), ZoneId$$ExternalSyntheticBackport0.m("AGT", "America/Argentina/Buenos_Aires"), ZoneId$$ExternalSyntheticBackport0.m("ART", "Africa/Cairo"), ZoneId$$ExternalSyntheticBackport0.m("AST", "America/Anchorage"), ZoneId$$ExternalSyntheticBackport0.m("BET", "America/Sao_Paulo"), ZoneId$$ExternalSyntheticBackport0.m("BST", "Asia/Dhaka"), ZoneId$$ExternalSyntheticBackport0.m("CAT", "Africa/Harare"), ZoneId$$ExternalSyntheticBackport0.m("CNT", "America/St_Johns"), ZoneId$$ExternalSyntheticBackport0.m("CST", "America/Chicago"), ZoneId$$ExternalSyntheticBackport0.m("CTT", "Asia/Shanghai"), ZoneId$$ExternalSyntheticBackport0.m("EAT", "Africa/Addis_Ababa"), ZoneId$$ExternalSyntheticBackport0.m(HttpHeaders.ECT, "Europe/Paris"), ZoneId$$ExternalSyntheticBackport0.m("IET", "America/Indiana/Indianapolis"), ZoneId$$ExternalSyntheticBackport0.m("IST", "Asia/Kolkata"), ZoneId$$ExternalSyntheticBackport0.m("JST", "Asia/Tokyo"), ZoneId$$ExternalSyntheticBackport0.m("MIT", "Pacific/Apia"), ZoneId$$ExternalSyntheticBackport0.m("NET", "Asia/Yerevan"), ZoneId$$ExternalSyntheticBackport0.m("NST", "Pacific/Auckland"), ZoneId$$ExternalSyntheticBackport0.m("PLT", "Asia/Karachi"), ZoneId$$ExternalSyntheticBackport0.m("PNT", "America/Phoenix"), ZoneId$$ExternalSyntheticBackport0.m("PRT", "America/Puerto_Rico"), ZoneId$$ExternalSyntheticBackport0.m("PST", "America/Los_Angeles"), ZoneId$$ExternalSyntheticBackport0.m("SST", "Pacific/Guadalcanal"), ZoneId$$ExternalSyntheticBackport0.m("VST", "Asia/Ho_Chi_Minh"), ZoneId$$ExternalSyntheticBackport0.m("EST", "-05:00"), ZoneId$$ExternalSyntheticBackport0.m("MST", "-07:00"), ZoneId$$ExternalSyntheticBackport0.m("HST", "-10:00")});
    private static final long serialVersionUID = 8352817235686L;

    ZoneId() {
        if (getClass() != ZoneOffset.class && getClass() != ZoneRegion.class) {
            throw new AssertionError("Invalid subclass");
        }
    }

    public static ZoneId from(TemporalAccessor temporalAccessor) {
        ZoneId zoneId = (ZoneId) temporalAccessor.query(TemporalQueries.zone());
        if (zoneId != null) {
            return zoneId;
        }
        String name = temporalAccessor.getClass().getName();
        throw new DateTimeException("Unable to obtain ZoneId from TemporalAccessor: " + temporalAccessor + " of type " + name);
    }

    public static ZoneId of(String str) {
        return of(str, true);
    }

    public static ZoneId of(String str, Map map) {
        Objects.requireNonNull(str, "zoneId");
        Objects.requireNonNull(map, "aliasMap");
        return of((String) Objects.requireNonNullElse((String) map.get(str), str));
    }

    static ZoneId of(String str, boolean z) {
        int i;
        Objects.requireNonNull(str, "zoneId");
        if (str.length() <= 1 || str.startsWith(Marker.ANY_NON_NULL_MARKER) || str.startsWith("-")) {
            return ZoneOffset.of(str);
        }
        if (str.startsWith("UTC") || str.startsWith("GMT")) {
            i = 3;
        } else if (!str.startsWith("UT")) {
            return ZoneRegion.ofId(str, z);
        } else {
            i = 2;
        }
        return ofWithPrefix(str, i, z);
    }

    public static ZoneId ofOffset(String str, ZoneOffset zoneOffset) {
        Objects.requireNonNull(str, "prefix");
        Objects.requireNonNull(zoneOffset, TypedValues.CycleType.S_WAVE_OFFSET);
        if (str.isEmpty()) {
            return zoneOffset;
        }
        if (str.equals("GMT") || str.equals("UTC") || str.equals("UT")) {
            if (zoneOffset.getTotalSeconds() != 0) {
                str = str.concat(zoneOffset.getId());
            }
            return new ZoneRegion(str, zoneOffset.getRules());
        }
        throw new IllegalArgumentException("prefix should be GMT, UTC or UT, is: " + str);
    }

    private static ZoneId ofWithPrefix(String str, int i, boolean z) {
        String substring = str.substring(0, i);
        if (str.length() == i) {
            return ofOffset(substring, ZoneOffset.UTC);
        }
        if (str.charAt(i) != '+' && str.charAt(i) != '-') {
            return ZoneRegion.ofId(str, z);
        }
        try {
            ZoneOffset of = ZoneOffset.of(str.substring(i));
            return of == ZoneOffset.UTC ? ofOffset(substring, of) : ofOffset(substring, of);
        } catch (DateTimeException e) {
            throw new DateTimeException("Invalid ID for offset-based ZoneId: " + str, e);
        }
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    public static ZoneId systemDefault() {
        return DesugarTimeZone.toZoneId(TimeZone.getDefault());
    }

    private TemporalAccessor toTemporal() {
        return new TemporalAccessor() {
            public /* synthetic */ int get(TemporalField temporalField) {
                return TemporalAccessor.CC.$default$get(this, temporalField);
            }

            public long getLong(TemporalField temporalField) {
                throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
            }

            public boolean isSupported(TemporalField temporalField) {
                return false;
            }

            public Object query(TemporalQuery temporalQuery) {
                return temporalQuery == TemporalQueries.zoneId() ? ZoneId.this : TemporalAccessor.CC.$default$query(this, temporalQuery);
            }

            public /* synthetic */ ValueRange range(TemporalField temporalField) {
                return TemporalAccessor.CC.$default$range(this, temporalField);
            }
        };
    }

    private Object writeReplace() {
        return new Ser((byte) 7, this);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ZoneId) {
            return getId().equals(((ZoneId) obj).getId());
        }
        return false;
    }

    public String getDisplayName(TextStyle textStyle, Locale locale) {
        return new DateTimeFormatterBuilder().appendZoneText(textStyle).toFormatter(locale).format(toTemporal());
    }

    public abstract String getId();

    public abstract ZoneRules getRules();

    public int hashCode() {
        return getId().hashCode();
    }

    public ZoneId normalized() {
        try {
            ZoneRules rules = getRules();
            if (rules.isFixedOffset()) {
                return rules.getOffset(Instant.EPOCH);
            }
        } catch (ZoneRulesException unused) {
        }
        return this;
    }

    public String toString() {
        return getId();
    }

    /* access modifiers changed from: package-private */
    public abstract void write(DataOutput dataOutput);
}
