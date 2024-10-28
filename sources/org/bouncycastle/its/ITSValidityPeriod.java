package org.bouncycastle.its;

import java.util.Date;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.Duration;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.Time32;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.UINT16;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.ValidityPeriod;

public class ITSValidityPeriod {
    private final UINT16 duration;
    private final long startDate;
    private final Unit timeUnit;

    public static class Builder {
        private final long startDate;

        Builder(Date date) {
            this.startDate = date.getTime();
        }

        public ITSValidityPeriod plusSixtyHours(int i) {
            return new ITSValidityPeriod(this.startDate, UINT16.valueOf(i), Unit.sixtyHours);
        }

        public ITSValidityPeriod plusYears(int i) {
            return new ITSValidityPeriod(this.startDate, UINT16.valueOf(i), Unit.years);
        }
    }

    public enum Unit {
        microseconds(0),
        milliseconds(1),
        seconds(2),
        minutes(3),
        hours(4),
        sixtyHours(5),
        years(6);
        
        /* access modifiers changed from: private */
        public final int unitTag;

        private Unit(int i) {
            this.unitTag = i;
        }
    }

    ITSValidityPeriod(long j, UINT16 uint16, Unit unit) {
        this.startDate = j;
        this.duration = uint16;
        this.timeUnit = unit;
    }

    public ITSValidityPeriod(ValidityPeriod validityPeriod) {
        this.startDate = validityPeriod.getStart().getValue().longValue();
        Duration duration2 = validityPeriod.getDuration();
        this.duration = duration2.getDuration();
        this.timeUnit = Unit.values()[duration2.getChoice()];
    }

    public static Builder from(Date date) {
        return new Builder(date);
    }

    public Date getStartDate() {
        return new Date(this.startDate);
    }

    public ValidityPeriod toASN1Structure() {
        return ValidityPeriod.builder().setStart(new Time32(this.startDate / 1000)).setDuration(new Duration(this.timeUnit.unitTag, this.duration)).createValidityPeriod();
    }
}
