package j$.time.temporal;

import io.ktor.server.plugins.cors.CORSConfig;
import io.ktor.server.sessions.SessionTransportCookieKt;
import j$.time.Duration;
import org.videolan.vlc.gui.dialogs.PickTimeFragment;

public enum ChronoUnit implements TemporalUnit {
    NANOS("Nanos", Duration.ofNanos(1)),
    MICROS("Micros", Duration.ofNanos(1000)),
    MILLIS("Millis", Duration.ofNanos(PickTimeFragment.SECONDS_IN_MICROS)),
    SECONDS("Seconds", Duration.ofSeconds(1)),
    MINUTES("Minutes", Duration.ofSeconds(60)),
    HOURS("Hours", Duration.ofSeconds(3600)),
    HALF_DAYS("HalfDays", Duration.ofSeconds(43200)),
    DAYS("Days", Duration.ofSeconds(CORSConfig.CORS_DEFAULT_MAX_AGE)),
    WEEKS("Weeks", Duration.ofSeconds(SessionTransportCookieKt.DEFAULT_SESSION_MAX_AGE)),
    MONTHS("Months", Duration.ofSeconds(2629746)),
    YEARS("Years", Duration.ofSeconds(31556952)),
    DECADES("Decades", Duration.ofSeconds(315569520)),
    CENTURIES("Centuries", Duration.ofSeconds(3155695200L)),
    MILLENNIA("Millennia", Duration.ofSeconds(31556952000L)),
    ERAS("Eras", Duration.ofSeconds(31556952000000000L)),
    FOREVER("Forever", Duration.ofSeconds(Long.MAX_VALUE, 999999999));
    
    private final Duration duration;
    private final String name;

    private ChronoUnit(String str, Duration duration2) {
        this.name = str;
        this.duration = duration2;
    }

    public Temporal addTo(Temporal temporal, long j) {
        return temporal.plus(j, this);
    }

    public Duration getDuration() {
        return this.duration;
    }

    public boolean isDateBased() {
        return compareTo(DAYS) >= 0 && this != FOREVER;
    }

    public boolean isTimeBased() {
        return compareTo(DAYS) < 0;
    }

    public String toString() {
        return this.name;
    }
}
