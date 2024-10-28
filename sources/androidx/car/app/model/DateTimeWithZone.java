package androidx.car.app.model;

import j$.time.LocalDateTime;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import j$.time.ZonedDateTime;
import j$.time.format.TextStyle;
import j$.util.Objects;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public final class DateTimeWithZone {
    private static final long MAX_ZONE_OFFSET_SECONDS = (TimeUnit.HOURS.toSeconds(1) * 18);
    private final long mTimeSinceEpochMillis;
    private final int mZoneOffsetSeconds;
    private final String mZoneShortName;

    public long getTimeSinceEpochMillis() {
        return this.mTimeSinceEpochMillis;
    }

    public int getZoneOffsetSeconds() {
        return this.mZoneOffsetSeconds;
    }

    public String getZoneShortName() {
        return this.mZoneShortName;
    }

    public String toString() {
        return "[time since epoch (ms): " + this.mTimeSinceEpochMillis + "( " + new Date(this.mTimeSinceEpochMillis) + ")  zone offset (s): " + this.mZoneOffsetSeconds + ", zone: " + this.mZoneShortName + "]";
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.mTimeSinceEpochMillis), Integer.valueOf(this.mZoneOffsetSeconds), this.mZoneShortName);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DateTimeWithZone)) {
            return false;
        }
        DateTimeWithZone dateTimeWithZone = (DateTimeWithZone) obj;
        if (this.mTimeSinceEpochMillis == dateTimeWithZone.mTimeSinceEpochMillis && this.mZoneOffsetSeconds == dateTimeWithZone.mZoneOffsetSeconds && Objects.equals(this.mZoneShortName, dateTimeWithZone.mZoneShortName)) {
            return true;
        }
        return false;
    }

    public static DateTimeWithZone create(long j, int i, String str) {
        if (j < 0) {
            throw new IllegalArgumentException("Time since epoch must be greater than or equal to zero");
        } else if (((long) Math.abs(i)) > MAX_ZONE_OFFSET_SECONDS) {
            throw new IllegalArgumentException("Zone offset not in valid range: -18:00 to +18:00");
        } else if (!((String) Objects.requireNonNull(str)).isEmpty()) {
            return new DateTimeWithZone(j, i, str);
        } else {
            throw new IllegalArgumentException("The time zone short name can not be null or empty");
        }
    }

    public static DateTimeWithZone create(long j, TimeZone timeZone) {
        if (j >= 0) {
            return create(j, (int) TimeUnit.MILLISECONDS.toSeconds((long) ((TimeZone) Objects.requireNonNull(timeZone)).getOffset(j)), timeZone.getDisplayName(false, 0));
        }
        throw new IllegalArgumentException("timeSinceEpochMillis must be greater than or equal to zero");
    }

    public static DateTimeWithZone create(ZonedDateTime zonedDateTime) {
        return Api26Impl.create(zonedDateTime);
    }

    private DateTimeWithZone() {
        this.mTimeSinceEpochMillis = 0;
        this.mZoneOffsetSeconds = 0;
        this.mZoneShortName = null;
    }

    private DateTimeWithZone(long j, int i, String str) {
        this.mTimeSinceEpochMillis = j;
        this.mZoneOffsetSeconds = i;
        this.mZoneShortName = str;
    }

    private static final class Api26Impl {
        private Api26Impl() {
        }

        public static DateTimeWithZone create(ZonedDateTime zonedDateTime) {
            LocalDateTime localDateTime = ((ZonedDateTime) Objects.requireNonNull(zonedDateTime)).toLocalDateTime();
            ZoneId zone = zonedDateTime.getZone();
            ZoneOffset offset = zone.getRules().getOffset(localDateTime);
            return DateTimeWithZone.create(TimeUnit.SECONDS.toMillis(localDateTime.toEpochSecond(offset)), offset.getTotalSeconds(), zone.getDisplayName(TextStyle.SHORT, Locale.getDefault()));
        }
    }
}
