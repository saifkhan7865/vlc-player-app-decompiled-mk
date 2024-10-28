package io.ktor.http;

import io.ktor.http.ContentRange;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.LongRange;
import kotlin.ranges.RangesKt;
import org.fusesource.jansi.AnsiRenderer;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\b\b\u0018\u00002\u00020\u0001B\u001d\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007B\u001d\u0012\b\b\u0002\u0010\u0002\u001a\u00020\b\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u000e\u001a\u00020\bHÆ\u0003J\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\b2\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\u001c\u0010\u0016\u001a\u00020\u00122\u0014\b\u0002\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00120\u0018J\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00052\u0006\u0010\u001b\u001a\u00020\u001cJ\u001e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00052\u0006\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u0015J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ\b\u0010\u001f\u001a\u00020\bH\u0016J\u001f\u0010 \u001a\b\u0012\u0004\u0012\u0002H!0\u0005\"\u0004\b\u0000\u0010!*\u0004\u0018\u0001H!H\u0002¢\u0006\u0002\u0010\"R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006#"}, d2 = {"Lio/ktor/http/RangesSpecifier;", "", "unit", "Lio/ktor/http/RangeUnits;", "ranges", "", "Lio/ktor/http/ContentRange;", "(Lio/ktor/http/RangeUnits;Ljava/util/List;)V", "", "(Ljava/lang/String;Ljava/util/List;)V", "getRanges", "()Ljava/util/List;", "getUnit", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "isValid", "rangeUnitPredicate", "Lkotlin/Function1;", "merge", "Lkotlin/ranges/LongRange;", "length", "", "maxRangeCount", "mergeToSingle", "toString", "toList", "T", "(Ljava/lang/Object;)Ljava/util/List;", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RangesSpecifier.kt */
public final class RangesSpecifier {
    private final List<ContentRange> ranges;
    private final String unit;

    public static /* synthetic */ RangesSpecifier copy$default(RangesSpecifier rangesSpecifier, String str, List<ContentRange> list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = rangesSpecifier.unit;
        }
        if ((i & 2) != 0) {
            list = rangesSpecifier.ranges;
        }
        return rangesSpecifier.copy(str, list);
    }

    public final String component1() {
        return this.unit;
    }

    public final List<ContentRange> component2() {
        return this.ranges;
    }

    public final RangesSpecifier copy(String str, List<? extends ContentRange> list) {
        Intrinsics.checkNotNullParameter(str, "unit");
        Intrinsics.checkNotNullParameter(list, "ranges");
        return new RangesSpecifier(str, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RangesSpecifier)) {
            return false;
        }
        RangesSpecifier rangesSpecifier = (RangesSpecifier) obj;
        return Intrinsics.areEqual((Object) this.unit, (Object) rangesSpecifier.unit) && Intrinsics.areEqual((Object) this.ranges, (Object) rangesSpecifier.ranges);
    }

    public int hashCode() {
        return (this.unit.hashCode() * 31) + this.ranges.hashCode();
    }

    public RangesSpecifier(String str, List<? extends ContentRange> list) {
        Intrinsics.checkNotNullParameter(str, "unit");
        Intrinsics.checkNotNullParameter(list, "ranges");
        this.unit = str;
        this.ranges = list;
        if (!(!list.isEmpty())) {
            throw new IllegalArgumentException("It should be at least one range".toString());
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ RangesSpecifier(String str, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? RangeUnits.Bytes.getUnitToken() : str, (List<? extends ContentRange>) list);
    }

    public final List<ContentRange> getRanges() {
        return this.ranges;
    }

    public final String getUnit() {
        return this.unit;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public RangesSpecifier(RangeUnits rangeUnits, List<? extends ContentRange> list) {
        this(rangeUnits.getUnitToken(), list);
        Intrinsics.checkNotNullParameter(rangeUnits, "unit");
        Intrinsics.checkNotNullParameter(list, "ranges");
    }

    public static /* synthetic */ boolean isValid$default(RangesSpecifier rangesSpecifier, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = RangesSpecifier$isValid$1.INSTANCE;
        }
        return rangesSpecifier.isValid(function1);
    }

    public final boolean isValid(Function1<? super String, Boolean> function1) {
        Intrinsics.checkNotNullParameter(function1, "rangeUnitPredicate");
        if (function1.invoke(this.unit).booleanValue()) {
            Iterable<ContentRange> iterable = this.ranges;
            if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                for (ContentRange contentRange : iterable) {
                    if (contentRange instanceof ContentRange.Bounded) {
                        ContentRange.Bounded bounded = (ContentRange.Bounded) contentRange;
                        if (bounded.getFrom() >= 0 && bounded.getTo() >= bounded.getFrom()) {
                        }
                    } else if (contentRange instanceof ContentRange.TailFrom) {
                        if (((ContentRange.TailFrom) contentRange).getFrom() < 0) {
                        }
                    } else if (!(contentRange instanceof ContentRange.Suffix)) {
                        throw new NoWhenBranchMatchedException();
                    } else if (((ContentRange.Suffix) contentRange).getLastCount() < 0) {
                    }
                }
            }
            return true;
        }
        return false;
    }

    public static /* synthetic */ List merge$default(RangesSpecifier rangesSpecifier, long j, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 50;
        }
        return rangesSpecifier.merge(j, i);
    }

    public final List<LongRange> merge(long j, int i) {
        if (this.ranges.size() > i) {
            return toList(mergeToSingle(j));
        }
        return merge(j);
    }

    public final List<LongRange> merge(long j) {
        return RangesKt.mergeRangesKeepOrder(RangesKt.toLongRanges(this.ranges, j));
    }

    public final LongRange mergeToSingle(long j) {
        Object obj;
        List<LongRange> longRanges = RangesKt.toLongRanges(this.ranges, j);
        Object obj2 = null;
        if (longRanges.isEmpty()) {
            return null;
        }
        Iterable iterable = longRanges;
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            obj = null;
        } else {
            obj = it.next();
            if (it.hasNext()) {
                long longValue = ((LongRange) obj).getStart().longValue();
                do {
                    Object next = it.next();
                    long longValue2 = ((LongRange) next).getStart().longValue();
                    if (longValue > longValue2) {
                        obj = next;
                        longValue = longValue2;
                    }
                } while (it.hasNext());
            }
        }
        Intrinsics.checkNotNull(obj);
        long longValue3 = ((LongRange) obj).getStart().longValue();
        Iterator it2 = iterable.iterator();
        if (it2.hasNext()) {
            obj2 = it2.next();
            if (it2.hasNext()) {
                long longValue4 = ((LongRange) obj2).getEndInclusive().longValue();
                do {
                    Object next2 = it2.next();
                    long longValue5 = ((LongRange) next2).getEndInclusive().longValue();
                    if (longValue4 < longValue5) {
                        obj2 = next2;
                        longValue4 = longValue5;
                    }
                } while (it2.hasNext());
            }
        }
        Intrinsics.checkNotNull(obj2);
        return new LongRange(longValue3, RangesKt.coerceAtMost(((LongRange) obj2).getEndInclusive().longValue(), j - 1));
    }

    public String toString() {
        return CollectionsKt.joinToString$default(this.ranges, AnsiRenderer.CODE_LIST_SEPARATOR, this.unit + '=', (CharSequence) null, 0, (CharSequence) null, (Function1) null, 60, (Object) null);
    }

    private final <T> List<T> toList(T t) {
        return t == null ? CollectionsKt.emptyList() : CollectionsKt.listOf(t);
    }
}
