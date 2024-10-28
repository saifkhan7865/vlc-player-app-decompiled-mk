package kotlin.ranges;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.UInt$$ExternalSyntheticBackport0;
import kotlin.ULong;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\n\u001a\u00020\u000bH\u0002J\u0013\u0010\f\u001a\u00020\u0002H\u0002ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eR\u0013\u0010\b\u001a\u00020\u0002X\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\f\u001a\u00020\u0002X\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\tR\u0013\u0010\u0005\u001a\u00020\u0002X\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\t\u0002\u0004\n\u0002\b!¨\u0006\u000f"}, d2 = {"Lkotlin/ranges/ULongProgressionIterator;", "", "Lkotlin/ULong;", "first", "last", "step", "", "(JJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "finalElement", "J", "hasNext", "", "next", "next-s-VKNKU", "()J", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ULongRange.kt */
final class ULongProgressionIterator implements Iterator<ULong>, KMappedMarker {
    private final long finalElement;
    private boolean hasNext;
    private long next;
    private final long step;

    public /* synthetic */ ULongProgressionIterator(long j, long j2, long j3, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3);
    }

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    private ULongProgressionIterator(long j, long j2, long j3) {
        this.finalElement = j2;
        boolean z = true;
        if (j3 <= 0 ? UInt$$ExternalSyntheticBackport0.m(j, j2) < 0 : UInt$$ExternalSyntheticBackport0.m(j, j2) > 0) {
            z = false;
        }
        this.hasNext = z;
        this.step = ULong.m1948constructorimpl(j3);
        this.next = !this.hasNext ? j2 : j;
    }

    public /* bridge */ /* synthetic */ Object next() {
        return ULong.m1942boximpl(m2216nextsVKNKU());
    }

    public boolean hasNext() {
        return this.hasNext;
    }

    /* renamed from: next-s-VKNKU  reason: not valid java name */
    public long m2216nextsVKNKU() {
        long j = this.next;
        if (j != this.finalElement) {
            this.next = ULong.m1948constructorimpl(this.step + j);
        } else if (this.hasNext) {
            this.hasNext = false;
        } else {
            throw new NoSuchElementException();
        }
        return j;
    }
}
