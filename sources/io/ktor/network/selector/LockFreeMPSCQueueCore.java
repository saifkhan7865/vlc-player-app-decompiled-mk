package io.ktor.network.selector;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\b\b\u0002\u0018\u0000 &*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001:\u0002&'B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00028\u0000¢\u0006\u0004\b\b\u0010\tJ'\u0010\r\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0000j\b\u0012\u0004\u0012\u00028\u0000`\f2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\r\u0010\u000eJ'\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0000j\b\u0012\u0004\u0012\u00028\u0000`\f2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u000f\u0010\u000eJ\r\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012J3\u0010\u0014\u001a\u0016\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\f2\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0016\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u0013\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ3\u0010\u001e\u001a\u0016\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\f2\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u001e\u0010\u001fR\u001c\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010 8\u0002X\u0004¢\u0006\u0006\n\u0004\b!\u0010\"R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010#R\u0011\u0010$\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b$\u0010\u0012R\u0014\u0010%\u001a\u00020\u00038\u0002X\u0004¢\u0006\u0006\n\u0004\b%\u0010#¨\u0006("}, d2 = {"Lio/ktor/network/selector/LockFreeMPSCQueueCore;", "", "E", "", "capacity", "<init>", "(I)V", "element", "addLast", "(Ljava/lang/Object;)I", "", "state", "Lio/ktor/network/selector/Core;", "allocateNextCopy", "(J)Lio/ktor/network/selector/LockFreeMPSCQueueCore;", "allocateOrGetNextCopy", "", "close", "()Z", "index", "fillPlaceholder", "(ILjava/lang/Object;)Lio/ktor/network/selector/LockFreeMPSCQueueCore;", "markFrozen", "()J", "next", "()Lio/ktor/network/selector/LockFreeMPSCQueueCore;", "removeFirstOrNull", "()Ljava/lang/Object;", "oldHead", "newHead", "removeSlowPath", "(II)Lio/ktor/network/selector/LockFreeMPSCQueueCore;", "Ljava/util/concurrent/atomic/AtomicReferenceArray;", "array", "Ljava/util/concurrent/atomic/AtomicReferenceArray;", "I", "isEmpty", "mask", "Companion", "Placeholder", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: LockFreeMPSCQueue.kt */
final class LockFreeMPSCQueueCore<E> {
    public static final int ADD_CLOSED = 2;
    public static final int ADD_FROZEN = 1;
    public static final int ADD_SUCCESS = 0;
    private static final int CAPACITY_BITS = 30;
    private static final long CLOSED_MASK = 2305843009213693952L;
    private static final int CLOSED_SHIFT = 61;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final long FROZEN_MASK = 1152921504606846976L;
    private static final int FROZEN_SHIFT = 60;
    private static final long HEAD_MASK = 1073741823;
    private static final int HEAD_SHIFT = 0;
    public static final int INITIAL_CAPACITY = 8;
    private static final int MAX_CAPACITY_MASK = 1073741823;
    public static final Object REMOVE_FROZEN = new LockFreeMPSCQueueCore$Companion$REMOVE_FROZEN$1();
    private static final long TAIL_MASK = 1152921503533105152L;
    private static final int TAIL_SHIFT = 30;
    private static final /* synthetic */ AtomicReferenceFieldUpdater _next$FU;
    private static final /* synthetic */ AtomicLongFieldUpdater _state$FU;
    private volatile /* synthetic */ Object _next = null;
    private volatile /* synthetic */ long _state = 0;
    private final AtomicReferenceArray<Object> array;
    private final int capacity;
    private final int mask;

    public LockFreeMPSCQueueCore(int i) {
        this.capacity = i;
        int i2 = i - 1;
        this.mask = i2;
        this.array = new AtomicReferenceArray<>(i);
        if (i2 > 1073741823) {
            throw new IllegalStateException("Check failed.".toString());
        } else if ((i & i2) != 0) {
            throw new IllegalStateException("Check failed.".toString());
        }
    }

    public final boolean isEmpty() {
        long j = this._state;
        return ((int) (1073741823 & j)) == ((int) ((j & 1152921503533105152L) >> 30));
    }

    private final LockFreeMPSCQueueCore<E> fillPlaceholder(int i, E e) {
        Object obj = this.array.get(this.mask & i);
        if (!(obj instanceof Placeholder) || ((Placeholder) obj).index != i) {
            return null;
        }
        this.array.set(i & this.mask, e);
        return this;
    }

    public final LockFreeMPSCQueueCore<E> next() {
        return allocateOrGetNextCopy(markFrozen());
    }

    private final LockFreeMPSCQueueCore<E> allocateNextCopy(long j) {
        LockFreeMPSCQueueCore<E> lockFreeMPSCQueueCore = new LockFreeMPSCQueueCore<>(this.capacity * 2);
        int i = (int) (1073741823 & j);
        int i2 = (int) ((1152921503533105152L & j) >> 30);
        while (true) {
            int i3 = this.mask;
            if ((i & i3) != (i2 & i3)) {
                AtomicReferenceArray<Object> atomicReferenceArray = lockFreeMPSCQueueCore.array;
                int i4 = lockFreeMPSCQueueCore.mask & i;
                Object obj = this.array.get(i3 & i);
                if (obj == null) {
                    obj = new Placeholder(i);
                }
                atomicReferenceArray.set(i4, obj);
                i++;
            } else {
                lockFreeMPSCQueueCore._state = Companion.wo(j, 1152921504606846976L);
                return lockFreeMPSCQueueCore;
            }
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lio/ktor/network/selector/LockFreeMPSCQueueCore$Placeholder;", "", "index", "", "(I)V", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: LockFreeMPSCQueue.kt */
    private static final class Placeholder {
        public final int index;

        public Placeholder(int i) {
            this.index = i;
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\f\u0010\u0014\u001a\u00020\u0004*\u00020\tH\u0002J\u0014\u0010\u0015\u001a\u00020\t*\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0004H\u0002J\u0014\u0010\u0017\u001a\u00020\t*\u00020\t2\u0006\u0010\u0018\u001a\u00020\u0004H\u0002JP\u0010\u0019\u001a\u0002H\u001a\"\u0004\b\u0001\u0010\u001a*\u00020\t26\u0010\u001b\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b( \u0012\u0004\u0012\u0002H\u001a0\u001cH\b¢\u0006\u0002\u0010!J\u0015\u0010\"\u001a\u00020\t*\u00020\t2\u0006\u0010#\u001a\u00020\tH\u0004R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u00018\u0000X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lio/ktor/network/selector/LockFreeMPSCQueueCore$Companion;", "", "()V", "ADD_CLOSED", "", "ADD_FROZEN", "ADD_SUCCESS", "CAPACITY_BITS", "CLOSED_MASK", "", "CLOSED_SHIFT", "FROZEN_MASK", "FROZEN_SHIFT", "HEAD_MASK", "HEAD_SHIFT", "INITIAL_CAPACITY", "MAX_CAPACITY_MASK", "REMOVE_FROZEN", "TAIL_MASK", "TAIL_SHIFT", "addFailReason", "updateHead", "newHead", "updateTail", "newTail", "withState", "T", "block", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "head", "tail", "(JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "wo", "other", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: LockFreeMPSCQueue.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final int addFailReason(long j) {
            return (j & 2305843009213693952L) != 0 ? 2 : 1;
        }

        /* access modifiers changed from: private */
        public final long wo(long j, long j2) {
            return j & (j2 ^ -1);
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final long updateHead(long j, int i) {
            return wo(j, 1073741823) | ((long) i);
        }

        /* access modifiers changed from: private */
        public final long updateTail(long j, int i) {
            return wo(j, 1152921503533105152L) | (((long) i) << 30);
        }

        private final <T> T withState(long j, Function2<? super Integer, ? super Integer, ? extends T> function2) {
            return function2.invoke(Integer.valueOf((int) (1073741823 & j)), Integer.valueOf((int) ((j & 1152921503533105152L) >> 30)));
        }
    }

    static {
        Class<LockFreeMPSCQueueCore> cls = LockFreeMPSCQueueCore.class;
        _next$FU = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "_next");
        _state$FU = AtomicLongFieldUpdater.newUpdater(cls, "_state");
    }

    public final boolean close() {
        long j;
        do {
            j = this._state;
            if ((j & 2305843009213693952L) != 0) {
                return true;
            }
            if ((1152921504606846976L & j) != 0) {
                return false;
            }
        } while (!_state$FU.compareAndSet(this, j, j | 2305843009213693952L));
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x004f A[LOOP:1: B:11:0x004f->B:14:0x0060, LOOP_START, PHI: r0 
      PHI: (r0v7 io.ktor.network.selector.LockFreeMPSCQueueCore) = (r0v6 io.ktor.network.selector.LockFreeMPSCQueueCore), (r0v9 io.ktor.network.selector.LockFreeMPSCQueueCore) binds: [B:10:0x0046, B:14:0x0060] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int addLast(E r11) {
        /*
            r10 = this;
            java.lang.String r0 = "element"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
        L_0x0005:
            long r3 = r10._state
            r0 = 3458764513820540928(0x3000000000000000, double:1.727233711018889E-77)
            long r0 = r0 & r3
            r7 = 0
            int r2 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r2 == 0) goto L_0x0017
            io.ktor.network.selector.LockFreeMPSCQueueCore$Companion r11 = Companion
            int r11 = r11.addFailReason(r3)
            return r11
        L_0x0017:
            io.ktor.network.selector.LockFreeMPSCQueueCore$Companion r0 = Companion
            r1 = 1073741823(0x3fffffff, double:5.304989472E-315)
            long r1 = r1 & r3
            int r2 = (int) r1
            r5 = 1152921503533105152(0xfffffffc0000000, double:1.2882296003504729E-231)
            long r5 = r5 & r3
            r1 = 30
            long r5 = r5 >> r1
            int r9 = (int) r5
            int r1 = r9 + 2
            int r5 = r10.mask
            r1 = r1 & r5
            r2 = r2 & r5
            if (r1 != r2) goto L_0x0032
            r11 = 1
            return r11
        L_0x0032:
            int r1 = r9 + 1
            r2 = 1073741823(0x3fffffff, float:1.9999999)
            r1 = r1 & r2
            java.util.concurrent.atomic.AtomicLongFieldUpdater r2 = _state$FU
            long r5 = r0.updateTail(r3, r1)
            r1 = r2
            r2 = r10
            boolean r0 = r1.compareAndSet(r2, r3, r5)
            if (r0 == 0) goto L_0x0005
            java.util.concurrent.atomic.AtomicReferenceArray<java.lang.Object> r0 = r10.array
            int r1 = r10.mask
            r1 = r1 & r9
            r0.set(r1, r11)
            r0 = r10
        L_0x004f:
            long r1 = r0._state
            r3 = 1152921504606846976(0x1000000000000000, double:1.2882297539194267E-231)
            long r1 = r1 & r3
            int r3 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r3 == 0) goto L_0x0062
            io.ktor.network.selector.LockFreeMPSCQueueCore r0 = r0.next()
            io.ktor.network.selector.LockFreeMPSCQueueCore r0 = r0.fillPlaceholder(r9, r11)
            if (r0 != 0) goto L_0x004f
        L_0x0062:
            r11 = 0
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.selector.LockFreeMPSCQueueCore.addLast(java.lang.Object):int");
    }

    public final Object removeFirstOrNull() {
        Object obj;
        long j = this._state;
        if ((1152921504606846976L & j) != 0) {
            return REMOVE_FROZEN;
        }
        Companion companion = Companion;
        int i = (int) (1073741823 & j);
        int i2 = (int) ((1152921503533105152L & j) >> 30);
        int i3 = this.mask;
        if ((i2 & i3) == (i & i3) || (obj = this.array.get(i3 & i)) == null || (obj instanceof Placeholder)) {
            return null;
        }
        int i4 = (i + 1) & 1073741823;
        if (_state$FU.compareAndSet(this, j, companion.updateHead(j, i4))) {
            this.array.set(this.mask & i, (Object) null);
            return obj;
        }
        LockFreeMPSCQueueCore lockFreeMPSCQueueCore = this;
        do {
            lockFreeMPSCQueueCore = lockFreeMPSCQueueCore.removeSlowPath(i, i4);
        } while (lockFreeMPSCQueueCore != null);
        return obj;
    }

    private final LockFreeMPSCQueueCore<E> removeSlowPath(int i, int i2) {
        long j;
        Companion companion;
        int i3;
        do {
            j = this._state;
            companion = Companion;
            i3 = (int) (1073741823 & j);
            if (i3 != i) {
                throw new IllegalStateException("This queue can have only one consumer".toString());
            } else if ((1152921504606846976L & j) != 0) {
                return next();
            }
        } while (!_state$FU.compareAndSet(this, j, companion.updateHead(j, i2)));
        this.array.set(this.mask & i3, (Object) null);
        return null;
    }

    private final long markFrozen() {
        long j;
        long j2;
        do {
            j = this._state;
            if ((j & 1152921504606846976L) != 0) {
                return j;
            }
            j2 = j | 1152921504606846976L;
        } while (!_state$FU.compareAndSet(this, j, j2));
        return j2;
    }

    private final LockFreeMPSCQueueCore<E> allocateOrGetNextCopy(long j) {
        while (true) {
            LockFreeMPSCQueueCore<E> lockFreeMPSCQueueCore = (LockFreeMPSCQueueCore) this._next;
            if (lockFreeMPSCQueueCore != null) {
                return lockFreeMPSCQueueCore;
            }
            AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_next$FU, this, (Object) null, allocateNextCopy(j));
        }
    }
}
